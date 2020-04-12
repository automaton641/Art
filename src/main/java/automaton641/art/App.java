package automaton641.art;
import java.util.Random;
import javax.sound.sampled.*;
import java.nio.ByteBuffer;
import javax.swing.*;

public class App {
    public static int size = 64;
    public static int width = App.size*2;
    public static int height = App.size;
    public static int cellSize = 10;
    public static int baseModulus = 256-1;
    public static int modulus = App.baseModulus;
    public static Random random = new Random();
    public static SourceDataLine line;

    public static boolean grey = false;
    public static Canvas canvas;
    public static int iterationTime = 0;
    public static Automaton automaton;
    public static void reset() {
        automaton.reset();
    }
    public static short getSample(int row, int column) {
		int value = App.automaton.cells[row][column].level;
        double sample = (value * 64*2) / (App.modulus-1) - 64;
        //System.out.println(sample);
        return (short)sample;
    }
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
        AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 48000, 16, 1, 2, 48000, false);
        App.line = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat); 
        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("ERROR: !AudioSystem.isLineSupported(info)");
            System.exit(0);
        }
        try {
            App.line = (SourceDataLine) AudioSystem.getLine(info);
            App.line.open(audioFormat);
        } catch (LineUnavailableException exception) {
            exception.printStackTrace();
            System.out.println("ERROR: LineUnavailableException exception");
            System.exit(0);
        }
        App.line.start();
        int r = 2;
        ByteBuffer byteBuffer = ByteBuffer.allocate(App.width*App.height*r*2);
        while (true) {
            App.canvas.drawAutomaton();
            App.line.drain();
            short n;
            byteBuffer.position(0);
            for (int i = 0; i < App.height; i++) {
                for (int j = 0; j < App.width; j++) {
                    n = App.getSample(i, j);
                    for (int k = 0; k < r; k++) {
                        byteBuffer.putShort(n);
                    }
                }
            }
            byte[] bytes = byteBuffer.array();
            App.line.write(bytes, 0, App.width*App.height*r*2);

            App.automaton.iterate();
        }
    }
}
