package poppuyo.controllers;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import poppuyo.model.*;
import poppuyo.model.grid.Grid;
import poppuyo.utils.puyoutils.Position;
import poppuyo.utils.puyoutils.PuyoPair;
import poppuyo.viewer.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArenaControllerTest {
    @Mock private Arena mockArenaModel;
    @Mock private Score mockScoreModel;
    @Mock private Stage mockStageModel;
    @Mock private PuyoPair mockActivePuyo;
    @Mock private PuyoPair mockNextPuyo;
    @Mock private ArenaViewer mockArenaViewer;
    @Mock private PuyoViewer mockFirstPuyoViewer;
    @Mock private PuyoViewer mockSecondPuyoViewer;
    @Mock private Puyo mockFirstPuyo;
    @Mock private Puyo mockSecondPuyo;
    @Mock private Position mockFirstPosition;
    @Mock private Position mockSecondPosition;
    @Mock private TextGraphics mockGraphics;
    private ArenaController arenaController;
    private Grid grid;

    @BeforeEach
    public void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        grid = new Grid();

        // Arena dependencies
        Mockito.when(mockArenaModel.getGrid()).thenReturn(grid);
        Mockito.when(mockArenaModel.getScore()).thenReturn(mockScoreModel);
        Mockito.when(mockArenaModel.getStage()).thenReturn(mockStageModel);
        Mockito.when(mockArenaModel.getActivePuyo()).thenReturn(mockActivePuyo);
        Mockito.when(mockArenaModel.getNextPuyo()).thenReturn(mockNextPuyo);

        // NextPuyo dependencies
        Mockito.when(mockNextPuyo.getFirstPuyo()).thenReturn(mockFirstPuyo);
        Mockito.when(mockNextPuyo.getSecondPuyo()).thenReturn(mockSecondPuyo);
        Mockito.when(mockFirstPuyo.getPuyoViewer()).thenReturn(mockFirstPuyoViewer);
        Mockito.when(mockSecondPuyo.getPuyoViewer()).thenReturn(mockSecondPuyoViewer);

        // Position dependencies
        Mockito.when(mockActivePuyo.getFirstPos()).thenReturn(mockFirstPosition);
        Mockito.when(mockActivePuyo.getSecondPos()).thenReturn(mockSecondPosition);

        // Setup Position coordinates
        Mockito.when(mockFirstPosition.getX()).thenReturn(2);
        Mockito.when(mockFirstPosition.getY()).thenReturn(0);
        Mockito.when(mockSecondPosition.getX()).thenReturn(3);
        Mockito.when(mockSecondPosition.getY()).thenReturn(0);

        arenaController = new ArenaController(mockArenaModel, mockArenaViewer);
    }

    @Test
    public void constructor() throws IOException {
        assertNotNull(arenaController);
        assertNotNull(arenaController.getGridController());
        assertNotNull(arenaController.getScoreController());
        assertNotNull(arenaController.getStageController());
        assertNotNull(arenaController.getNextPuyoViewer());
        assertEquals(mockArenaModel, arenaController.getArenaModel());
    }

    @Test
    public void getGridController() {
        Assertions.assertNotNull(arenaController.getGridController());
    }

    @Test
    public void getStageController() {
        Assertions.assertNotNull(arenaController.getStageController());
    }

    @Test
    public void getScoreController() {
        Assertions.assertNotNull(arenaController.getScoreController());
    }

    @Test
    public void getNextPuyoViewer() {
        Assertions.assertNotNull(arenaController.getNextPuyoViewer());
    }

    @Test
    public void getArenaModel() {
        Assertions.assertSame(mockArenaModel, arenaController.getArenaModel());
    }

    @Test
    public void draw() {
        Position mockPosition = mock(Position.class);
        arenaController.draw(mockGraphics, mockPosition);
        Mockito.verify(mockArenaViewer).draw(mockGraphics, mockPosition);
    }

    @Test
    public void updateWithAutoDropWhenCanMoveDown() throws IOException {
        Mockito.when(mockFirstPosition.getY()).thenReturn(0);
        Mockito.when(mockFirstPosition.getX()).thenReturn(2);
        Mockito.when(mockSecondPosition.getY()).thenReturn(0);
        Mockito.when(mockSecondPosition.getX()).thenReturn(3);
        Mockito.when(mockArenaModel.getActivePuyo().getController()).thenReturn(Mockito.mock(PuyoPairController.class));

        for (int i = 0; i < Arena.dropInterval; i++) {
            arenaController.update();
        }

        Mockito.verify(mockArenaModel, atLeastOnce()).getActivePuyo();
    }

    @Test
    public void updateWithGameOverCondition() throws IOException {
        grid.setPuyo(2, 0, mockFirstPuyo);
        grid.setPuyo(3, 0, mockSecondPuyo);

        Mockito.when(mockFirstPosition.getY()).thenReturn(Grid.ROWS - 1);
        Mockito.when(mockFirstPosition.getX()).thenReturn(2);
        Mockito.when(mockSecondPosition.getY()).thenReturn(Grid.ROWS - 1);
        Mockito.when(mockSecondPosition.getX()).thenReturn(3);

        arenaController.update();

        Mockito.verify(mockArenaModel, never()).setActivePuyo(any());
        Mockito.verify(mockArenaModel, never()).setNextPuyo(any());
    }
}