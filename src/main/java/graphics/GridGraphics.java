package graphics;

import com.googlecode.lanterna.graphics.TextGraphics;
import utils.puyoutils.Position;

import java.io.IOException;

public class GridGraphics implements Drawable {
    // Attributes
    private SpriteLoader gridLoader;


    // Constructor
    public GridGraphics() throws IOException {
        this.gridLoader = new SpriteLoader("/sprites/background/gamegrid_only.png");
    }


    // Getters
    public SpriteLoader getGridLoader() {
        return gridLoader;
    }


    // Setters
    public void setGridLoader(SpriteLoader gridLoader) {
        this.gridLoader = gridLoader;
    }


    public void draw(TextGraphics graphics, Position corner) {
        gridLoader.draw(graphics, corner);

    }
}