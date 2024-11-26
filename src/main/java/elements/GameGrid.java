package elements;

import graphics.GridGraphics;
import utils.puyoutils.Position;
import utils.puyoutils.PuyoPair;

import java.io.IOException;

public class GameGrid {
    // Attributes
    public static final int ROWS = 12;
    public static final int COLUMNS = 6;
    private Puyo[][] grid;
    private GridGraphics gridGraphics;
    private final Position gridCorner = new Position(8, 8);


    // Constructor
    public GameGrid() throws IOException {
        this.grid = new Puyo[ROWS][COLUMNS];
        gridGraphics = new GridGraphics();
    }

    // Getters
    public Puyo[][] getGrid() {
        return grid;
    }

    public GridGraphics getGridGraphics() {
        return gridGraphics;
    }


    // Setters
    public void setGrid(Puyo[][] grid) {
        this.grid = grid;
    }

    public void setGridGraphics(GridGraphics gridGraphics) {
        this.gridGraphics = gridGraphics;
    }

    public void setPuyo(int row, int col, Puyo p) {
        grid[row][col] = p;
    }


    // Checks if there's any puyos on the cell
    public boolean isEmpty(int row, int col) {
        return grid[row][col] == null;
    }

    public boolean applyGravity() {
        boolean fell = false;

        for (int col = 0; col < COLUMNS; col++) {
            for (int row = ROWS - 2; row >= 0; row--) { // No need to check bottom row, can't fall any further from that
                if (!isEmpty(row, col) && isEmpty(row + 1, col)) {
                    grid[row + 1][col] = grid[row][col];
                    grid[row][col] = null;
                    fell = true; // At least 1 puyo fell
                }
            }
        }

        return fell;
    }

    // Integrates the active puyo.Puyo Pair into the static section of the game grid
    public void integrateGrid(PuyoPair activePuyo) {
        Position firstPos = activePuyo.getFirstPos();
        Position secondPos = activePuyo.getSecondPos();

        setPuyo(firstPos.getY(), firstPos.getX(), activePuyo.getFirstPuyo());
        setPuyo(secondPos.getY(), secondPos.getX(), activePuyo.getSecondPuyo());
    }

    // Takes a position in the game grid array and turns it into a position in the UI
    public static Position translatePosition(Position pos) {
        return new Position(8 + pos.getX() * 32, 8 + pos.getY() * 32);
    }
}
