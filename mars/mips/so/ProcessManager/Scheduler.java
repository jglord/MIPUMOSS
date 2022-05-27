package mars.mips.so.ProcessManager;

import java.util.Queue;
/*
* Escalonador de processos.
* Atualmente baseado em uma fila.
*
* */
public class Scheduler {

    // Fila de processos prontos a serem executados.
    public Queue<PCB> readyQueue;
    public PCB scheduledProcess;

    /*
    * Seleciona o processo a ser executado, atualmente baseado em uma fila, ou seja,
    * resgata o primeiro processo da fila.
    * */
    public PCB schedule(ProcessTable processTable){

        // Iterando fila
        this.getFila().forEach(pcb -> {
            // Testando se processo esta como pronto, se sim retornando ele
            if(pcb.getState().getState() == 1){
                 PCB scheduledProcess = this.getFila().remove();
            }
        });
        return scheduledProcess;
    }

    public void addQueueProcess(PCB process){
        // se state do process == 1, ou seja, ready, entao adiciona na fila
        if(process.getState().getState() == 1){
            this.getFila().add(process);
        }
    }

    public PCB selectExecProcess(){
        return this.getFila().remove();
    }

    public Queue<PCB> getFila() {
        return readyQueue;
    }

    public void setFila(Queue<PCB> fila) {
        this.readyQueue = fila;
    }

    public Queue<PCB> getReadyQueue() {
        return readyQueue;
    }

    public void setReadyQueue(Queue<PCB> readyQueue) {
        this.readyQueue = readyQueue;
    }

    public PCB getScheduledProcess() {
        return scheduledProcess;
    }

    public void setScheduledProcess(PCB scheduledProcess) {
        this.scheduledProcess = scheduledProcess;
    }
}
