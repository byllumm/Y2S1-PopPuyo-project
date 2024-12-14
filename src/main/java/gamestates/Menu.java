package gamestates;

import com.googlecode.lanterna.input.KeyStroke;
import elements.MenuButton;


public class Menu implements StateMethods {

    private MenuButton[] buttons;
    private int selectedButton;

    public Menu(){
        buttons = new MenuButton[3];
        buttons[0] = new MenuButton(GameState.PLAYING, 0, true);
        buttons[1] = new MenuButton(GameState.CREDITS, 1, true);
        buttons[2] = new MenuButton(GameState.EXIT, 2, false);
        selectedButton = 0;
    }

    private void moveSelectionUp(){
        if(selectedButton == 0){
            return;
        }

        buttons[selectedButton].setSelection(false);
        buttons[selectedButton - 1].setSelection(true);

        selectedButton = selectedButton - 1;
    }

    private void moveSelectionDown(){
        if(selectedButton == 2){
            return;
        }

        buttons[selectedButton].setSelection(false);
        buttons[selectedButton + 1].setSelection(true);

        selectedButton = selectedButton + 1;
    }


    private void selectButton(){
        GameState.state = buttons[selectedButton].getState();
    }

    @Override
    public void processKey(KeyStroke key){
        switch(key.getKeyType()){
            case ArrowUp:
                moveSelectionUp();
                break;

            case ArrowDown:
                moveSelectionDown();
                break;

            case Enter:
                selectButton();
                break;

            default:
                break;
        }
    }

    @Override
    public void draw(){

        for(MenuButton b: buttons){
            if(b.getSelection()){
                //draw respective button as if selected
            }
            else{
                //draw respective button as if not selected
            }
        }
    }



}
