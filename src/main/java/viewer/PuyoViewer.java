package viewer;

import com.googlecode.lanterna.graphics.TextGraphics;
import utils.puyoutils.Position;

import java.io.IOException;

public class PuyoViewer implements Viewer {
    // Attributes
    private SpriteLoader puyoLoader;


    // Constructor
    public PuyoViewer(String color) throws IOException {
        String spriteFilepath = switch (color) {
            case "blue" -> "/sprites/puyo/blue/blue_NORMAL.png";
            case "green" -> "/sprites/puyo/green/green_NORMAL.png";
            case "red" -> "/sprites/puyo/red/red_NORMAL.png";
            case "yellow" -> "/sprites/puyo/yellow/yellow_NORMAL.png";
            case "purple" -> "/sprites/puyo/purple/purple_NORMAL.png";
            default -> "";
        };
        // Puyos are 16x16, must be rendered as 32x32
        puyoLoader = new SpriteLoader(spriteFilepath, 2);
    }


    // Getter
    public SpriteLoader getPuyoLoader() {
        return puyoLoader;
    }


    // Setter
    public void setPuyoLoader(SpriteLoader puyoLoader) {
        this.puyoLoader = puyoLoader;
    }


    public void draw(TextGraphics graphics, Position corner) {
        puyoLoader.draw(graphics, corner);
    }
}