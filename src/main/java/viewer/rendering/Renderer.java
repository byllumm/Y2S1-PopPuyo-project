package viewer.rendering;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import utils.puyoutils.Position;

public class Renderer {

    public static void render(TextGraphics graphics, String[][] pixelMatrix, Position corner, int scalingFactor) {
        int width = pixelMatrix.length;
        int height = pixelMatrix[0].length;

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                if(pixelMatrix[x][y] != null) {
                    graphics.setBackgroundColor(com.googlecode.lanterna.TextColor.Factory.fromString(pixelMatrix[x][y]));
                    graphics.enableModifiers(SGR.BOLD);

                    // Apply scaling factor
                    for(int scaledX = 0; scaledX < scalingFactor; scaledX++) {
                        for(int scaledY = 0; scaledY < scalingFactor; scaledY++) {
                            if(corner != null) {
                                graphics.putString(new TerminalPosition(corner.getX() + (x * scalingFactor) + scaledX, corner.getY() + (y * scalingFactor) + scaledY), " ");
                            }
                            else {
                                graphics.putString(new TerminalPosition((x * scalingFactor) + scaledX, (y * scalingFactor) + scaledY), " ");
                            }
                        }
                    }
                }
            }
        }
    }
}
