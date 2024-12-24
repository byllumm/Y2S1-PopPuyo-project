package poppuyo.model.grid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import poppuyo.model.Puyo;
import poppuyo.utils.puyoutils.Position;
import poppuyo.viewer.PuyoViewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ChainDetectorTest {
    @Mock private Grid mockGrid;
    @Mock private Puyo mockPuyo;
    @Mock private PuyoViewer mockPuyoViewer;
    private ChainDetector chainDetector;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        chainDetector = new ChainDetector(mockGrid);
    }

    @Test
    public void sIsValidPositionWithNullsValidPosition() {
        Position position = new Position(0, 0);
        Assertions.assertTrue(ChainDetector.isValidPositionWithNulls(position));
    }

    @Test
    public void isValidPositionWithNullsInvalidPosition() {
        Position position = new Position(-1, 0);
        Assertions.assertFalse(ChainDetector.isValidPositionWithNulls(position));
    }

    @Test
    public void detectChainEmptyGrid() throws IOException {
        Mockito.when(mockGrid.getPuyo(anyInt(), anyInt())).thenReturn(null);

        List<List<Position>> chains = chainDetector.detectChain();

        Assertions.assertTrue(chains.isEmpty());
    }

    @Test
    public void detectChainValidChain() throws IOException {
        Puyo mockPuyo1 = mock(Puyo.class);
        Puyo mockPuyo2 = mock(Puyo.class);
        Puyo mockPuyo3 = mock(Puyo.class);
        Puyo mockPuyo4 = mock(Puyo.class);

        Mockito.when(mockPuyo1.getColor()).thenReturn("Red");
        Mockito.when(mockPuyo2.getColor()).thenReturn("Red");
        Mockito.when(mockPuyo3.getColor()).thenReturn("Red");
        Mockito.when(mockPuyo4.getColor()).thenReturn("Red");

        Mockito.when(mockGrid.getPuyo(0, 0)).thenReturn(mockPuyo1);
        Mockito.when(mockGrid.getPuyo(1, 0)).thenReturn(mockPuyo2);
        Mockito.when(mockGrid.getPuyo(2, 0)).thenReturn(mockPuyo3);
        Mockito.when(mockGrid.getPuyo(3, 0)).thenReturn(mockPuyo4);

        Mockito.when(mockGrid.getPuyo(0, 1)).thenReturn(null);
        Mockito.when(mockGrid.getPuyo(1, 1)).thenReturn(null);
        Mockito.when(mockGrid.getPuyo(2, 1)).thenReturn(null);
        Mockito.when(mockGrid.getPuyo(3, 1)).thenReturn(null);

        List<List<Position>> chains = chainDetector.detectChain();

        Assertions.assertEquals(1, chains.size());
        Assertions.assertEquals(4, chains.get(0).size());
    }

    @Test
    public void detectChainNoValidChain() throws IOException {
        Puyo mockPuyo1 = mock(Puyo.class);
        Puyo mockPuyo2 = mock(Puyo.class);

        Mockito.when(mockPuyo1.getColor()).thenReturn("Red");
        Mockito.when(mockPuyo2.getColor()).thenReturn("Red");

        Mockito.when(mockGrid.getPuyo(0, 0)).thenReturn(mockPuyo1);
        Mockito.when(mockGrid.getPuyo(1, 0)).thenReturn(mockPuyo2);

        Mockito.when(mockGrid.getPuyo(0, 1)).thenReturn(null);
        Mockito.when(mockGrid.getPuyo(1, 1)).thenReturn(null);

        List<List<Position>> chains = chainDetector.detectChain();

        Assertions.assertTrue(chains.isEmpty());
    }

    @Test
    public void dfs() throws IOException {
        Puyo mockPuyo = mock(Puyo.class);
        Mockito.when(mockPuyo.getColor()).thenReturn("Red");

        Mockito.when(mockGrid.getPuyo(0, 0)).thenReturn(mockPuyo);

        Puyo mockNeighbor = mock(Puyo.class);
        Mockito.when(mockNeighbor.getColor()).thenReturn("Red");
        Mockito.when(mockGrid.getPuyo(1, 0)).thenReturn(mockNeighbor);
        Mockito.when(mockGrid.getPuyo(2, 0)).thenReturn(mockNeighbor);

        List<Position> chain = new ArrayList<>();

        chainDetector.dfs(new Position(0, 0), mockPuyo, new boolean[Grid.ROWS][Grid.COLUMNS], chain);

        Assertions.assertTrue(chain.size() >= 3);
    }


    @Test
    public void updatePuyoSprite() throws IOException {
        Mockito.when(mockPuyo.getColor()).thenReturn("red");
        Mockito.when(mockPuyo.getAdjacent()).thenReturn(0b0001);

        chainDetector.updatePuyoSprite(0, 0, mockPuyo);

        Mockito.verify(mockPuyo).setPuyoViewer(any(PuyoViewer.class));
    }

    @Test
    public void chainDetectionWithDifferentColors() throws IOException {
        Puyo redPuyo = mock(Puyo.class);
        Puyo bluePuyo = mock(Puyo.class);

        Mockito.when(mockGrid.getPuyo(0, 0)).thenReturn(redPuyo);
        Mockito.when(mockGrid.getPuyo(1, 0)).thenReturn(bluePuyo);
        Mockito.when(redPuyo.getColor()).thenReturn("red");
        Mockito.when(bluePuyo.getColor()).thenReturn("blue");

        List<List<Position>> chains = chainDetector.detectChain();

        Assertions.assertTrue(chains.isEmpty());
    }
}
