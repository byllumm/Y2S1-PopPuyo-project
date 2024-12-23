package poppuyo.viewer.loader;

import poppuyo.utils.custom_exceptions.ResourceException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageLoader {

    public static BufferedImage loadImage(URL resourceURL) throws IOException {
        return ImageIO.read(resourceURL);
    }

    public static BufferedImage loadImage(String filePath) throws ResourceException, IOException {
        URL resourceURL = ImageLoader.class.getResource(filePath);
        if (resourceURL == null) {
            throw new ResourceException("Resource not found: " + filePath);
        }
        return loadImage(resourceURL);
    }
}