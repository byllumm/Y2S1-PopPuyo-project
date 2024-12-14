package elements;

import graphics.PuyoGraphics;
import utils.puyoutils.Color;
import utils.puyoutils.Position;

import java.io.IOException;

public class    Puyo {
    // Attributes
    private Position position;
    private PuyoGraphics puyoGraphics;
    private String color;

    // Constructor
    public Puyo(Position position) throws IOException {
        this.position = position;
        this.color = Color.getRandomColor();
        this.puyoGraphics = new PuyoGraphics(color);
    }


    // Getters
    public PuyoGraphics getPuyoGraphics() {
        return puyoGraphics;
    }

    public Position getPosition() {
        return position;
    }

    public String getColor() {
        return color;
    }


    // Setters
    public void setPuyoGraphics(PuyoGraphics puyoGraphics) {
        this.puyoGraphics = puyoGraphics;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setColor(Color ColorHex) {
        this.color = color;
    }
}
