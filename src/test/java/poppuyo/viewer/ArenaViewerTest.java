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
import java.lang.reflect.Field;

class ArenaViewerTest {
    @Mock private TextGraphics mockGraphics;
    @Mock private Position mockCorner;
    @Mock private SpriteLoader mockBackgroundLoader;
    private ArenaViewer arenaViewer;

    @BeforeEach
    void setUp() throws IOException, NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);

        arenaViewer = new ArenaViewer();

        // Inject the mock SpriteLoader into the ArenaViewer instance using reflection
        setPrivateField(arenaViewer, "backgroundLoader", mockBackgroundLoader);
    }

    private void setPrivateField(Object target, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    @Test
    public void constructor() throws IOException {
        arenaViewer = new ArenaViewer();
        Assertions.assertNotNull(arenaViewer.getBackgroundLoader());
    }

    @Test
    public void draw() throws IOException {
        arenaViewer.draw(mockGraphics, mockCorner);
        Mockito.verify(mockBackgroundLoader).draw(mockGraphics, mockCorner);
    }
}

