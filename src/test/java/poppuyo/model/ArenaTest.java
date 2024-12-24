package poppuyo.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import poppuyo.model.grid.Grid;
import poppuyo.utils.puyoutils.Position;
import poppuyo.utils.puyoutils.PuyoPair;

import java.io.IOException;

class ArenaTest {
    @Mock private Grid mockGrid;
    @Mock private PuyoPair mockActivePuyo;
    @Mock private PuyoPair mockNextPuyo;
    @Mock private Score mockScore;
    @Mock private Stage mockStage;
    private Arena arena;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        Mockito.when(mockGrid.getGrid()).thenReturn(new Puyo[Grid.ROWS][Grid.COLUMNS]);

        arena = new Arena();
        arena.setGrid(mockGrid);
        arena.setActivePuyo(mockActivePuyo);
        arena.setNextPuyo(mockNextPuyo);
        arena.setScore(mockScore);
        arena.setStage(mockStage);
    }

    @Test
    public void constructor() throws Exception {
        Assertions.assertNotNull(arena.getGrid());
        Assertions.assertNotNull(arena.getActivePuyo());
        Assertions.assertNotNull(arena.getNextPuyo());
        Assertions.assertNotNull(arena.getScore());
        Assertions.assertNotNull(arena.getStage());
    }

    @Test
    public void getGrid() {
        Assertions.assertSame(mockGrid, arena.getGrid());
    }

    @Test
    public void getActivePuyo() {
        Assertions.assertSame(mockActivePuyo, arena.getActivePuyo());
    }

    @Test
    public void getNextPuyo() {
        Assertions.assertSame(mockNextPuyo, arena.getNextPuyo());
    }

    @Test
    public void getScore() {
        Assertions.assertSame(mockScore, arena.getScore());
    }

    @Test
    public void getStage() {
        Assertions.assertSame(mockStage, arena.getStage());
    }

    @Test
    public void setGrid() {
        Grid newMockGrid = Mockito.mock(Grid.class);
        arena.setGrid(newMockGrid);
        Assertions.assertSame(newMockGrid, arena.getGrid());
    }

    @Test
    public void setActivePuyo() {
        PuyoPair newActivePuyo = Mockito.mock(PuyoPair.class);
        arena.setActivePuyo(newActivePuyo);
        Assertions.assertSame(newActivePuyo, arena.getActivePuyo());
    }

    @Test
    public void setNextPuyo() {
        PuyoPair newNextPuyo = Mockito.mock(PuyoPair.class);
        arena.setNextPuyo(newNextPuyo);
        Assertions.assertSame(newNextPuyo, arena.getNextPuyo());
    }

    @Test
    public void setScore() {
        Score newScore = Mockito.mock(Score.class);
        arena.setScore(newScore);
        Assertions.assertSame(newScore, arena.getScore());
    }

    @Test
    public void setStage() {
        Stage newStage = Mockito.mock(Stage.class);
        arena.setStage(newStage);
        Assertions.assertSame(newStage, arena.getStage());
    }

    @Test
    public void canMoveDownWithEmptySpaceBelow() {
        Position firstPos = new Position(2, 2);
        Position secondPos = new Position(3, 2);
        Mockito.when(mockActivePuyo.getFirstPos()).thenReturn(firstPos);
        Mockito.when(mockActivePuyo.getSecondPos()).thenReturn(secondPos);

        Assertions.assertTrue(Arena.canMoveDown(mockActivePuyo));
    }

    @Test
    public void canMoveDownAtBottomRow() {
        Position firstPos = new Position(2, Grid.ROWS - 1);
        Position secondPos = new Position(3, Grid.ROWS - 1);
        Mockito.when(mockActivePuyo.getFirstPos()).thenReturn(firstPos);
        Mockito.when(mockActivePuyo.getSecondPos()).thenReturn(secondPos);

        Assertions.assertFalse(Arena.canMoveDown(mockActivePuyo));
    }

    @Test
    void canMoveDown_WithPuyoBelow_ReturnsFalse() throws IOException {
        Position firstPos = new Position(2, 3);
        Position secondPos = new Position(3, 3);

        Mockito.when(mockActivePuyo.getFirstPos()).thenReturn(firstPos);
        Mockito.when(mockActivePuyo.getSecondPos()).thenReturn(secondPos);

        // Workaround due to grid being static. Bad decision, too late now.
        Grid realGrid = new Grid();
        realGrid.getGrid()[4][2] = new Puyo(new Position(2, 4));
        realGrid.getGrid()[4][3] = new Puyo(new Position(3, 4));

        arena.setGrid(realGrid);

        Assertions.assertFalse(Arena.canMoveDown(mockActivePuyo));
    }

    @Test
    public void canMoveDownVerticalPairInMiddle() {
        Position firstPos = new Position(2, 3);
        Position secondPos = new Position(2, 4);
        Mockito.when(mockActivePuyo.getFirstPos()).thenReturn(firstPos);
        Mockito.when(mockActivePuyo.getSecondPos()).thenReturn(secondPos);

        Assertions.assertTrue(Arena.canMoveDown(mockActivePuyo));
    }

    @Test
    public void canMoveLeftWithEmptySpaceLeft() {
        Position firstPos = new Position(2, 2);
        Position secondPos = new Position(3, 2);
        Mockito.when(mockActivePuyo.getFirstPos()).thenReturn(firstPos);
        Mockito.when(mockActivePuyo.getSecondPos()).thenReturn(secondPos);

        Assertions.assertTrue(Arena.canMoveLeft(mockActivePuyo));
    }

    @Test
    public void canMoveLeftAtLeftBoundary() {
        Position firstPos = new Position(0, 2);
        Position secondPos = new Position(1, 2);
        Mockito.when(mockActivePuyo.getFirstPos()).thenReturn(firstPos);
        Mockito.when(mockActivePuyo.getSecondPos()).thenReturn(secondPos);

        Assertions.assertFalse(Arena.canMoveLeft(mockActivePuyo));
    }

    @Test
    public void canMoveLeft_WithPuyoToTheLeft_ReturnsFalse() throws IOException {
        Position firstPos = new Position(2, 3);
        Position secondPos = new Position(3, 3);

        Mockito.when(mockActivePuyo.getFirstPos()).thenReturn(firstPos);
        Mockito.when(mockActivePuyo.getSecondPos()).thenReturn(secondPos);

        Grid realGrid = new Grid();
        realGrid.getGrid()[3][1] = new Puyo(new Position(1, 3));
        realGrid.getGrid()[3][2] = new Puyo(new Position(2, 3));

        arena.setGrid(realGrid);

        Assertions.assertFalse(Arena.canMoveLeft(mockActivePuyo));
    }

    @Test
    public void canMoveLeftVerticalPairInMiddle() {
        Position firstPos = new Position(3, 3);
        Position secondPos = new Position(3, 4);
        Mockito.when(mockActivePuyo.getFirstPos()).thenReturn(firstPos);
        Mockito.when(mockActivePuyo.getSecondPos()).thenReturn(secondPos);

        Assertions.assertTrue(Arena.canMoveLeft(mockActivePuyo));
    }

    @Test
    public void canMoveRightWithEmptySpaceRight() {
        Position firstPos = new Position(2, 2);
        Position secondPos = new Position(3, 2);
        Mockito.when(mockActivePuyo.getFirstPos()).thenReturn(firstPos);
        Mockito.when(mockActivePuyo.getSecondPos()).thenReturn(secondPos);

        Assertions.assertTrue(Arena.canMoveRight(mockActivePuyo));
    }

    @Test
    public void canMoveRightAtRightBoundary() {
        Position firstPos = new Position(Grid.COLUMNS - 1, 2);
        Position secondPos = new Position(Grid.COLUMNS - 2, 2);
        Mockito.when(mockActivePuyo.getFirstPos()).thenReturn(firstPos);
        Mockito.when(mockActivePuyo.getSecondPos()).thenReturn(secondPos);

        Assertions.assertFalse(Arena.canMoveRight(mockActivePuyo));
    }

    @Test
    public void canMoveRight_WithPuyoToTheRight_ReturnsFalse() throws IOException {
        Position firstPos = new Position(2, 3);
        Position secondPos = new Position(3, 3);

        Mockito.when(mockActivePuyo.getFirstPos()).thenReturn(firstPos);
        Mockito.when(mockActivePuyo.getSecondPos()).thenReturn(secondPos);

        Grid realGrid = new Grid();
        realGrid.getGrid()[3][4] = new Puyo(new Position(4, 3));
        realGrid.getGrid()[3][5] = new Puyo(new Position(5, 3));

        arena.setGrid(realGrid);

        Assertions.assertFalse(Arena.canMoveRight(mockActivePuyo));
    }

    @Test
    public void canMoveRightVerticalPairInMiddle() {
        Position firstPos = new Position(3, 3);
        Position secondPos = new Position(3, 4);
        Mockito.when(mockActivePuyo.getFirstPos()).thenReturn(firstPos);
        Mockito.when(mockActivePuyo.getSecondPos()).thenReturn(secondPos);

        Assertions.assertTrue(Arena.canMoveRight(mockActivePuyo));
    }

    @Test
    public void isValidPositionInsideBoundsAndEmpty() {
        Grid grid = new Grid();
        Position position = new Position(3, 2);

        Assertions.assertTrue(Arena.isValidPosition(position, grid));
    }

    @Test
    public void isValidPosition_OutsideBounds_ReturnsFalse() {
        Grid grid = new Grid();

        Position negativePosition = new Position(-1, 2);
        Position outOfBoundsPosition = new Position(Grid.COLUMNS, 2);
        Position negativeYPosition = new Position(3, -1);
        Position outOfBoundsYPosition = new Position(3, Grid.ROWS);

        Assertions.assertFalse(Arena.isValidPosition(negativePosition, grid));
        Assertions.assertFalse(Arena.isValidPosition(outOfBoundsPosition, grid));
        Assertions.assertFalse(Arena.isValidPosition(negativeYPosition, grid));
        Assertions.assertFalse(Arena.isValidPosition(outOfBoundsYPosition, grid));
    }

    @Test
    public void gameOverWithEmptyTopRows() {
        Grid grid = new Grid();

        Assertions.assertFalse(Arena.gameOver(grid));
    }

    @Test
    public void gameOverWithPuyoInSpawnArea() throws IOException {
        Grid grid = new Grid();

        grid.getGrid()[0][2] = new Puyo(new Position(2, 0));
        grid.getGrid()[0][3] = new Puyo(new Position(3, 0));

        Assertions.assertTrue(Arena.gameOver(grid));
    }
}