package game;

import controllers.ArenaController;
import gamestates.GameState;
import gamestates.MenuStateController;
import gamestates.PlayingStateController;
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

    private PlayingStateController playingStateController;
    private MenuStateController menuStateController;


    // Constructor
    public Game() throws IOException, FontFormatException, URISyntaxException {
        arena = new Arena();
        arenaViewer = new ArenaViewer();
        gameScreen = new GameScreen();
        arenaController = new ArenaController(arena, arenaViewer);
        playingStateController = new PlayingStateController(arenaController, gameScreen);
        menuStateController = new MenuStateController();
        gameThread = new Thread(this);
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
        switch(GameState.state){
            case MENU:
                menuStateController.processKey(key);
                break;

            case PLAYING:
                playingStateController.processKey(key);
                if(gameOver(arena.getGrid()) || !isRunning){
                    /* GameState.state = GameState.MENU; */
                    gameScreen.getScreen().close();
                    gameThread.join();
                    isRunning = false;
                    System.exit(0);
                    break;

                }
                break;

            case EXIT:
                gameScreen.getScreen().close();
                gameThread.join();
                isRunning = false;
                System.exit(0);
                break;
        }
    }

    // game.Game loop, if the drawInterval has been passed, we update, process new input and redraw
    @Override
    public void run() {
        switch(GameState.state) {
            case MENU:
                while(GameState.state == GameState.MENU){
                    gameScreen.getScreen().clear();
                    menuStateController.draw();
                    try {
                        gameScreen.getScreen().refresh();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;


            case PLAYING:
                double drawInterval = 1000000000.0 / FPS;
                double delta = 0;
                long lastTime = System.nanoTime();
                long currentTime;
                KeyStroke key = null;

                // Rendering the background only once significantly improves performance!
                arenaController.draw(gameScreen.getGraphics(), null);

                while (isRunning) {
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
                            playingStateController.draw();
                            key = gameScreen.getScreen().pollInput();
                            if (key != null) processKey(key);
                        } catch (IOException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        delta--;

                    }
                }
                break;
        }

    }

    public void draw() throws IOException{

        switch(GameState.state){
            case PLAYING:
                playingStateController.draw();
                break;

            case MENU:
                menuStateController.draw();
                break;
        }

    }
}