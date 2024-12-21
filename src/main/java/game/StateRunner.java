package game;

import java.io.IOException;

public interface StateRunner {
    void run(Game game, GameScreen gameScreen) throws IOException, InterruptedException;
}
