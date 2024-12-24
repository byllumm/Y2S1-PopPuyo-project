package poppuyo.viewer.loader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import poppuyo.utils.custom_exceptions.ResourceException;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

class ImageLoaderTest {
    @Test
    public void loadImageWithURL() throws IOException {
        URL resourceURL = getClass().getResource("/sprites/test-sprite1.png");
        BufferedImage result = ImageLoader.loadImage(resourceURL);

        Assertions.assertNotNull(result);
    }

    @Test
    public void loadImageWithFilePath() throws IOException {
        String filePath = "/sprites/test-sprite1.png";
        URL resourceURL = getClass().getResource(filePath);
        BufferedImage result = ImageLoader.loadImage(filePath);

        Assertions.assertNotNull(result);
    }

    @Test
    public void loadImageWithInvalidFilePath() {
        String invalidFilePath = "On the tenth twilight, at journey's end, you shall attain to the power of the Golden Land's treasures";

        Assertions.assertThrows(ResourceException.class, () -> {
            ImageLoader.loadImage(invalidFilePath);
        });
    }
}
