package poppuyo.gamestates.controllers;


import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import poppuyo.gamestates.GameState;
import poppuyo.utils.puyoutils.Position;
import poppuyo.viewer.CreditsStateViewer;

import java.io.IOException;

public class CreditsStateController implements StateController {

    public CreditsStateViewer creditsStateViewer;

    public CreditsStateController() throws IOException {
        creditsStateViewer = new CreditsStateViewer();
    }


    @Override
    public void processKey(KeyStroke key){
        if(key.getKeyType() == KeyType.Character && key.getCharacter() != null && key.getCharacter() == 'm'){
            GameState.state = GameState.MENU;
        }
    }

    @Override
    public void draw(TextGraphics graphics, Position position){
        creditsStateViewer.draw(graphics, position);
    }
}
