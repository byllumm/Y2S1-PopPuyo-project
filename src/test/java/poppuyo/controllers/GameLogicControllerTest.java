package poppuyo.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import poppuyo.model.Score;
import poppuyo.model.Stage;

public class GameLogicControllerTest {
    @Mock private GridController mockGridController;
    @Mock private ScoreController mockScoreController;
    @Mock private StageController mockStageController;
    private GameLogicController gameLogicController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        gameLogicController = new GameLogicController(mockGridController, mockScoreController, mockStageController);
    }

    @Test
    public void updateStageIfNeeded() {
        Stage mockStageModel = Mockito.mock(Stage.class);
        Mockito.when(mockStageController.getStageModel()).thenReturn(mockStageModel);
        Mockito.when(mockStageModel.getStage()).thenReturn(1);

        Score mockScoreModel = Mockito.mock(Score.class);
        Mockito.when(mockScoreController.getScoreModel()).thenReturn(mockScoreModel);
        Mockito.when(mockScoreModel.getScore()).thenReturn(500);

        Mockito.doNothing().when(mockStageController).updateStage(500);

        gameLogicController.updateStageIfNeeded();

        Mockito.verify(mockStageController, Mockito.times(2)).getStageModel();
        Mockito.verify(mockStageController).updateStage(500);
    }
}