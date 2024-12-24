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

class GridViewerTest {
    @Mock private TextGraphics mockGraphics;
    @Mock private Position mockPosition;
    @Mock private SpriteLoader mockGridLoader;
    private GridViewer gridViewer;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        gridViewer = new GridViewer();
        gridViewer.setGridLoader(mockGridLoader);
    }

    @Test
    public void setGridLoader() {
        SpriteLoader newMockLoader = Mockito.mock(SpriteLoader.class);
        gridViewer.setGridLoader(newMockLoader);
        gridViewer.draw(mockGraphics, mockPosition);

        Mockito.verify(newMockLoader).draw(mockGraphics, mockPosition);
    }

    @Test
    public void drawCallsGridLoaderDraw() {
        gridViewer.draw(mockGraphics, mockPosition);
        Mockito.verify(mockGridLoader).draw(mockGraphics, mockPosition);
    }
}
