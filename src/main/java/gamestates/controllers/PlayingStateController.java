package gamestates.controllers;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import controllers.ArenaController;
import game.GameScreen;
import gamestates.GameState;
import utils.puyoutils.Position;

import java.io.IOException;

import static model.Arena.*;
import static model.grid.Grid.*;
import static model.grid.Grid.translatePosition;

public class PlayingStateController implements StateController {
    // Attributes
    private ArenaController arenaController;
    private GameScreen gameScreen;


    // Constructor
    public PlayingStateController(ArenaController arenaController, GameScreen gameScreen){
        this.arenaController= arenaController;
        this.gameScreen = gameScreen;
    }


    // Class Methods
    @Override
    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowLeft: // Active puyo pair should move to the left
                if (canMoveLeft(arenaController.getArenaModel().getActivePuyo())) {
                    arenaController.getArenaModel().getActivePuyo().getController().moveLeft();
                }
                break;
            case ArrowRight: // Active puyo pair should move to the right
                if (canMoveRight(arenaController.getArenaModel().getActivePuyo())) {
                    arenaController.getArenaModel().getActivePuyo().getController().moveRight();
                }
                break;

            case ArrowDown: // Active puyo pair should move down
                if (canMoveDown(arenaController.getArenaModel().getActivePuyo())) {
                    arenaController.getArenaModel().getActivePuyo().getController().moveDown();
                }
                break;

            case Escape:
                GameState.state = GameState.MENU;
                break;

            case Character:
                // Active puyo pair should rotate clockwise
                if (key.getCharacter() != null && key.getCharacter() == 'x') {
                    // New position of the second puyo after turning
                    Position newPos = arenaController.getArenaModel().getActivePuyo().getController().rotate(true);
                    // If position is valid can rotate, else it should not and the rotation state goes back to what it was before
                    if (isValidPosition(newPos, arenaController.getArenaModel().getGrid())) {
                        arenaController.getArenaModel().getActivePuyo().getSecondPuyo().setPosition(newPos);
                    } else {
                        arenaController.getArenaModel().getActivePuyo().getController().revertRotationState(true);
                    }
                }

                // Active puyo pair should rotate counter-clockwise
                if (key.getCharacter() != null && key.getCharacter() == 'z') {

                    // New position of the second puyo after turning
                    Position newPos = arenaController.getArenaModel().getActivePuyo().getController().rotate(false);

                    // If position is valid can rotate, else it should not and rotation state goes back to what it was before
                    if(isValidPosition(newPos, arenaController.getArenaModel().getGrid())){
                        arenaController.getArenaModel().getActivePuyo().getSecondPuyo().setPosition(newPos);
                    } else {
                        arenaController.getArenaModel().getActivePuyo().getController().revertRotationState(false);
                    }

                    break;
                }

                // Exit game
                if (key.getCharacter() != null && key.getCharacter() == 'q') {
                    GameState.state = GameState.MENU;
                    break;
                }

                break;
        }
    }

    @Override
    public void draw(TextGraphics textGraphics, Position position) throws IOException {
        arenaController.getGridController().draw(gameScreen.getGraphics());
        arenaController.getScoreController().draw(gameScreen.getGraphics(), new Position(0, 0));
        arenaController.getStageController().draw(gameScreen.getGraphics(), new Position(0, 0));

        for (int col = 0; col < COLUMNS; col++) {
            for (int row = ROWS - 1; row >= 0; row--) {
                if(!isEmpty(row,col)) {
                    arenaController.getGridController().getPuyoAt(row, col).getPuyoViewer().draw(gameScreen.getGraphics(), translatePosition(new Position(col, row)));
                }
            }
        }

        arenaController.getArenaModel().getActivePuyo().getFirstPuyo().getPuyoViewer().draw(gameScreen.getGraphics(), translatePosition(arenaController.getArenaModel().getActivePuyo().getFirstPos()));
        arenaController.getArenaModel().getActivePuyo().getSecondPuyo().getPuyoViewer().draw(gameScreen.getGraphics(), translatePosition(arenaController.getArenaModel().getActivePuyo().getSecondPos()));

        arenaController.getNextPuyoViewer().draw(gameScreen.getGraphics(), new Position(212, 8));

        gameScreen.getScreen().refresh();
    }

}
