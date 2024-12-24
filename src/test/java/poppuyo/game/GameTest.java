package poppuyo.game;

import com.googlecode.lanterna.input.KeyStroke;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import poppuyo.gamestates.GameState;
import poppuyo.gamestates.controllers.MenuStateController;
import poppuyo.gamestates.controllers.PlayingStateController;
import poppuyo.gamestates.runners.MenuStateRunner;
import poppuyo.gamestates.runners.StateRunner;
import poppuyo.model.Arena;
import poppuyo.model.Puyo;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class GameTest {
    @Mock private KeyStroke mockKey;
    @Mock private MenuStateController mockMenuStateController;
    @Mock private PlayingStateController mockPlayingStateController;
    @Mock private MenuStateRunner mockMenuStateRunner;
    @Mock private StateRunner mockStateRunner;
    private Game game;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException {
        MockitoAnnotations.openMocks(this);
        game = Game.getInstance();
    }

    @Test
    void getInstance() throws IOException, FontFormatException, URISyntaxException {
        Game instance1 = Game.getInstance();
        Game instance2 = Game.getInstance();

        assertSame(instance1, instance2);
    }

    @Test
    void initialize() throws IOException, FontFormatException, URISyntaxException {

        assertNotNull(game.getArenaController());
        assertNotNull(game.getGameScreen());
        assertNotNull(game.getMenuStateController());
        assertNotNull(game.getPlayingStateController());
        assertNotNull(game.getCreditsStateController());
    }

    @Test
    void processKeyInMenuState() throws IOException, InterruptedException {
        game.setMenuStateController(mockMenuStateController);

        GameState.state = GameState.MENU;

        game.processKey(mockKey);

        Mockito.verify(mockMenuStateController, times(1)).processKey(mockKey);
    }

    @Test
    void processKeyInPlayingStateGameOver() throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        Game gameSpy = Mockito.spy(Game.getInstance());
        gameSpy.setPlayingStateController(mockPlayingStateController);

        Arena arena = gameSpy.getArena();
        arena.getGrid().getGrid()[0][0] = mock(Puyo.class);

        GameState.state = GameState.PLAYING;

        gameSpy.processKey(mockKey);

        // Assert
        Mockito.verify(mockPlayingStateController, times(1)).processKey(mockKey );
        Mockito.verify(gameSpy, times(1)).handleGameOver();  // Verify that handleGameOver was called on the spy
    }

    @Test
    void resetGame() throws IOException {
        game.resetGame();

        Assertions.assertNotNull(game.getArena());
        Assertions.assertNotNull(game.getArenaController());
        Assertions.assertNotNull(game.getPlayingStateController());
    }

    @Test
    void runState() throws IOException, InterruptedException {
        game.runState(mockStateRunner);

        Mockito.verify(mockStateRunner, times(1)).run(game, game.getGameScreen());
    }

    @Test
    void runMenuState() throws IOException {
        GameState.state = GameState.MENU;

        game.runState(mockMenuStateRunner);

        Mockito.verify(mockMenuStateRunner, times(1)).run(game, game.getGameScreen());
    }
}

