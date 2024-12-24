package poppuyo.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.mock;

class GameScreenTest {
    @Mock Terminal mockTerminal;
    @Mock TerminalScreen mockTerminalScreen;
    @Mock AWTTerminalFontConfiguration mockFontConfig;
    private GameScreen gameScreen;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException {
        MockitoAnnotations.openMocks(this);
        gameScreen = GameScreen.getInstance();
    }

    @Test
    void constructor() {
        Mockito.when(mockTerminalScreen.newTextGraphics()).thenReturn(mock(TextGraphics.class));
        Mockito.when(mockTerminalScreen.getTerminal()).thenReturn(mockTerminal);

        Assertions.assertNotNull(gameScreen.getScreen());
        Assertions.assertNotNull(gameScreen.getGraphics());
    }

    @Test
    void getInstance() throws IOException, FontFormatException, URISyntaxException {
        GameScreen gameScreen1 = GameScreen.getInstance();
        GameScreen gameScreen2 = GameScreen.getInstance();

        Assertions.assertSame(gameScreen1, gameScreen2);
    }

    @Test
    void getGraphics() {
        TextGraphics graphics = gameScreen.getGraphics();

        Assertions.assertNotNull(graphics);
    }

    @Test
    void getScreen() {
        Screen screen = gameScreen.getScreen();

        Assertions.assertNotNull(screen);
    }
}
