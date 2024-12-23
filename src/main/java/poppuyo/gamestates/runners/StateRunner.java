package poppuyo.gamestates.runners;

import poppuyo.game.Game;
import poppuyo.game.GameScreen;

import java.io.IOException;

public interface StateRunner {
    void run(Game game, GameScreen gameScreen) throws IOException, InterruptedException;
}
