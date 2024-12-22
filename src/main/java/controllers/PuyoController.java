package controllers;

import com.googlecode.lanterna.graphics.TextGraphics;
import model.Puyo;
import utils.puyoutils.Position;
import viewer.PuyoViewer;

public class PuyoController {
    // Attributes
    private Puyo puyoModel;
    private PuyoViewer puyoViewer;


    // Constructor
    public PuyoController(Puyo puyoModel, PuyoViewer puyoViewer) {
        this.puyoModel = puyoModel;
        this.puyoViewer = puyoViewer;
    }


    // Getters
    public Puyo getPuyoModel() { return puyoModel; }
    public PuyoViewer getPuyoViewer() { return puyoViewer; }


    // Setters
    public void setPuyoModel(Puyo puyoModel) { this.puyoModel = puyoModel; }
    public void setPuyoViewer(PuyoViewer puyoViewer) { this.puyoViewer = puyoViewer; }


    // Class Methods
    public void draw(TextGraphics graphics, Position corner) { puyoViewer.draw(graphics, corner); }
}
