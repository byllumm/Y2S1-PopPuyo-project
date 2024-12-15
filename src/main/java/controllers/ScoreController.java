package controllers;

import model.Score;
import viewer.ScoreViewer;

public class ScoreController {
    // Attributes
    private Score scoreModel;
    private ScoreViewer scoreViewer;


    // Constructor
    public ScoreController(Score scoreModel, ScoreViewer scoreViewer) {
        this.scoreModel = scoreModel;
        this.scoreViewer = scoreViewer;
    }

    // Setters
    public Score getScoreModel() {
        return scoreModel;
    }

    public ScoreViewer getScoreViewer() {
        return scoreViewer;
    }

    // Getters
    public void setScoreModel(Score scoreModel) {
        this.scoreModel = scoreModel;
    }

    public void setScoreViewer(ScoreViewer scoreViewer) {
        this.scoreViewer = scoreViewer;
    }

    private static final int[] colorBonusTable = new int[6];
    static {
        colorBonusTable[1] = 0;
        colorBonusTable[2] = 3;
        colorBonusTable[3] = 6;
        colorBonusTable[4] = 12;
        colorBonusTable[5] = 24;
    }

    private static final int[] groupBonusTable = new int[12];
    static {
        groupBonusTable[4] = 0;
        groupBonusTable[5] = 2;
        groupBonusTable[6] = 3;
        groupBonusTable[7] = 4;
        groupBonusTable[8] = 5;
        groupBonusTable[9] = 6;
        groupBonusTable[10] = 7;
        groupBonusTable[11] = 10;
    }

    // Methods
    public int computeGroupBonus(int group) {
        return groupBonusTable[group];
    }

    // Score = (10 * PC) * (CB + GB) https://puyonexus.com/wiki/Scoring (Chain Power omitted)
    public void updateScore(int pc, int cb, int gb) {
        int newScore = 0;

        if (colorBonusTable[cb] + gb == 0) {
            newScore = 10 * pc;
        } else {
            newScore = (10 * pc) * (gb + colorBonusTable[cb]);
        }

        newScore += 2;

        scoreModel.setScore(scoreModel.getScore() + newScore);
    }
}
