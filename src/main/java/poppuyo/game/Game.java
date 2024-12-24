package poppuyo.game;

import poppuyo.controllers.ArenaController;
import poppuyo.gamestates.controllers.CreditsStateController;
import poppuyo.gamestates.GameState;
import poppuyo.gamestates.controllers.MenuStateController;
import poppuyo.gamestates.controllers.PlayingStateController;
import poppuyo.gamestates.runners.CreditsStateRunner;
import poppuyo.gamestates.runners.MenuStateRunner;
import poppuyo.gamestates.runners.PlayingStateRunner;
import poppuyo.gamestates.runners.StateRunner;
import poppuyo.model.Arena;

import com.googlecode.lanterna.input.KeyStroke;
import poppuyo.viewer.ArenaViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static poppuyo.model.Arena.gameOver;
import static poppuyo.model.Arena.isRunning;

public class Game implements Runnable {
    // Attributes
    private static Game instance;
    private Thread gameThread;
    private ArenaController arenaController;

    private GameState currentState;
    private ArenaViewer arenaViewer;
    private Arena arena;
    private GameScreen gameScreen;
    private PlayingStateController playingStateController;
    private MenuStateController menuStateController;
    private CreditsStateController creditsStateController;


    // Constructor
    private Game() throws IOException, FontFormatException, URISyntaxException {
        initialize();
    }

    public static Game getInstance() throws IOException, FontFormatException, URISyntaxException{
        if(instance == null){
            instance = new Game();
        }
        return instance;
    }

    private void initialize() throws IOException, FontFormatException, URISyntaxException{
        arena = new Arena();
        arenaViewer = new ArenaViewer();
        gameScreen = GameScreen.getInstance();
        arenaController = new ArenaController(arena, arenaViewer);

        menuStateController = new MenuStateController();
        creditsStateController = new CreditsStateController();
        playingStateController = new PlayingStateController(arenaController, gameScreen);

        gameThread = new Thread(this);
    }


    // Getters
    public ArenaController getArenaController() { return arenaController; }
    public GameScreen getGameScreen() { return gameScreen; }
    public MenuStateController getMenuStateController(){ return menuStateController; }
    public PlayingStateController getPlayingStateController(){ return playingStateController; }
    public CreditsStateController getCreditsStateController(){ return creditsStateController; }
    public Arena getArena(){ return arena; }


    // Setters
    public void setArenaController(ArenaController arenaController) { this.arenaController = arenaController; }
    public void setGameScreen(GameScreen gameScreen) { this.gameScreen = gameScreen; }


    // Class Methods
    private void handleGameOver() throws IOException{
        if(gameOver(arena.getGrid())){
            resetGame();
            GameState.state = GameState.MENU;
        }
    }

    public void resetGame() throws IOException {
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


    // Processes input and checks if com.t09g07.poppuyo.game is over. In the latter case, the screen closes
    public void processKey(KeyStroke key) throws IOException, InterruptedException {
        switch (GameState.state) {
            case MENU:
                menuStateController.processKey(key);
                break;
            case CREDITS:
                creditsStateController.processKey(key);
                break;
            case PLAYING:
                handleGameOver();
                playingStateController.processKey(key);
                break;
            case EXIT:
                exitGame();
                break;

        }
    }

    private void runState(StateRunner stateRunner) {
        try {
            stateRunner.run(this, gameScreen);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error in State", e);
        }
    }

    // poppuyo.game.Game loop, if the drawInterval has been passed, we update, process new input and redraw
    @Override
    public void run() {
        while (isRunning) {
            switch (GameState.state) {

                case MENU:
                    runState(new MenuStateRunner());
                    break;

                case PLAYING:
                    runState(new PlayingStateRunner());
                    break;

                case CREDITS:
                    runState(new CreditsStateRunner());
                    break;
                case EXIT:
                    try {
                        exitGame();
                    } catch (InterruptedException | IOException e) {
                        throw new RuntimeException(e);
                    }
                    return; // Exit the loop when the com.t09g07.poppuyo.game is shutting down

            }
        }
    }
}