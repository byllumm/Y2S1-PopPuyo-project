package poppuyo.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import poppuyo.utils.puyoutils.Position;
import poppuyo.viewer.PuyoViewer;

import java.io.IOException;

public class PuyoTest {
    private Position position;
    private Puyo puyo;

    @BeforeEach
    public void setUp() throws IOException {
        position = new Position(2, 3);
        puyo = new Puyo(position);
    }

    @Test
    public void constructor() throws IOException {
        Position position = new Position(1, 1);
        Puyo puyo = new Puyo(position);

        Assertions.assertEquals(position, puyo.getPosition());
        Assertions.assertNotNull(puyo.getColor());
        Assertions.assertNotNull(puyo.getPuyoViewer());
        Assertions.assertEquals(0b0000, puyo.getAdjacent());
    }

    @Test
    public void getPosition() {
        Assertions.assertEquals(new Position(2, 3), puyo.getPosition());
    }

    @Test
    public void setPosition() {
        Position newPosition = new Position(5, 6);
        puyo.setPosition(newPosition);
        Assertions.assertEquals(newPosition, puyo.getPosition());
    }

    @Test
    public void getColor() {
        Assertions.assertNotNull(puyo.getColor());
    }

    @Test
    public void setColor() {
        String newColor = "red";
        puyo.setColor(newColor);
        Assertions.assertEquals(newColor, puyo.getColor());
    }

    @Test
    public void getAdjacent() {
        Assertions.assertEquals(0b0000, puyo.getAdjacent());
    }

    @Test
    public void setAdjacent() {
        int newAdjacent = 0b1010;
        puyo.setAdjacent(newAdjacent);
        Assertions.assertEquals(newAdjacent, puyo.getAdjacent());
    }

    @Test
    public void getPuyoViewer() {
        Assertions.assertNotNull(puyo.getPuyoViewer());
    }

    @Test
    public void setPuyoViewer() throws IOException {
        PuyoViewer newPuyoViewer = new PuyoViewer("blue", 0b0101); // Example viewer setup
        puyo.setPuyoViewer(newPuyoViewer);
        Assertions.assertEquals(newPuyoViewer, puyo.getPuyoViewer());
    }
}
