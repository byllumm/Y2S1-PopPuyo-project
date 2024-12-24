package poppuyo.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import poppuyo.gamestates.GameState;

import java.util.ArrayList;
import java.util.List;

public class MenuTest {
    private Menu menu;

    @BeforeEach
    public void setUp() {
        menu = new Menu();
    }

    @Test
    public void testConstructor() {
        List<MenuButton> buttons = menu.getButtons();
        Assertions.assertEquals(3, buttons.size());
        Assertions.assertEquals(GameState.PLAYING, buttons.get(0).getState());
        Assertions.assertEquals(GameState.CREDITS, buttons.get(1).getState());
        Assertions.assertEquals(GameState.EXIT, buttons.get(2).getState());
        Assertions.assertEquals(0, menu.getSelectedButton());
    }

    @Test
    public void testGetSelectedButton() {
        Assertions.assertEquals(0, menu.getSelectedButton());
    }

    @Test
    public void testSetSelectedButton() {
        menu.setSelectedButton(1);
        Assertions.assertEquals(1, menu.getSelectedButton());
    }

    @Test
    public void testGetButtons() {
        List<MenuButton> buttons = menu.getButtons();
        Assertions.assertEquals(3, buttons.size());
    }

    @Test
    public void testSetButtons() {
        List<MenuButton> newButtons = new ArrayList<>();
        newButtons.add(new MenuButton(GameState.MENU, 0));
        newButtons.add(new MenuButton(GameState.PLAYING, 1));
        menu.setButtons(newButtons);

        Assertions.assertEquals(2, menu.getButtons().size());
        Assertions.assertEquals(GameState.MENU, menu.getButtons().get(0).getState());
        Assertions.assertEquals(GameState.PLAYING, menu.getButtons().get(1).getState());
    }

    @Test
    public void testMoveSelectionUp() {
        menu.moveSelectionUp();
        Assertions.assertEquals(2, menu.getSelectedButton());

        menu.moveSelectionUp();
        Assertions.assertEquals(1, menu.getSelectedButton());

        menu.moveSelectionUp();
        Assertions.assertEquals(0, menu.getSelectedButton());
    }

    @Test
    public void testMoveSelectionDown() {
        menu.moveSelectionDown();
        Assertions.assertEquals(1, menu.getSelectedButton());

        menu.moveSelectionDown();
        Assertions.assertEquals(2, menu.getSelectedButton());

        menu.moveSelectionDown();
        Assertions.assertEquals(0, menu.getSelectedButton());
    }

    @Test
    public void testSelectButton() {
        menu.setSelectedButton(0);
        menu.selectButton();
        Assertions.assertEquals(GameState.PLAYING, GameState.state);

        menu.setSelectedButton(1);
        menu.selectButton();
        Assertions.assertEquals(GameState.CREDITS, GameState.state);

        menu.setSelectedButton(2);
        menu.selectButton();
        Assertions.assertEquals(GameState.EXIT, GameState.state);
    }
}
