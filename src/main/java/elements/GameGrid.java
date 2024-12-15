package elements;

import graphics.GridGraphics;
import utils.puyoutils.Position;
import utils.puyoutils.PuyoPair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    private static final int[][] adjacent_positions = {
            {0, 1},  // Right
            {1, 0},  // Below
            {0, -1}, // Left
            {-1, 0}, // Above
    };

    // Mostly a hack, its a version of isValidPosition that doesn't exclude nulls.
    // Ideally this would work more elegantly...
    public static boolean isValidPositionWithNulls(Position pos) {
        int row = pos.getX();
        int col = pos.getY();
        return row >= 0 && row < ROWS && col >= 0 && col < COLUMNS;
    }

    // Uses a depth-first search to find chains of puyos
    public List<List<Position>> detectChain() {
        // might be better to make "visited" an attribute for each puyo
        boolean[][] visited = new boolean[ROWS][COLUMNS];
        List<List<Position>> chains = new ArrayList<>();

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                Position pos = new Position(row, col);
                if (!visited[row][col] && grid[row][col] != null) {
                    List<Position> chain = new ArrayList<>();
                    dfs(pos, grid[row][col], visited, chain);

                    // If a chain has 4 or more puyos, it is valid
                    if (chain.size() >= 4) {
                        chains.add(chain);
                    }
                }
            }
        }

        return chains;
    }

    private void dfs(Position pos, Puyo p, boolean[][] visited, List<Position> chain) {
        int row = pos.getX();
        int col = pos.getY();

        // Mark this grid position as visited
        visited[row][col] = true;
        chain.add(pos);

        // Explore all neighbours of current puyo
        for (int[] direction : adjacent_positions) {
            Position neighbor = new Position(row + direction[0], col + direction[1]);

            if (isValidPositionWithNulls(neighbor) && !visited[neighbor.getX()][neighbor.getY()] &&
                grid[neighbor.getX()][neighbor.getY()] != null && grid[neighbor.getX()][neighbor.getY()].getColor().equals(p.getColor())) {
                dfs(neighbor, p, visited, chain);
            }
        }
    }

    // Takes a position in the game grid array and turns it into a position in the UI
    public static Position translatePosition(Position pos) {
        return new Position(8 + pos.getX() * 32, 8 + pos.getY() * 32);
    }
}
