package controllers;

import com.googlecode.lanterna.graphics.TextGraphics;
import model.Score;
import utils.puyoutils.Position;
import viewer.DigitDisplayViewer;

public class ScoreController {
    // Attributes
    private Score scoreModel;
    private DigitDisplayViewer scoreViewer;
    private Position[] positions = new Position[8];


    // Constructor
    public ScoreController(Score scoreModel, DigitDisplayViewer scoreViewer) {
        this.scoreModel = scoreModel;
        this.scoreViewer = scoreViewer;
        positions[0] = new Position(257, 31);
        positions[1] = new Position(273, 31);
        positions[2] = new Position(289, 31);
        positions[3] = new Position(305, 31);
        positions[4] = new Position(321, 31);
        positions[5] = new Position(337, 31);
        positions[6] = new Position(353, 31);
        positions[7] = new Position(369, 31);
    }

    // Setters
    public Score getScoreModel() {
        return scoreModel;
    }

    public DigitDisplayViewer getScoreViewer() {
        return scoreViewer;
    }

    // Getters
    public void setScoreModel(Score scoreModel) {
        this.scoreModel = scoreModel;
    }

    public void setScoreViewer(DigitDisplayViewer digitDisplayViewer) {
        this.scoreViewer = digitDisplayViewer;
    }

    // Methods
    public int computeGroupBonus(int group) {
        return Score.getGroupBonusTable()[group];
    }

    // Score = (10 * PC) * (CB + GB) https://puyonexus.com/wiki/Scoring (Chain Power omitted)
    public void updateScore(int pc, int cb, int gb) {
        int newScore = 0;

        if (Score.getColorBonusTable()[cb] + gb == 0) {
            newScore = 10 * pc;
        } else {
            newScore = (10 * pc) * (gb + Score.getColorBonusTable()[cb]);
        }

        newScore += 2;

        scoreModel.setScore(scoreModel.getScore() + newScore);
    }

    public void draw(TextGraphics graphics, Position position) {
        int score = scoreModel.getScore();
        String formattedScore = String.format("%08d", score);
        int i = 0;
        for (char c : formattedScore.toCharArray()) {
            scoreViewer.setCurrentDigit(c - '0');
            scoreViewer.draw(graphics, positions[i]);
            i++;
        }
    }
}
