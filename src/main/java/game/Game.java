package game;

import controllers.ArenaController;
import gamestates.CreditsStateController;
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
    private CreditsStateController creditsStateController;


    // Constructor
    public Game() throws IOException, FontFormatException, URISyntaxException {
        arena = new Arena();
        arenaViewer = new ArenaViewer();
        gameScreen = new GameScreen();
        arenaController = new ArenaController(arena, arenaViewer);
        playingStateController = new PlayingStateController(arenaController, gameScreen);
        menuStateController = new MenuStateController();
        creditsStateController = new CreditsStateController();
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
        switch (GameState.state) {
            case MENU -> menuStateController.processKey(key);
            case CREDITS -> creditsStateController.processKey(key);
            case PLAYING -> {
                handleGameOver();
                playingStateController.processKey(key);
            }
            case EXIT -> exitGame();
        }
    }

    private void handleGameOver() throws IOException{
        if(gameOver(arena.getGrid())){
            resetGame();
            GameState.state = GameState.MENU;
        }
    }

    private void resetGame() throws IOException {
        arena = new Arena();
        arenaController = new ArenaController(arena, arenaViewer);
        playingStateController = new PlayingStateController(arenaController, gameScreen);
    }


    private void exitGame() throws IOException, InterruptedException {
        gameScreen.getScreen().close();
        gameThread.join();
        isRunning = false;
        System.exit(0);
    }


    // game.Game loop, if the drawInterval has been passed, we update, process new input and redraw
    @Override
    public void run() {
        while (isRunning) {
            switch (GameState.state) {
                case MENU:
                    runMenuState();
                    break;

                case PLAYING:
                    runPlayingState();
                    break;

                case CREDITS:
                    runCreditsState();
                    break;

                case EXIT:
                    try {
                        exitGame();
                    } catch (InterruptedException | IOException e) {
                        throw new RuntimeException(e);
                    }
                    return; // Exit the loop when the game is shutting down
            }
        }
    }

    private void runMenuState() {
        try {
            while (GameState.state == GameState.MENU) {
                gameScreen.getScreen().clear();

                // Draw the menu
                menuStateController.draw(gameScreen.getGraphics(), new Position(0,0));

                // Refresh and process input
                gameScreen.getScreen().refresh();
                KeyStroke key = gameScreen.getScreen().pollInput();
                if (key != null) {
                    menuStateController.processKey(key);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error in Menu State", e);
        }
    }

    private void runPlayingState() {
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        gameScreen.getScreen().clear();
        // Render background once for better performance
        arenaController.draw(gameScreen.getGraphics(), null);

        try {
            while (isRunning) {
                if(GameState.state != GameState.PLAYING){
                    break;
                }
                long currentTime = System.nanoTime();
                delta += (currentTime - lastTime) / drawInterval;
                lastTime = currentTime;

                if (delta >= 1) {
                    // Update game logic
                    arenaController.update();
                    handleGameOver();

                    // Draw and process input
                    playingStateController.draw(gameScreen.getGraphics(), new Position(0,0) );
                    KeyStroke key = gameScreen.getScreen().pollInput();
                    if (key != null) {
                        processKey(key);
                    }

                    delta--;
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error in Playing State", e);
        }
    }

    private void runCreditsState() {
        try {
            while (GameState.state == GameState.CREDITS) {
                gameScreen.getScreen().clear();

                // Draw the credits screen
                creditsStateController.draw(gameScreen.getGraphics(), new Position(0,0));

                // Refresh and process input
                gameScreen.getScreen().refresh();
                KeyStroke key = gameScreen.getScreen().pollInput();
                if (key != null) {
                    creditsStateController.processKey(key);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error in Credits State", e);
        }
    }
}