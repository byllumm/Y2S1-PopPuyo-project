package game;

import com.googlecode.lanterna.input.KeyStroke;
import gamestates.GameState;
import utils.puyoutils.Position;

import java.io.IOException;

public class CreditsStateRunner implements StateRunner {
    // Class Methods
    @Override
    public void run(Game game, GameScreen gameScreen) throws IOException {
        while (GameState.state == GameState.CREDITS) {
            gameScreen.getScreen().clear();

            // Draw the credits
            game.getCreditsStateController().draw(gameScreen.getGraphics(), new Position(0, 0));

            // Refresh the screen and process input
            gameScreen.getScreen().refresh();
            KeyStroke key = gameScreen.getScreen().pollInput();
            if (key != null) {
                game.getCreditsStateController().processKey(key);
            }
        }
    }
}
