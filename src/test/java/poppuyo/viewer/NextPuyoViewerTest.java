package poppuyo.viewer;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import poppuyo.utils.puyoutils.Position;
import poppuyo.viewer.loader.SpriteLoader;

import java.io.IOException;
import java.lang.reflect.Field;

class NextPuyoViewerTest {
    @Mock private TextGraphics mockGraphics;
    @Mock private Position mockPosition;
    @Mock private SpriteLoader mockNextPuyoLoader;
    @Mock private PuyoViewer mockFirstPuyoViewer;
    @Mock private PuyoViewer mockSecondPuyoViewer;
    private NextPuyoViewer nextPuyoViewer;

    @BeforeEach
    void setUp() throws IOException, NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);
        nextPuyoViewer = new NextPuyoViewer(mockFirstPuyoViewer, mockSecondPuyoViewer);

        Field loaderField = NextPuyoViewer.class.getDeclaredField("nextPuyoLoader");
        loaderField.setAccessible(true);
        loaderField.set(nextPuyoViewer, mockNextPuyoLoader);
    }

    @Test
    public void drawNextPuyoBackground() {
        nextPuyoViewer.draw(mockGraphics, mockPosition);
        Mockito.verify(mockNextPuyoLoader).draw(mockGraphics, mockPosition);
    }

    @Test
    public void drawFirstPuyo() {
        nextPuyoViewer.draw(mockGraphics, mockPosition);
        Mockito.verify(mockFirstPuyoViewer).draw(mockGraphics, mockPosition);
    }

    @Test
    public void drawSecondPuyo() {
        nextPuyoViewer.draw(mockGraphics, mockPosition);
        Mockito.verify(mockSecondPuyoViewer).draw(mockGraphics, new Position(mockPosition.getX(), mockPosition.getY() + 32));
    }
}
