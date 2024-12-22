package gamestates.runners;

import game.Game;
import game.GameScreen;

import java.io.IOException;

public interface StateRunner {
    void run(Game game, GameScreen gameScreen) throws IOException, InterruptedException;
}
