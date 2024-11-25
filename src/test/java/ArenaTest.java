import elements.GameGrid;
import elements.Puyo;
import elements.puyo_utils.Position;
import elements.puyo_utils.PuyoPair;
import elements.Arena;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ArenaTest {
    private Arena arena;
    private GameGrid gridMock;
    private PuyoPair puyoPairMock;

    @BeforeEach
    void setUp() throws IOException {
        arena = new Arena();
        gridMock = mock(GameGrid.class);
        puyoPairMock = mock(PuyoPair.class);
    }

    @Test
    void testCanMoveDown(){
        when(puyoPairMock.getFirstPos()).thenReturn(new Position(1, 2));
        when(puyoPairMock.getSecondPos()).thenReturn(new Position(2, 2));
        when(gridMock.isEmpty(3, 1)).thenReturn(true);
        when(gridMock.isEmpty(3, 2)).thenReturn(true);

        boolean canMove = arena.canMoveDown(puyoPairMock);
        assertTrue(canMove);
    }

    @Test
    void testCanMoveLeft(){
        when(puyoPairMock.getFirstPos()).thenReturn(new Position(1, 2));
        when(puyoPairMock.getSecondPos()).thenReturn(new Position(2, 2));
        when(gridMock.isEmpty(2, 0)).thenReturn(true);
        when(gridMock.isEmpty(2, 1)).thenReturn(true);

        boolean canMove = arena.canMoveLeft(puyoPairMock);
        assertTrue(canMove);
    }

    @Test
    void testCanMoveRight(){
        when(puyoPairMock.getFirstPos()).thenReturn(new Position(1, 2));
        when(puyoPairMock.getSecondPos()).thenReturn(new Position(2, 2));
        when(gridMock.isEmpty(2, 2)).thenReturn(true);
        when(gridMock.isEmpty(2, 3)).thenReturn(true);

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
        when(secondPosMock.getX()).thenReturn(2);
        when(secondPosMock.getY()).thenReturn(2);

        when(puyoPairMock.getFirstPos()).thenReturn(firstPosMock);
        when(puyoPairMock.getSecondPos()).thenReturn(secondPosMock);

        Arena arenaMock = mock(Arena.class);
        when(arenaMock.canMoveLeft(any(PuyoPair.class))).thenReturn(true);

        doAnswer(invocation -> {
            when(firstPosMock.getX()).thenReturn(0);
            return null;
        }).when(puyoPairMock).moveLeft();

        arena.setActivePuyo(puyoPairMock);

        KeyStroke key = mock(KeyStroke.class);
        when(key.getKeyType()).thenReturn(KeyType.ArrowLeft);

        arena.processKey(key);

        assertEquals(0, arena.getActivePuyo().getFirstPos().getX());
    }

    @Test
    void testProcessKeyRight() {
        Position firstPosMock = mock(Position.class);
        Position secondPosMock = mock(Position.class);

        when(firstPosMock.getX()).thenReturn(1);
        when(firstPosMock.getY()).thenReturn(2);
        when(secondPosMock.getX()).thenReturn(2);
        when(secondPosMock.getY()).thenReturn(2);

        when(puyoPairMock.getFirstPos()).thenReturn(firstPosMock);
        when(puyoPairMock.getSecondPos()).thenReturn(secondPosMock);

        Arena arenaMock = mock(Arena.class);
        when(arenaMock.canMoveRight(any(PuyoPair.class))).thenReturn(true);

        doAnswer(invocation -> {
            when(firstPosMock.getX()).thenReturn(2);
            return null;
        }).when(puyoPairMock).moveRight();

        arena.setActivePuyo(puyoPairMock);

        KeyStroke key = mock(KeyStroke.class);
        when(key.getKeyType()).thenReturn(KeyType.ArrowRight);

        arena.processKey(key);

        assertEquals(2, arena.getActivePuyo().getFirstPos().getX());
    }

    @Test
    void testProcessKeyDown() {
        Position firstPosMock = mock(Position.class);
        Position secondPosMock = mock(Position.class);

        when(firstPosMock.getX()).thenReturn(1);
        when(firstPosMock.getY()).thenReturn(2);
        when(secondPosMock.getX()).thenReturn(2);
        when(secondPosMock.getY()).thenReturn(2);

        when(puyoPairMock.getFirstPos()).thenReturn(firstPosMock);
        when(puyoPairMock.getSecondPos()).thenReturn(secondPosMock);

        Arena arenaMock = mock(Arena.class);
        when(arenaMock.canMoveDown(any(PuyoPair.class))).thenReturn(true);

        doAnswer(invocation -> {
            when(firstPosMock.getY()).thenReturn(3);
            return null;
        }).when(puyoPairMock).moveDown();

        arena.setActivePuyo(puyoPairMock);

        KeyStroke key = mock(KeyStroke.class);
        when(key.getKeyType()).thenReturn(KeyType.ArrowDown);

        arena.processKey(key);

        assertEquals(3, arena.getActivePuyo().getFirstPos().getY());
    }

    @Test
    void testProcessKeyRotateClockwise() {
        KeyStroke key = mock(KeyStroke.class);
        when(key.getKeyType()).thenReturn(KeyType.Character);
        when(key.getCharacter()).thenReturn('x');

        Position position = new Position(1, 2);
        when(gridMock.getGrid()).thenReturn(new Puyo[GameGrid.ROWS][GameGrid.COLUMNS]);

        when(arena.isValidPosition(position, gridMock)).thenReturn(true);

        arena.setActivePuyo(puyoPairMock);
        arena.processKey(key);

        verify(arena.getActivePuyo()).rotate(true);
    }

/*    @Test
    void testUpdateIntegratesGrid() {
    }*/

    @Test
    void testDraw() throws IOException {
        TextGraphics graphics = mock(TextGraphics.class);

        arena.draw(graphics, null);

        verify(graphics).setBackgroundColor(TextColor.Factory.fromString("#001326"));
        verify(graphics).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(GameGrid.COLUMNS, GameGrid.ROWS), ' ');
        verify(graphics, times(1)).fillRectangle(any(), any(), eq(' '));
    }
}
