package poppuyo.model.grid;

import poppuyo.model.Puyo;
import poppuyo.utils.puyoutils.Position;
import poppuyo.utils.puyoutils.PuyoPair;

import static poppuyo.model.grid.Grid.*;
import static poppuyo.model.grid.Grid.isEmpty;

public class GridManager {
    private Grid grid;

    // Constructor
    public GridManager(Grid grid) {
        this.grid = grid;
    }

    // Getter
    public Grid getGrid(){
        return grid;
    }

    //Setter
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

    // Integrates the active puyo pair into the static section of the com.t09g07.poppuyo.game grid
    public void integrateGrid(PuyoPair activePuyo) {
        Position firstPos = activePuyo.getFirstPos();
        Position secondPos = activePuyo.getSecondPos();

        grid.setPuyo(firstPos.getY(), firstPos.getX(), activePuyo.getFirstPuyo());
        grid.setPuyo(secondPos.getY(), secondPos.getX(), activePuyo.getSecondPuyo());
    }
}
