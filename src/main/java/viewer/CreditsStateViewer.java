package viewer;

import com.googlecode.lanterna.graphics.TextGraphics;
import utils.puyoutils.Position;

import java.io.IOException;

public class CreditsStateViewer implements Viewer{
    private static SpriteLoader creditsState;
    private int currentState;

    public CreditsStateViewer() throws IOException {
        creditsState = new SpriteLoader("/sprites/credits/credits.png");
    }
    public void draw(TextGraphics graphics, Position position) {
        creditsState.draw(graphics, position);
    }
}
