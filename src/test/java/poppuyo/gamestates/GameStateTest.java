package poppuyo.gamestates;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GameStateTest {

    @Test
    void testDefaultState()  {
        // Ensure initial state is MENU
        assertEquals(GameState.MENU, GameState.state);
    }

    @Test
    void testStateTransition() {
        GameState.state = GameState.PLAYING;
        assertEquals(GameState.PLAYING, GameState.state);

        GameState.state = GameState.CREDITS;
        assertEquals(GameState.CREDITS, GameState.state);
    }

    @Test
    void testEnumValues() {
        // Ensure all defined states are present
        GameState[] states = GameState.values();
        assertArrayEquals(new GameState[] {GameState.PLAYING, GameState.MENU, GameState.CREDITS, GameState.EXIT}, states);
    }

    @Test
    void testInvalidStateTransition() {
        assertThrows(IllegalArgumentException.class, () -> {GameState.state = GameState.valueOf("INVALID_STATE");});
    }
}
