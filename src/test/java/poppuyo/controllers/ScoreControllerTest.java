package poppuyo.controllers;

import com.googlecode.lanterna.graphics.TextGraphics;
import poppuyo.controllers.ScoreController;
import poppuyo.model.Score;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import poppuyo.utils.puyoutils.Position;
import poppuyo.viewer.DigitDisplayViewer;

public class ScoreControllerTest {
    @Mock private Score mockScoreModel;
    @Mock private DigitDisplayViewer mockDigitDisplayViewer;
    @Mock private TextGraphics mockTextGraphics;
    private ScoreController scoreController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        scoreController = new ScoreController(mockScoreModel, mockDigitDisplayViewer);
    }

    @Test
    public void constructor() {
        Assertions.assertNotNull(scoreController);
        Assertions.assertEquals(mockScoreModel, scoreController.getScoreModel());
        Assertions.assertEquals(mockDigitDisplayViewer, scoreController.getScoreViewer());
    }

    @Test
    public void getScoreModel() {
        Assertions.assertEquals(mockScoreModel, scoreController.getScoreModel());
    }

    @Test
    public void getScoreViewer() {
        Assertions.assertEquals(mockDigitDisplayViewer, scoreController.getScoreViewer());
    }

    @Test
    public void setScoreModel() {
        Score newMockScoreModel = Mockito.mock(Score.class);
        scoreController.setScoreModel(newMockScoreModel);
        Assertions.assertEquals(newMockScoreModel, scoreController.getScoreModel());
    }

    @Test
    public void setScoreViewer() {
        DigitDisplayViewer newMockScoreViewer = Mockito.mock(DigitDisplayViewer.class);
        scoreController.setScoreViewer(newMockScoreViewer);
        Assertions.assertEquals(newMockScoreViewer, scoreController.getScoreViewer());
    }

    @Test
    public void computeGroupBonus() {
        Assertions.assertEquals(0, Score.getGroupBonusTable()[4]);
        Assertions.assertEquals(2, Score.getGroupBonusTable()[5]);
        Assertions.assertEquals(3, Score.getGroupBonusTable()[6]);
        Assertions.assertEquals(4, Score.getGroupBonusTable()[7]);
        Assertions.assertEquals(5, Score.getGroupBonusTable()[8]);
        Assertions.assertEquals(6, Score.getGroupBonusTable()[9]);
        Assertions.assertEquals(7, Score.getGroupBonusTable()[10]);
        Assertions.assertEquals(10, Score.getGroupBonusTable()[11]);
    }

    @Test
    public void updateScore() {
        Mockito.when(mockScoreModel.getScore()).thenReturn(1000);

        // Expected score: (10 * 5) * (4 + 4) + 2 = 402
        scoreController.updateScore(5, 2, 4);
        Mockito.verify(mockScoreModel, Mockito.times(1)).setScore(mockScoreModel.getScore() + 402);
    }

    @Test
    public void updateScoreNoBonus() {
        Mockito.when(mockScoreModel.getScore()).thenReturn(1000);

        // Expected score: 10 * 5 + 2 = 52
        scoreController.updateScore(5, 0, 0);
        Mockito.verify(mockScoreModel, Mockito.times(1)).setScore(mockScoreModel.getScore() + 52);
    }

    @Test
    public void draw() {
        Mockito.when(mockScoreModel.getScore()).thenReturn(123456);

        scoreController.draw(mockTextGraphics, new Position(0, 0));

        // Expected display: 00123456
        Mockito.verify(mockDigitDisplayViewer, Mockito.times(2)).setCurrentDigit(0);
        Mockito.verify(mockDigitDisplayViewer, Mockito.times(1)).setCurrentDigit(1);
        Mockito.verify(mockDigitDisplayViewer, Mockito.times(1)).setCurrentDigit(2);
        Mockito.verify(mockDigitDisplayViewer, Mockito.times(1)).setCurrentDigit(3);
        Mockito.verify(mockDigitDisplayViewer, Mockito.times(1)).setCurrentDigit(4);
        Mockito.verify(mockDigitDisplayViewer, Mockito.times(1)).setCurrentDigit(5);
        Mockito.verify(mockDigitDisplayViewer, Mockito.times(1)).setCurrentDigit(6);
    }
}

