package poppuyo.gamestates.controllers;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import poppuyo.controllers.*;
import poppuyo.game.GameScreen;
import poppuyo.gamestates.GameState;
import poppuyo.model.Arena;
import poppuyo.model.Puyo;
import poppuyo.model.grid.Grid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import poppuyo.utils.puyoutils.Position;
import poppuyo.utils.puyoutils.PuyoPair;
import poppuyo.viewer.GridViewer;
import poppuyo.viewer.NextPuyoViewer;
import poppuyo.viewer.PuyoViewer;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PlayingStateControllerTest {
    @Mock private ArenaController mockArenaController;
    @Mock private GameScreen mockGameScreen;
    @Mock private PuyoPair mockActivePuyo;
    @Mock private Puyo mockFirstPuyo;
    @Mock private Puyo mockSecondPuyo;
    @Mock private Arena mockArena;
    @Mock private PuyoPairController mockPuyoPairController;
    @Mock private TextGraphics mockGraphics;
    @Mock private Position mockFirstPos;
    @Mock private Position mockSecondPos;
    private PlayingStateController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        when(mockArenaController.getArenaModel()).thenReturn(mockArena);
        when(mockArena.getActivePuyo()).thenReturn(mockActivePuyo);
        when(mockActivePuyo.getController()).thenReturn(mockPuyoPairController);
        when(mockActivePuyo.getFirstPuyo()).thenReturn(mockFirstPuyo);
        when(mockActivePuyo.getSecondPuyo()).thenReturn(mockSecondPuyo);
        when(mockActivePuyo.getFirstPos()).thenReturn(mockFirstPos);
        when(mockActivePuyo.getSecondPos()).thenReturn(mockSecondPos);

        controller = new PlayingStateController(mockArenaController, mockGameScreen);
    }

    // Could not use mocks for grid since I'm using isEmpty method and reusing the return value
    // It seems impossible to test this with a mockGrid since we need to access an actual grid
    @Test
    public void testProcessKeyMoveLeft() {
        KeyStroke keyStroke = new KeyStroke(KeyType.ArrowLeft, false, false);

        Position mockFirstPos = mock(Position.class);
        Position mockSecondPos = mock(Position.class);
        Grid gridArray = new Grid();

        when(mockFirstPos.getX()).thenReturn(2);
        when(mockFirstPos.getY()).thenReturn(2);

        when(mockSecondPos.getX()).thenReturn(2);
        when(mockSecondPos.getY()).thenReturn(3);

        when(mockActivePuyo.getFirstPos()).thenReturn(mockFirstPos);
        when(mockActivePuyo.getSecondPos()).thenReturn(mockSecondPos);

        when(mockArena.getGrid()).thenReturn(gridArray);

        controller.processKey(keyStroke);

        verify(mockActivePuyo.getController(), times(1)).moveLeft();
    }

    @Test
    public void testProcessKeyMoveRight() {
        KeyStroke keyStroke = new KeyStroke(KeyType.ArrowRight, false, false);

        Position mockFirstPos = mock(Position.class);
        Position mockSecondPos = mock(Position.class);
        Grid gridArray = new Grid();

        when(mockFirstPos.getX()).thenReturn(2);
        when(mockFirstPos.getY()).thenReturn(2);

        when(mockSecondPos.getX()).thenReturn(2);
        when(mockSecondPos.getY()).thenReturn(3);

        when(mockActivePuyo.getFirstPos()).thenReturn(mockFirstPos);
        when(mockActivePuyo.getSecondPos()).thenReturn(mockSecondPos);

        when(mockArena.getGrid()).thenReturn(gridArray);

        controller.processKey(keyStroke);

        verify(mockActivePuyo.getController(), times(1)).moveRight();
    }

    @Test
    public void testProcessKeyMoveDown() {
        KeyStroke keyStroke = new KeyStroke(KeyType.ArrowDown, false, false);

        Position mockFirstPos = mock(Position.class);
        Position mockSecondPos = mock(Position.class);
        Grid gridArray = new Grid();

        when(mockFirstPos.getX()).thenReturn(2);
        when(mockFirstPos.getY()).thenReturn(2);

        when(mockSecondPos.getX()).thenReturn(2);
        when(mockSecondPos.getY()).thenReturn(3);

        when(mockActivePuyo.getFirstPos()).thenReturn(mockFirstPos);
        when(mockActivePuyo.getSecondPos()).thenReturn(mockSecondPos);

        when(mockArena.getGrid()).thenReturn(gridArray);

        controller.processKey(keyStroke);

        verify(mockActivePuyo.getController(), times(1)).moveDown();
    }

    @Test
    public void testProcessKeyEsc() {
        KeyStroke keyStroke = new KeyStroke(KeyType.Escape, false, false);

        controller.processKey(keyStroke);

        assertEquals(GameState.MENU, GameState.state);
    }

    @Test
    public void testProcessKeyCharacterX() {
        KeyStroke keyStroke = new KeyStroke('x', false, false);

        Position mockNewPos = mock(Position.class);
        Grid mockGrid = mock(Grid.class);
        Puyo[][] mockGridArray = new Puyo[12][6];

        // Setup grid mock
        when(mockGrid.getGrid()).thenReturn(mockGridArray);
        when(mockNewPos.getX()).thenReturn(1);
        when(mockNewPos.getY()).thenReturn(1);

        when(mockArenaController.getArenaModel().getGrid()).thenReturn(mockGrid);

        when(mockActivePuyo.getController().rotate(true)).thenReturn(mockNewPos);

        controller.processKey(keyStroke);

        verify(mockActivePuyo.getController()).rotate(true);
        verify(mockActivePuyo.getSecondPuyo()).setPosition(mockNewPos);
    }

    @Test
    public void testProcessKeyCharacterZ() {
        KeyStroke keyStroke = new KeyStroke('z', false, false);

        Position mockNewPos = mock(Position.class);
        Grid mockGrid = mock(Grid.class);
        Puyo[][] mockGridArray = new Puyo[12][6];

        // Setup grid mock
        when(mockGrid.getGrid()).thenReturn(mockGridArray);
        when(mockNewPos.getX()).thenReturn(2);
        when(mockNewPos.getY()).thenReturn(2);

        when(mockArenaController.getArenaModel().getGrid()).thenReturn(mockGrid);

        when(mockActivePuyo.getController().rotate(false)).thenReturn(mockNewPos);

        controller.processKey(keyStroke);

        verify(mockActivePuyo.getController()).rotate(false);
        verify(mockActivePuyo.getSecondPuyo()).setPosition(mockNewPos);
    }

    @Test
    public void testProcessKeyCharacterQ() {
        KeyStroke keyStroke = new KeyStroke('q', false, false);

        controller.processKey(keyStroke);

        assertEquals(GameState.MENU, GameState.state);
    }

    @Test
    public void testDrawWithEmptyGrid() throws IOException {
        Grid realEmptyGrid = new Grid();
        when(mockArenaController.getGridController()).thenReturn(mock(GridController.class));

        // Mock other components
        ScoreController mockScoreController = mock(ScoreController.class);
        StageController mockStageController = mock(StageController.class);
        NextPuyoViewer mockNextPuyoViewer = mock(NextPuyoViewer.class);

        when(mockArenaController.getScoreController()).thenReturn(mockScoreController);
        when(mockArenaController.getStageController()).thenReturn(mockStageController);
        when(mockActivePuyo.getFirstPuyo().getPuyoViewer()).thenReturn(mock(PuyoViewer.class));
        when(mockActivePuyo.getSecondPuyo().getPuyoViewer()).thenReturn(mock(PuyoViewer.class));
        when(mockArenaController.getNextPuyoViewer()).thenReturn(mockNextPuyoViewer);

        Screen mockScreen = mock(Screen.class);
        when(mockGameScreen.getGraphics()).thenReturn(mockGraphics);
        when(mockGameScreen.getScreen()).thenReturn(mockScreen);

        controller.draw(mockGraphics, new Position(0, 0));

        verify(mockScoreController).draw(mockGraphics, new Position(0, 0));
        verify(mockStageController).draw(mockGraphics, new Position(0, 0));
        verify(mockNextPuyoViewer).draw(mockGraphics, new Position(212, 8));
        verify(mockScreen).refresh();

        // No verification for PuyoViewer.draw() because the grid is empty
    }

    // Again couldn't use mocks for need to access a lot of internal parts of the class
    @Test
    public void testDrawWithOnePuyoOnGrid() throws IOException {
        Grid realEmptyGrid = new Grid();
        GridController realGridController = new GridController(realEmptyGrid, mock(GridViewer.class));

        ScoreController mockScoreController = mock(ScoreController.class);
        StageController mockStageController = mock(StageController.class);
        NextPuyoViewer mockNextPuyoViewer = mock(NextPuyoViewer.class);

        when(mockArenaController.getGridController()).thenReturn(realGridController);
        when(mockArenaController.getScoreController()).thenReturn(mockScoreController);
        when(mockArenaController.getStageController()).thenReturn(mockStageController);
        when(mockArenaController.getNextPuyoViewer()).thenReturn(mockNextPuyoViewer);
        when(mockActivePuyo.getFirstPuyo().getPuyoViewer()).thenReturn(mock(PuyoViewer.class));
        when(mockActivePuyo.getSecondPuyo().getPuyoViewer()).thenReturn(mock(PuyoViewer.class));

        Puyo testPuyo = mock(Puyo.class);
        PuyoViewer mockPuyoViewer = mock(PuyoViewer.class);
        when(testPuyo.getPuyoViewer()).thenReturn(mockPuyoViewer);

        realEmptyGrid.setPuyo(0, 0, testPuyo);

        Screen mockScreen = mock(Screen.class);
        when(mockGameScreen.getGraphics()).thenReturn(mockGraphics);
        when(mockGameScreen.getScreen()).thenReturn(mockScreen);

        controller.draw(mockGraphics, new Position(0, 0));

        verify(mockScoreController, times(1)).draw(mockGraphics, new Position(0, 0));
        verify(mockStageController, times(1)).draw(mockGraphics, new Position(0, 0));
        verify(mockNextPuyoViewer, times(1)).draw(mockGraphics, new Position(212, 8));

        verify(mockPuyoViewer, times(1)).draw(mockGraphics, new Position(8, 8));

        verify(mockScreen, times(1)).refresh();
    }

}

