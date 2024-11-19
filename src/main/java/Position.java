public class Position {
    private int x, y;

    public Position() {
        this.x = 0;
        this.y = 0;
    }

    public Position(int x_, int y_) {
        this.x = x_;
        this.y = y_;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
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
