package elements;

import gamestates.GameState;

public class MenuButton {

    private int index;
    private GameState state;
    private boolean selected;

    public MenuButton(GameState state, int index, boolean selected){
        this.index = index;
        this.state = state;
        this.selected = selected;
    }

    public int getIndex(){
        return index;
    }

    public GameState getState(){
        return state;
    }

    public boolean getSelection(){
        return selected;
    }

    public void setSelection(boolean selected){
        this.selected = selected;
    }
}
