package poppuyo.controllers;

import com.googlecode.lanterna.graphics.TextGraphics;
import poppuyo.model.Puyo;
import poppuyo.model.grid.ChainDetector;
import poppuyo.model.grid.Grid;
import poppuyo.model.grid.GridManager;
import poppuyo.utils.puyoutils.Position;
import poppuyo.utils.puyoutils.PuyoPair;
import poppuyo.viewer.GridViewer;

import java.io.IOException;
import java.util.List;

public class GridController {
    private Grid grid;
    private GridManager gridManager;
    private ChainDetector chainDetector;
    private GridViewer gridViewer;
    private final Position gridCorner = new Position(8, 8);

    public GridController(Grid grid, GridViewer gridViewer) {
        this.grid = grid;
        this.gridManager = new GridManager(grid);
        this.chainDetector = new ChainDetector(grid);
        this.gridViewer = gridViewer;
    }

    public Puyo getPuyoAt(int row, int col) {
        return grid.getPuyo(row, col);
    }

    public boolean applyGravity(){
        return gridManager.applyGravity();
    }

    public void integrateGrid(PuyoPair activePuyo) {
        gridManager.integrateGrid(activePuyo);
    }

    // Uses a depth-first search to find chains of puyos
    public List<List<Position>> detectChain() throws IOException {
        return chainDetector.detectChain();
    }


    public void updatePuyoSprite(int row, int col, Puyo p) throws IOException{
        chainDetector.updatePuyoSprite(row, col, p);// Assign the new com.t09g07.poppuyo.viewer to the Puyo
    }

    public void draw(TextGraphics graphics) {
        gridViewer.draw(graphics, gridCorner);
    }
}
