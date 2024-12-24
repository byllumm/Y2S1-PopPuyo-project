package poppuyo.viewer.puyosprites;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PuyoSpriteKeyTest {
    private PuyoSpriteKey key1;
    private PuyoSpriteKey key2;
    private PuyoSpriteKey key3;

    @BeforeEach
    void setUp() {
        key1 = new PuyoSpriteKey("red", 0b0001);
        key2 = new PuyoSpriteKey("red", 0b0010);
        key3 = new PuyoSpriteKey("blue", 0b0011);
    }

    @Test
    public void constructor() {
        Assertions.assertNotNull(key1);
        Assertions.assertEquals("red", key1.getColor());
        Assertions.assertEquals(1, key1.getMode());
    }

    @Test
    public void equalsSameObject() {
        Assertions.assertEquals(key1, key1);
    }

    @Test
    public void equalsDifferentObjectSameValues() {
        PuyoSpriteKey anotherKeyWithSameValues = new PuyoSpriteKey("red", 0b0001);
        Assertions.assertEquals(key1, anotherKeyWithSameValues);
    }

    @Test
    public void equalsDifferentValues() {
        Assertions.assertNotEquals(key1, key3);
    }

    @Test
    public void equalsNull() {
        Assertions.assertNotEquals(null, key1);
    }

    @Test
    public void equalsDifferentClass() {
        String nonPuyoSpriteKeyObject = "Behold the sweetfish river running through my beloved hometown.";
        Assertions.assertNotEquals(key1, nonPuyoSpriteKeyObject);
    }

    @Test
    public void hashCodeSameValues() {
        PuyoSpriteKey anotherKeyWithSameValues = new PuyoSpriteKey("red", 1);
        Assertions.assertEquals(key1.hashCode(), anotherKeyWithSameValues.hashCode());
    }

    @Test
    public void hashCodeDifferentValues() {
        Assertions.assertNotEquals(key1.hashCode(), key3.hashCode());
    }
}
