package poppuyo.model.grid;

import poppuyo.model.Puyo;
import poppuyo.utils.puyoutils.Position;
import poppuyo.viewer.PuyoViewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static poppuyo.model.grid.Grid.COLUMNS;
import static poppuyo.model.grid.Grid.ROWS;

public class ChainDetector {
    private Grid grid;
    private static final int[][] adjacent_positions = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    public ChainDetector(Grid grid) {
        this.grid = grid;
    }

    // Mostly a hack, it's a version of isValidPosition that doesn't exclude nulls.
    // Ideally this would work more elegantly...
    public static boolean isValidPositionWithNulls(Position pos) {
        int row = pos.getX();
        int col = pos.getY();
        return row >= 0 && row < ROWS && col >= 0 && col < COLUMNS;
    }

    // Uses a depth-first search to find chains of puyos
    public List<List<Position>> detectChain() throws IOException {
        boolean[][] visited = new boolean[ROWS][COLUMNS];
        List<List<Position>> chains = new ArrayList<>();

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                Position pos = new Position(row, col);
                if (!visited[row][col] && grid.getPuyo(row, col) != null) {
                    List<Position> chain = new ArrayList<>();
                    dfs(pos, grid.getPuyo(row, col), visited, chain);

                    // If a chain has 4 or more puyos, it is valid
                    if (chain.size() >= 4) {
                        chains.add(chain);
                    }
                }
            }
        }

        return chains;
    }

    void dfs(Position pos, Puyo p, boolean[][] visited, List<Position> chain) throws IOException {
        int row = pos.getX();
        int col = pos.getY();

        // Mark this grid position as visited
        visited[row][col] = true;
        chain.add(pos);

        // Save original adjacency mode
        int adjacencyMode = p.getAdjacent();

        // Explore all neighbours of current puyo
        for (int i = 0; i < 4; i++) {
            Position neighbor = new Position(row + adjacent_positions[i][0], col + adjacent_positions[i][1]);
            if (isValidPositionWithNulls(neighbor) && !visited[neighbor.getX()][neighbor.getY()] &&
                    grid.getPuyo(neighbor.getX(), neighbor.getY()) != null &&
                    grid.getPuyo(neighbor.getX(), neighbor.getY()).getColor().equals(p.getColor())) {
                // If the neighbor is of the same color, set the adjacency bit to 1, then DFS
                switch (i) {
                    case 0: // Right, neighbor is on the left
                        grid.getPuyo(row, col).setAdjacent(grid.getPuyo(row, col).getAdjacent() | 0b1000); // Current Puyo looks right
                        grid.getPuyo(neighbor.getX(), neighbor.getY()).setAdjacent(grid.getPuyo(neighbor.getX(), neighbor.getY()).getAdjacent() | 0b0010); // Neighbor looks left
                        break;
                    case 1: // Below, neighbor is above
                        grid.getPuyo(row, col).setAdjacent(grid.getPuyo(row, col).getAdjacent() | 0b0100); // Current Puyo looks below
                        grid.getPuyo(neighbor.getX(), neighbor.getY()).setAdjacent(grid.getPuyo(neighbor.getX(), neighbor.getY()).getAdjacent() | 0b0001); // Neighbor looks above
                        break;
                    case 2: // Left, neighbor is on the right
                        grid.getPuyo(row, col).setAdjacent(grid.getPuyo(row, col).getAdjacent() | 0b0010); // Current Puyo looks left
                        grid.getPuyo(neighbor.getX(), neighbor.getY()).setAdjacent(grid.getPuyo(neighbor.getX(), neighbor.getY()).getAdjacent() | 0b1000); // Neighbor looks right
                        break;
                    case 3: // Above, neighbor is below
                        grid.getPuyo(row, col).setAdjacent(grid.getPuyo(row, col).getAdjacent() | 0b0001); // Current Puyo looks above
                        grid.getPuyo(neighbor.getX(), neighbor.getY()).setAdjacent(grid.getPuyo(neighbor.getX(), neighbor.getY()).getAdjacent() | 0b0100); // Neighbor looks below
                        break;
                }
                dfs(neighbor, p, visited, chain);
            }
        }

        // If the adjacency mode changed, update the sprite
        if (adjacencyMode != p.getAdjacent()) {
            updatePuyoSprite(row, col, p);

            for (int i = 0; i < 4; i++) {
                Position neighbor = new Position(row + adjacent_positions[i][0], col + adjacent_positions[i][1]);
                if (isValidPositionWithNulls(neighbor) &&
                        grid.getPuyo(neighbor.getX(), neighbor.getY()) != null &&
                        grid.getPuyo(neighbor.getX(), neighbor.getY()).getColor().equals(p.getColor())) {
                    updatePuyoSprite(neighbor.getX(), neighbor.getY(), grid.getPuyo(neighbor.getX(), neighbor.getY()));
                }
            }
        }
    }

    public void updatePuyoSprite(int row, int col, Puyo p) throws IOException{
        PuyoViewer viewer = new PuyoViewer(p.getColor(), p.getAdjacent());
        p.setPuyoViewer(viewer); // Assign the new com.t09g07.poppuyo.viewer to the Puyo
    }
}
