import com.googlecode.lanterna.input.KeyType;
import elements.Arena;
import gui.GameScreen;

import com.googlecode.lanterna.input.KeyStroke;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game implements Runnable {
    private final static int FPS = 30;
    private Thread gameThread;
    private Arena arena;
    private GameScreen gameScreen;

    public Game() throws IOException, FontFormatException, URISyntaxException {
        gameScreen = new GameScreen();
        arena = new Arena();
    }

    public Arena getArena(){ return arena; }
    public GameScreen getGameScreen(){ return gameScreen; }

    public void setArena(Arena arena){ this.arena = arena; }
    public void setGameScreen(GameScreen gameScreen){ this.gameScreen = gameScreen; }


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
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        KeyStroke key = null;

        while (true) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                arena.update();
                // I had to use a try catch block because since I am using run from runnable it doesn't allow me to implement IOException
                try {
                    draw();
                    key = gameScreen.getScreen().pollInput();
                    if (key != null) processKey(key);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }

                delta--;
            }
        }
    }

    public void draw() throws IOException{
        gameScreen.getScreen().clear(); // This makes the screen flick sometimes, but at least the puyos themselves don't flick alone on random places as much
        arena.draw(gameScreen.getGraphics(), null);
        gameScreen.getScreen().refresh();
    }
}
