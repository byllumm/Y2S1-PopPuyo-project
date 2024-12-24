package poppuyo.controllers;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import poppuyo.model.Stage;
import poppuyo.utils.puyoutils.Position;
import poppuyo.viewer.DigitDisplayViewer;

public class StageControllerTest {
    @Mock private Stage mockStageModel;
    @Mock private DigitDisplayViewer mockStageViewer;
    @Mock private TextGraphics mockTextGraphics;
    private StageController stageController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        stageController = new StageController(mockStageModel, mockStageViewer);
    }

    @Test
    public void constructor() {
        Assertions.assertNotNull(stageController);
        Assertions.assertEquals(mockStageModel, stageController.getStageModel());
    }

    @Test
    public void getStageModel() {
        Assertions.assertEquals(mockStageModel, stageController.getStageModel());
    }

    @Test
    public void getStageViewer() {
        Assertions.assertEquals(mockStageViewer, stageController.getStageViewer());
    }

    @Test
    public void setStageModel() {
        Stage newMockStageModel = Mockito.mock(Stage.class);
        stageController.setStageModel(newMockStageModel);
        Assertions.assertEquals(newMockStageModel, stageController.getStageModel());
    }

    @Test
    public void setStageViewer() {
        DigitDisplayViewer newMockViewer = Mockito.mock(DigitDisplayViewer.class);
        stageController.setStageViewer(newMockViewer);
        Assertions.assertEquals(newMockViewer, stageController.getStageViewer());
    }

    @Test
    public void updateStage() {
        Mockito.when(mockStageModel.getStage()).thenReturn(1);

        // Score below threshold
        stageController.updateStage(499);
        Mockito.verify(mockStageModel, Mockito.never()).setStage(Mockito.anyInt());

        // Score above threshold
        stageController.updateStage(500);
        Mockito.verify(mockStageModel, Mockito.times(1)).setStage(Mockito.anyInt());
    }

    @Test
    public void draw() {
        Mockito.when(mockStageModel.getStage()).thenReturn(5);
        Position[] expectedPositions = {
                new Position(351, 60), // First digit position
                new Position(365, 60)  // Second digit position
        };

        stageController.draw(mockTextGraphics, new Position(0, 0));

        // Expected display: 05
        Mockito.verify(mockStageViewer).setCurrentDigit(0);
        Mockito.verify(mockStageViewer).draw(mockTextGraphics, expectedPositions[0]);
        Mockito.verify(mockStageViewer).setCurrentDigit(5);
        Mockito.verify(mockStageViewer).draw(mockTextGraphics, expectedPositions[1]);

        // Verify the order of interactions
        Mockito.verify(mockStageViewer, Mockito.times(2)).setCurrentDigit(Mockito.anyInt());
        Mockito.verify(mockStageViewer, Mockito.times(2)).draw(Mockito.eq(mockTextGraphics), Mockito.any(Position.class));
    }
}
