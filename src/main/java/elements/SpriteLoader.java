// ! HIGHLY EXPERIMENTAL CLASS

package elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import custom_exceptions.ResourceException;
import elements.puyo_utils.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class SpriteLoader implements Drawable {
    private BufferedImage sprite;
    private int width;
    private int height;
    private int scalingFactor = 1;      // By default, scaling is set to 1
    private String[][] pixelMatrix;     // Each element of the matrix will have the hex code of the pixel. null if transparent.

    public SpriteLoader(URL resourceURL) throws IOException {
        loadImage(resourceURL);
        spriteToPixels();
    }

    public SpriteLoader(URL resourceURL, int scalingFactor) throws IOException {
        loadImage(resourceURL);
        spriteToPixels();
        this.scalingFactor = scalingFactor;
    }

    public void loadImage(URL resourceURL) throws IOException {
        sprite = ImageIO.read(resourceURL);
        width = sprite.getWidth();
        height = sprite.getHeight();
        pixelMatrix = new String[width][height];
    }

    // Converts a file path into a resourceURL so that ImageIO can take the resource image
    public static URL pathToURL(String resourcePath) throws ResourceException {
        URL resourceURL = SpriteLoader.class.getResource(resourcePath);
        if (resourceURL == null) {
            throw new ResourceException("Resource not found: " + resourcePath);
        }
        return resourceURL;
    }

    private void spriteToPixels() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = sprite.getRGB(x, y);
                String hexString = Integer.toHexString(rgb).toUpperCase(); // Format: [Alpha][Red][Green][Blue]

                // Check if fully transparent
                int alphaBits = Integer.parseInt(hexString.substring(0,2), 16);
                if (alphaBits < 128) {
                    pixelMatrix[x][y] = null;
                } else {
                    pixelMatrix[x][y] = "#" + hexString.substring(2);
                }
            }
        }
    }

    // SCALING FEATURE IS UNTESTED!
    public void draw(TextGraphics graphics, Position corner) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (pixelMatrix[x][y] != null) {
                    graphics.setBackgroundColor(TextColor.Factory.fromString(pixelMatrix[x][y]));
                    graphics.enableModifiers(SGR.BOLD);

                    // Apply scaling factor
                    for (int scaledX = 0; scaledX < scalingFactor; scaledX++) {
                        for (int scaledY = 0; scaledY < scalingFactor; scaledY++) {
                            graphics.putString(new TerminalPosition(corner.getX() + (x * scalingFactor) + scaledX,
                                                                corner.getY() + (y * scalingFactor) + scaledY), " ");
                        }
                    }
                }
            }
        }
    }
}
