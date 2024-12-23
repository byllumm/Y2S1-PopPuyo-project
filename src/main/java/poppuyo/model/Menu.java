package poppuyo.model;

import poppuyo.gamestates.GameState;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    // Attributes
    private List<MenuButton> buttons;
    private int selectedButton;


    // Constructor
    public Menu(){
        buttons = new ArrayList<>();
        buttons.add(new MenuButton(GameState.PLAYING, 0));
        buttons.add(new MenuButton(GameState.CREDITS, 1));
        buttons.add(new MenuButton(GameState.EXIT, 2));
        selectedButton = 0;
    }


    // Getters
    public int getSelectedButton() { return selectedButton; }
    public List<MenuButton> getButtons() { return buttons; }


    // Setters
    public void setSelectedButton(int selectedButton) { this.selectedButton = selectedButton; }
    public void setButtons(List<MenuButton> buttons) { this.buttons = buttons; }


    // Class Methods
    public void moveSelectionUp() {
        selectedButton = (selectedButton - 1 + buttons.size()) %  buttons.size();
    }
    public void moveSelectionDown() {
        selectedButton = (selectedButton + 1) % buttons.size();
    }

    public void selectButton() {
        if(selectedButton >= 0 && selectedButton < buttons.size()){
            GameState.state = buttons.get(selectedButton).getState();
        }
    }
}
