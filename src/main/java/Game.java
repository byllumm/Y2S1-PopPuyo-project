import elements.Arena;
import gamestates.GameState;
import graphics.GameScreen;
import gamestates.Playing;

import com.googlecode.lanterna.input.KeyStroke;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static gamestates.Playing.isRunning;

public class Game implements Runnable {
    // Attributes
    private final static int FPS = 60; // Higher framerate decreases input latency, somehow
    private Arena arena;
    private Thread gameThread;
    private GameScreen gameScreen;
    private Playing playing;
    private Menu menu;


    // Constructor
    public Game() throws IOException, FontFormatException, URISyntaxException {
        gameScreen = new GameScreen();
        arena = new Arena();
        playing = new Playing(arena, gameScreen);
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

    public void update() throws IOException {
        switch (GameState.state){
            case MENU:
                break;
            case PLAYING:
                playing.update();
                break;
            default:
                break;
        }
    }


    // Processes input and checks if game is over. In the latter case, the screen closes
    public void processKey(KeyStroke key) throws IOException, InterruptedException {
        switch(GameState.state){
            case MENU:
                break;

            case PLAYING:
                playing.processKey(key);

                if (arena.gameOver(arena.getGrid()) || !isRunning) {
                    gameScreen.getScreen().close();
                    gameThread.join();
                }
                break;

            default:
                break;

        }
    }

    public void draw() throws IOException{

        switch (GameState.state){
            case MENU:
                break;
            case PLAYING:
                playing.draw();
                break;
            default:
                break;

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
                    update();
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
}