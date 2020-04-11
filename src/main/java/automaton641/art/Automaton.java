package automaton641.art;

public class Automaton {
    public Cell[][] cells;
    public Automaton() {
        initializeCells();
    }
    public void initializeCells() {
        cells = new Cell[App.height][App.width];
        for (int row = 0; row < App.height; row++) {
            for (int column = 0; column < App.width; column++) {
                cells[row][column] = new Cell();
                cells[row][column].level = App.modulus/2;
            }
        }
    }
    public void iterate() {
        try {
			Thread.sleep(App.iterationTime);
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
    }

}