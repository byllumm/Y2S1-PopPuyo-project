package poppuyo.gamestates.runners;

import com.googlecode.lanterna.input.KeyStroke;
import poppuyo.game.Game;
import poppuyo.game.GameScreen;
import poppuyo.gamestates.GameState;
import poppuyo.gamestates.controllers.MenuStateController;
import poppuyo.utils.puyoutils.Position;

import java.io.IOException;

public class MenuStateRunner implements StateRunner {
    // Class Methods
    @Override
    public void run(Game game, GameScreen gameScreen) throws IOException {
        MenuStateController menuStateController = game.getMenuStateController();

        // Draw only once to reduce workload
        menuStateController.menuStateViewer.drawBackground(gameScreen.getGraphics(), new Position(0,0));
        while (GameState.state == GameState.MENU) {
            // Draw the menu buttons on top of the stripped menu
            menuStateController.draw(gameScreen.getGraphics(), new Position(134,147));

            // Refresh and process input
            gameScreen.getScreen().refresh();
            KeyStroke key = gameScreen.getScreen().pollInput();
            if (key != null) {
                menuStateController.processKey(key);
            }
        }
    }
}
