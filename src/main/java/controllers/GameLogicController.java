package controllers;

import model.Arena;
import utils.puyoutils.Position;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameLogicController {
    private GridController gridController;
    private ScoreController scoreController;
    private StageController stageController;

    public GameLogicController(GridController gridController, ScoreController scoreController, StageController stageController) {
        this.gridController = gridController;
        this.scoreController = scoreController;
        this.stageController = stageController;
    }

    public void processGravityAndChains(Arena arena) throws IOException {
        gridController.integrateGrid(arena.getActivePuyo());

        while(gridController.applyGravity()) {/* do nothing */}

        // Detect chains and process them
        List<List<Position>> chains = gridController.detectChain();

        // Organize scoring data
        int puyoInChain = 0;
        int colorBonus = 0;
        int groupBonus = 0;
        List<String> differentColors = new ArrayList<>();

        while(!chains.isEmpty()) {
            for(List<Position> chain: chains) {

                puyoInChain += chain.size();
                int chainSize = chain.size();

                if(chainSize > 11) chainSize = 11;
                groupBonus += scoreController.computeGroupBonus(chainSize);
                Position pos = chain.get(0);
                String color = arena.getGrid().getPuyo(pos.getX(), pos.getY()).getColor();

                if(!differentColors.contains(color)){
                    differentColors.add(color);
                }

                // Clear the puyos in the chain
                for(Position position: chain) {
                    arena.getGrid().setPuyo(position.getX(), position.getY(), null);
                }
            }

            // Apply gravity after chain removal
            while(gridController.applyGravity()) {/* do nothing */}

            // Detect new chains after gravity settles
            chains = gridController.detectChain();
        }
        scoreController.updateScore(puyoInChain, colorBonus, groupBonus);
    }

    public void updateStageIfNeeded() {
        int currentStage = stageController.getStageModel().getStage();
        stageController.updateStage(scoreController.getScoreModel().getScore());
        int afterStage = stageController.getStageModel().getStage();

        if(currentStage != afterStage) {
            Arena.dropInterval -= 1;
        }
    }
}
