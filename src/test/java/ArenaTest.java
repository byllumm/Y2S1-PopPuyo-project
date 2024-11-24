import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import elements.GameGrid;
import elements.Puyo;
import puyoUtils.Position;
import puyoUtils.PuyoPair;
import elements.Arena;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import javax.swing.*;

import java.lang.reflect.Field;
import java.security.Key;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ArenaTest {
    private Arena arena;
    private GameGrid gridMock;
    private PuyoPair puyoPairMock;

    @BeforeEach
    void setUp(){
        arena = new Arena();
        gridMock = mock(GameGrid.class);
        puyoPairMock = mock(PuyoPair.class);
    }

    @Test
    void testCanMoveDown(){
        when(puyoPairMock.getFirstPos()).thenReturn(new Position(1, 2));
        when(puyoPairMock.getSecondPos()).thenReturn(new Position(1, 3));
        when(gridMock.isEmpty(2, 2)).thenReturn(true);
        when(gridMock.isEmpty(2, 3)).thenReturn(true);

        boolean canMove = arena.canMoveDown(puyoPairMock);
        assertTrue(canMove);
    }

    @Test
    void testCanMoveLeft(){
        when(puyoPairMock.getFirstPos()).thenReturn(new Position(1, 2));
        when(puyoPairMock.getSecondPos()).thenReturn(new Position(1, 3));
        when(gridMock.isEmpty(1, 1)).thenReturn(true);
        when(gridMock.isEmpty(1, 2)).thenReturn(true);

        boolean canMove = arena.canMoveLeft(puyoPairMock);
        assertTrue(canMove);
    }

    @Test
    void testCanMoveRight(){
        when(puyoPairMock.getFirstPos()).thenReturn(new Position(1, 2));
        when(puyoPairMock.getSecondPos()).thenReturn(new Position(1, 3));
        when(gridMock.isEmpty(1, 3)).thenReturn(true);
        when(gridMock.isEmpty(1, 4)).thenReturn(true);

        boolean canMove = arena.canMoveRight(puyoPairMock);
        assertTrue(canMove);
    }

    @Test
    void testIsValidPosition(){
        Position position = new Position(1, 2);
        when(gridMock.getGrid()).thenReturn(new Puyo[GameGrid.ROWS][GameGrid.COLUMNS]);

        boolean isValid = arena.isValidPosition(position, gridMock);
        assertTrue(isValid);
    }

    @Test
    void testGameOver(){
        when(gridMock.isEmpty(0, 2)).thenReturn(false);

        boolean gameOver = arena.gameOver(gridMock);

        assertTrue(gameOver);
    }

    @Test
    void testProcessKeyLeft() {
        Position firstPosMock = mock(Position.class);
        Position secondPosMock = mock(Position.class);

        when(firstPosMock.getX()).thenReturn(1);
        when(firstPosMock.getY()).thenReturn(2);
        when(secondPosMock.getX()).thenReturn(1);
        when(secondPosMock.getY()).thenReturn(3);

        when(puyoPairMock.getFirstPos()).thenReturn(firstPosMock);
        when(puyoPairMock.getSecondPos()).thenReturn(secondPosMock);

        Arena arenaMock = mock(Arena.class);
        when(arenaMock.canMoveDown(any(PuyoPair.class))).thenReturn(true);

        doAnswer(invocation -> {
            when(firstPosMock.getX()).thenReturn(0);
            return null;
        }).when(puyoPairMock).moveLeft();

        arena.setActivePuyo(puyoPairMock);

        KeyStroke key = mock(KeyStroke.class);
        when(key.getKeyType()).thenReturn(KeyType.ArrowLeft);

        arena.processKey(key);

        // Verify that the first Puyo's X position is now 0 after moving left
        assertEquals(0, arena.getActivePuyo().getFirstPos().getX());

    }
    
}
