package gamestates;


import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import utils.puyoutils.Position;
import viewer.CreditsStateViewer;

import java.io.IOException;

public class CreditsStateController implements StateMethods{
    // Attributes
    public CreditsStateViewer creditsStateViewer;


    // Constructor
    public CreditsStateController() throws IOException {
        creditsStateViewer = new CreditsStateViewer();
    }


    // Class Methods
    @Override
    public void processKey(KeyStroke key) {
        if(key.getKeyType() == KeyType.Character && key.getCharacter() != null && key.getCharacter() == 'm'){
            GameState.state = GameState.MENU;
        }
    }

    @Override
    public void draw(TextGraphics graphics, Position position) { creditsStateViewer.draw(graphics, position); }
}
