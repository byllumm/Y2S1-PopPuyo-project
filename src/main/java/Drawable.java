import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

// Interface for elements that can be drawn.
public interface Drawable {
    public void draw(TextGraphics graphics) throws IOException;
}
