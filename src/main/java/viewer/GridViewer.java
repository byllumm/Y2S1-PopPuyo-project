package viewer;

import com.googlecode.lanterna.graphics.TextGraphics;
import utils.puyoutils.Position;

import java.io.IOException;

public class GridViewer implements Viewer {
    // Attributes
    private SpriteLoader gridLoader;


    // Constructor
    public GridViewer() throws IOException {
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


    // Methods
    public void draw(TextGraphics graphics, Position corner) {
        gridLoader.draw(graphics, corner);
    }
}