package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.hardware.Register;
import mars.mips.hardware.RegisterFile;
import mars.mips.so.ProcessManager.PCB;
import mars.mips.so.ProcessManager.ProcessState;
import mars.mips.so.ProcessManager.ProcessTable;

import java.util.Random;

// Essa syscall cria um PCB e o adiciona na tabela de processos
public class SyscallFork extends AbstractSyscall{

    public PCB process = new PCB(RegisterFile.getValue(4), new ProcessState(1));
    public static ProcessTable processTable; // Tabela de processos estatica.
    public static int callNumber = RegisterFile.getValue(2);
    public int initAdress = RegisterFile.getValue(4);
    /**
     * Constructor is provided so subclass may initialize instance variables.
     *
     * @param //number default assigned service number -> indentificador da syscall
    * @param //name   service name which may be used for reference independent of number - nome da syscall
     */
    public SyscallFork() {
        super(18, "SyscallFork");
    }

    @Override // Operação da syscall sendo executada.
    public void simulate(ProgramStatement statement) throws ProcessingException {
        int callNumber = RegisterFile.getValue(2); // Definindo o numerdo da sycall como sendo o valor do registrador 2($v0)
        int initAdress = RegisterFile.getValue(4); // Definindo o endereço inicial como sendo o passado no registrado $a0

        // Criando novo processo
        PCB newProcess = new PCB(initAdress, new ProcessState(1));
        this.setProcess(newProcess); // Definindo novo processo que acabou de ser criado;

        // Incluindo na tabela de processos
        processTable.addProcess(processTable.getReadyProcessList(), newProcess);

    }

    public PCB getProcess() {
        return process;
    }

    public void setProcess(PCB process) {
        this.process = process;
    }

    public static ProcessTable getProcessTable() {
        return processTable;
    }

    public static void setProcessTable(ProcessTable processTable) {
        SyscallFork.processTable = processTable;
    }

    public int getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(int callNumber) {
        this.callNumber = callNumber;
    }

    public int getInitAdress() {
        return initAdress;
    }

    public void setInitAdress(int initAdress) {
        this.initAdress = initAdress;
    }
}
