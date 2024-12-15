package controllers;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import model.Arena;
import model.Grid;
import utils.puyoutils.Position;
import utils.puyoutils.PuyoPair;
import viewer.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static model.Arena.*;

public class ArenaController {
    // Attributes
    private Arena arenaModel;
    private ArenaViewer arenaViewer;
    private NextPuyoViewer nextPuyoViewer;
    private GridController gridController;
    private ScoreController scoreController;

    public StageController getStageController() {
        return stageController;
    }

    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

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
    }


    // Getter
    public GridController getGridController() {
        return gridController;
    }

    public ScoreController getScoreController() {
        return scoreController;
    }

    public NextPuyoViewer getNextPuyoViewer() {
        return nextPuyoViewer;
    }


    // Setter
    public void setGridController(GridController gridController) {
        this.gridController = gridController;
    }

    public void setScoreController(ScoreController scoreController) {
        this.scoreController = scoreController;
    }

    public void setNextPuyoViewer(NextPuyoViewer nextPuyoViewer) {
        this.nextPuyoViewer = nextPuyoViewer;
    }


    // Methods
    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowLeft: // Active puyo pair should move to the left
                if (canMoveLeft(arenaModel.getActivePuyo())) {
                    arenaModel.getActivePuyo().getController().moveLeft();
                }
                break;
            case ArrowRight: // Active puyo pair should move to the right
                if (canMoveRight(arenaModel.getActivePuyo())) {
                    arenaModel.getActivePuyo().getController().moveRight();
                }
                break;

            case ArrowDown: // Active puyo pair should move down
                if (canMoveDown(arenaModel.getActivePuyo())) {
                    arenaModel.getActivePuyo().getController().moveDown();
                }
                break;

            case Escape:
                isRunning = false;
                break;

            case Character:
                // Active puyo pair should rotate clockwise
                if (key.getCharacter() != null && key.getCharacter() == 'x') {
                    // New position of the second puyo after turning
                    Position newPos = arenaModel.getActivePuyo().getController().rotate(true);
                    // If position is valid can rotate, else it should not and the rotation state goes back to what it was before
                    if (isValidPosition(newPos, arenaModel.getGrid())) {
                        arenaModel.getActivePuyo().getSecondPuyo().setPosition(newPos);
                    } else {
                        arenaModel.getActivePuyo().getController().revertRotationState(true);
                    }
                }

                // Active puyo pair should rotate counter-clockwise
                if (key.getCharacter() != null && key.getCharacter() == 'z') {

                    // New position of the second puyo after turning
                    Position newPos = arenaModel.getActivePuyo().getController().rotate(false);

                    // If position is valid can rotate, else it should not and rotation state goes back to what it was before
                    if(isValidPosition(newPos, arenaModel.getGrid())){
                        arenaModel.getActivePuyo().getSecondPuyo().setPosition(newPos);
                    } else {
                        arenaModel.getActivePuyo().getController().revertRotationState(false);
                    }

                    break;
                }

                // Exit game
                if (key.getCharacter() != null && key.getCharacter() == 'q') {
                    isRunning = false;
                    break;
                }

                break;
        }
    }

    public void update() throws IOException {

        //Process input function
        //Also a rotate function to help the input function
        autoDropCounter++;

        if(autoDropCounter >= Arena.dropInterval){
            if (canMoveDown(arenaModel.getActivePuyo())) {
                arenaModel.getActivePuyo().getController().moveDown();
            }
            else {
                gridController.integrateGrid(arenaModel.getActivePuyo());
                while (gridController.applyGravity()) {/* do nothing */ }

                // Handle chain/score logic here
                List<List<Position>> chains = gridController.detectChain();

                int puyo_in_chain = 0;
                int color_bonus = 0;
                int group_bonus = 0;
                List<String> differentColors = new ArrayList<>();

                while (!chains.isEmpty()) {
                    for (List<Position> chain : chains) {

                        // Section for organizing scoring data
                        puyo_in_chain += chain.size();
                        int chain_size = chain.size();
                        if (chain_size > 11) chain_size = 11;
                        group_bonus += scoreController.computeGroupBonus(chain_size);
                        Position pos = chain.get(0);
                        String color = arenaModel.getGrid().getPuyo(pos.getX(),pos.getY()).getColor();
                        if (!differentColors.contains(color)) {
                            differentColors.add(color);
                        }
                        //////////////////////////////////////

                        for (Position position : chain) {
                            arenaModel.getGrid().setPuyo(position.getX(), position.getY(), null); // Remove Puyos in the chain
                        }
                    }

                    // Apply gravity after removing chains
                    while (gridController.applyGravity()) { /* do nothing */ }

                    // Detect new chains after gravity settles
                    chains = gridController.detectChain();
                }

                scoreController.updateScore(puyo_in_chain, color_bonus, group_bonus);
                int currentStage = stageController.getStageModel().getStage();
                stageController.updateStage(scoreController.getScoreModel().getScore());
                int afterStage = stageController.getStageModel().getStage();
                if(currentStage != afterStage){
                    dropInterval -= 1;
                }

                System.out.println("Score: " + arenaModel.getScore().getScore());
                System.out.println("Stage: " + arenaModel.getStage().getStage());
                System.out.println("Puyos in chain: " + puyo_in_chain);

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

    public void drawNext(TextGraphics graphics, Position position) {
        nextPuyoViewer.draw(graphics, new Position(212, 8));
    }
}
