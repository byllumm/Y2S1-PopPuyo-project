

package viewer;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import utils.custom_exceptions.ResourceException;
import utils.puyoutils.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class SpriteLoader implements Viewer {
    // Attributes
    private BufferedImage sprite;
    private int width;
    private int height;
    private int scalingFactor = 1;      // By default, scaling is set to 1
    private String[][] pixelMatrix;     // Each element of the matrix will have the hex code of the pixel. null if transparent.


    // Constructors
    public SpriteLoader(String filePath) throws IOException {
        loadImage(pathToURL(filePath));
        spriteToPixels();
    }

    public SpriteLoader(String filePath, int scalingFactor) throws IOException {
        loadImage(pathToURL(filePath));
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
    private URL pathToURL(String resourcePath) throws ResourceException {
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
                String hexString = String.format("%08X", rgb); // Ensures 8-digit representation, if its under 8 digits it gives errors.

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

    public void draw(TextGraphics graphics, Position corner) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (pixelMatrix[x][y] != null) {
                    graphics.setBackgroundColor(TextColor.Factory.fromString(pixelMatrix[x][y]));
                    graphics.enableModifiers(SGR.BOLD);

                    // Apply scaling factor
                    for (int scaledX = 0; scaledX < scalingFactor; scaledX++) {
                        for (int scaledY = 0; scaledY < scalingFactor; scaledY++) {
                            if (corner != null) {
                                graphics.putString(new TerminalPosition(corner.getX() + (x * scalingFactor) + scaledX,
                                        corner.getY() + (y * scalingFactor) + scaledY), " ");
                            } else {
                                graphics.putString(new TerminalPosition((x * scalingFactor) + scaledX, (y * scalingFactor) + scaledY), " ");
                            }

                        }
                    }
                }
            }
        }
    }
}
