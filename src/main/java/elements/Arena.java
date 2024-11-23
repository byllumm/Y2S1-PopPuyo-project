package elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import puyoUtils.Position;
import puyoUtils.PuyoPair;

import java.io.IOException;

public class Arena implements Drawable {
    private final GameGrid grid = new GameGrid();
    private PuyoPair activePuyo;
    int autoDropCounter = 0;

    public static int dropInterval = 500; //puyo.Puyo drop every 60 frames, temporarily changed for debugging


    public Arena(){
        activePuyo = PuyoPair.spawnPuyoPair();
    }


    //Checks if puyos can go down next row
    public boolean canMoveDown(PuyoPair activePuyo){
        Position firstPos = activePuyo.getFirstPos();
        Position secondPos = activePuyo.getSecondPos();
        //do not know if I should put <= or <
        return(firstPos.getY() + 1 < GameGrid.ROWS && grid.isEmpty(firstPos.getY() + 1, firstPos.getX()) &&
                secondPos.getY() + 1 < GameGrid.ROWS && grid.isEmpty(secondPos.getY() + 1,secondPos.getX()));
    }

    public boolean canMoveLeft(PuyoPair activePuyo){
        Position firstPos = activePuyo.getFirstPos();

        return(firstPos.getX() - 1 >= 0 && grid.isEmpty(firstPos.getY(), firstPos.getX() - 1));
    }

    public boolean canMoveRight(PuyoPair activePuyo){
        Position secondPos = activePuyo.getSecondPos();

        return (secondPos.getX() + 1 < GameGrid.COLUMNS && grid.isEmpty(secondPos.getY(), secondPos.getX() + 1));
    }


    public boolean gameOver(){
        return (!grid.isEmpty(0, 2) || !grid.isEmpty(0, 3));
    }

    public void processKey(KeyStroke key){
        switch(key.getKeyType()){
            case ArrowLeft:
                if(canMoveLeft(activePuyo)){
                    activePuyo.moveLeft();
                }
                break;
            case ArrowRight:
                if(canMoveRight(activePuyo)){
                    activePuyo.moveRight();
                }
                break;

            case ArrowDown:
                if(canMoveDown(activePuyo)){
                    activePuyo.moveDown();
                }
                break;
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
                }
            }

            autoDropCounter = 0;
        }
    }

    @Override
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#001326"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(GameGrid.COLUMNS, GameGrid.ROWS), ' ');
        activePuyo.draw(graphics);
        grid.draw(graphics);
    }
}
