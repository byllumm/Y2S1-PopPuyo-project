package poppuyo.gamestates;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameStateTest {

    @Test
    public void testDefaultState()  {
        // Ensure initial state is MENU
        assertEquals(GameState.MENU, GameState.state);
    }

    @Test
    public void testStateTransition() {
        GameState.state = GameState.PLAYING;
        assertEquals(GameState.PLAYING, GameState.state);

        GameState.state = GameState.CREDITS;
        assertEquals(GameState.CREDITS, GameState.state);
    }

    @Test
    public void testEnumValues() {
        // Ensure all defined states are present
        GameState[] states = GameState.values();
        assertArrayEquals(new GameState[] {GameState.PLAYING, GameState.MENU, GameState.CREDITS, GameState.EXIT}, states);
    }

    @Test
    public void testInvalidStateTransition() {
        assertThrows(IllegalArgumentException.class, () -> {GameState.state = GameState.valueOf("INVALID_STATE");});
    }
}
