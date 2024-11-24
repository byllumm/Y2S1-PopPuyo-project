import elements.Arena;
import elements.GameScreen;

import javax.swing.*;
import com.googlecode.lanterna.input.KeyStroke;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.Key;

public class Game implements Runnable {
    private final static int FPS = 60;
    private Thread gameThread;
    private Arena arena;
    private GameScreen gameScreen;

    public Game() throws IOException, FontFormatException, URISyntaxException {
        gameScreen = new GameScreen();
        arena = new Arena();
    }

    // Starts game thread
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Processes input and checks if game is over. In the latter case, the screen closes
    public void processKey(KeyStroke key) throws IOException, InterruptedException {
        arena.processKey(key);

        if(arena.gameOver(arena.getGrid())){
            gameScreen.getScreen().close();
            gameThread.join();
        }
    }

    // Game loop, if the drawInterval has been passed, we update, process new input and redraw
    @Override
    public void run() {
        startGameThread();
        double drawInterval = 1000000000.0 / FPS; //0.0166667 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        KeyStroke key = null;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                arena.update();
                // I had to use a try catch block because since I am using run from runnable it doesn't allow me to implement IOException
                try {
                    draw();
                    key = gameScreen.getScreen().readInput();
                    processKey(key);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }

                delta--;
            }
        }
    }

    public void draw() throws IOException{
        gameScreen.getScreen().clear(); // This makes the screen flick some times, but at least the puyos themselves don't flick alone on random places
        arena.draw(gameScreen.getGraphics());
        gameScreen.getScreen().refresh();
    }
}
