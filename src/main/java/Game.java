import elements.Arena;
import graphics.GameScreen;

import com.googlecode.lanterna.input.KeyStroke;
import utils.puyoutils.Position;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static elements.GameGrid.*;

public class Game implements Runnable {
    // Attributes
    private final static int FPS = 60; // Higher framerate decreases input latency, somehow
    private Thread gameThread;
    private Arena arena;
    private GameScreen gameScreen;


    // Constructor
    public Game() throws IOException, FontFormatException, URISyntaxException {
        gameScreen = new GameScreen();
        arena = new Arena();
    }


    // Getters
    public Arena getArena() {
        return arena;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }


    // Setters
    public void setArena(Arena arena) {
        this.arena = arena;
    }

    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
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
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        KeyStroke key = null;

        // Rendering the background only once significantly improves performance!
        arena.getArenaGraphics().draw(gameScreen.getGraphics(), null);

        while (true) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                try {
                    arena.update();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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
        arena.getGrid().getGridGraphics().draw(gameScreen.getGraphics(), new Position(8,8));

        for (int col = 0; col < COLUMNS; col++) {
            for (int row = ROWS - 1; row >= 0; row--) {
                if(!arena.getGrid().isEmpty(row,col)) {
                    arena.getGrid().getGrid()[row][col].getPuyoGraphics().draw(gameScreen.getGraphics(), translatePosition(new Position(col, row)));
                }
            }
        }

        arena.getActivePuyo().getFirstPuyo().getPuyoGraphics().draw(gameScreen.getGraphics(), translatePosition(arena.getActivePuyo().getFirstPos()));
        arena.getActivePuyo().getSecondPuyo().getPuyoGraphics().draw(gameScreen.getGraphics(), translatePosition(arena.getActivePuyo().getSecondPos()));

        gameScreen.getScreen().refresh();
    }
}