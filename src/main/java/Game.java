import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import java.io.IOException;

public class Game implements Runnable{

    private Thread gameThread;
    private final static int FPS = 60;
    private Arena arena;
    private Screen screen;
    private TextGraphics graphics;

    public Game() throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        arena = new Arena();

        graphics = screen.newTextGraphics();
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
                arena.update(graphics);
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
        arena.draw(graphics);
        screen.refresh();
    }

    public static void main(String[] args) throws IOException{
        Game game = new Game();
        game.run();
    }
}
