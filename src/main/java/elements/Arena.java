package elements;

import com.googlecode.lanterna.input.KeyStroke;
import graphics.ArenaGraphics;
import graphics.NextPuyoGraphics;
import utils.puyoutils.Position;
import utils.puyoutils.PuyoPair;

import java.io.IOException;
import java.util.List;

import static elements.GameGrid.*;

public class Arena {
    // Attributes
    private static GameGrid grid;
    private final ArenaGraphics arenaGraphics;
    private NextPuyoGraphics nextPuyoGraphics;
    private PuyoPair activePuyo;
    private PuyoPair nextPuyo;
    int autoDropCounter = 0;
    public static int dropInterval = 10;
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
    public static GameGrid getGrid() {
        return grid;
    }

    public PuyoPair getActivePuyo() {
        return activePuyo;
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

    public void setGrid(GameGrid grid) {
        this.grid = grid;
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
    public static boolean isValidPosition(Position position, GameGrid grid){
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

    // Processes input (must be delegated to ArenaController)
    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowLeft: // Active puyo pair should move to the left
                if (canMoveLeft(activePuyo)) {
                    activePuyo.getController().moveLeft();
                }
                break;
            case ArrowRight: // Active puyo pair should move to the right
                if (canMoveRight(activePuyo)) {
                    activePuyo.getController().moveRight();
                }
                break;

            case ArrowDown: // Active puyo pair should move down
                if (canMoveDown(activePuyo)) {
                    activePuyo.getController().moveDown();
                }
                break;

            case Character:
                // Active puyo pair should rotate clockwise
                if (key.getCharacter() != null && key.getCharacter() == 'x') {
                    // New position of the second puyo after turning
                    Position newPos = activePuyo.getController().rotate(true);
                    // If position is valid can rotate, else it should not and the rotation state goes back to what it was before
                    if (isValidPosition(newPos, grid)) {
                        activePuyo.getSecondPuyo().setPosition(newPos);
                    } else {
                        activePuyo.getController().revertRotationState(true);
                    }
                }

                // Active puyo pair should rotate counter-clockwise
                if (key.getCharacter() != null && key.getCharacter() == 'z') {

                    // New position of the second puyo after turning
                    Position newPos = activePuyo.getController().rotate(false);

                    // If position is valid can rotate, else it should not and rotation state goes back to what it was before
                    if(isValidPosition(newPos, grid)){
                        activePuyo.getSecondPuyo().setPosition(newPos);
                    } else {
                        activePuyo.getController().revertRotationState(false);
                    }
                }

                // Exit game
                if (key.getCharacter() != null && key.getCharacter() == 'q') {
                    isRunning = false;
                }
        }
    }


    //Update game every frame, making puyos fall and checking if they hit the static puyos
    public void update() throws IOException {

        //Process input function (need to make a function)
        //Also a rotate function to help the input function
        autoDropCounter++;

        if(autoDropCounter >= Arena.dropInterval){
            if (canMoveDown(activePuyo)) {
                activePuyo.getController().moveDown();
            }
            else {
                grid.integrateGrid(activePuyo);

                while (grid.applyGravity()) {/* do nothing */ }

                // Handle chain/score logic here
                List<List<Position>> chains = grid.detectChain();

                // Score isn't being handled yet...
                while (!chains.isEmpty()) {
                    for (List<Position> chain : chains) {
                        for (Position position : chain) {
                            grid.setPuyo(position.getX(), position.getY(), null); // Remove Puyos in the chain
                        }
                    }

                    // Apply gravity after removing chains
                    while (grid.applyGravity()) { /* do nothing */ }

                    // Detect new chains after gravity settles
                    chains = grid.detectChain();
                }


                // Check if the puyo.Puyo pair can even spawn
                if (grid.isEmpty(0,2) && grid.isEmpty(0,3)) {
                    activePuyo = nextPuyo;
                    nextPuyo = PuyoPair.spawnPuyoPair();
                    nextPuyoGraphics = new NextPuyoGraphics(nextPuyo.getFirstPuyo().getPuyoGraphics(), nextPuyo.getSecondPuyo().getPuyoGraphics());
                }
            }

            autoDropCounter = 0;
        }
    }
}
