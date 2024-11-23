package elements;

import com.googlecode.lanterna.graphics.TextGraphics;
import puyo_utils.Position;
import puyo_utils.PuyoPair;

import java.io.IOException;
import java.net.URL;

public class Arena implements Drawable {
    private final GameGrid grid = new GameGrid();
    private final SpriteLoader backgroundLoader;
    private PuyoPair activePuyo;
    int autoDropCounter = 0;

    public static int dropInterval = 500; //puyo.Puyo drop every 60 frames, temporarily changed for debugging


    public Arena() throws IOException {
        URL resourceURL = SpriteLoader.pathToURL("/sprites/background/temporary_background.png");
        backgroundLoader = new SpriteLoader(resourceURL);
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


    //Makes puyos fall down
    public void moveDown(PuyoPair activePuyo) {
        Position firstPos = activePuyo.getFirstPos();
        Position secondPos = activePuyo.getSecondPos();
        firstPos.setY(firstPos.getY() + 1);
        secondPos.setY(secondPos.getY() + 1);
    }


    //Update game every frame, making puyos fall and checking if they hit the static puyos
    public void update(TextGraphics graphics) {

        //Process input function (need to make a function)
        //Also a rotate function to help the input function
        autoDropCounter++;
        if(autoDropCounter == Arena.dropInterval){
            if (canMoveDown(activePuyo)) {
                moveDown(activePuyo);
            }
            else {
               grid.integrateGrid(activePuyo);
               while (grid.applyGravity()) { /* Do nothing...*/ }

                // Check if the puyo.Puyo pair can even spawn
                if (grid.isEmpty(2,0) && grid.isEmpty(3,0)) {
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
        /* Original Background code, halted for now
        graphics.setBackgroundColor(TextColor.Factory.fromString("#001326"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(GameGrid.COLUMNS, GameGrid.ROWS), ' ');
         */
        backgroundLoader.draw(graphics, new Position(0, 0));
        activePuyo.draw(graphics, null);
        grid.draw(graphics, null);
    }
}
