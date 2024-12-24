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

class DigitDisplayViewerTest {
    @Mock private TextGraphics mockGraphics;
    @Mock private Position mockPosition;
    @Mock private SpriteLoader mockDigitLoader;
    private DigitDisplayViewer digitDisplayViewer;

    @BeforeEach
    void setUp() throws IOException, NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);

        digitDisplayViewer = new DigitDisplayViewer();

        // Inject a mock SpriteLoader for the current digit using reflections
        setStaticPrivateField(DigitDisplayViewer.class, "digitLoaders", new SpriteLoader[]{mockDigitLoader, mockDigitLoader, mockDigitLoader, mockDigitLoader, mockDigitLoader, mockDigitLoader, mockDigitLoader, mockDigitLoader, mockDigitLoader, mockDigitLoader});
    }

    private void setStaticPrivateField(Class<?> targetClass, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        java.lang.reflect.Field field = targetClass.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(null, value);
    }

    @Test
    public void constructor() throws IOException {
        digitDisplayViewer = new DigitDisplayViewer();
        Assertions.assertNotNull(digitDisplayViewer);
    }

    @Test
    public void draw() {
        digitDisplayViewer.setCurrentDigit(3);
        digitDisplayViewer.draw(mockGraphics, mockPosition);

        Mockito.verify(mockDigitLoader).draw(mockGraphics, mockPosition);
    }
}
