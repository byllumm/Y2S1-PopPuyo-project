package viewer;

import com.googlecode.lanterna.graphics.TextGraphics;
import utils.puyoutils.Position;
import viewer.loader.SpriteLoader;

import java.io.IOException;

public class GridViewer implements Viewer {
    // Attributes
    private SpriteLoader gridLoader;
    private SpriteLoader beatoLoader;


    // Constructor
    public GridViewer() throws IOException {
        this.gridLoader = new SpriteLoader("/sprites/background/gamegrid_only.png");
        // ADD THE ALTERNATIVE BEATRICE DRAWING FOR WHEN A CHAIN IS MADE!
        //this.beatoLoader = new SpriteLoader("/sprites/background/beato.png");
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