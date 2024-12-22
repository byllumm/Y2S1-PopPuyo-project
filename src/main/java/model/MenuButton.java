package model;

import gamestates.GameState;

public class MenuButton {
    // Attributes
    private int index;
    private GameState state;


    // Constructor
    public MenuButton(GameState state, int index){
        this.index = index;
        this.state = state;
    }


    // Getters
    public int getIndex() { return index; }
    public GameState getState() { return state; }


    // Setters
    public void setIndex(int index) { this.index = index; }
    public void setState(GameState state) { this.state = state; }
}
