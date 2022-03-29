package mars.mips.so.ProcessManager;

import java.util.List;

// Tabela de processos
public class ProcessTable {

    // Lista de processor prontos para executar
    private List<PCB> processList;
    // Processo executando no momento
    private PCB execProcess;

    // Adiciona um processo em uma List<PCB>, lista de blocos de controle de processos
    public List<PCB> addProcess(List<PCB> processList, PCB newProcess){
        processList.add(newProcess);
        return processList;
    }


}
