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

class CreditsStateViewerTest {
    @Mock private TextGraphics mockGraphics;
    @Mock private Position mockPosition;
    @Mock private SpriteLoader mockCreditsState;
    private CreditsStateViewer creditsStateViewer;

    @BeforeEach
    void setUp() throws IOException, NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);

        creditsStateViewer = new CreditsStateViewer();

        // Inject the mock SpriteLoader into the CreditsStateViewer instance using reflection
        setPrivateField(creditsStateViewer, "creditsState", mockCreditsState);
    }

    private void setPrivateField(Object target, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    @Test
    public void constructor() throws IOException {
        creditsStateViewer = new CreditsStateViewer();
        Assertions.assertNotNull(creditsStateViewer);
    }

    @Test
    public void draw() {
        creditsStateViewer.draw(mockGraphics, mockPosition);
        Mockito.verify(mockCreditsState).draw(mockGraphics, mockPosition);
    }

}