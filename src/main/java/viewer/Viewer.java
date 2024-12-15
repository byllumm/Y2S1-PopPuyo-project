package viewer;

import com.googlecode.lanterna.graphics.TextGraphics;
import utils.puyoutils.Position;

import java.io.IOException;

// Interface for elements that can be drawn.
public interface Viewer {
    // If corner and/or dimensions are passed as null, they are not being used.
    public void draw(TextGraphics graphics, Position corner) throws IOException;
}
