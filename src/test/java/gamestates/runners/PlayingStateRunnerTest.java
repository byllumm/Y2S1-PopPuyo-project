package gamestates.runners;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import game.Game;
import game.GameScreen;
import gamestates.GameState;
import gamestates.controllers.PlayingStateController;
import model.Arena;
import controllers.ArenaController;
import model.Puyo;
import model.grid.Grid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import utils.puyoutils.Position;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PlayingStateRunnerTest {

    @Mock private Game mockGame;
    @Mock private GameScreen mockGameScreen;
    @Mock private ArenaController mockArenaController;
    @Mock private PlayingStateController mockPlayingStateController;
    @Mock private Arena mockArena;

    private PlayingStateRunner playingStateRunner;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        playingStateRunner = new PlayingStateRunner();

        // Mock the relevant methods
        when(mockGame.getArenaController()).thenReturn(mockArenaController);
        when(mockGame.getPlayingStateController()).thenReturn(mockPlayingStateController);
        when(mockGame.getArena()).thenReturn(mockArena);
    }

    @Test
    public void testRunDrawsAndProcessesInput() throws IOException, InterruptedException {
        KeyStroke keyStroke = new KeyStroke(KeyType.Enter, false, false);
        Grid gridArray = new Grid();

        when(mockGameScreen.getScreen()).thenReturn(mock(Screen.class));
        when(mockGameScreen.getGraphics()).thenReturn(mock(TextGraphics.class));
        when(mockArena.getGrid()).thenReturn(gridArray);

        GameState.state = GameState.PLAYING;

        when(mockGameScreen.getScreen().pollInput()).thenReturn(keyStroke).thenReturn(null);

        doAnswer(invocation -> {
            GameState.state = GameState.MENU;
            return null;
        }).when(mockPlayingStateController).processKey(any());

        playingStateRunner.run(mockGame, mockGameScreen);

        verify(mockArenaController, times(1)).draw(mockGameScreen.getGraphics(), null);
        verify(mockArenaController).update();
        verify(mockPlayingStateController).draw(mockGameScreen.getGraphics(), new Position(0, 0));
        verify(mockPlayingStateController).processKey(eq(keyStroke));
        verify(mockGameScreen.getScreen()).clear();
    }

    @Test
    public void testRunGameOver() throws IOException, InterruptedException {
        Grid gridArray = new Grid();

        when(mockGameScreen.getScreen()).thenReturn(mock(Screen.class));
        when(mockGameScreen.getGraphics()).thenReturn(mock(TextGraphics.class));
        when(mockArena.getGrid()).thenReturn(gridArray);

        gridArray.setPuyo(0, 2, new Puyo(new Position(0,2)));
        gridArray.setPuyo(0, 3, new Puyo(new Position(0, 3)));


        GameState.state = GameState.PLAYING;

        playingStateRunner.run(mockGame, mockGameScreen);

        verify(mockArenaController, times(1)).draw(mockGameScreen.getGraphics(), null);
        verify(mockArenaController).update();

        verify(mockGame).resetGame();
        verify(mockGameScreen.getScreen()).clear();
        assertEquals(GameState.MENU, GameState.state);
    }

}
