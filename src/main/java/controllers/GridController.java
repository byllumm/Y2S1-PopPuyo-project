package controllers;

import com.googlecode.lanterna.graphics.TextGraphics;
import model.Grid;
import model.Puyo;
import utils.puyoutils.Position;
import utils.puyoutils.PuyoPair;
import viewer.GridViewer;
import viewer.PuyoViewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static model.Grid.*;

public class GridController {
    private Grid grid;
    private GridViewer gridViewer;
    private final Position gridCorner = new Position(8, 8);

    public GridController(Grid grid, GridViewer gridViewer) {
        this.grid = grid;
        this.gridViewer = gridViewer;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public boolean applyGravity() {
        boolean fell = false;

        for (int col = 0; col < COLUMNS; col++) {
            for (int row = ROWS - 2; row >= 0; row--) { // No need to check bottom row, can't fall any further from that
                if (!isEmpty(row, col) && isEmpty(row + 1, col)) {
                    Puyo p = grid.getGrid()[row][col];
                    grid.setPuyo(row + 1, col, p);
                    grid.setPuyo(row, col, null);
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

        grid.setPuyo(firstPos.getY(), firstPos.getX(), activePuyo.getFirstPuyo());
        grid.setPuyo(secondPos.getY(), secondPos.getX(), activePuyo.getSecondPuyo());
    }

    private static final int[][] adjacent_positions = {
            {0, 1},  // Right
            {1, 0},  // Below
            {0, -1}, // Left
            {-1, 0}, // Above
    };

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

    private void dfs(Position pos, Puyo p, boolean[][] visited, List<Position> chain) throws IOException {
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
        p.setPuyoViewer(viewer); // Assign the new viewer to the Puyo
    }

    public void draw(TextGraphics graphics) {
        gridViewer.draw(graphics, gridCorner);
    }
}
