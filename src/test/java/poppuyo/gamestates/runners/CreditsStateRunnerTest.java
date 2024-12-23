package poppuyo.gamestates.runners;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import game.Game;
import game.GameScreen;
import gamestates.GameState;
import gamestates.controllers.CreditsStateController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import utils.puyoutils.Position;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class CreditsStateRunnerTest {
    @Mock private Game mockGame;
    @Mock private GameScreen mockGameScreen;
    @Mock private CreditsStateController mockCreditsStateController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRunDrawsCreditsAndProcessesInput() throws IOException {
        KeyStroke keyStroke = new KeyStroke(KeyType.Backspace, false, false);

        when(mockGame.getCreditsStateController()).thenReturn(mockCreditsStateController);

        when(mockGameScreen.getScreen()).thenReturn(mock(Screen.class));
        when(mockGameScreen.getGraphics()).thenReturn(mock(TextGraphics.class));

        GameState.state = GameState.CREDITS;

        when(mockGameScreen.getScreen().pollInput()).thenReturn(keyStroke).thenReturn(null); // After one poll, return null to exit the loop

        CreditsStateRunner runner = new CreditsStateRunner();

        doAnswer(invocation -> {
            GameState.state = GameState.MENU;
            return null;
        }).when(mockCreditsStateController).processKey(any());

        runner.run(mockGame, mockGameScreen);

        verify(mockCreditsStateController, times(1)).draw(mockGameScreen.getGraphics(), new Position(0,0));
        verify(mockGameScreen.getScreen(), times(1)).refresh();
        verify(mockCreditsStateController, times(1)).processKey(keyStroke);
    }
}

