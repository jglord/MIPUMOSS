package mars.mips.so.ProcessManager;

// Define estados de um processo
public class ProcessState {
/*
     * state define o estado do processo
     * 0 = bloqueado -> sem implementação no momento
     * 1 = pronto
     * 2 = executando
     *
*/
    public int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
