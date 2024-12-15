import model.Grid;
import utils.puyoutils.PuyoPair;
import model.Arena;

public class ArenaTest {
    private Arena arena;
    private Grid gridMock;
    private PuyoPair puyoPairMock;

    /*
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
    void testProcessKey_RotateClockwise() {
        KeyStroke key = mock(KeyStroke.class);
        when(key.getKeyType()).thenReturn(KeyType.Character);
        when(key.getCharacter()).thenReturn('x');


        PuyoPair puyoPair = new PuyoPair(new Puyo(new Position(1, 2)), new Puyo(new Position(2, 2)));
        Position newSecondPos = new Position(1, 3);

        arena.setActivePuyo(puyoPair);
        arena.processKey(key);

        assertEquals(puyoPair.getSecondPos(), newSecondPos);
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

    /* Failing test
    @Test
    void testProcessKeyRotateClockwise() {
        KeyStroke key = mock(KeyStroke.class);
        when(key.getKeyType()).thenReturn(KeyType.Character);
        when(key.getCharacter()).thenReturn('z');


        PuyoPair puyoPair = new PuyoPair(new Puyo(new Position(1, 2)), new Puyo(new Position(2, 2)));
        Position newSecondPos = new Position(1, 1);

        arena.setActivePuyo(puyoPair);
        arena.processKey(key);

        assertEquals(puyoPair.getSecondPos(), newSecondPos);
    }
     */

/*    @Test
    void testUpdateIntegratesGrid() {
    }*/

    /* Broken Test
    @Test
    void testDraw() throws IOException {
        TextGraphics graphics = mock(TextGraphics.class);

        arena.draw(graphics, null);

        verify(graphics).setBackgroundColor(TextColor.Factory.fromString("#001326"));
        verify(graphics).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(GameGrid.COLUMNS, GameGrid.ROWS), ' ');
        verify(graphics, times(1)).fillRectangle(any(), any(), eq(' '));
    }
    */

}
