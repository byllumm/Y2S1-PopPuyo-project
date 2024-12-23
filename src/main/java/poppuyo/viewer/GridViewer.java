package poppuyo.viewer;

import com.googlecode.lanterna.graphics.TextGraphics;
import poppuyo.utils.puyoutils.Position;
import poppuyo.viewer.loader.SpriteLoader;

import java.io.IOException;

public class GridViewer implements Viewer {
    // Attributes
    private SpriteLoader gridLoader;
    private SpriteLoader beatoLoader;


    // Constructor
    public GridViewer() throws IOException {
        this.gridLoader = new SpriteLoader("/sprites/background/gamegrid_only.png");
    }


    // Getters
    public SpriteLoader getGridLoader() { return gridLoader; }


    // Setters
    public void setGridLoader(SpriteLoader gridLoader) { this.gridLoader = gridLoader; }


    // Class Methods
    public void draw(TextGraphics graphics, Position corner) { gridLoader.draw(graphics, corner); }
}