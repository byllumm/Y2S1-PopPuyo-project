package poppuyo.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import poppuyo.gamestates.GameState;

public class MenuButtonTest {
    @Mock private MenuButton menuButton;
    private GameState mockState;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        menuButton = new MenuButton(mockState, 1);
    }

    @Test
    public void constructor() {
        Assertions.assertEquals(1, menuButton.getIndex());
        Assertions.assertEquals(mockState, menuButton.getState());
    }

    @Test
    public void getIndex() {
        Assertions.assertEquals(1, menuButton.getIndex());
    }

    @Test
    public void getState() {
        Assertions.assertEquals(mockState, menuButton.getState());
    }

    @Test
    public void setIndex() {
        menuButton.setIndex(5);
        Assertions.assertEquals(5, menuButton.getIndex());
    }

    @Test
    public void setState() {
        MenuButton menuButton = new MenuButton(GameState.MENU, 1);
        menuButton.setState(GameState.PLAYING);

        Assertions.assertEquals(GameState.PLAYING, menuButton.getState());
    }
}