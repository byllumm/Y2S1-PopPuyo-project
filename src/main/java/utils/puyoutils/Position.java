package utils.puyoutils;

public class Position {
    // Attributes
    private int x, y;


    // Constructors
    public Position() {
        this.x = 0;
        this.y = 0;
    }

    public Position(int x_, int y_) {
        this.x = x_;
        this.y = y_;
    }


    // Getters
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }


    // Setters
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    // This override makes it easier to check position overlap
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        Position p = (Position) o;
        return this.x == p.getX() && this.y == p.getY();
    }
}
