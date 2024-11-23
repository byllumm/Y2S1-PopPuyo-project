import elements.Arena;
import elements.GameScreen;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game implements Runnable {
    private final static int FPS = 60;
    private Thread gameThread;
    private Arena arena;
    private GameScreen gameScreen;

    public Game() throws IOException, FontFormatException, URISyntaxException {
        gameScreen = new GameScreen();
        arena = new Arena();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        startGameThread();
        double drawInterval = 1000000000.0 / FPS; //0.0166667 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                arena.update(gameScreen.getGraphics());
                // I had to use a try catch block because since I am using run from runnable it doesn't allow me to implement IOException
                try {
                    draw();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                delta--;
            }
        }
    }

    public void draw() throws IOException{
        arena.draw(gameScreen.getGraphics());
        gameScreen.getScreen().refresh();
    }
}
