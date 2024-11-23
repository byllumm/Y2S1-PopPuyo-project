import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Game implements Runnable{

    private Thread gameThread;
    private final static int FPS = 60;
    private Arena arena;
    private Screen screen;
    private TextGraphics graphics;
    private final static int width = 6;
    private final static int height = 11;

    public Game() throws IOException, FontFormatException, URISyntaxException {

        ////////////////////////////////////////////////////////////////////////////////////////
        //This block is used to load the square font
        URL resource = getClass().getClassLoader().getResource("fonts/square.ttf");
        File fontFile = new File(resource.toURI());
        Font font =  Font.createFont(Font.TRUETYPE_FONT, fontFile);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        Font newfont = font.deriveFont(Font.BOLD, 48); // To make things bigger, just increase font size...
                                                            // Not sure if this method would work with sprites though.
        ////////////////////////////////////////////////////////////////////////////////////////

        AWTTerminalFontConfiguration cfg = AWTTerminalFontConfiguration.newInstance(newfont);

        Terminal terminal = new DefaultTerminalFactory()
                .setInitialTerminalSize(new TerminalSize(width, height))
                .setTerminalEmulatorFontConfiguration(cfg)
                .setForceAWTOverSwing(true)
                .createTerminal();

        //Terminal terminal = new DefaultTerminalFactory().createTerminal(); <-- Probably not needed anymore but keeping for now...
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        arena = new Arena();

        graphics = screen.newTextGraphics();
    }

    public void startGameThread(){
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
}
