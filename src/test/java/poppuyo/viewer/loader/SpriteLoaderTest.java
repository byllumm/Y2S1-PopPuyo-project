package poppuyo.viewer.loader;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import poppuyo.utils.puyoutils.Position;
import poppuyo.viewer.rendering.Renderer;

import java.io.IOException;

class SpriteLoaderTest {
    @Mock private TextGraphics mockGraphics;
    @Mock private Renderer mockRenderer;
    private SpriteLoader spriteLoader;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        spriteLoader = new SpriteLoader("/sprites/test-sprite1.png");
    }

    @Test
    public void constructor() throws IOException {
        String filePath = "/sprites/test-sprite1.png";
        SpriteLoader loader = new SpriteLoader(filePath);

        Assertions.assertNotNull(loader);
    }

    @Test
    public void constructorWithScalingFactor() throws IOException {
        String filePath = "/sprites/test-sprite1.png";
        int scalingFactor = 2;
        SpriteLoader loader = new SpriteLoader(filePath, scalingFactor);

        Assertions.assertNotNull(loader);
        Assertions.assertEquals(scalingFactor, loader.scalingFactor);
    }

    @Test
    public void loadImage() throws IOException {
        String filePath = "/sprites/test-sprite1.png";
        spriteLoader = new SpriteLoader(filePath);

        Assertions.assertNotNull(spriteLoader.sprite);
    }

    @Test
    public void loadImageThrowsIOException() {
        String invalidFilePath = "Sleep peacefully, my beloved witch, Beatrice";

        Assertions.assertThrows(IOException.class, () -> {
            new SpriteLoader(invalidFilePath);
        });
    }

    @Test
    public void testDraw() {
        Position corner = new Position(0, 0);
        spriteLoader.draw(mockGraphics, corner);

        // 2x2 image
        int imageResolution = spriteLoader.pixelMatrix[0].length * spriteLoader.pixelMatrix[1].length;

        Assertions.assertNotNull(spriteLoader.pixelMatrix);
        Assertions.assertEquals(4, imageResolution);
    }
}
