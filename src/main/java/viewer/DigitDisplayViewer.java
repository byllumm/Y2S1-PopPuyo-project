package viewer;

import com.googlecode.lanterna.graphics.TextGraphics;
import utils.puyoutils.Position;
import viewer.loader.SpriteLoader;

import java.io.IOException;

// All-purpose Viewer class for objects that are represented in digits.
// May turn this into an abstract class later on?
public class DigitDisplayViewer implements Viewer {
    // Attributes
    private static final SpriteLoader[] digitLoaders = new SpriteLoader[10];
    private int currentDigit;

    // Constructor
    public DigitDisplayViewer() throws IOException {
        currentDigit = 0;
    }

    // initialise static digit spriteLoaders
    static {
        try {
            digitLoaders[0] = new SpriteLoader("/sprites/digits/digit_0.png");
            digitLoaders[1] = new SpriteLoader("/sprites/digits/digit_1.png");
            digitLoaders[2] = new SpriteLoader("/sprites/digits/digit_2.png");
            digitLoaders[3] = new SpriteLoader("/sprites/digits/digit_3.png");
            digitLoaders[4] = new SpriteLoader("/sprites/digits/digit_4.png");
            digitLoaders[5] = new SpriteLoader("/sprites/digits/digit_5.png");
            digitLoaders[6] = new SpriteLoader("/sprites/digits/digit_6.png");
            digitLoaders[7] = new SpriteLoader("/sprites/digits/digit_7.png");
            digitLoaders[8] = new SpriteLoader("/sprites/digits/digit_8.png");
            digitLoaders[9] = new SpriteLoader("/sprites/digits/digit_9.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // Setter
    public void setCurrentDigit(int digit) {
        currentDigit = digit;
    }


    // Methods
    public void draw(TextGraphics graphics, Position corner) {
        digitLoaders[currentDigit].draw(graphics, corner);
    }
}