package poppuyo.viewer.rendering;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class PixelConverterTest {
    @Mock private BufferedImage mockBufferedImage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void convertToPixels() {
        int width = 2;
        int height = 2;

        Mockito.when(mockBufferedImage.getWidth()).thenReturn(width);
        Mockito.when(mockBufferedImage.getHeight()).thenReturn(height);
        Mockito.when(mockBufferedImage.getRGB(0, 0)).thenReturn(0xFFFFFFFF); // White pixel
        Mockito.when(mockBufferedImage.getRGB(0, 1)).thenReturn(0x80FFFFFF); // Semi-transparent pixel
        Mockito.when(mockBufferedImage.getRGB(1, 0)).thenReturn(0x00000000); // Fully transparent pixel
        Mockito.when(mockBufferedImage.getRGB(1, 1)).thenReturn(0xFFFF0000); // Red pixel

        String[][] pixelMatrix = PixelConverter.convertToPixels(mockBufferedImage);

        assertEquals("#FFFFFF", pixelMatrix[0][0]);
        assertEquals("#FFFFFF", pixelMatrix[0][1]);
        assertNull(pixelMatrix[1][0]);
        assertEquals("#FF0000", pixelMatrix[1][1]);
    }
}

