package poppuyo.viewer;

import com.googlecode.lanterna.graphics.TextGraphics;
import poppuyo.utils.puyoutils.Position;
import poppuyo.viewer.loader.SpriteLoader;

import java.io.IOException;

public class NextPuyoViewer implements Viewer {
    // Attributes
    private final SpriteLoader nextPuyoLoader;
    private final PuyoViewer firstPuyoViewer;
    private final PuyoViewer secondPuyoViewer;


    // Constructor
    public NextPuyoViewer(PuyoViewer firstPuyo, PuyoViewer secondPuyo) throws IOException {
        this.nextPuyoLoader = new SpriteLoader("/sprites/background/next_grid.png");
        this.firstPuyoViewer = firstPuyo;
        this.secondPuyoViewer = secondPuyo;
    }


    // Getter
    public SpriteLoader getNextPuyodLoader() { return nextPuyoLoader; }


    public void draw(TextGraphics graphics, Position corner) {
        nextPuyoLoader.draw(graphics, corner);
        firstPuyoViewer.draw(graphics, corner);
        secondPuyoViewer.draw(graphics, new Position(corner.getX(), corner.getY() + 32));
    }
}
