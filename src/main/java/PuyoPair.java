import com.googlecode.lanterna.graphics.TextGraphics;

public class PuyoPair implements Drawable{
    private Puyo firstPuyo;
    private Puyo secondPuyo;
    private RotationState rotationState;

    public enum RotationState{
        RIGHT, UP, LEFT, DOWN;
    }

    public PuyoPair(Puyo firstPuyo, Puyo secondPuyo){
        this.firstPuyo = firstPuyo;
        this.secondPuyo = secondPuyo;
        this.rotationState = RotationState.RIGHT; //The second puyo starts on the right of the first one
    }

    public Puyo getFirstPuyo(){
        return firstPuyo;
    }

    public Puyo getSecondPuyo(){
        return secondPuyo;
    }

    //Wrapper functions
    public Position getFirstPos(){
        return firstPuyo.getPosition();
    }
    public Position getSecondPos(){
        return secondPuyo.getPosition();
    }

    public Position rotateUp(){
        int x = this.getFirstPos().getX();
        int y = this.getSecondPos().getY();
        return new Position(x, y - 1);
    }

    public Position rotateDown(){
        int x = this.getFirstPos().getX();
        int y = this.getSecondPos().getY();
        return new Position(x, y + 1);
    }

    public Position rotateLeft(){
        int x = this.getFirstPos().getX();
        int y = this.getSecondPos().getY();
        return new Position(x - 1, y);
    }
    public Position rotateRight(){
        int x = this.getFirstPos().getX();
        int y = this.getSecondPos().getY();
        return new Position(x + 1, y);
    }

    @Override
    public void draw(TextGraphics graphics){
        firstPuyo.draw(graphics);
        secondPuyo.draw(graphics);
    }
}
