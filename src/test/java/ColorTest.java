import custom_exceptions.ColorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import elements.puyo_utils.Color;

public class ColorTest {
    @Test
    public void blueTest() {
        Color color = new Color("blue");
        Assertions.assertEquals(color.getColor(), "#0080C0");
    }

    @Test
    public void redTest() {
        Color color = new Color("red");
        Assertions.assertEquals(color.getColor(), "#802000");
    }

    @Test
    public void greenTest() {
        Color color = new Color("green");
        Assertions.assertEquals(color.getColor(), "#60C060");
    }

    @Test
    public void yellowTest() {
        Color color = new Color("yellow");
        Assertions.assertEquals(color.getColor(), "#E0E040");
    }

    @Test
    public void purpleTest() {
        Color color = new Color("purple");
        Assertions.assertEquals(color.getColor(), "#A040E0");
    }

    @Test
    public void unsupportedColorTest() {
        Assertions.assertThrows(ColorException.class, () -> { Color color = new Color("black"); });
    }
}
