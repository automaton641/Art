package automaton641.art;

public class Automaton {
    public Cell[][] cells;
    public Cell[][] backCells;
    public Automaton() {
        initializeCells();
    }
    public Cell getCell(Position position) {
        return cells[position.row][position.column];
    }
    public void initializeCells() {
        cells = new Cell[App.height][App.width];
        backCells = new Cell[App.height][App.width];
        for (int row = 0; row < App.height; row++) {
            for (int column = 0; column < App.width; column++) {
                cells[row][column] = new Cell();
                backCells[row][column] = new Cell();
                cells[row][column].level = 1;
                cells[row][column].tickModulus = 1;
            }
        }
        int prevLevel = 1;
        for (int row = 0; row < App.height; row++) {
            for (int column = 0; column < App.width; column++) {
                Position position = new Position(row, column);
                Cell cell = getCell(position);
                cell.level += prevLevel;
                cell.level %= App.modulus;
                prevLevel = cell.level;
                cell.tickModulus = column + row;
                cell.tickModulus %= App.tickModulusModulus;
                cell.tickModulus++;
            }
        }
    }
    public void iterate() {
        //fillBackCells();
        for (int row = 0; row < App.height; row++) {
            for (int column = 0; column < App.width; column++) {
                Position position = new Position(row, column);
                Cell cell = getCell(position);
                cell.tryTick();
                if (cell.level == App.modulus-1) {
                    getCell(moveUpOffset(position, 1)).tryTick();
                    getCell(moveLeftOffset(position, 1)).tryTick();
                }
            }
        }
        try {
			Thread.sleep(App.iterationTime);
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
    }
    public Position moveDownOffset(Position position, int offset) {
        Position moved = new Position(position.row, position.column);
		moved.row += offset;
		while (moved.row >= App.height) {
			moved.row -= App.height;
		}
        return moved;
	}

	public Position moveRightOffset(Position position, int offset) {
        Position moved = new Position(position.row, position.column);
		moved.column += offset;
		while (moved.column >= App.width) {
			moved.column -= App.width;
		}
        return moved;
	}

	public Position moveUpOffset(Position position, int offset) {
        Position moved = new Position(position.row, position.column);
		moved.row -= offset;
		while (moved.row < 0) {
			moved.row += App.height;
		}
        return moved;	
	}

	public Position moveLeftOffset(Position position, int offset) {
        Position moved = new Position(position.row, position.column);
		moved.column -= offset;
		while (moved.column < 0) {
			moved.column += App.width;
		}
        return moved;
	}
    public void fillBackCells() {
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                backCells[row][column].level = cells[row][column].level;
                backCells[row][column].tickIndex = cells[row][column].tickIndex;
                backCells[row][column].tickModulus = cells[row][column].tickModulus;

            }
        }
    }
}