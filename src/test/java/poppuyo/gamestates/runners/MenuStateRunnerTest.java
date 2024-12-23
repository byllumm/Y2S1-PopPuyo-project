package poppuyo.gamestates.runners;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import poppuyo.game.Game;
import poppuyo.game.GameScreen;
import poppuyo.gamestates.GameState;
import poppuyo.gamestates.controllers.MenuStateController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import poppuyo.utils.puyoutils.Position;
import poppuyo.viewer.MenuStateViewer;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class MenuStateRunnerTest {
    @Mock private Game mockGame;
    @Mock private GameScreen mockGameScreen;
    @Mock private MenuStateController mockMenuStateController;
    @Mock private MenuStateViewer mockMenuStateViewer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRunDrawsMenuAndProcessesInput() throws IOException {
        KeyStroke keyStroke = new KeyStroke(KeyType.Enter, false, false);

        when(mockGame.getMenuStateController()).thenReturn(mockMenuStateController);
        when(mockMenuStateController.getMenuStateViewer()).thenReturn(mockMenuStateViewer);

        when(mockGameScreen.getScreen()).thenReturn(mock(Screen.class));
        when(mockGameScreen.getGraphics()).thenReturn(mock(TextGraphics.class));

        GameState.state = GameState.MENU;

        when(mockGameScreen.getScreen().pollInput()).thenReturn(keyStroke).thenReturn(null);
        MenuStateRunner runner = new MenuStateRunner();

        doAnswer(invocation -> {
            GameState.state = GameState.PLAYING;
            return null;
        }).when(mockMenuStateController).processKey(any());

        runner.run(mockGame, mockGameScreen);

        verify(mockMenuStateViewer, times(1)).drawBackground(mockGameScreen.getGraphics(), new Position(0,0));
        verify(mockMenuStateController, times(1)).draw(mockGameScreen.getGraphics(), new Position(134, 147));
        verify(mockGameScreen.getScreen(), times(1)).refresh();
        verify(mockMenuStateController).processKey(keyStroke);
    }
}
