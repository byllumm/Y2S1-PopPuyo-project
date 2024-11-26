package graphics;

import com.googlecode.lanterna.graphics.TextGraphics;
import utils.puyoutils.Position;

import java.io.IOException;

public class NextPuyoGraphics implements Drawable {
    // Attributes
    private final SpriteLoader nextPuyoLoader;
    private final PuyoGraphics firstPuyoGraphic;
    private final PuyoGraphics secondPuyoGraphic;

    // Constructor
    public NextPuyoGraphics(PuyoGraphics firstPuyo, PuyoGraphics secondPuyo) throws IOException {
        this.nextPuyoLoader = new SpriteLoader("/sprites/background/next_grid.png");
        this.firstPuyoGraphic = firstPuyo;
        this.secondPuyoGraphic = secondPuyo;
    }


    // Getter
    public SpriteLoader getNextPuyodLoader() {
        return nextPuyoLoader;
    }


    public void draw(TextGraphics graphics, Position corner) {
        nextPuyoLoader.draw(graphics, corner);
        firstPuyoGraphic.draw(graphics, corner);
        secondPuyoGraphic.draw(graphics, new Position(corner.getX(), corner.getY() + 32));
    }
}
