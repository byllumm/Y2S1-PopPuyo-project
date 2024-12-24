package poppuyo.utils.puyoutils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PositionTest {
    private Position position;

    @BeforeEach
    void setUp() {
        position = new Position();
    }

    @Test
    public void defaultConstructor() {
        Assertions.assertEquals(0, position.getX());
        Assertions.assertEquals(0, position.getY());
    }

    @Test
    public void constructor() {
        position = new Position(5, 10);
        Assertions.assertEquals(5, position.getX());
        Assertions.assertEquals(10, position.getY());
    }

    @Test
    public void getX() {
        Assertions.assertEquals(0, position.getX());
    }

    @Test
    public void getY() {
        Assertions.assertEquals(0, position.getY());
    }

    @Test
    public void setX() {
        position.setX(7);
        Assertions.assertEquals(7, position.getX());
    }

    @Test
    public void setY() {
        position.setY(15);
        Assertions.assertEquals(15, position.getY());
    }

    @Test
    public void equalsSamePosition() {
        Position position1 = new Position(3, 4);
        Position position2 = new Position(3, 4);
        Assertions.assertTrue(position1.equals(position2));
    }

    @Test
    public void equalsDifferentPosition() {
        Position position1 = new Position(3, 4);
        Position position2 = new Position(5, 6);
        Assertions.assertFalse(position1.equals(position2));
    }

    @Test
    public void equalsNull() {
        position = new Position(3, 4);
        Assertions.assertFalse(position.equals(null));
    }

    @Test
    public void equalsDifferentClass() {
        Position position = new Position(3, 4);
        String notAPosition = "On the ninth twilight, the witch shall revive, and none shall be left alive.";
        Assertions.assertFalse(position.equals(notAPosition));
    }
}
