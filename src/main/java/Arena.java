import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class Arena implements Drawable {

    static int rows = 11;
    static int columns = 6;
    private Puyo grid[][] = new Puyo[rows][columns];
    private PuyoPair activePuyo;
    int autoDropCounter = 0;

    public static int dropInterval = 500; //Puyo drop every 60 frames, temporarily changed for debugging

    public Arena(){
        activePuyo = spawnPuyoPair();
    }

    private PuyoPair spawnPuyoPair() {
        Position firstPos = new Position(2, 0);
        Position secondPos = new Position(3, 0);

        Puyo firstPuyo = new Puyo(firstPos);
        Puyo secondPuyo = new Puyo(secondPos);

        return new PuyoPair(firstPuyo, secondPuyo);
    }

    //Checks if there's any puyos on the cell
    public boolean isEmpty(int row, int col){
        return grid[row][col] == null;
    }

    //Checks if puyos can go down next row
    public boolean canMoveDown(PuyoPair activePuyo){
        Position firstPos = activePuyo.getFirstPos();
        Position secondPos = activePuyo.getSecondPos();
        //do not know if I should put <= or <
        return(firstPos.getY() + 1 < Arena.rows && isEmpty(firstPos.getY() + 1, firstPos.getX()) &&
                secondPos.getY() + 1 < Arena.rows && isEmpty(secondPos.getY() + 1,secondPos.getX()));
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
               integrateGrid();
               while (applyGravity()) { /* Do nothing...*/ }

                // Check if the Puyo pair can even spawn
                if (isEmpty(2,0) && isEmpty(3,0)) {
                    activePuyo = spawnPuyoPair();
                } else { // In this case, the game would be over... Handle that logic later.
                    /*Code for Game ending*/
                }
            }

            autoDropCounter = 0;
        }
    }

    //Locks active puyo to the static puyos
    public void integrateGrid() {
        Position firstPos = activePuyo.getFirstPos();
        Position secondPos = activePuyo.getSecondPos();

        grid[firstPos.getY()][firstPos.getX()] = activePuyo.getFirstPuyo();
        grid[secondPos.getY()][secondPos.getX()] = activePuyo.getSecondPuyo();

        // activePuyo = null; <- This is causing null-pointer exceptions
    }

    //Checks if any individual puyo can fall even more, after the pair has hit the static puyos
    public boolean applyGravity(){
        boolean fell = false;

        for(int col = 0; col < columns; col++){
            for(int row = rows - 2; row >= 0; row--){
                if(!isEmpty(row, col) && isEmpty(row + 1, col)){

                    grid[row + 1][col] = grid[row][col];
                    grid[row][col] = null;
                    fell = true; // While at least 1 puyo is affected by gravity, keep looping.

                }
            }
        }
        return fell;
    }

    @Override
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#001326"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(columns, rows), ' ');
        activePuyo.draw(graphics);
        // Static Puyo Drawing
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] != null) {
                    grid[i][j].draw(graphics);
                }
            }
        }
    }
}
