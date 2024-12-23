package poppuyo.viewer;

import com.googlecode.lanterna.graphics.TextGraphics;
import poppuyo.utils.puyoutils.Position;
import poppuyo.viewer.loader.SpriteLoader;
import poppuyo.viewer.puyosprites.PuyoSpriteRepository;

import java.io.IOException;
import java.util.HashMap;

public class PuyoViewer implements Viewer {
    // Attributes
    private SpriteLoader puyoLoader;
    // Closest thing I could find to the C++ <utility> Pair...
    private record Pair<String, Integer>(String first, Integer second) {}
    private static HashMap<Pair<String, Integer>, String> spriteMap = new HashMap<>();

    // Constructor
    public PuyoViewer(String color, int mode) throws IOException {
        String spriteFilepath = PuyoSpriteRepository.getSpriteFilePath(color, mode);

        if(spriteFilepath != null){
            // Puyos are 16x16, must be rendered as 32x32
            puyoLoader = new SpriteLoader(spriteFilepath, 2);
        }
        else {
            throw new IOException("Sprite not found for color: " + color + " and mode: " + mode);
        }
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