package gamestates;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import utils.puyoutils.Position;

import java.io.IOException;

public interface StateMethods {
    public void processKey(KeyStroke key);
    public void draw(TextGraphics textGraphics, Position position) throws IOException;
}
