package poppuyo.utils.puyoutils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import poppuyo.model.Puyo;

import java.io.IOException;

class PuyoPairTest {
    @Mock private Puyo mockFirstPuyo;
    @Mock private Puyo mockSecondPuyo;
    private PuyoPair puyoPair;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        puyoPair = new PuyoPair(mockFirstPuyo, mockSecondPuyo);
    }

    @Test
    public void constructor() {
        Assertions.assertNotNull(puyoPair.getController());
        Assertions.assertEquals(mockFirstPuyo, puyoPair.getFirstPuyo());
        Assertions.assertEquals(mockSecondPuyo, puyoPair.getSecondPuyo());
    }

    @Test
    public void getFirstPos() {
        Position firstPos = new Position(2, 0);
        Mockito.when(mockFirstPuyo.getPosition()).thenReturn(firstPos);
        Position firstPosReturned = puyoPair.getFirstPos();

        Assertions.assertEquals(firstPos, firstPosReturned);
    }

    @Test
    public void getSecondPos() {
        Position secondPos = new Position(2, 0);
        Mockito.when(mockSecondPuyo.getPosition()).thenReturn(secondPos);
        PuyoPair puyoPair = new PuyoPair(mockFirstPuyo, mockSecondPuyo);
        Position secondPosReturned = puyoPair.getSecondPos();

        Assertions.assertEquals(secondPos, secondPosReturned);
    }

    @Test
    public void setFirstPuyo() {
        Puyo newFirstPuyo = Mockito.mock(Puyo.class);
        puyoPair.setFirstPuyo(newFirstPuyo);

        Assertions.assertEquals(newFirstPuyo, puyoPair.getFirstPuyo());
    }

    @Test
    public void fetSecondPuyo() {
        Puyo newSecondPuyo = Mockito.mock(Puyo.class);
        puyoPair.setSecondPuyo(newSecondPuyo);

        Assertions.assertEquals(newSecondPuyo, puyoPair.getSecondPuyo());
    }

    @Test
    public void setFirstPos() throws IOException {
        Position newPos = new Position(4, 0);
        Puyo realFirstPuyo = new Puyo(new Position(2, 0));
        puyoPair = new PuyoPair(realFirstPuyo, mockSecondPuyo);
        puyoPair.setFirstPos(newPos);

        Assertions.assertEquals(newPos, puyoPair.getFirstPos());
    }

    @Test
    public void setSecondPos() throws IOException {
        Position newPos = new Position(4, 0);
        Puyo realSecondPuyo = new Puyo(new Position(3, 0));
        puyoPair = new PuyoPair(mockFirstPuyo, realSecondPuyo);
        puyoPair.setSecondPos(newPos);

        Assertions.assertEquals(newPos, puyoPair.getSecondPos());
    }

    @Test
    public void spawnPuyoPair() throws IOException {
        PuyoPair newPuyoPair = PuyoPair.spawnPuyoPair();

        Assertions.assertNotNull(newPuyoPair);
        Assertions.assertEquals(2, newPuyoPair.getFirstPos().getX());
        Assertions.assertEquals(0, newPuyoPair.getFirstPos().getY());
        Assertions.assertEquals(3, newPuyoPair.getSecondPos().getX());
        Assertions.assertEquals(0, newPuyoPair.getSecondPos().getY());
    }
}

