import gui.Frame;

import javax.swing.*;

public final class Main {

    private Main() {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Frame frame = new Frame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setTitle("Cellular Automaton");
            frame.setVisible(true);
        });
    }
}