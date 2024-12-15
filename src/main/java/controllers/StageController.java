package controllers;

import com.googlecode.lanterna.graphics.TextGraphics;
import model.Stage;
import utils.puyoutils.Position;
import viewer.DigitDisplayViewer;

public class StageController {
    // Attributes
    private Stage stageModel;
    private DigitDisplayViewer stageViewer;
    private Position[] positions = new Position[2];

    // Constructor
    public StageController(Stage stageModel, DigitDisplayViewer stageViewer){
        this.stageModel = stageModel;
        this.stageViewer = stageViewer;
        positions[0] = new Position(351,60);
        positions[1] = new Position(365, 60);
    }

    // Getters
    public Stage getStageModel(){
        return stageModel;
    }

    public DigitDisplayViewer getStageViewer(){
        return stageViewer;
    }

    // Setters
    public void setStageModel(Stage stageModel) {
        this.stageModel = stageModel;
    }

    public void setStageViewer(DigitDisplayViewer stageViewer) {
        this.stageViewer = stageViewer;
    }

    public void updateStage(int score){
        int stage = stageModel.getStage();
        double[] scoreForEachStage = stageModel.getScoreForEachStage();
        if(score >= scoreForEachStage[stage]){
            stageModel.setStage(stage + 1);
        }
    }

    public void draw(TextGraphics graphics, Position position) {
        int score = stageModel.getStage();
        String formattedStage = String.format("%02d", score);
        int i = 0;
        for (char c : formattedStage.toCharArray()) {
            stageViewer.setCurrentDigit(c - '0');
            stageViewer.draw(graphics, positions[i]);
            i++;
        }
    }
}
