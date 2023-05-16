import implementations.*;
import model.gui.Frame;

import javax.swing.*;

public final class Main {

    private Main() {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Frame frame = new Frame(new Replika());
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setTitle("Cellular Automaton");
            frame.setVisible(true);
        });
    }
}