package automaton641.art;

public class DrawingThread extends Thread {
    @Override
    public void run() {
        try {
            App.canvas.drawAutomaton();
			Thread.sleep(16);
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
    }
}