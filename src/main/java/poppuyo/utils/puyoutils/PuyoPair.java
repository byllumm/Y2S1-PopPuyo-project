package poppuyo.utils.puyoutils;

import poppuyo.controllers.PuyoPairController;
import poppuyo.model.Puyo;

import java.io.IOException;

public class PuyoPair {
    // Attributes
    private Puyo firstPuyo;
    private Puyo secondPuyo;
    private PuyoPairController controller;


    // Constructor
    public PuyoPair(Puyo firstPuyo, Puyo secondPuyo){
        this.firstPuyo = firstPuyo;
        this.secondPuyo = secondPuyo;
        this.controller = new PuyoPairController(this);
    }


    // Getters
    public Puyo getFirstPuyo() { return firstPuyo; }
    public Puyo getSecondPuyo() { return secondPuyo; }
    public Position getFirstPos() { return firstPuyo.getPosition(); }
    public Position getSecondPos() { return secondPuyo.getPosition(); }
    public PuyoPairController getController() { return controller; }
    public void setController(PuyoPairController controller) { this.controller = controller; }


    // Setters
    public void setFirstPuyo(Puyo firstPuyo) { this.firstPuyo = firstPuyo; }
    public void setSecondPuyo(Puyo secondPuyo) { this.secondPuyo = secondPuyo; }
    public void setFirstPos(Position pos) { firstPuyo.setPosition(pos); }
    public void setSecondPos(Position pos) { secondPuyo.setPosition(pos); }


    // Class Methods

    // Creates a new puyo pair in the middle of the first row on the screen
    public static PuyoPair spawnPuyoPair() throws IOException {
        Position firstPos = new Position(2, 0);
        Position secondPos = new Position(3, 0);

        Puyo firstPuyo = new Puyo(firstPos);
        Puyo secondPuyo = new Puyo(secondPos);

        return new PuyoPair(firstPuyo, secondPuyo);
    }
}
