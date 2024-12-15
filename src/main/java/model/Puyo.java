package model;

import viewer.PuyoViewer;
import utils.puyoutils.Color;
import utils.puyoutils.Position;

import java.io.IOException;

public class Puyo {
    // Attributes
    private Position position;
    private PuyoViewer puyoViewer;
    private String color;


    // Constructor
    public Puyo(Position position) throws IOException {
        this.position = position;
        this.color = Color.getRandomColor();
        this.puyoViewer = new PuyoViewer(color);
    }


    // Getters
    public PuyoViewer getPuyoViewer() {
        return puyoViewer;
    }

    public Position getPosition() {
        return position;
    }

    public String getColor() {
        return color;
    }


    // Setters
    public void setPuyoViewer(PuyoViewer puyoViewer) {
        this.puyoViewer = puyoViewer;
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
