package gamestates;


import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public class CreditsStateController implements StateMethods{

    public CreditsStateController(){
    }


    @Override
    public void processKey(KeyStroke key){
        if(key.getKeyType() == KeyType.Character && key.getCharacter() != null && key.getCharacter() == 'm'){
            GameState.state = GameState.MENU;
        }
    }

    @Override
    public void draw(){
        // Draw the credits
    }
}
