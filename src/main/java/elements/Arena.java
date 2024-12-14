package elements;

import com.googlecode.lanterna.input.KeyStroke;
import graphics.ArenaGraphics;
import graphics.NextPuyoGraphics;
import utils.puyoutils.Position;
import utils.puyoutils.PuyoPair;

import java.io.IOException;

import static elements.GameGrid.*;

public class Arena {
    // Attributes
    private GameGrid grid;
    private final ArenaGraphics arenaGraphics;
    private NextPuyoGraphics nextPuyoGraphics;
    private PuyoPair activePuyo;
    private PuyoPair nextPuyo;
    public static boolean isRunning = true;


    // Constructor
    public Arena() throws IOException {
        arenaGraphics = new ArenaGraphics();
        activePuyo = PuyoPair.spawnPuyoPair();
        nextPuyo = PuyoPair.spawnPuyoPair();
        nextPuyoGraphics = new NextPuyoGraphics(nextPuyo.getFirstPuyo().getPuyoGraphics(), nextPuyo.getSecondPuyo().getPuyoGraphics());
        grid = new GameGrid();
    }


    // Getters
    public GameGrid getGrid() {
        return grid;
    }

    public PuyoPair getActivePuyo() {
        return activePuyo;
    }

    public PuyoPair getNextPuyo(){
        return nextPuyo;
    }

    public ArenaGraphics getArenaGraphics() {
        return arenaGraphics;
    }

    public NextPuyoGraphics getNextPuyoGraphics() {
        return nextPuyoGraphics;
    }


    // Setters
    public void setActivePuyo(PuyoPair activePuyo) {
        this.activePuyo = activePuyo;
    }

    public void setNextPuyo(PuyoPair nextPuyo){
        this.nextPuyo = nextPuyo;
    }

    public void setGrid(GameGrid grid) {
        this.grid = grid;
    }

    public void setNextPuyoGraphics(NextPuyoGraphics nextPuyoGraphics){
        this.nextPuyoGraphics = nextPuyoGraphics;
    }


    // Checks if active puyo pair can go down next row
    public boolean canMoveDown(PuyoPair activePuyo){
        Position firstPos = activePuyo.getFirstPos();
        Position secondPos = activePuyo.getSecondPos();
        //do not know if I should put <= or <
        return(firstPos.getY() + 1 < ROWS && grid.isEmpty(firstPos.getY() + 1, firstPos.getX()) &&
                secondPos.getY() + 1 < ROWS && grid.isEmpty(secondPos.getY() + 1,secondPos.getX()));
    }

    // Checks if active puyo pair can move to the left
    public boolean canMoveLeft(PuyoPair activePuyo){
        Position firstPos = activePuyo.getFirstPos();
        Position secondPos = activePuyo.getSecondPos();

        return(firstPos.getX() - 1 >= 0 && grid.isEmpty(firstPos.getY(), firstPos.getX() - 1) &&
                secondPos.getX() - 1 >= 0 && grid.isEmpty(secondPos.getY(), secondPos.getX() - 1));
    }

    // Checks if active puyo pair can move to the right
    public boolean canMoveRight(PuyoPair activePuyo){
        Position firstPos = activePuyo.getFirstPos();
        Position secondPos = activePuyo.getSecondPos();

        return (secondPos.getX() + 1 < COLUMNS && grid.isEmpty(secondPos.getY(), secondPos.getX() + 1) &&
                firstPos.getX() + 1 < COLUMNS && grid.isEmpty(firstPos.getY(), firstPos.getX() + 1));
    }

    // Checks if a position is available or not, by checking if cell is currently empty and positions are within the limits of the grid
    public boolean isValidPosition(Position position, GameGrid grid){
        int x = position.getX();
        int y = position.getY();

        if(x < 0 || x >= COLUMNS || y < 0 || y >= ROWS){
            return false;
        }

        return grid.getGrid()[y][x] == null;
    }

    // If there's no space to spawn a new puyo pair game over (later should be changed)
    public boolean gameOver(GameGrid grid){
        return (!grid.isEmpty(0, 2) || !grid.isEmpty(0, 3));
    }


}
