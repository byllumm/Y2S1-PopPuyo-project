package game;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class GameScreen {
    // Attributes
    private static GameScreen instance;
    private Screen screen;
    private TextGraphics graphics;
    private final static int width = 390;
    private final static int height = 400;


    // Constructor
    private GameScreen() throws IOException, FontFormatException, URISyntaxException {

        ////////////////////////////////////////////////////////////////////////////////////////
        //This block is used to load the square font
        URL resource = getClass().getClassLoader().getResource("fonts/square.ttf");
        assert resource != null;
        File fontFile = new File(resource.toURI());
        Font font =  Font.createFont(Font.TRUETYPE_FONT, fontFile);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        Font newfont = font.deriveFont(Font.BOLD, 2);
        ////////////////////////////////////////////////////////////////////////////////////////

        AWTTerminalFontConfiguration cfg = AWTTerminalFontConfiguration.newInstance(newfont);

        Terminal terminal = new DefaultTerminalFactory()
                .setInitialTerminalSize(new TerminalSize(width, height))
                .setTerminalEmulatorFontConfiguration(cfg)
                .setForceAWTOverSwing(true)
                .createTerminal();

        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        graphics = screen.newTextGraphics();
    }

    public static GameScreen getInstance() throws IOException, FontFormatException, URISyntaxException{
        if(instance == null){
            instance = new GameScreen();
        }
        return instance;
    }


    // Getters
    public TextGraphics getGraphics() { return graphics; }
    public Screen getScreen() { return screen; }

    // Setters
    public void setGraphics(TextGraphics graphics) { this.graphics = graphics; }
    public void setScreen(Screen screen) { this.screen = screen; }
}
