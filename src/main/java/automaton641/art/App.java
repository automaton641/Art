package automaton641.art;
import java.util.Random;
import javax.sound.sampled.*;
import java.nio.ByteBuffer;
import javax.swing.*;
import java.util.LinkedList;

public class App {
    public static int size = 64;
    public static int width = App.size*2;
    public static int height = App.size;
    public static int cellSize = 10;
    public static int baseModulus = 12;
    public static int modulus = App.baseModulus;
    public static Random random = new Random();
    public static SourceDataLine line;
    public static int tickModulusModulus = 64;
    public static boolean grey = false;
    public static Canvas canvas;
    public static int iterationTime = 128;
    public static Automaton automaton;
    public static void reset() {
        App.automaton.reset();/*
        App.automaton.iterate();
        App.canvas.drawAutomaton();
        */
    }
    public static short getSample(int row, int column) {
		int value = App.automaton.cells[row][column].level;
        double sample = (value * 128) / (App.modulus-1) - 64;
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
        int r = 1;
        short[] duple = new short[2];
        while (true) {
            App.canvas.drawAutomaton();
            App.automaton.iterate();
            /*App.line.drain();
            short n;
            int takeSampleIndex = 0;
            int bufferLength = 0;
            LinkedList<MyShort> sampleList = new LinkedList<MyShort>();
            for (int i = 0; i < App.height; i++) {
                for (int j = 0; j < App.width; j++) {
                    duple[takeSampleIndex] = App.getSample(i, j);
                    if (takeSampleIndex==1){
                        if (duple[1] > duple[0]) {
                            double step = ((double)duple[1] - (double)duple[0])/32;
                            for (double sample = (double)duple[0]; sample < (double)duple[1]; sample+=step) {
                                MyShort mySample = new MyShort();
                                mySample.value = (short)sample;
                                sampleList.add(mySample);
                                bufferLength+=2;
                            }
                        } else {
                            double step = ((double)duple[0] - (double)duple[1])/32;
                            for (double sample = (double)duple[0]; sample > (double)duple[1]; sample-=step) {
                                MyShort mySample = new MyShort();
                                mySample.value = (short)sample;
                                sampleList.add(mySample);
                                bufferLength+=2;
                            }
                        }
                        
                        duple[0] = duple[1];
                    } else {
                        takeSampleIndex++;
                        takeSampleIndex%=2;
                    }
                }
            }
            ByteBuffer byteBuffer = ByteBuffer.allocate(bufferLength);
            for (MyShort sample : sampleList) {
                byteBuffer.putShort(sample.value);
            }
            App.line.write(byteBuffer.array(), 0, bufferLength);*/
        }
    }
}
