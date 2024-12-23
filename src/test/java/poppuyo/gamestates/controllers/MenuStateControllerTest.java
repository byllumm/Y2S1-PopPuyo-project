package poppuyo.gamestates.controllers;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import poppuyo.model.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import poppuyo.utils.custom_exceptions.ResourceException;
import poppuyo.utils.puyoutils.Position;
import poppuyo.viewer.MenuStateViewer;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MenuStateControllerTest {
    @Mock private Menu mockMenuModel;
    @Mock private MenuStateViewer mockMenuStateViewer;
    @Mock private TextGraphics mockGraphics;
    private MenuStateController controller;

    @BeforeEach
    public void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);

        controller = new MenuStateController();
        controller.menuModel = mockMenuModel;
        controller.menuStateViewer = mockMenuStateViewer;
    }

    @Test
    public void constructor() throws IOException {
        MenuStateController newController = new MenuStateController();
        assertNotNull(newController.menuModel);
        assertNotNull(newController.menuStateViewer);
    }

    @Test
    public void testProcessKeyArrowUp() {
        KeyStroke keyStroke = new KeyStroke(KeyType.ArrowUp, false, false);
        controller.processKey(keyStroke);
        verify(mockMenuModel, Mockito.times(1)).moveSelectionUp();
    }

    @Test
    public void testProcessKeyArrowDown() {
        KeyStroke keyStroke = new KeyStroke(KeyType.ArrowDown, false, false);
        controller.processKey(keyStroke);
        verify(mockMenuModel, Mockito.times(1)).moveSelectionDown();
    }

    @Test
    public void testProcessKeyEnter() {
        KeyStroke keyStroke = new KeyStroke(KeyType.Enter, false, false);
        controller.processKey(keyStroke);
        verify(mockMenuModel, Mockito.times(1)).selectButton();
    }

    @Test
    public void testDrawForButton0() throws ResourceException {
        when(mockMenuModel.getSelectedButton()).thenReturn(0);

        Position stubPosition = Mockito.mock(Position.class);
        controller.draw(mockGraphics, stubPosition);

        verify(mockMenuStateViewer, Mockito.times(1)).setState(0);
        verify(mockMenuStateViewer, Mockito.times(1)).draw(mockGraphics, stubPosition);
    }

    @Test
    public void testDrawForButton1() throws ResourceException {
        when(mockMenuModel.getSelectedButton()).thenReturn(1);

        Position stubPosition = Mockito.mock(Position.class);
        controller.draw(mockGraphics, stubPosition);

        verify(mockMenuStateViewer, Mockito.times(1)).setState(1);
        verify(mockMenuStateViewer, Mockito.times(1)).draw(mockGraphics, stubPosition);
    }

    @Test
    public void testDrawForButton2() throws ResourceException {
        when(mockMenuModel.getSelectedButton()).thenReturn(2);

        Position stubPosition = Mockito.mock(Position.class);
        controller.draw(mockGraphics, stubPosition);

        verify(mockMenuStateViewer, Mockito.times(1)).setState(2);
        verify(mockMenuStateViewer, Mockito.times(1)).draw(mockGraphics, stubPosition);
    }

    @Test
    public void nullGraphicsDraw() {
        Position stubPosition = Mockito.mock(Position.class);

        doThrow(new NullPointerException()).when(mockMenuStateViewer).draw(null, stubPosition);

        assertThrows(NullPointerException.class, () -> { controller.draw(null, stubPosition); });
    }
}
