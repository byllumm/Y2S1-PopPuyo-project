package gamestates;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import model.Menu;
import model.MenuButton;
import utils.custom_exceptions.ResourceException;
import utils.puyoutils.Position;
import viewer.MenuStateViewer;

import java.io.IOException;

public class MenuStateController implements StateMethods{

    public Menu menuModel;
    public MenuStateViewer menuStateViewer;

    public MenuStateController() throws IOException {
        menuModel = new Menu();
        menuStateViewer = new MenuStateViewer();
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
        }
    }

    @Override
    public void draw(TextGraphics graphics, Position position) throws ResourceException {
        int selectedButton = menuModel.getSelectedButton();
        if(selectedButton == 0){
            menuStateViewer.setState(0);
            menuStateViewer.draw(graphics, position);
        }

        else if(selectedButton == 1){
            menuStateViewer.setState(1);
            menuStateViewer.draw(graphics, position);
        }

        else if(selectedButton == 2){
            menuStateViewer.setState(2);
            menuStateViewer.draw(graphics, position);
        }
    }
}
