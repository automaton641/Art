package automaton641.art;
import automaton641.art.DirectionNode.Direction;

public class Automaton {
    public Cell[][] cells;
    public Cell[][] backCells;
    public DirectionGraph[][] directionGraphs;
    public Walker walker;
    public Automaton() {
        initializeCells();
        createDirectionGraph();
    }
    public void createDirectionGraph() {
        directionGraphs = new DirectionGraph[16][8];
        for (int i = 0; i < directionGraphs.length; i++) {
            for (int j = 0; j < directionGraphs[i].length; j++) {
                directionGraphs[i][j] = new DirectionGraph(App.random, 8, 16);
            }
        }
        walker = new Walker(directionGraphs, 0, 0);

        /*directionGraph = new DirectionGraph();
        directionGraph.addDirection(Direction.DOWN);
        directionGraph.addDirection(Direction.RIGHT);
    */
    }
    public Cell getCell(Position position) {
        return cells[position.row][position.column];
    }
    public void reset() {
        for (int row = 0; row < App.height; row++) {
            for (int column = 0; column < App.width; column++) {
                cells[row][column].level = 0;
                cells[row][column].tickPower = 1;
            }
        }
    }
    public void initializeCells() {
        cells = new Cell[App.height][App.width];
        backCells = new Cell[App.height][App.width];
        for (int row = 0; row < App.height; row++) {
            for (int column = 0; column < App.width; column++) {
                cells[row][column] = new Cell();
                backCells[row][column] = new Cell();
                cells[row][column].level = 0;
                cells[row][column].tickPower = 1;
            }
        }
        //cells[0][0].level = App.modulus/2;
        /*
        int prevtickModulus = 1;
        for (int row = 0; row < App.height; row++) {
            for (int column = 0; column < App.width; column++) {
                Position position = new Position(row, column);
                Cell cell = getCell(position);
                cell.tickModulus += prevtickModulus+row+column;
                cell.tickModulus %= App.tickModulusModulus;
                cell.tickModulus++;
                prevtickModulus = cell.tickModulus;
            }
        }
        */
    }
    public void tickCell(Cell cell) {
        try {
            cell.level += cell.tickPower;
            boolean shouldReact = false;
            if (cell.level >= App.modulus) {
            shouldReact = true;
            }
            cell.tickPower++;
            cell.tickPower%=128;
            cell.level %= App.modulus;
            if (shouldReact) {
                tickCell(getCell(moveDownOffset(walker.position, 1)));
                tickCell(getCell(moveRightOffset(walker.position, 1)));
                tickCell(getCell(moveUpOffset(walker.position, 1)));
                tickCell(getCell(moveLeftOffset(walker.position, 1)));
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }
    public void iterate() {
        //fillBackCells();
        boolean shouldIterate = true;
        while (shouldIterate) {
            Cell walkerCell = getCell(walker.position);
            tickCell(walkerCell);
            shouldIterate = walker.move();
        }
        Cell walkerCell = getCell(walker.position);
        //walker.position = moveRightOffset(walker.position, walkerCell.level);
        for (int row = 0; row < App.height; row++) {
            for (int column = 0; column < App.width; column++) {
                Position position = new Position(row, column);
                Cell cell = getCell(position);
                tickCell(cell);
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
        for (int row = 0; row < App.height; row++) {
            for (int column = 0; column < App.width; column++) {
                backCells[row][column].level = cells[row][column].level;
                backCells[row][column].tickIndex = cells[row][column].tickIndex;
                backCells[row][column].tickModulus = cells[row][column].tickModulus;

            }
        }
    }
}