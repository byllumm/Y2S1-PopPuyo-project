package poppuyo.gamestates.controllers;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import poppuyo.gamestates.GameState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import poppuyo.utils.puyoutils.Position;
import poppuyo.viewer.CreditsStateViewer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreditsStateControllerTest {

    @Mock private CreditsStateViewer mockCreditsStateViewer;
    @Mock private TextGraphics mockGraphics;
    private CreditsStateController controller;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);

        controller = new CreditsStateController();
        controller.creditsStateViewer = mockCreditsStateViewer;
    }

    @Test
    public void constructor() throws IOException {
        CreditsStateController newController = new CreditsStateController();
        assertNotNull(newController.creditsStateViewer);
    }

    @Test
    public void testProcessKey() {
        KeyStroke keyStroke = new KeyStroke('m', false, false);
        GameState.state = GameState.CREDITS;

        controller.processKey(keyStroke);

        assertEquals(GameState.MENU, GameState.state);
    }

    @Test
    public void testInvalidKey() {
        KeyStroke keyStroke = new KeyStroke('x', false, false);
        GameState.state = GameState.CREDITS;

        controller.processKey(keyStroke);

        assertEquals(GameState.CREDITS, GameState.state);
    }

    @Test
    public void testDraw() {
        Position stubPosition = Mockito.mock(Position.class);
        controller.draw(mockGraphics, stubPosition);
        verify(mockCreditsStateViewer, Mockito.times(1)).draw(mockGraphics, stubPosition);
    }

    @Test
    public void nullGraphicsDraw() {
        Position stubPosition = Mockito.mock(Position.class);

        doThrow(new NullPointerException()).when(mockCreditsStateViewer).draw(null, stubPosition);

        Assertions.assertThrows(NullPointerException.class, () -> { controller.draw(null, stubPosition); });
    }
}