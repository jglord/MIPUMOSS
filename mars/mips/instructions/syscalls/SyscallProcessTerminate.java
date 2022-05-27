package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.hardware.RegisterFile;
import mars.mips.so.ProcessManager.PCB;
import mars.mips.so.ProcessManager.ProcessState;
import mars.mips.so.ProcessManager.ProcessTable;
import mars.mips.so.ProcessManager.Scheduler;

public class SyscallProcessTerminate extends AbstractSyscall{

    public PCB terminatedProcess;
    public Scheduler scheduler;
    public PCB changedProcess;
    public static ProcessTable processTable;

    // public SyscallProcessTerminate() fazer construror para cada syscall


    /**
     * Constructor is provided so subclass may initialize instance variables.
     *
     //* @param number default assigned service number
     //* @param name   service name which may be used for reference independent of number
     */

    public SyscallProcessTerminate() {
        super(20,"SyscallProcessTerminate");

    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {
        int callNumber = RegisterFile.getValue(2); // Definindo o numero da sycall como sendo o valor do registrador 2($v0)


        // Remover pcb da tabela de processo.
        processTable.removeProcess(terminatedProcess);

        // Escolonar novo processo pronto.
        changedProcess = scheduler.schedule(processTable);
        changedProcess.setState(new ProcessState(2));
    }


    public PCB getTerminatedProcess() {
        return terminatedProcess;
    }

    public void setTerminatedProcess(PCB terminatedProcess) {
        this.terminatedProcess = terminatedProcess;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public PCB getChangedProcess() {
        return changedProcess;
    }

    public void setChangedProcess(PCB changedProcess) {
        this.changedProcess = changedProcess;
    }

    public static ProcessTable getProcessTable() {
        return processTable;
    }

    public static void setProcessTable(ProcessTable processTable) {
        SyscallProcessTerminate.processTable = processTable;
    }
}
