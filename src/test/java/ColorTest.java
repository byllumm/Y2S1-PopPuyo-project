import customExceptions.ColorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ColorTest {
    @Test
    public void blueTest() {
        Color color = new Color("blue");
        Assertions.assertEquals(color.getColor(), "#0000FF");
    }

    @Test
    public void redTest() {
        Color color = new Color("red");
        Assertions.assertEquals(color.getColor(), "#FF0000");
    }

    @Test
    public void greenTest() {
        Color color = new Color("green");
        Assertions.assertEquals(color.getColor(), "#00FF00");
    }

    @Test
    public void yellowTest() {
        Color color = new Color("yellow");
        Assertions.assertEquals(color.getColor(), "#FFFF00");
    }

    @Test
    public void purpleTest() {
        Color color = new Color("purple");
        Assertions.assertEquals(color.getColor(), "#8A00C2");
    }

    @Test
    public void unsupportedColorTest() {
        Assertions.assertThrows(ColorException.class, () -> { Color color = new Color("black"); });
    }
}
