import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import java.io.IOException;import com.googlecode.lanterna.terminal.swing.TerminalScrollController;

public class Application{

    public static void main(String[] args) {
        try {
            Game game = new Game();
            game.run();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

}
