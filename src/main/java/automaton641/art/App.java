package automaton641.art;

import javax.swing.*;

public class App {
    public static int width = 16;
    public static int height = 16;
    public static int cellSize = 16;
    public static int modulus = 4;
    public static boolean grey = false;
    public static Canvas canvas;
    public static int iterationTime = 512;
    public static Automaton automaton;
    public static void main(String[] args) {
        JFrame frame = new JFrame("Art");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        App.canvas = new Canvas();
        App.automaton = new Automaton();
        frame.add(App.canvas);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        canvas.fillColors();
        while (true) {
            App.canvas.drawAutomaton();
            App.automaton.iterate();
        }
    }
}
