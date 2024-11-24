package elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import elements.puyo_utils.Position;
import elements.puyo_utils.PuyoPair;

import java.io.IOException;
import java.net.URL;

public class Arena implements Drawable {
    private GameGrid grid;
    private final SpriteLoader backgroundLoader;
    private PuyoPair activePuyo;

    int autoDropCounter = 0;
    public static int dropInterval = 500;// Puyo drop every 60 frames, temporarily changed for debugging

    public Arena() throws IOException {
        URL resourceURL = SpriteLoader.pathToURL("/sprites/background/temporary_background.png");
        backgroundLoader = new SpriteLoader(resourceURL);
        activePuyo = PuyoPair.spawnPuyoPair();
        grid = new GameGrid();
    }

    public GameGrid getGrid(){ return grid; }
    public PuyoPair getActivePuyo() { return activePuyo; }

    public PuyoPair setActivePuyo(PuyoPair activePuyo){ return this.activePuyo = activePuyo; }
    public GameGrid setGrid(GameGrid grid){ return this.grid = grid; }

    // Checks if active puyo pair can go down next row
    public boolean canMoveDown(PuyoPair activePuyo){
        Position firstPos = activePuyo.getFirstPos();
        Position secondPos = activePuyo.getSecondPos();
        //do not know if I should put <= or <
        return(firstPos.getY() + 1 < GameGrid.ROWS && grid.isEmpty(firstPos.getY() + 1, firstPos.getX()) &&
                secondPos.getY() + 1 < GameGrid.ROWS && grid.isEmpty(secondPos.getY() + 1,secondPos.getX()));
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

        return (secondPos.getX() + 1 < GameGrid.COLUMNS && grid.isEmpty(secondPos.getY(), secondPos.getX() + 1) &&
                firstPos.getX() + 1 < GameGrid.COLUMNS && grid.isEmpty(firstPos.getY(), firstPos.getX() + 1));
    }

    // Checks if a position is available or not, by checking if cell is currently empty and positions are within the limits of the grid
    public boolean isValidPosition(Position position, GameGrid grid){
        int x = position.getX();
        int y = position.getY();

        if(x < 0 || x >= GameGrid.COLUMNS || y < 0 || y >= GameGrid.ROWS){
            return false;
        }

        return grid.getGrid()[y][x] == null;
    }

    // If there's no space to spawn a new puyo pair game over (later should be changed)
    public boolean gameOver(GameGrid grid){
        return (!grid.isEmpty(0, 2) || !grid.isEmpty(0, 3));
    }

    // Processes input
    public void processKey(KeyStroke key){
        switch(key.getKeyType()){
            case ArrowLeft: // Active puyo pair should move to the left
                if(canMoveLeft(activePuyo)){
                    activePuyo.moveLeft();
                }
                break;
            case ArrowRight: // Active puyo pair should move to the right
                if(canMoveRight(activePuyo)){
                    activePuyo.moveRight();
                }
                break;

            case ArrowDown: // Active puyo pair should move down
                if(canMoveDown(activePuyo)){
                    activePuyo.moveDown();
                }
                break;

            case Character:
                // Active puyo pair should rotate clockwise
                if(key.getCharacter() != null && key.getCharacter() == 'x') {
                    // New position of the second puyo after turning
                    Position newPos = activePuyo.rotate(true);
                    // If position is valid can rotate, else it should not and the rotation state goes back to what it was before
                    if (isValidPosition(newPos, grid)) {
                        activePuyo.getSecondPuyo().setPosition(newPos);
                    }
                    else {
                        activePuyo.revertRotationState(true);
                    }

                }

                // Active puyo pair should rotate counter-clockwise
                if (key.getCharacter() != null && key.getCharacter() == 'z') {

                    // New position of the second puyo after turning
                    Position newPos = activePuyo.rotate(false);

                    // If position is valid can rotate, else it should not and rotation state goes back to what it was before
                    if(isValidPosition(newPos, grid)){
                        activePuyo.getSecondPuyo().setPosition(newPos);
                    }
                    else{
                        activePuyo.revertRotationState(false);
                    }
                }
        }
    }


    //Update game every frame, making puyos fall and checking if they hit the static puyos
    public void update() {

        //Process input function (need to make a function)
        //Also a rotate function to help the input function
        autoDropCounter++;
        if(autoDropCounter == Arena.dropInterval){
            if (canMoveDown(activePuyo)) {
                activePuyo.moveDown();
            }
            else {
               grid.integrateGrid(activePuyo);
               while (grid.applyGravity()) { /* Do nothing...*/ }

                // Check if the puyo.Puyo pair can even spawn
                if (grid.isEmpty(0,2) && grid.isEmpty(0,3)) {
                    activePuyo = PuyoPair.spawnPuyoPair();
                } else { // In this case, the game would be over... Handle that logic later.
                    /*Code for Game ending*/
                }
            }

            autoDropCounter = 0;
        }
    }

    @Override
    public void draw(TextGraphics graphics, Position corner) throws IOException {
        // Original Background code
        graphics.setBackgroundColor(TextColor.Factory.fromString("#001326"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(GameGrid.COLUMNS, GameGrid.ROWS), ' ');
        // Still in test phase -> backgroundLoader.draw(graphics, new Position(0, 0));
        activePuyo.draw(graphics, null);
        grid.draw(graphics, null);
    }
}
