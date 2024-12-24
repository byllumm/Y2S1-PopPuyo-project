package poppuyo.utils.puyoutils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class ColorTest {

    @Test
    public void getRandomColorReturnsValidColor() {
        String color = Color.getRandomColor();

        // Assert
        Assertions.assertNotNull(color);
        Assertions.assertTrue(Color.colors.contains(color));
    }

    @Test
    public void getRandomColorIsRandom() {
        Set<String> returnedColors = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            String color = Color.getRandomColor();
            returnedColors.add(color);
        }

        Assertions.assertTrue(returnedColors.size() > 1);
    }
}