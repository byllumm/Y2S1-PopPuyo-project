public class Puyo implements Drawable {
    private Position position;
    private Color color;

    public Position getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public Puyo(Position pos, Color color) {
        this.position = pos;
        this.color = color;
    }

    // TO BE IMPLEMENTED
    public void draw() {}
}
