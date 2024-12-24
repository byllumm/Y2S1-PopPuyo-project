package poppuyo.model.grid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import poppuyo.model.Puyo;
import poppuyo.utils.puyoutils.Position;

public class GridTest {
    @Mock private Puyo mockPuyo;
    private Grid grid;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        grid = new Grid();
    }

    @Test
    public void getPuyo() {
        grid.setPuyo(0, 0, mockPuyo);
        Puyo result = grid.getPuyo(0, 0);
        Assertions.assertEquals(mockPuyo, result);
    }

    @Test
    public void setPuyo() {
        grid.setPuyo(1, 1, mockPuyo);

        Assertions.assertEquals(mockPuyo, grid.getPuyo(1, 1));
    }

    @Test
    public void isEmptyWhenCellIsEmpty() {
        boolean result = Grid.isEmpty(0, 0);

        Assertions.assertTrue(result);
    }

    @Test
    public void isEmptyWhenCellIsNotEmpty() {
        grid.setPuyo(0, 0, mockPuyo);
        boolean result = Grid.isEmpty(0, 0);

        Assertions.assertFalse(result);
    }

    @Test
    public void translatePosition() {
        Position position = new Position(1, 2);
        Position result = Grid.translatePosition(position);

        Assertions.assertEquals(new Position(40, 72), result);
    }
}

