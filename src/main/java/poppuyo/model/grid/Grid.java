package poppuyo.model.grid;
import poppuyo.model.Puyo;
import poppuyo.utils.puyoutils.Position;

public class Grid {
    // Attributes
    public static final int ROWS = 12;
    public static final int COLUMNS = 6;
    private static Puyo[][] grid;


    // Constructor
    public Grid() {
        grid = new Puyo[ROWS][COLUMNS];
    }


    // Getters
    public Puyo[][] getGrid() { return grid; }

    public Puyo getPuyo(int row, int col) { return grid[row][col]; }


    // Setters
    public void setGrid(Puyo[][] grid) { Grid.grid = grid; }

    public void setPuyo(int row, int col, Puyo p) { grid[row][col] = p; }


    // Class Methods
    // Checks if there's any puyos on the cell
    public static boolean isEmpty(int row, int col) { return grid[row][col] == null; }

    // Takes a position in the com.t09g07.poppuyo.game grid array and turns it into a position in the UI
    public static Position translatePosition(Position pos) { return new Position(8 + pos.getX() * 32, 8 + pos.getY() * 32); }
}
