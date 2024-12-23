package gamestates.runners;

import com.googlecode.lanterna.input.KeyStroke;
import game.Game;
import game.GameScreen;
import gamestates.GameState;
import gamestates.controllers.MenuStateController;
import utils.puyoutils.Position;

import java.io.IOException;

public class MenuStateRunner implements StateRunner {
    // Class Methods
    @Override
    public void run(Game game, GameScreen gameScreen) throws IOException {
        MenuStateController menuStateController = game.getMenuStateController();

        // Draw only once to reduce workload
        menuStateController.getMenuStateViewer().drawBackground(gameScreen.getGraphics(), new Position(0,0));
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
