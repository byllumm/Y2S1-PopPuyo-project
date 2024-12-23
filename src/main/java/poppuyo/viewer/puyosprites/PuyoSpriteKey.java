package poppuyo.viewer.puyosprites;

import java.util.Objects;

public class PuyoSpriteKey {
    private final String color;
    private final int mode;

    public PuyoSpriteKey(String color, int mode) {
        this.color = color;
        this.mode = mode;
    }

    public String getColor(){
        return color;
    }

    public int getMode(){
        return mode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PuyoSpriteKey spriteKey = (PuyoSpriteKey) o;
        return mode == spriteKey.mode && Objects.equals(color, spriteKey.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, mode);
    }
}
