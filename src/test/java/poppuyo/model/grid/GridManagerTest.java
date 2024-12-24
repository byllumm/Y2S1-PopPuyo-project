package poppuyo.model.grid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import poppuyo.model.Puyo;
import poppuyo.utils.puyoutils.Position;
import poppuyo.utils.puyoutils.PuyoPair;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

class GridManagerTest {
    @Mock private Grid mockGrid;
    @Mock private Puyo mockPuyo;
    @Mock  private PuyoPair mockPuyoPair;
    @Mock private Position mockFirstPos;
    @Mock private Position mockSecondPos;
    private GridManager gridManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        gridManager = new GridManager(mockGrid);
    }

    @Test
    public void applyGravityWhenNoPuyosFall() {
        Puyo[][] gridArray = new Puyo[Grid.ROWS][Grid.COLUMNS];
        Grid.setGrid(gridArray);

        Mockito.when(mockGrid.getGrid()).thenReturn(gridArray);

        boolean result = gridManager.applyGravity();

        Assertions.assertFalse(result);
    }

    @Test
    public void applyGravityWhenPuyosFall() {
        Grid gridInstance = new Grid();
        Puyo[][] gridArray = new Puyo[Grid.ROWS][Grid.COLUMNS];

        Puyo mockPuyo = mock(Puyo.class);
        gridArray[0][0] = mockPuyo;

        gridInstance.setGrid(gridArray);

        GridManager gridManager = new GridManager(gridInstance);
        gridInstance.getGrid()[1][0] = null;

        boolean result = gridManager.applyGravity();

        Assertions.assertTrue(result);

        Assertions.assertEquals(mockPuyo, gridArray[1][0]);
        Assertions.assertNull(gridArray[0][0]);
    }

    @Test
    public void integrateGrid() {
        Mockito.when(mockPuyoPair.getFirstPos()).thenReturn(mockFirstPos);
        Mockito.when(mockPuyoPair.getSecondPos()).thenReturn(mockSecondPos);
        Mockito.when(mockFirstPos.getY()).thenReturn(0);
        Mockito.when(mockFirstPos.getX()).thenReturn(0);
        Mockito.when(mockSecondPos.getY()).thenReturn(1);
        Mockito.when(mockSecondPos.getX()).thenReturn(1);

        gridManager.integrateGrid(mockPuyoPair);

        Mockito.verify(mockGrid, times(1)).setPuyo(0, 0, mockPuyoPair.getFirstPuyo());
        Mockito.verify(mockGrid, times(1)).setPuyo(1, 1, mockPuyoPair.getSecondPuyo());
    }

    @Test
    public void setGrid() {
        Grid newGrid = Mockito.mock(Grid.class);
        gridManager.setGrid(newGrid);

        Assertions.assertSame(newGrid, gridManager.getGrid());
    }
}