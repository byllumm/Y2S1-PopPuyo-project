package controllers;

import model.Stage;
import viewer.StageViewer;

public class StageController {
    // Attributes
    private Stage stageModel;
    private StageViewer stageViewer;

    // Constructor
    public StageController(Stage stageModel, StageViewer stageViewer){
        this.stageModel = stageModel;
        this.stageViewer = stageViewer;
    }

    // Getters
    public Stage getStageModel(){
        return stageModel;
    }

    public StageViewer getStageViewer(){
        return stageViewer;
    }

    // Setters
    public void setStageModel(Stage stageModel) {
        this.stageModel = stageModel;
    }

    public void setStageViewer(StageViewer stageViewer) {
        this.stageViewer = stageViewer;
    }

    public void updateStage(int score){
        int stage = stageModel.getStage();
        double[] scoreForEachStage = stageModel.getScoreForEachStage();
        if(score >= scoreForEachStage[stage]){
            stageModel.setStage(stage + 1);
        }
    }


}
