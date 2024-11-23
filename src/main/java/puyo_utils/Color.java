package puyo_utils;

import custom_exceptions.ColorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Color {
    // Puyos come in 5 colors: green, yellow, red, blue, purple.
    // Lanterna reads colors as HEX codes, so for simplicity we only pass the color in string for and translate it to hex code.
    String colorHex;
    public static HashMap<String, String> colorMap;

    // Call only once
    static {
        colorMap = new HashMap<>();
        colorMap.put("blue", "#0080C0");
        colorMap.put("green", "#60C060");
        colorMap.put("yellow", "#E0E040");
        colorMap.put("red", "#802000");
        colorMap.put("purple", "#A040E0");
    }

    public Color(String color) {
        if (!colorMap.containsKey(color)) {
            throw new ColorException("puyo.Color " + color + " is not supported!");
        }
        colorHex = colorMap.get(color);
    }

    public String getColor() {
        return colorHex;
    }

    public static Color getRandomColor(){
        List<String> keys = new ArrayList<>(colorMap.keySet());
        Random random = new Random();
        String randomColorName = keys.get(random.nextInt(keys.size()));
        return new Color(randomColorName);
    }
}
