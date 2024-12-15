package gamestates;

import com.googlecode.lanterna.input.KeyStroke;
import model.Menu;
import model.MenuButton;

public class MenuStateController implements StateMethods{

    public Menu menuModel;

    public MenuStateController(){
        menuModel = new Menu();
    }

    @Override
    public void processKey(KeyStroke key){
        switch (key.getKeyType()){
            case ArrowUp:
                menuModel.moveSelectionUp();
                break;

            case ArrowDown:
                menuModel.moveSelectionDown();
                break;

            case Enter:
                menuModel.selectButton();
                break;

            default:
                break;
        }
    }

    @Override
    public void draw(){
        int selectedButton = menuModel.getSelectedButton();
        if(selectedButton == 0){
            // Draw first as selected, and the others as unselected
        }

        else if(selectedButton == 1){
            // Draw second as selected, and the others as unselected
        }

        else if(selectedButton == 2){
            // Draw third as selected and the others as unselected
        }
    }
}
