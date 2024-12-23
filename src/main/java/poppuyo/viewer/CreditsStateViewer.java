package poppuyo.viewer;

import com.googlecode.lanterna.graphics.TextGraphics;
import poppuyo.utils.puyoutils.Position;
import poppuyo.viewer.loader.SpriteLoader;

import java.io.IOException;

public class CreditsStateViewer implements Viewer {
    // Attributes
    private static SpriteLoader creditsState;
    private int currentState;


    // Constructor
    public CreditsStateViewer() throws IOException {
        creditsState = new SpriteLoader("/sprites/credits/credits.png");
    }


    // Class Methods
    public void draw(TextGraphics graphics, Position position) {
        creditsState.draw(graphics, position);
    }
}
