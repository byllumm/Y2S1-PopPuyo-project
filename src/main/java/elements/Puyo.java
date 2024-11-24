package elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import elements.puyo_utils.Color;
import elements.puyo_utils.Position;

public class Puyo implements Drawable {
    private Position position;
    private Color color;

    public Puyo(Position position){
        this.position = position;
        this.color = Color.getRandomColor();
    }

    public Position getPosition() {
        return position;
    }

    public Position setPosition(Position position) { return this.position = position; }

    public Color getColor() {
        return color;
    }

    @Override
    public void draw(TextGraphics graphics, Position corner) {
        Color puyoColor = getColor();
        graphics.setBackgroundColor(TextColor.Factory.fromString(puyoColor.getColor()));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), " ");
    }
}
