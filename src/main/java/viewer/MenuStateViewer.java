package viewer;

import com.googlecode.lanterna.graphics.TextGraphics;
import utils.custom_exceptions.ResourceException;
import utils.puyoutils.Position;

import java.io.IOException;

public class MenuStateViewer implements Viewer {
    private static SpriteLoader menuStates[];
    private int currentState;

    public MenuStateViewer() throws IOException {
        currentState = 0;
        menuStates = new SpriteLoader[3];
        menuStates[0] = new SpriteLoader("/sprites/menu_states/menu_play.png");
        menuStates[1] = new SpriteLoader("/sprites/menu_states/menu_credits.png");
        menuStates[2] = new SpriteLoader("/sprites/menu_states/menu_exit.png");
    }

    public void setState(int state) throws ResourceException {
        if (state > 2) {
            throw new ResourceException("There are only " + menuStates.length + "menus.");
        } else {
            currentState = state;
        }
    }

    public void draw(TextGraphics graphics, Position position) {
        menuStates[currentState].draw(graphics, position);
    }
}
