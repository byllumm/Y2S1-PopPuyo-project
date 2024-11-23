package puyoUtils;

import com.googlecode.lanterna.graphics.TextGraphics;
import elements.Drawable;
import elements.Puyo;

public class PuyoPair implements Drawable {
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

    //Makes a puyo.Puyo Pair fall down
    public void moveDown() {
        Position firstPos = this.getFirstPos();
        Position secondPos = this.getSecondPos();
        firstPuyo.getPosition().setY(firstPos.getY() + 1);
        secondPuyo.getPosition().setY(secondPos.getY() + 1);
    }

    public void moveLeft(){
        Position firstPos = this.getFirstPos();
        Position secondPos = this.getSecondPos();
        firstPuyo.getPosition().setX(firstPos.getX() - 1);
        secondPuyo.getPosition().setX(secondPos.getX() - 1);
    }

    public void moveRight(){
        Position firstPos = this.getFirstPos();
        Position secondPos = this.getSecondPos();
        firstPuyo.getPosition().setX(firstPos.getX() + 1);
        secondPuyo.getPosition().setX(secondPos.getX() + 1);
    }

    public static PuyoPair spawnPuyoPair() {
        Position firstPos = new Position(2, 0);
        Position secondPos = new Position(3, 0);

        Puyo firstPuyo = new Puyo(firstPos);
        Puyo secondPuyo = new Puyo(secondPos);

        return new PuyoPair(firstPuyo, secondPuyo);
    }

    @Override
    public void draw(TextGraphics graphics){
        firstPuyo.draw(graphics);
        secondPuyo.draw(graphics);
    }
}
