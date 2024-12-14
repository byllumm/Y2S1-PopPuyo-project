import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Application{

    public static void main(String[] args) {
        try {
            Game game = new Game();
            game.run();
        } catch (IOException | FontFormatException | URISyntaxException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
