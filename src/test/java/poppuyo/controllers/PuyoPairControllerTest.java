package poppuyo.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import poppuyo.utils.puyoutils.Position;
import poppuyo.utils.puyoutils.PuyoPair;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

public class PuyoPairControllerTest {
    @Mock private PuyoPair mockPuyoPair;
    @Mock private Position mockFirstPosition;
    @Mock private Position mockSecondPosition;
    private PuyoPairController puyoPairController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        puyoPairController = new PuyoPairController(mockPuyoPair);

        Mockito.when(mockPuyoPair.getFirstPos()).thenReturn(mockFirstPosition);
        Mockito.when(mockPuyoPair.getSecondPos()).thenReturn(mockSecondPosition);
        Mockito.when(mockFirstPosition.getX()).thenReturn(5);
        Mockito.when(mockFirstPosition.getY()).thenReturn(5);
    }

    @Test
    public void constructor() {
        Assertions.assertNotNull(puyoPairController);
        Assertions.assertEquals(PuyoPairController.RotationState.RIGHT, puyoPairController.getRotationState());
    }

    @Test
    public void getRotationState() {
        // This test does pretty much the same thing as the constructor test... May be redundant
        Assertions.assertEquals(PuyoPairController.RotationState.RIGHT, puyoPairController.getRotationState());
    }

    @Test
    public void setRotationState() {
        puyoPairController.setRotationState(PuyoPairController.RotationState.LEFT);
        Assertions.assertEquals(PuyoPairController.RotationState.LEFT, puyoPairController.getRotationState());
    }

    @Test
    public void rotateUp() {
        Position newPosition = puyoPairController.rotateUp();
        Assertions.assertEquals(new Position(5, 4), newPosition);
    }

    @Test
    public void rotateDown() {
        Position newPosition = puyoPairController.rotateDown();
        Assertions.assertEquals(new Position(5, 6), newPosition);
    }

    @Test
    public void rotateLeft() {
        Position newPosition = puyoPairController.rotateLeft();
        Assertions.assertEquals(new Position(4, 5), newPosition);
    }

    @Test
    public void rotateRight() {
        Position newPosition = puyoPairController.rotateRight();
        Assertions.assertEquals(new Position(6, 5), newPosition);
    }

    @Test
    public void moveDown() {
        puyoPairController.moveDown();
        verify(mockPuyoPair).setFirstPos(argThat(pos -> pos.getX() == mockFirstPosition.getX()
                                                    && pos.getY() == mockFirstPosition.getY() + 1));
        verify(mockPuyoPair).setSecondPos(argThat(pos -> pos.getX() == mockSecondPosition.getX()
                                                    && pos.getY() == mockSecondPosition.getY() + 1));
    }

    @Test
    public void moveLeft() {
        puyoPairController.moveLeft();
        verify(mockPuyoPair).setFirstPos(argThat(pos -> pos.getX() == mockFirstPosition.getX() - 1
                                                    && pos.getY() == mockFirstPosition.getY()));
        verify(mockPuyoPair).setSecondPos(argThat(pos -> pos.getX() == mockSecondPosition.getX() - 1
                                                    && pos.getY() == mockSecondPosition.getY()));
    }

    @Test
    public void moveRight() {
        puyoPairController.moveRight();
        verify(mockPuyoPair).setFirstPos(argThat(pos -> pos.getX() == mockFirstPosition.getX() + 1
                                                    && pos.getY() == mockFirstPosition.getY()));
        verify(mockPuyoPair).setSecondPos(argThat(pos -> pos.getX() == mockSecondPosition.getX() + 1
                                                    && pos.getY() == mockSecondPosition.getY() ));
    }

    @Test
    public void findIndex() {
        Assertions.assertEquals(0, puyoPairController.findIndex(PuyoPairController.RotationState.UP));
        Assertions.assertEquals(1, puyoPairController.findIndex(PuyoPairController.RotationState.RIGHT));
        Assertions.assertEquals(2, puyoPairController.findIndex(PuyoPairController.RotationState.DOWN));
        Assertions.assertEquals(3, puyoPairController.findIndex(PuyoPairController.RotationState.LEFT));
        Assertions.assertEquals(-1, puyoPairController.findIndex(null));
    }

    @Test
    public void getNextState() {
        // Clockwise Rotations
        Assertions.assertEquals(PuyoPairController.RotationState.RIGHT, puyoPairController.getNextState(PuyoPairController.RotationState.UP, true));
        Assertions.assertEquals(PuyoPairController.RotationState.DOWN, puyoPairController.getNextState(PuyoPairController.RotationState.RIGHT, true));
        Assertions.assertEquals(PuyoPairController.RotationState.LEFT, puyoPairController.getNextState(PuyoPairController.RotationState.DOWN, true));
        Assertions.assertEquals(PuyoPairController.RotationState.UP, puyoPairController.getNextState(PuyoPairController.RotationState.LEFT, true));

        // Counter-clockwise Rotations
        Assertions.assertEquals(PuyoPairController.RotationState.LEFT, puyoPairController.getNextState(PuyoPairController.RotationState.UP, false));
        Assertions.assertEquals(PuyoPairController.RotationState.UP, puyoPairController.getNextState(PuyoPairController.RotationState.RIGHT, false));
        Assertions.assertEquals(PuyoPairController.RotationState.RIGHT, puyoPairController.getNextState(PuyoPairController.RotationState.DOWN, false));
        Assertions.assertEquals(PuyoPairController.RotationState.DOWN, puyoPairController.getNextState(PuyoPairController.RotationState.LEFT, false));
    }

    @Test
    public void rotate() {
        // Starts at RIGHT
        Position newPosition;

        // Clockwise Rotations

        // Rotate clockwise to DOWN
        newPosition = puyoPairController.rotate(true);
        Assertions.assertEquals(new Position(5, 6), newPosition);
        Assertions.assertEquals(PuyoPairController.RotationState.DOWN, puyoPairController.getRotationState());

        // Rotate clockwise to LEFT
        newPosition = puyoPairController.rotate(true);
        Assertions.assertEquals(new Position(4, 5), newPosition);
        Assertions.assertEquals(PuyoPairController.RotationState.LEFT, puyoPairController.getRotationState());

        // Rotate clockwise to UP
        newPosition = puyoPairController.rotate(true);
        Assertions.assertEquals(new Position(5, 4), newPosition);
        Assertions.assertEquals(PuyoPairController.RotationState.UP, puyoPairController.getRotationState());

        // Rotate clockwise to RIGHT
        newPosition = puyoPairController.rotate(true);
        Assertions.assertEquals(new Position(6, 5), newPosition);
        Assertions.assertEquals(PuyoPairController.RotationState.RIGHT, puyoPairController.getRotationState());


        // Counter-clockwise Rotations

        // Rotate counter-clockwise to UP
        newPosition = puyoPairController.rotate(false);
        Assertions.assertEquals(new Position(5, 4), newPosition);
        Assertions.assertEquals(PuyoPairController.RotationState.UP, puyoPairController.getRotationState());

        // Rotate counter-clockwise to LEFT
        newPosition = puyoPairController.rotate(false);
        Assertions.assertEquals(new Position(4, 5), newPosition);
        Assertions.assertEquals(PuyoPairController.RotationState.LEFT, puyoPairController.getRotationState());

        // Rotate counter-clockwise to DOWN
        newPosition = puyoPairController.rotate(false);
        Assertions.assertEquals(new Position(5, 6), newPosition);
        Assertions.assertEquals(PuyoPairController.RotationState.DOWN, puyoPairController.getRotationState());

        // Rotate counter-clockwise to RIGHT
        newPosition = puyoPairController.rotate(false);
        Assertions.assertEquals(new Position(6, 5), newPosition);
        Assertions.assertEquals(PuyoPairController.RotationState.RIGHT, puyoPairController.getRotationState());
    }

    @Test
    public void revertRotationState() {
        // Revert clockwise rotation

        // Revert clockwise to LEFT
        puyoPairController.setRotationState(PuyoPairController.RotationState.UP);
        puyoPairController.revertRotationState(true);
        Assertions.assertEquals(PuyoPairController.RotationState.LEFT, puyoPairController.getRotationState());

        // Revert clockwise to UP
        puyoPairController.setRotationState(PuyoPairController.RotationState.RIGHT);
        puyoPairController.revertRotationState(true);
        Assertions.assertEquals(PuyoPairController.RotationState.UP, puyoPairController.getRotationState());

        // Revert clockwise to RIGHT
        puyoPairController.setRotationState(PuyoPairController.RotationState.DOWN);
        puyoPairController.revertRotationState(true);
        Assertions.assertEquals(PuyoPairController.RotationState.RIGHT, puyoPairController.getRotationState());

        // Revert clockwise to DOWN
        puyoPairController.setRotationState(PuyoPairController.RotationState.LEFT);
        puyoPairController.revertRotationState(true);
        Assertions.assertEquals(PuyoPairController.RotationState.DOWN, puyoPairController.getRotationState());


        // Revert counter-clockwise rotation

        // Revert counter-clockwise to RIGHT
        puyoPairController.setRotationState(PuyoPairController.RotationState.UP);
        puyoPairController.revertRotationState(false);
        Assertions.assertEquals(PuyoPairController.RotationState.RIGHT, puyoPairController.getRotationState());

        // Revert counter-clockwise to DOWN
        puyoPairController.setRotationState(PuyoPairController.RotationState.RIGHT);
        puyoPairController.revertRotationState(false);
        Assertions.assertEquals(PuyoPairController.RotationState.DOWN, puyoPairController.getRotationState());

        // Revert counter-clockwise to LEFT
        puyoPairController.setRotationState(PuyoPairController.RotationState.DOWN);
        puyoPairController.revertRotationState(false);
        Assertions.assertEquals(PuyoPairController.RotationState.LEFT, puyoPairController.getRotationState());

        // Revert counter-clockwise to UP
        puyoPairController.setRotationState(PuyoPairController.RotationState.LEFT);
        puyoPairController.revertRotationState(false);
        Assertions.assertEquals(PuyoPairController.RotationState.UP, puyoPairController.getRotationState());
    }
}
