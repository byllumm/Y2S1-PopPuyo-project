package gamestates.controllers;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import utils.puyoutils.Position;

import java.io.IOException;

public interface StateController {
    void processKey(KeyStroke key);
    void draw(TextGraphics textGraphics, Position position) throws IOException;
}
