package viewer;

import com.googlecode.lanterna.graphics.TextGraphics;
import utils.custom_exceptions.ResourceException;
import utils.puyoutils.Position;

import java.io.IOException;

public class MenuStateViewer implements Viewer {
    // Attributes
    private static SpriteLoader menuStates[];
    private static SpriteLoader background;
    private int currentState;


    // Constructor
    public MenuStateViewer() throws IOException {
        currentState = 0;
        menuStates = new SpriteLoader[3];
        menuStates[0] = new SpriteLoader("/sprites/menu_states/play_button.png");
        menuStates[1] = new SpriteLoader("/sprites/menu_states/credits_button.png");
        menuStates[2] = new SpriteLoader("/sprites/menu_states/exit_button.png");
        background = new SpriteLoader("/sprites/menu_states/menu_stripped.png");
    }


    // Setters
    public void setState(int state) throws ResourceException {
        if (state > 2) {
            throw new ResourceException("There are only " + menuStates.length + "menus.");
        } else {
            currentState = state;
        }
    }


    // Class Methods
    public void drawBackground(TextGraphics graphics, Position position) {
        background.draw(graphics, position);
    }

    public void draw(TextGraphics graphics, Position position) {
        menuStates[currentState].draw(graphics, position);
    }
}
