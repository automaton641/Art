package automaton641.art;

import javax.swing.*;

public class App {
    public static int size = 128;
    public static int width = App.size*2;
    public static int height = App.size;
    public static int cellSize = 5;
    public static int tickModulusModulus = 32;
    public static int modulus = 16;
    public static boolean grey = false;
    public static Canvas canvas;
    public static int iterationTime = 64;
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
