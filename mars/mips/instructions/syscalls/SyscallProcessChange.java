package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.hardware.Register;
import mars.mips.hardware.RegisterFile;
import mars.mips.so.ProcessManager.PCB;
import mars.mips.so.ProcessManager.ProcessState;
import mars.mips.so.ProcessManager.ProcessTable;
import mars.mips.so.ProcessManager.Scheduler;

public class SyscallProcessChange extends AbstractSyscall{

    // Tabela de processos.
    public static ProcessTable processTable;
    // o número da sycall passado no registrador 2 ($v0)
    public static int callNumber = RegisterFile.getValue(2);

    // Processo parado.
    public PCB oldProcess ;

    // Novo processo a ser executado
    public PCB execProcess;

    // Escalonador
    Scheduler scheduler;
    /**
     * Constructor is provided so subclass may initialize instance variables.
     *
     * @param //number default assigned service number
     * @param //name   service name which may be used for reference independent of number
     */

    public SyscallProcessChange() {
        super(19, "SyscallProcessChange");
        this.setOldProcess(oldProcess);
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {

        // Definindo o numerdo da sycall como sendo o valor do registrador 2($v0)
        int callNumber = RegisterFile.getValue(2);

        // Adicionando processo parado a tabela de processos estatica
        this.processTable.addProcess(processTable.getReadyProcessList(),this.getOldProcess());

        // Trocando estado do processo parado para pronto.
        this.getOldProcess().setState(new ProcessState(1));
        
        // Escalonando novo processo a ser executado
        execProcess = scheduler.schedule(processTable);
        //  Definindo estado como executando
        execProcess.setState(new ProcessState(2));

        // Copiar dados dos regs da cpu para os regs virtuais da pcb
        this.getOldProcess().hardToPcb(RegisterFile.getRegisters());

        // Copiar dados dos regs virtuais do processo novo para os regs da cpu
        this.getExecProcess().pcbToHard();

        this.getExecProcess().setState(new ProcessState(2));

    }

    public static ProcessTable getProcessTable() {
        return processTable;
    }

    public static void setProcessTable(ProcessTable processTable) {
        SyscallProcessChange.processTable = processTable;
    }

    public PCB getOldProcess() {
        return oldProcess;
    }

    public void setOldProcess(PCB oldProcess) {
        this.oldProcess = oldProcess;
    }

    public PCB getExecProcess() {
        return execProcess;
    }

    public void setExecProcess(PCB execProcess) {
        this.execProcess = execProcess;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
