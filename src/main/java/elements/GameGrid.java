package elements;

import com.googlecode.lanterna.graphics.TextGraphics;
import elements.puyo_utils.Position;
import elements.puyo_utils.PuyoPair;

public class GameGrid implements Drawable {
    public static final int ROWS = 11;
    public static final int COLUMNS = 6;
    private Puyo[][] grid;

    public GameGrid() {
        this.grid = new Puyo[ROWS][COLUMNS];
    }

    public Puyo[][] getGrid() {
        return grid;
    }

    public void setGrid(Puyo[][] grid) {
        this.grid = grid;
    }

    // Checks if there's any puyos on the cell
    public boolean isEmpty(int row, int col) {
        return grid[row][col] == null;
    }

    public void setPuyo(int row, int col, Puyo p) {
        grid[row][col] = p;
    }

    public boolean applyGravity() {
        boolean fell = false;

        for (int col = 0; col < COLUMNS; col++) {
            for (int row = ROWS - 2; row >= 0; row--) {
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

    public void draw(TextGraphics graphics, Position corner) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (grid[i][j] != null) {
                    grid[i][j].draw(graphics, null);
                }
            }
        }
    }
}
