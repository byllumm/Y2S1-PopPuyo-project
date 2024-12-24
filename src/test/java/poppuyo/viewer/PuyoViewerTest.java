package poppuyo.viewer;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import poppuyo.utils.puyoutils.Position;
import poppuyo.viewer.loader.SpriteLoader;

import java.io.IOException;

class PuyoViewerTest {
    @Mock private TextGraphics mockGraphics;
    @Mock private SpriteLoader mockSpriteLoader;
    private PuyoViewer viewer;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        viewer = new PuyoViewer("red", 0b0001);
    }

    @Test
    void constructor() throws IOException {
        Assertions.assertNotNull(viewer);
        Assertions.assertNotNull(viewer.getPuyoLoader());
    }

    @Test
    void constructorInvalidColor() {
        Assertions.assertThrows(IOException.class, () -> new PuyoViewer("Witch Gold", 0b0001));
    }

    @Test
    void constructorInvalidMode() {
        Assertions.assertThrows(IOException.class, () -> new PuyoViewer("red", -1));
    }

    @Test
    void getPuyoLoader() throws IOException {
        Assertions.assertNotNull(viewer.getPuyoLoader());
    }

    @Test
    void setPuyoLoader() throws IOException {
        viewer.setPuyoLoader(mockSpriteLoader);
        Assertions.assertEquals(mockSpriteLoader, viewer.getPuyoLoader());
    }

    @Test
    void draw() throws IOException {
        viewer.setPuyoLoader(mockSpriteLoader);
        Position testPosition = new Position(10, 10);
        viewer.draw(mockGraphics, testPosition);

        Mockito.verify(mockSpriteLoader).draw(mockGraphics, testPosition);
    }
}