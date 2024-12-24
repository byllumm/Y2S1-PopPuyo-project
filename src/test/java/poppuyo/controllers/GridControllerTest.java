package poppuyo.controllers;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import poppuyo.model.Puyo;
import poppuyo.model.grid.ChainDetector;
import poppuyo.model.grid.Grid;
import poppuyo.model.grid.GridManager;
import poppuyo.utils.puyoutils.Position;
import poppuyo.utils.puyoutils.PuyoPair;
import poppuyo.viewer.GridViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class GridControllerTest {

    @Mock private Grid mockGrid;
    @Mock private GridManager mockGridManager;
    @Mock private ChainDetector mockChainDetector;
    @Mock private GridViewer mockGridViewer;
    @Mock private PuyoPair mockActivePuyo;
    @Mock private Puyo mockPuyo;
    @Mock private TextGraphics mockTextGraphics;

    private GridController gridController;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);
        gridController = new GridController(mockGrid, mockGridViewer);

        // Use reflection to inject mocks into private fields
        setPrivateField(gridController, "gridManager", mockGridManager);
        setPrivateField(gridController, "chainDetector", mockChainDetector);
    }

    // Helper method to set private fields using reflection
    private void setPrivateField(Object obj, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }


    @Test
    void getPuyoAt() {
        int row = 0;
        int col = 1;
        Mockito.when(mockGrid.getPuyo(0, 1)).thenReturn(mockPuyo);

        Puyo result = gridController.getPuyoAt(row, col);

        Assertions.assertEquals(mockPuyo, result);
        Mockito.verify(mockGrid).getPuyo(row, col);
    }

    @Test
    void applyGravity() {
        Mockito.when(mockGridManager.applyGravity()).thenReturn(true).thenReturn(false);

        Assertions.assertTrue(gridController.applyGravity());
        Mockito.verify(mockGridManager).applyGravity();

        Assertions.assertFalse(gridController.applyGravity());
        Mockito.verify(mockGridManager, times(2)).applyGravity();
    }

    @Test
    void integrateGrid() {
        gridController.integrateGrid(mockActivePuyo);

        Mockito.verify(mockGridManager).integrateGrid(mockActivePuyo);
    }

    @Test
    void detectChain() throws IOException {
        List<List<Position>> mockChains = Collections.singletonList(Collections.singletonList(new Position(0, 0)));
        Mockito.when(mockChainDetector.detectChain()).thenReturn(mockChains);

        List<List<Position>> result = gridController.detectChain();

        Assertions.assertEquals(mockChains, result);
        Mockito.verify(mockChainDetector).detectChain();
    }

    @Test
    void updatePuyoSprite() throws IOException {
        int row = 0;
        int col = 1;
        Mockito.when(mockGrid.getPuyo(row, col)).thenReturn(mockPuyo);

        gridController.updatePuyoSprite(row, col, mockPuyo);
        Mockito.verify(mockChainDetector).updatePuyoSprite(row, col, mockPuyo);
    }

    @Test
    void draw() {
        gridController.draw(mockTextGraphics);
        Mockito.verify(mockGridViewer).draw(mockTextGraphics, new Position(8, 8));
    }
}
