package model;

import gamestates.GameState;

public class Menu {
    // Attributes
    private MenuButton[] buttons;
    private int selectedButton;

    // Constructor
    public Menu(){
        buttons = new MenuButton[3];
        buttons[0] = new MenuButton(GameState.PLAYING, 0);
        buttons[1] = new MenuButton(GameState.CREDITS, 1);
        buttons[2] = new MenuButton(GameState.EXIT, 2);
        selectedButton = 0;
    }

    // Getters
    public int getSelectedButton() {
        return selectedButton;
    }

    public MenuButton[] getButtons() {
        return buttons;
    }

    // Setters
    public void setSelectedButton(int selectedButton) {
        this.selectedButton = selectedButton;
    }

    public void setButtons(MenuButton[] buttons) {
        this.buttons = buttons;
    }

    public void moveSelectionUp(){
        if(selectedButton == 0){
            return;
        }
        selectedButton -= 1;
    }

    public void moveSelectionDown(){
        if(selectedButton == 2){
            return;
        }
        selectedButton += 1;
    }

    public void selectButton(){
        GameState.state = buttons[selectedButton].getState();
    }
}
