package mars.mips.so.ProcessManager;

import mars.mips.hardware.Register;

import java.util.List;

// Tabela de processos
public class ProcessTable {

    // Lista de processor prontos para executar
    private List<PCB> readyProcessList;
    // Processo executando no momento
    private PCB execProcess;

    // Adiciona um processo em uma List<PCB>, lista de blocos de controle de processos
    public List<PCB> addProcess(List<PCB> processList, PCB newProcess){
        processList.add(newProcess);
        return processList;
    }

    public PCB newProcess(int initAdress, int PID, ProcessState state, Register[] registers){
        return new PCB(initAdress, PID, state, registers);
    }


}
