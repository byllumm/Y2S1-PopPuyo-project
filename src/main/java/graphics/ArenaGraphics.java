package graphics;

import com.googlecode.lanterna.graphics.TextGraphics;
import utils.puyoutils.Position;

import java.io.IOException;

public class ArenaGraphics implements Drawable {
    // Attributes
    private final SpriteLoader backgroundLoader;


    // Constructor
    public ArenaGraphics() throws IOException {
        this.backgroundLoader = new SpriteLoader("/sprites/background/background_static_stripped.png");
    }


    // Getter
    public SpriteLoader getBackgroundLoader() {
        return backgroundLoader;
    }


    public void draw(TextGraphics graphics, Position corner) {
        backgroundLoader.draw(graphics, corner);
    }
}
