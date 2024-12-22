package viewer.puyosprites;

import java.util.HashMap;

public class PuyoSpriteRepository {
    private static final HashMap<PuyoSpriteKey, String> spriteMap = new HashMap<PuyoSpriteKey, String>();

    static {
        // All blue sprites
        spriteMap.put(new PuyoSpriteKey("blue", 0b0000), "/sprites/puyo/blue/blue_NORMAL.png");
        spriteMap.put(new PuyoSpriteKey("blue", 0b0001), "/sprites/puyo/blue/blue_ABOVE.png");
        spriteMap.put(new PuyoSpriteKey("blue", 0b0010), "/sprites/puyo/blue/blue_LEFT.png");
        spriteMap.put(new PuyoSpriteKey("blue", 0b0011), "/sprites/puyo/blue/blue_LEFT_ABOVE.png");
        spriteMap.put(new PuyoSpriteKey("blue", 0b0100), "/sprites/puyo/blue/blue_BELOW.png");
        spriteMap.put(new PuyoSpriteKey("blue", 0b0101), "/sprites/puyo/blue/blue_ABOVE_BELOW.png");
        spriteMap.put(new PuyoSpriteKey("blue", 0b0110), "/sprites/puyo/blue/blue_LEFT_BELOW.png");
        spriteMap.put(new PuyoSpriteKey("blue", 0b0111), "/sprites/puyo/blue/blue_LEFT_ABOVE_BELOW.png");
        spriteMap.put(new PuyoSpriteKey("blue", 0b1000), "/sprites/puyo/blue/blue_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("blue", 0b1001), "/sprites/puyo/blue/blue_ABOVE_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("blue", 0b1010), "/sprites/puyo/blue/blue_LEFT_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("blue", 0b1011), "/sprites/puyo/blue/blue_LEFT_ABOVE_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("blue", 0b1100), "/sprites/puyo/blue/blue_BELOW_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("blue", 0b1101), "/sprites/puyo/blue/blue_ABOVE_BELOW_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("blue", 0b1110), "/sprites/puyo/blue/blue_LEFT_BELOW_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("blue", 0b1111), "/sprites/puyo/blue/blue_LEFT_ABOVE_RIGHT_BELOW.png");

        // All green sprites
        spriteMap.put(new PuyoSpriteKey("green", 0b0000), "/sprites/puyo/green/green_NORMAL.png");
        spriteMap.put(new PuyoSpriteKey("green", 0b0001), "/sprites/puyo/green/green_ABOVE.png");
        spriteMap.put(new PuyoSpriteKey("green", 0b0010), "/sprites/puyo/green/green_LEFT.png");
        spriteMap.put(new PuyoSpriteKey("green", 0b0011), "/sprites/puyo/green/green_LEFT_ABOVE.png");
        spriteMap.put(new PuyoSpriteKey("green", 0b0100), "/sprites/puyo/green/green_BELOW.png");
        spriteMap.put(new PuyoSpriteKey("green", 0b0101), "/sprites/puyo/green/green_ABOVE_BELOW.png");
        spriteMap.put(new PuyoSpriteKey("green", 0b0110), "/sprites/puyo/green/green_LEFT_BELOW.png");
        spriteMap.put(new PuyoSpriteKey("green", 0b0111), "/sprites/puyo/green/green_LEFT_ABOVE_BELOW.png");
        spriteMap.put(new PuyoSpriteKey("green", 0b1000), "/sprites/puyo/green/green_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("green", 0b1001), "/sprites/puyo/green/green_ABOVE_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("green", 0b1010), "/sprites/puyo/green/green_LEFT_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("green", 0b1011), "/sprites/puyo/green/green_LEFT_ABOVE_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("green", 0b1100), "/sprites/puyo/green/green_BELOW_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("green", 0b1101), "/sprites/puyo/green/green_ABOVE_BELOW_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("green", 0b1110), "/sprites/puyo/green/green_LEFT_BELOW_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("green", 0b1111), "/sprites/puyo/green/green_LEFT_ABOVE_RIGHT_BELOW.png");

        // All red sprites
        spriteMap.put(new PuyoSpriteKey("red", 0b0000), "/sprites/puyo/red/red_NORMAL.png");
        spriteMap.put(new PuyoSpriteKey("red", 0b0001), "/sprites/puyo/red/red_ABOVE.png");
        spriteMap.put(new PuyoSpriteKey("red", 0b0010), "/sprites/puyo/red/red_LEFT.png");
        spriteMap.put(new PuyoSpriteKey("red", 0b0011), "/sprites/puyo/red/red_LEFT_ABOVE.png");
        spriteMap.put(new PuyoSpriteKey("red", 0b0100), "/sprites/puyo/red/red_BELOW.png");
        spriteMap.put(new PuyoSpriteKey("red", 0b0101), "/sprites/puyo/red/red_ABOVE_BELOW.png");
        spriteMap.put(new PuyoSpriteKey("red", 0b0110), "/sprites/puyo/red/red_LEFT_BELOW.png");
        spriteMap.put(new PuyoSpriteKey("red", 0b0111), "/sprites/puyo/red/red_LEFT_ABOVE_BELOW.png");
        spriteMap.put(new PuyoSpriteKey("red", 0b1000), "/sprites/puyo/red/red_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("red", 0b1001), "/sprites/puyo/red/red_ABOVE_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("red", 0b1010), "/sprites/puyo/red/red_LEFT_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("red", 0b1011), "/sprites/puyo/red/red_LEFT_ABOVE_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("red", 0b1100), "/sprites/puyo/red/red_BELOW_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("red", 0b1101), "/sprites/puyo/red/red_ABOVE_BELOW_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("red", 0b1110), "/sprites/puyo/red/red_LEFT_BELOW_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("red", 0b1111), "/sprites/puyo/red/red_LEFT_ABOVE_RIGHT_BELOW.png");

        // All yellow sprites
        spriteMap.put(new PuyoSpriteKey("yellow", 0b0000), "/sprites/puyo/yellow/yellow_NORMAL.png");
        spriteMap.put(new PuyoSpriteKey("yellow", 0b0001), "/sprites/puyo/yellow/yellow_ABOVE.png");
        spriteMap.put(new PuyoSpriteKey("yellow", 0b0010), "/sprites/puyo/yellow/yellow_LEFT.png");
        spriteMap.put(new PuyoSpriteKey("yellow", 0b0011), "/sprites/puyo/yellow/yellow_LEFT_ABOVE.png");
        spriteMap.put(new PuyoSpriteKey("yellow", 0b0100), "/sprites/puyo/yellow/yellow_BELOW.png");
        spriteMap.put(new PuyoSpriteKey("yellow", 0b0101), "/sprites/puyo/yellow/yellow_ABOVE_BELOW.png");
        spriteMap.put(new PuyoSpriteKey("yellow", 0b0110), "/sprites/puyo/yellow/yellow_LEFT_BELOW.png");
        spriteMap.put(new PuyoSpriteKey("yellow", 0b0111), "/sprites/puyo/yellow/yellow_LEFT_ABOVE_BELOW.png");
        spriteMap.put(new PuyoSpriteKey("yellow", 0b1000), "/sprites/puyo/yellow/yellow_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("yellow", 0b1001), "/sprites/puyo/yellow/yellow_ABOVE_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("yellow", 0b1010), "/sprites/puyo/yellow/yellow_LEFT_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("yellow", 0b1011), "/sprites/puyo/yellow/yellow_LEFT_ABOVE_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("yellow", 0b1100), "/sprites/puyo/yellow/yellow_BELOW_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("yellow", 0b1101), "/sprites/puyo/yellow/yellow_ABOVE_BELOW_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("yellow", 0b1110), "/sprites/puyo/yellow/yellow_LEFT_BELOW_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("yellow", 0b1111), "/sprites/puyo/yellow/yellow_LEFT_ABOVE_RIGHT_BELOW.png");

        // All purple sprites
        spriteMap.put(new PuyoSpriteKey("purple", 0b0000), "/sprites/puyo/purple/purple_NORMAL.png");
        spriteMap.put(new PuyoSpriteKey("purple", 0b0001), "/sprites/puyo/purple/purple_ABOVE.png");
        spriteMap.put(new PuyoSpriteKey("purple", 0b0010), "/sprites/puyo/purple/purple_LEFT.png");
        spriteMap.put(new PuyoSpriteKey("purple", 0b0011), "/sprites/puyo/purple/purple_LEFT_ABOVE.png");
        spriteMap.put(new PuyoSpriteKey("purple", 0b0100), "/sprites/puyo/purple/purple_BELOW.png");
        spriteMap.put(new PuyoSpriteKey("purple", 0b0101), "/sprites/puyo/purple/purple_ABOVE_BELOW.png");
        spriteMap.put(new PuyoSpriteKey("purple", 0b0110), "/sprites/puyo/purple/purple_LEFT_BELOW.png");
        spriteMap.put(new PuyoSpriteKey("purple", 0b0111), "/sprites/puyo/purple/purple_LEFT_ABOVE_BELOW.png");
        spriteMap.put(new PuyoSpriteKey("purple", 0b1000), "/sprites/puyo/purple/purple_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("purple", 0b1001), "/sprites/puyo/purple/purple_ABOVE_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("purple", 0b1010), "/sprites/puyo/purple/purple_LEFT_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("purple", 0b1011), "/sprites/puyo/purple/purple_LEFT_ABOVE_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("purple", 0b1100), "/sprites/puyo/purple/purple_BELOW_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("purple", 0b1101), "/sprites/puyo/purple/purple_ABOVE_RIGHT_BELOW.png");
        spriteMap.put(new PuyoSpriteKey("purple", 0b1110), "/sprites/puyo/purple/purple_LEFT_BELOW_RIGHT.png");
        spriteMap.put(new PuyoSpriteKey("purple", 0b1111), "/sprites/puyo/purple/purple_LEFT_ABOVE_RIGHT_BELOW.png");
    }

    public static String getSpriteFilePath(String color, int mode) {
        return spriteMap.get(new PuyoSpriteKey(color, mode));
    }
}
