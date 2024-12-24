package poppuyo.viewer;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import poppuyo.utils.custom_exceptions.ResourceException;
import poppuyo.utils.puyoutils.Position;
import poppuyo.viewer.loader.SpriteLoader;

import java.io.IOException;
import java.lang.reflect.Field;

class MenuStateViewerTest {
    @Mock private TextGraphics mockGraphics;
    @Mock private Position mockPosition;
    @Mock private SpriteLoader mockMenuState0;
    @Mock private SpriteLoader mockMenuState1;
    @Mock private SpriteLoader mockMenuState2;
    @Mock private SpriteLoader mockBackground;
    private MenuStateViewer menuStateViewer;

    @BeforeEach
    void setUp() throws IOException, NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);
        menuStateViewer = new MenuStateViewer();

        // Set the static SpriteLoader fields using reflection
        setStaticField("menuStates", new SpriteLoader[] { mockMenuState0, mockMenuState1, mockMenuState2 });
        setStaticField("background", mockBackground);
    }

    private void setStaticField(String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = MenuStateViewer.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(null, value);
    }

    @Test
    public void drawBackground() {
        menuStateViewer.drawBackground(mockGraphics, mockPosition);
        Mockito.verify(mockBackground).draw(mockGraphics, mockPosition);
    }

    @Test
    public void drawCurrentMenu() {
        menuStateViewer.draw(mockGraphics, mockPosition);
        Mockito.verify(mockMenuState0).draw(mockGraphics, mockPosition);
    }

    @Test
    public void setState() throws ResourceException {
        menuStateViewer.setState(1);
        menuStateViewer.draw(mockGraphics, mockPosition);
        Mockito.verify(mockMenuState1).draw(mockGraphics, mockPosition);
    }
}

