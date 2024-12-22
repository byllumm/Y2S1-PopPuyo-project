package gamestates.runners;

import com.googlecode.lanterna.input.KeyStroke;
import game.Game;
import game.GameScreen;
import gamestates.GameState;
import model.Arena;
import utils.puyoutils.Position;

import java.io.IOException;

public class PlayingStateRunner implements StateRunner {
    private final static int FPS = 60;

    @Override
    public void run(Game game, GameScreen gameScreen) throws IOException, InterruptedException {
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();

        gameScreen.getScreen().clear();
        // Draw initial background (static elements like grid)
        game.getArenaController().draw(gameScreen.getGraphics(), null);

        while (GameState.state == GameState.PLAYING) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                // Update game logic
                game.getArenaController().update();

                // Check if the game is over
                if (Arena.gameOver(game.getArena().getGrid())) {
                    game.resetGame(); // Reset the game state
                    GameState.state = GameState.MENU; // Transition to MENU state
                    return; // Exit the PlayingStateRunner
                }

                // Draw the game
                game.getPlayingStateController().draw(gameScreen.getGraphics(), new Position(0, 0));

                // Process input
                KeyStroke key = gameScreen.getScreen().pollInput();
                if (key != null) {
                    game.getPlayingStateController().processKey(key);
                }

                delta--;
            }
        }
    }
}

