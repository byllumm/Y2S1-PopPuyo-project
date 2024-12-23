package poppuyo.viewer.loader;

import com.googlecode.lanterna.graphics.TextGraphics;
import poppuyo.utils.puyoutils.Position;
import poppuyo.viewer.Viewer;
import poppuyo.viewer.rendering.PixelConverter;
import poppuyo.viewer.rendering.Renderer;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteLoader implements Viewer {
    // Attributes
    private BufferedImage sprite;
    private int scalingFactor = 1;      // By default, scaling is set to 1
    private String[][] pixelMatrix;     // Each element of the matrix will have the hex code of the pixel. null if transparent.


    // Constructors
    public SpriteLoader(String filePath) throws IOException {
        loadImage(filePath);
    }

    public SpriteLoader(String filePath, int scalingFactor) throws IOException {
        loadImage(filePath);
        this.scalingFactor = scalingFactor;
    }

    // Helper method to load the image, convert it to pixels and prepare it for rendering
    private void loadImage(String filePath) throws IOException {
        // Load image
        sprite = ImageLoader.loadImage(filePath);

        // Convert the image to pixel matrix
        pixelMatrix = PixelConverter.convertToPixels(sprite);
    }


    public void draw(TextGraphics graphics, Position corner) {
        Renderer.render(graphics, pixelMatrix, corner, scalingFactor);
    }
}
