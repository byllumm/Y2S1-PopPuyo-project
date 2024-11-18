import java.util.HashMap;

public class Color {
    // Puyos come in 5 colors: green, yellow, red, blue, purple.
    // Lanterna reads colors as HEX codes, so for simplicity we only pass the color in string for and translate it to hex code.
    String colorHex;
    private static HashMap<String, String> colorMap;

    // Call only once
    static {
        colorMap = new HashMap<String, String>();
        colorMap.put("blue", "#0000FF");
        colorMap.put("green", "#00FF00");
        colorMap.put("yellow", "#FFFF00");
        colorMap.put("red", "#FF0000");
        colorMap.put("purple", "#8A00C2");
    }

    public Color(String color) {
        if (!colorMap.containsKey(color)) {
            throw new ColorException("Color " + color + " is not supported!");
        }
        colorHex = colorMap.get(color);
    }

    public String getColor() {
        return colorHex;
    }
}
