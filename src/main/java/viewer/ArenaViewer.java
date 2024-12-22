package viewer;

import com.googlecode.lanterna.graphics.TextGraphics;
import utils.puyoutils.Position;
import viewer.loader.SpriteLoader;

import java.io.IOException;

public class ArenaViewer implements Viewer {
    // Attributes
    private final SpriteLoader backgroundLoader;


    // Constructor
    public ArenaViewer() throws IOException {
        this.backgroundLoader = new SpriteLoader("/sprites/background/background_static_stripped.png");
    }


    // Getter
    public SpriteLoader getBackgroundLoader() { return backgroundLoader; }


    // Class Methods
    public void draw(TextGraphics graphics, Position corner) { backgroundLoader.draw(graphics, corner); }
}
