public class PuyoPair {
    private Puyo firstPuyo;
    private Puyo secondPuyo;
    private RotationState rotationState;

    public enum RotationState{
        RIGHT, UP, LEFT, DOWN;
    }

    public PuyoPair(Puyo firstPuyo, Puyo secondPuyo){
        this.firstPuyo = firstPuyo;
        this.secondPuyo = secondPuyo;
        this.rotationState = rotationState.RIGHT; //The second puyo starts on the right of the first one
    }

    public Puyo getFirstPuyo(){
        return firstPuyo;
    }

    public Puyo getSecondPuyo(){
        return secondPuyo;
    }

    public Position getFirstPos(){
        return firstPuyo.getPosition();
    }

    public Position getSecondPos(){
        return secondPuyo.getPosition();
    }

    public Position rotateUp(PuyoPair puyoPair){
        int x = puyoPair.getFirstPos().getX();
        int y = puyoPair.getSecondPos().getY();
        Position newSecondPosition = new Position(x, y - 1);
        return newSecondPosition;
    }

    public Position rotateDown(PuyoPair puyoPair){
        int x = puyoPair.getFirstPos().getX();
        int y = puyoPair.getSecondPos().getY();
        return new Position(x, y + 1);
    }

    public Position rotateLeft(PuyoPair puyoPair){
        int x = puyoPair.getFirstPos().getX();
        int y = puyoPair.getSecondPos().getY();
        return new Position(x - 1, y);
    }
    public Position rotateRight(PuyoPair puyoPair){
        int x = puyoPair.getFirstPos().getX();
        int y = puyoPair.getSecondPos().getY();
        return new Position(x + 1, y);
    }
}
