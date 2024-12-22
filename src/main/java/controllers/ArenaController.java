package controllers;

import com.googlecode.lanterna.graphics.TextGraphics;
import model.Arena;
import model.grid.Grid;
import utils.puyoutils.Position;
import utils.puyoutils.PuyoPair;
import viewer.*;

import java.io.IOException;

import static model.Arena.*;

public class ArenaController {
    // Attributes
    private GameLogicController gameLogicController;
    private Arena arenaModel;
    private ArenaViewer arenaViewer;
    private NextPuyoViewer nextPuyoViewer;
    private GridController gridController;
    private ScoreController scoreController;
    private StageController stageController;
    private int autoDropCounter = 0;


    // Constructor
    public ArenaController(Arena arena, ArenaViewer arenaViewer) throws IOException {
        this.arenaModel = arena;
        this.arenaViewer = arenaViewer;
        this.gridController = new GridController(arena.getGrid(), new GridViewer());
        this.scoreController = new ScoreController(arena.getScore(), new DigitDisplayViewer());
        this.stageController = new StageController(arena.getStage(), new DigitDisplayViewer());
        this.nextPuyoViewer = new NextPuyoViewer(arenaModel.getNextPuyo().getFirstPuyo().getPuyoViewer(),
                                                    arenaModel.getNextPuyo().getSecondPuyo().getPuyoViewer());
        this.gameLogicController = new GameLogicController(gridController, scoreController, stageController);
    }


    // Getter
    public GridController getGridController() {
        return gridController;
    }

    public StageController getStageController() {
        return stageController;
    }

    public ScoreController getScoreController() {
        return scoreController;
    }

    public NextPuyoViewer getNextPuyoViewer() {
        return nextPuyoViewer;
    }

    public Arena getArenaModel(){
        return arenaModel;
    }

    // Methods
    public void update() throws IOException {

        //Process input function
        //Also a rotate function to help the input function
        autoDropCounter++;

        if(autoDropCounter >= Arena.dropInterval){
            if (canMoveDown(arenaModel.getActivePuyo())) {
                arenaModel.getActivePuyo().getController().moveDown();
            }
            else {

                gameLogicController.processGravityAndChains(arenaModel);
                gameLogicController.updateStageIfNeeded();

                // Check if the puyo pair can even spawn
                if (Grid.isEmpty(0,2) && Grid.isEmpty(0,3)) {
                    arenaModel.setActivePuyo(arenaModel.getNextPuyo());
                    arenaModel.setNextPuyo(PuyoPair.spawnPuyoPair());
                    this.nextPuyoViewer = new NextPuyoViewer(arenaModel.getNextPuyo().getFirstPuyo().getPuyoViewer(),
                            arenaModel.getNextPuyo().getSecondPuyo().getPuyoViewer());
                }
            }

            autoDropCounter = 0;
        }
    }

    public void draw(TextGraphics graphics, Position position) {
        arenaViewer.draw(graphics, position);
    }
}
