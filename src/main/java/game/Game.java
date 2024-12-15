package game;

import controllers.ArenaController;
import model.Arena;

import com.googlecode.lanterna.input.KeyStroke;
import utils.puyoutils.Position;
import viewer.ArenaViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static model.Arena.gameOver;
import static model.Arena.isRunning;
import static model.Grid.*;

public class Game implements Runnable {
    // Attributes
    private final static int FPS = 60; // Higher framerate decreases input latency, somehow
    private Thread gameThread;
    // Arena
    private ArenaController arenaController;
    private ArenaViewer arenaViewer;
    private Arena arena;

    private GameScreen gameScreen;


    // Constructor
    public Game() throws IOException, FontFormatException, URISyntaxException {
        arena = new Arena();
        arenaViewer = new ArenaViewer();
        gameScreen = new GameScreen();
        arenaController = new ArenaController(arena, arenaViewer);
    }


    // Getters
    public ArenaController getArenaController() {
        return arenaController;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }


    // Setters
    public void setArenaController(ArenaController arenaController) {
        this.arenaController = arenaController;
    }

    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }


    // Processes input and checks if game is over. In the latter case, the screen closes
    public void processKey(KeyStroke key) throws IOException, InterruptedException {
        arenaController.processKey(key);

        if (gameOver(arena.getGrid()) || !isRunning) {
            gameScreen.getScreen().close();
            gameThread.join();
        }
    }

    // game.Game loop, if the drawInterval has been passed, we update, process new input and redraw
    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        KeyStroke key = null;

        // Rendering the background only once significantly improves performance!
        arenaController.draw(gameScreen.getGraphics(), null);

        while (true) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                try {
                    arenaController.update();
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
        arenaController.getGridController().draw(gameScreen.getGraphics());

        for (int col = 0; col < COLUMNS; col++) {
            for (int row = ROWS - 1; row >= 0; row--) {
                if(!isEmpty(row,col)) {
                    arenaController.getGridController().getGrid().getPuyo(row, col).getPuyoViewer().draw(gameScreen.getGraphics(), translatePosition(new Position(col, row)));
                }
            }
        }

        arena.getActivePuyo().getFirstPuyo().getPuyoViewer().draw(gameScreen.getGraphics(), translatePosition(arena.getActivePuyo().getFirstPos()));
        arena.getActivePuyo().getSecondPuyo().getPuyoViewer().draw(gameScreen.getGraphics(), translatePosition(arena.getActivePuyo().getSecondPos()));

        arenaController.getNextPuyoViewer().draw(gameScreen.getGraphics(), new Position(212, 8));

        gameScreen.getScreen().refresh();
    }
}