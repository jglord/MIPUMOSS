package mars.mips.so.ProcessManager;

import mars.mips.hardware.Register;
import mars.mips.hardware.RegisterFile;

import java.util.Random;

public class PCB {
    private int initAdress;
    private int PID;
    private ProcessState state;
    private Register[] registers;

    // PCB constructor, constroi um novo bloco de controle de processo
    public PCB(int initAdress, ProcessState state){
        this.setInitAdress(initAdress);
        this.setPID(new Random(33).nextInt()); // setando pid aleatorio
        this.setState(state);
        this.setRegisters(RegisterFile.getRegisters());
    }

    // Mostra os registradores e seus valores
    public void showRegisters(){
        // Definindo registradores no pcb
        this.hardToPcb(RegisterFile.getRegisters());

        // Printando registradores e seus valores
        System.out.println("Registradores: \n");
        for (int i = 0; i < this.getRegisters().length; i++) {
            System.out.println(i + "-> " + this.getRegisters()[i].getValue());
        }
    }

    // TODO: Copiar o conteúdo dos registradores físicos do hardware para a PCB
    public void hardToPcb(Register[] registers){
        this.setRegisters(registers);
    }

    // TODO: Copiar da PCB para os registradores físicos -> checar hi e lo
    public void pcbToHard(){
        for (int i = 0; i < registers.length; i++) {
            RegisterFile.updateRegister(i, this.getRegisters()[i].getValue());
        }
    }

    //Define valor de um registrador, numero do reg e valor para atribuir
    public void setRegValue(int reg, int value){
        RegisterFile.updateRegister(reg, value);
    }

    public int getInitAdress() {
        return initAdress;
    }

    public void setInitAdress(int initAdress) {
        this.initAdress = initAdress;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public ProcessState getState() {
        return state;
    }

    public void setState(ProcessState state) {
        this.state = state;
    }

    public Register[] getRegisters() {
        return registers;
    }

    public void setRegisters(Register[] registers) {
        this.registers = registers;
    }
}
