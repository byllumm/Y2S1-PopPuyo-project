package controllers;

import com.googlecode.lanterna.graphics.TextGraphics;
import model.Puyo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import utils.puyoutils.Position;
import viewer.PuyoViewer;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

public class PuyoControllerTest {
    @Mock private Puyo mockPuyoModel;
    @Mock private PuyoViewer mockPuyoViewer;
    @Mock private TextGraphics mockTextGraphics;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void constructor() {
        PuyoController puyoController = new PuyoController(mockPuyoModel, mockPuyoViewer);
        Assertions.assertNotNull(puyoController);
    }

    @Test
    public void getPuyoModel() {
        PuyoController puyoController = new PuyoController(mockPuyoModel, mockPuyoViewer);
        Assertions.assertEquals(mockPuyoModel, puyoController.getPuyoModel());
    }

    @Test
    public void getPuyoViewer() {
        PuyoController puyoController = new PuyoController(mockPuyoModel, mockPuyoViewer);
        Assertions.assertEquals(mockPuyoViewer, puyoController.getPuyoViewer());
    }

    @Test
    public void setPuyoModel() {
        PuyoController puyoController = new PuyoController(mockPuyoModel, mockPuyoViewer);
        Puyo newMockPuyo = Mockito.mock(Puyo.class);
        puyoController.setPuyoModel(newMockPuyo);
        Assertions.assertEquals(newMockPuyo, puyoController.getPuyoModel());
    }

    @Test
    public void setPuyoViewer() {
        PuyoController puyoController = new PuyoController(mockPuyoModel, mockPuyoViewer);
        PuyoViewer newMockPuyoViewer = Mockito.mock(PuyoViewer.class);
        puyoController.setPuyoViewer(newMockPuyoViewer);
        Assertions.assertEquals(newMockPuyoViewer, puyoController.getPuyoViewer());
    }

    @Test
    public void draw() {
        PuyoController puyoController = new PuyoController(mockPuyoModel, mockPuyoViewer);
        Position stubPosition = Mockito.mock(Position.class);
        puyoController.draw(mockTextGraphics, stubPosition);
        verify(mockPuyoViewer, Mockito.times(1)).draw(mockTextGraphics, stubPosition);
    }

    @Test
    public void nullGraphicsDraw() {
        PuyoController puyoController = new PuyoController(mockPuyoModel, mockPuyoViewer);
        Position stubPosition = Mockito.mock(Position.class);

        doThrow(new NullPointerException()).when(mockPuyoViewer).draw(null, stubPosition);

        Assertions.assertThrows(NullPointerException.class, () -> { puyoController.draw(null, stubPosition); });
    }
}
