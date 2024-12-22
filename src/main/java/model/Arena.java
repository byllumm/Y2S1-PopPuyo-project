package model;

import viewer.ArenaViewer;
import viewer.NextPuyoViewer;
import utils.puyoutils.Position;
import utils.puyoutils.PuyoPair;

import java.io.IOException;

import static model.Grid.*;

public class Arena {
    // Attributes
    private static Grid grid;
    private PuyoPair activePuyo;
    private PuyoPair nextPuyo;
    private Score score;
    private Stage stage;
    public static int dropInterval = 10;
    public static boolean isRunning = true;
    public static boolean scored = false;


    // Constructor
    public Arena() throws IOException {
        activePuyo = PuyoPair.spawnPuyoPair();
        nextPuyo = PuyoPair.spawnPuyoPair();
        grid = new Grid();
        score = new Score();
        stage = new Stage();
    }


    // Getters
    public Grid getGrid() { return grid; }
    public PuyoPair getActivePuyo() { return activePuyo; }
    public PuyoPair getNextPuyo() { return nextPuyo; }
    public Score getScore() { return score; }
    public Stage getStage() { return stage; }


    // Setters
    public void setActivePuyo(PuyoPair activePuyo) { this.activePuyo = activePuyo; }
    public void setGrid(Grid grid) { Arena.grid = grid; }
    public void setNextPuyo(PuyoPair nextPuyo) { this.nextPuyo = nextPuyo; }
    public void setScore(Score score) { this.score = score; }


    // Class Methods

    // Checks if active puyo pair can go down next row
    public static boolean canMoveDown(PuyoPair activePuyo){
        Position firstPos = activePuyo.getFirstPos();
        Position secondPos = activePuyo.getSecondPos();
        //do not know if I should put <= or <
        return(firstPos.getY() + 1 < ROWS && isEmpty(firstPos.getY() + 1, firstPos.getX()) &&
                secondPos.getY() + 1 < ROWS && isEmpty(secondPos.getY() + 1,secondPos.getX()));
    }

    // Checks if active puyo pair can move to the left
    public static boolean canMoveLeft(PuyoPair activePuyo){
        Position firstPos = activePuyo.getFirstPos();
        Position secondPos = activePuyo.getSecondPos();

        return(firstPos.getX() - 1 >= 0 && isEmpty(firstPos.getY(), firstPos.getX() - 1) &&
                secondPos.getX() - 1 >= 0 && isEmpty(secondPos.getY(), secondPos.getX() - 1));
    }

    // Checks if active puyo pair can move to the right
    public static boolean canMoveRight(PuyoPair activePuyo){
        Position firstPos = activePuyo.getFirstPos();
        Position secondPos = activePuyo.getSecondPos();

        return (secondPos.getX() + 1 < COLUMNS && isEmpty(secondPos.getY(), secondPos.getX() + 1) &&
                firstPos.getX() + 1 < COLUMNS && isEmpty(firstPos.getY(), firstPos.getX() + 1));
    }

    // Checks if a position is available or not, by checking if cell is currently empty and positions are within the limits of the grid
    public static boolean isValidPosition(Position position, Grid grid){
        int x = position.getX();
        int y = position.getY();

        if(x < 0 || x >= COLUMNS || y < 0 || y >= ROWS){
            return false;
        }

        return grid.getGrid()[y][x] == null;
    }

    // If there's no space to spawn a new puyo pair game over (later should be changed)
    public static boolean gameOver(Grid grid){
        return (!isEmpty(0, 2) || !isEmpty(0, 3));
    }
}
