package mars.tools;

import mars.mips.hardware.Memory;

import javax.swing.*;
import java.awt.*;

public class Timer extends AbstractMarsToolAndApplication {

    private static String name    = "Timer";
    private static String version = "Version 0.1 (João goulart)";
    private static String heading = "Its a timer";

    /**
     * Simple constructor
     *
     * @param title   String containing title bar text
     * @param heading
     */
    public Timer(String title, String heading) {
        super(title,heading);
    }


    public Timer() {
        super(name + ", " + version, heading);
    }

    @Override
    public String getName() {
        return name;
    }
    //	@Override
    protected void addAsObserver() {
        addAsObserver(Memory.textBaseAddress, Memory.textLimitAddress);
    }

    @Override
    protected JComponent buildMainDisplayArea() {
        // Create everything
        JPanel panel = new JPanel(new GridBagLayout());

        // botao iniciar contagem
        // form para quantidade de instrucoes a serem contadas


        return panel;
    }
}
