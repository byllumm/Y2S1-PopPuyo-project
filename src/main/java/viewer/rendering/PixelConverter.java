package viewer.rendering;

import java.awt.image.BufferedImage;

public class PixelConverter {

    public static String[][] convertToPixels(BufferedImage sprite) {
        int width = sprite.getWidth();
        int height = sprite.getHeight();
        String[][] pixelMatrix = new String[width][height];

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                int rgb = sprite.getRGB(x, y);
                String hexString = String.format("%08X", rgb); // Ensures 8-digit representation

                // Check if fully transparent
                int alphaBits = Integer.parseInt(hexString.substring(0, 2), 16);
                if(alphaBits < 128) {
                    pixelMatrix[x][y] = null;
                }
                else {
                    pixelMatrix[x][y] = "#" + hexString.substring(2);
                }
            }
        }
        return pixelMatrix;
    }
}
