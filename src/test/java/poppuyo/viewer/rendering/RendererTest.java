package poppuyo.viewer.rendering;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import poppuyo.utils.puyoutils.Position;

import static org.mockito.Mockito.*;

class RendererTest {
    @Mock private TextGraphics mockGraphics;
    @Mock private Position mockCorner;
    private String[][] pixelMatrix;
    private int scalingFactor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        scalingFactor = 2;
        pixelMatrix = new String[2][2];
        pixelMatrix[0][0] = "#FFFFFF"; // White pixel
        pixelMatrix[0][1] = "#000000"; // Black pixel
        pixelMatrix[1][0] = null;       // Transparent pixel (no color)
        pixelMatrix[1][1] = "#FF0000";  // Red pixel
    }

    @Test
    void renderWithScaling() {
        Mockito.when(mockCorner.getX()).thenReturn(5);
        Mockito.when(mockCorner.getY()).thenReturn(5);

        Renderer.render(mockGraphics, pixelMatrix, mockCorner, scalingFactor);

        Mockito.verify(mockGraphics).setBackgroundColor(com.googlecode.lanterna.TextColor.Factory.fromString("#FFFFFF"));
        Mockito. verify(mockGraphics).setBackgroundColor(com.googlecode.lanterna.TextColor.Factory.fromString("#000000"));
        Mockito.verify(mockGraphics).setBackgroundColor(com.googlecode.lanterna.TextColor.Factory.fromString("#FF0000"));

        // Expected 12 times
        // Explanation: original 2x2 image upscaled to 4x4. Only 3 pixels of the original image would be rendered, so
        //              only 12 will be rendered after upscaling, and not 16 as you might have believed.
        Mockito.verify(mockGraphics, times(12)).putString(Mockito.any(TerminalPosition.class), eq(" "));

        // Expected 3 times, 1 per pixel of the original image that gets rendered
        Mockito.verify(mockGraphics, times(3)).enableModifiers(SGR.BOLD);
    }

    @Test
    void testRenderWithoutScaling() {
        scalingFactor = 1;

        Renderer.render(mockGraphics, pixelMatrix, mockCorner, scalingFactor);

        verify(mockGraphics).setBackgroundColor(com.googlecode.lanterna.TextColor.Factory.fromString("#FFFFFF"));
        verify(mockGraphics).setBackgroundColor(com.googlecode.lanterna.TextColor.Factory.fromString("#000000"));
        verify(mockGraphics).setBackgroundColor(com.googlecode.lanterna.TextColor.Factory.fromString("#FF0000"));

        verify(mockGraphics, times(3)).putString(Mockito.any(TerminalPosition.class), eq(" "));
    }
}
