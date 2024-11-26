import elements.Puyo;
import utils.puyoutils.Position;
import utils.puyoutils.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PuyoTest {
    private Puyo puyo;
    private Position initialPos;

    /*
    @BeforeEach
    void setUp(){
        initialPos = new Position(5, 10);
        puyo = new Puyo(initialPos);
    }

    @Test
    void testPuyoInitialization(){
        assertEquals(initialPos, puyo.getPosition());
        assertNotNull(puyo.getColor());
    }

    @Test
    void testGetPosition(){
        assertEquals(initialPos, puyo.getPosition());
    }

    @Test
    void testSetPosition(){
        Position newPos = new Position(1, 2);
        puyo.setPosition(newPos);

        assertEquals(newPos, puyo.getPosition());
    }

    @Test
    void testGetColor(){
        Color color = puyo.getColor();
        assertNotNull(color);
    }

    @Test
    void testRandomColorUniqueness(){
        int numPuyos = 50;
        Set<Color> uniqueColors = new HashSet<>();
        List<Puyo> puyos = new ArrayList<>();

        for(int i = 0; i < numPuyos; i++){
            Puyo puyo = new Puyo(new Position(1, i));
            puyos.add(puyo);
            uniqueColors.add(puyo.getColor());
        }

        assertTrue(uniqueColors.size() > 1);
    }

    @Test
    void testDraw(){
        TextGraphics graphics = mock(TextGraphics.class);

        Color puyoColor = puyo.getColor();
        Position puyoPos = puyo.getPosition();

        puyo.draw(graphics, null);

        verify(graphics).setBackgroundColor(TextColor.Factory.fromString(puyoColor.getColor()));
        verify(graphics).enableModifiers(SGR.BOLD);
        verify(graphics).putString(new TerminalPosition(puyoPos.getX(), puyoPos.getY()), " ");
    }
     */
}
