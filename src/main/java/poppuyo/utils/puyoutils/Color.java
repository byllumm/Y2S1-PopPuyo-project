package poppuyo.utils.puyoutils;

import java.util.List;
import java.util.Random;

public class Color {
    // Attributes
    private static final List<String> colors = List.of("blue", "red", "yellow", "purple", "green"); // Puyos come in 5 colors: green, yellow, red, blue, purple.


    // Class Methods
    public static String getRandomColor() {
        Random rand = new Random();
        int index = rand.nextInt(colors.size());
        return colors.get(index);
    }
}
