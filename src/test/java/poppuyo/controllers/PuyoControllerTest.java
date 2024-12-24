package poppuyo.controllers;

import com.googlecode.lanterna.graphics.TextGraphics;
import poppuyo.model.Puyo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import poppuyo.utils.puyoutils.Position;
import poppuyo.viewer.PuyoViewer;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

public class PuyoControllerTest {
    @Mock private Puyo mockPuyoModel;
    @Mock private PuyoViewer mockPuyoViewer;
    @Mock private TextGraphics mockTextGraphics;
    private PuyoController puyoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        puyoController = new PuyoController(mockPuyoModel, mockPuyoViewer);
    }

    @Test
    public void constructor() {
        Assertions.assertNotNull(puyoController);
    }

    @Test
    public void getPuyoModel() {
        Assertions.assertSame(mockPuyoModel, puyoController.getPuyoModel());
    }

    @Test
    public void getPuyoViewer() {
        Assertions.assertSame(mockPuyoViewer, puyoController.getPuyoViewer());
    }

    @Test
    public void setPuyoModel() {
        Puyo newMockPuyo = Mockito.mock(Puyo.class);
        puyoController.setPuyoModel(newMockPuyo);
        Assertions.assertEquals(newMockPuyo, puyoController.getPuyoModel());
    }

    @Test
    public void setPuyoViewer() {
        PuyoViewer newMockPuyoViewer = Mockito.mock(PuyoViewer.class);
        puyoController.setPuyoViewer(newMockPuyoViewer);
        Assertions.assertEquals(newMockPuyoViewer, puyoController.getPuyoViewer());
    }

    @Test
    public void draw() {
        Position stubPosition = Mockito.mock(Position.class);
        puyoController.draw(mockTextGraphics, stubPosition);
        verify(mockPuyoViewer, Mockito.times(1)).draw(mockTextGraphics, stubPosition);
    }

    @Test
    public void nullGraphicsDraw() {
        Position stubPosition = Mockito.mock(Position.class);

        doThrow(new NullPointerException()).when(mockPuyoViewer).draw(null, stubPosition);

        Assertions.assertThrows(NullPointerException.class, () -> { puyoController.draw(null, stubPosition); });
    }
}
