package automaton641.art;

import javax.swing.*;

public class App {
    public static int width = 16;
    public static int height = 16;
    public static int cellSize = 16;
    public static Canvas canvas;
    public static void main(String[] args) {
        JFrame frame = new JFrame("Art");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        App.canvas = new Canvas();
        frame.add(App.canvas);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
