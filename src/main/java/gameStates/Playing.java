package gameStates;

import com.googlecode.lanterna.input.KeyStroke;
import elements.Arena;
import graphics.GameScreen;
import graphics.NextPuyoGraphics;
import utils.puyoutils.Position;
import utils.puyoutils.PuyoPair;

import java.io.IOException;

import static elements.GameGrid.*;
import static elements.GameGrid.translatePosition;

public class Playing implements StateMethods{
    private Arena arena;
    private GameScreen gameScreen;
    private int autoDropCounter = 0;
    public static int dropInterval = 10;
    public static boolean isRunning = true;

    public Playing(Arena arena, GameScreen gameScreen){
        this.arena = arena;
        this.gameScreen = gameScreen;
    }

    //Update game every frame, making puyos fall and checking if they hit the static
    @Override
    public void update() throws IOException{
        autoDropCounter++;

        if(autoDropCounter >= Playing.dropInterval){
            if(arena.canMoveDown(arena.getActivePuyo())){
                arena.getActivePuyo().getController().moveDown();
            }

            else{
                arena.getGrid().integrateGrid(arena.getActivePuyo());
                while(arena.getGrid().applyGravity()){
                    /* do nothing */
                }

                if(arena.getGrid().isEmpty(0, 2) && arena.getGrid().isEmpty(0,3)){
                    arena.setActivePuyo(arena.getNextPuyo());
                    arena.setNextPuyo(PuyoPair.spawnPuyoPair());
                    arena.setNextPuyoGraphics(new NextPuyoGraphics(arena.getNextPuyo().getFirstPuyo().getPuyoGraphics(), arena.getNextPuyo().getSecondPuyo().getPuyoGraphics()));
                }
            }
            autoDropCounter = 0;
        }
    }

    @Override
    public void draw() throws IOException{
        arena.getGrid().getGridGraphics().draw(gameScreen.getGraphics(), new Position(8, 8));

        for(int col = 0; col < COLUMNS; col++){
            for(int row = ROWS - 1; row <= 0; row--){
                if(!arena.getGrid().isEmpty(row, col)){
                    arena.getGrid().getGrid()[row][col].getPuyoGraphics().draw(gameScreen.getGraphics(), translatePosition(new Position(col, row)));
                }
            }
        }

        arena.getActivePuyo().getFirstPuyo().getPuyoGraphics().draw(gameScreen.getGraphics(), translatePosition(arena.getActivePuyo().getFirstPos()));
        arena.getActivePuyo().getSecondPuyo().getPuyoGraphics().draw(gameScreen.getGraphics(), translatePosition(arena.getActivePuyo().getSecondPos()));

        arena.getNextPuyoGraphics().draw(gameScreen.getGraphics(), new Position(212, 8));

        gameScreen.getScreen().refresh();
    }


    // Processes input
    @Override
    public void processKey(KeyStroke key){
        switch(key.getKeyType()){
            case ArrowLeft: // Active puyo pair should move to the left
                if(arena.canMoveLeft(arena.getActivePuyo())){
                    arena.getActivePuyo().getController().moveLeft();
                }
                break;


            case ArrowRight: // Active puyo pair should move to the right
                if(arena.canMoveRight(arena.getActivePuyo())){
                    arena.getActivePuyo().getController().moveRight();
                }
                break;

            case ArrowDown:
                if(arena.canMoveDown(arena.getActivePuyo())){
                    arena.getActivePuyo().getController().moveDown();
                }
                break;

            case Character:

                // Active puyo pair should rotate clockwise
                if(key.getCharacter() != null && key.getCharacter() == 'x'){

                    // New position of the second puyo after turning
                    Position newPos = arena.getActivePuyo().getController().rotate(true);

                    // If position is valid can rotate, else it should not and the rotation state goes back to what it was before
                    if(arena.isValidPosition(newPos, arena.getGrid())){
                        arena.getActivePuyo().getSecondPuyo().setPosition(newPos);
                    }
                    else{
                        arena.getActivePuyo().getController().revertRotationState(true);
                    }
                }

                // Active puyo pair should rotate counter-clockwise
                if(key.getCharacter() != null && key.getCharacter() == 'z'){

                    // New position of the second puyo after turning
                    Position newPos = arena.getActivePuyo().getController().rotate(false);

                    // If position is valid can rotate, else it should not and rotation state goes back to what it was
                    if(arena.isValidPosition(newPos, arena.getGrid())){
                        arena.getActivePuyo().getSecondPuyo().setPosition(newPos);
                    }
                    else{
                        arena.getActivePuyo().getController().revertRotationState(false);
                    }
                }

                if(key.getCharacter() != null && key.getCharacter() == 'q'){
                    isRunning = false;
                }
        }
    }

}
