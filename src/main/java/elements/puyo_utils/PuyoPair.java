package elements.puyo_utils;

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

    public void setFirstPuyo(Puyo firstPuyo){ this.firstPuyo = firstPuyo; }
    public void setSecondPuyo(Puyo secondPuyo){ this.secondPuyo = secondPuyo; }

    public Position getFirstPos(){
        return firstPuyo.getPosition();
    }
    public Position getSecondPos(){
        return secondPuyo.getPosition();
    }

    // Rotates puyo pair up, that is, moving the second puyo to above the first puyo
    public Position rotateUp(){
        int x = this.getFirstPos().getX();
        int y = this.getFirstPos().getY();
        return new Position(x, y - 1);
    }

    // Rotates puyo pair down, that is, moving the second puyo to below the first puyo
    public Position rotateDown(){
        int x = this.getFirstPos().getX();
        int y = this.getFirstPos().getY();
        return new Position(x, y + 1);
    }

    // Rotates puyo pair to the left, that is, moving the second puyo to the left of the first puyo
    public Position rotateLeft(){
        int x = this.getFirstPos().getX();
        int y = this.getFirstPos().getY();
        return new Position(x - 1, y);
    }

    // Rotates puyo pair to the right, that is, moving the second puyo to the right of the first puyo
    public Position rotateRight(){
        int x = this.getFirstPos().getX();
        int y = this.getFirstPos().getY();
        return new Position(x + 1, y);
    }

    // Makes current puyo pair fall down
    public void moveDown() {
        Position firstPos = this.getFirstPos();
        Position secondPos = this.getSecondPos();
        firstPuyo.getPosition().setY(firstPos.getY() + 1);
        secondPuyo.getPosition().setY(secondPos.getY() + 1);
    }

    // Moves current puyo pair 1 pixel to the left
    public void moveLeft(){
        Position firstPos = this.getFirstPos();
        Position secondPos = this.getSecondPos();
        firstPuyo.getPosition().setX(firstPos.getX() - 1);
        secondPuyo.getPosition().setX(secondPos.getX() - 1);
    }

    // Moves current puyo pair 1 pixel to the right
    public void moveRight(){
        Position firstPos = this.getFirstPos();
        Position secondPos = this.getSecondPos();
        firstPuyo.getPosition().setX(firstPos.getX() + 1);
        secondPuyo.getPosition().setX(secondPos.getX() + 1);
    }

    // Returns the position of the second puyo after rotating
    public Position rotate(boolean clockwise){
        Position newSecondPos = new Position(0, 0);
        if(clockwise){
            switch (rotationState){
                case UP:
                    newSecondPos = rotateRight();
                    this.rotationState = RotationState.RIGHT;
                    break;

                case RIGHT:
                    newSecondPos = rotateDown();
                    this.rotationState = RotationState.DOWN;
                    break;

                case DOWN:
                    newSecondPos = rotateLeft();
                    this.rotationState = RotationState.LEFT;
                    break;

                case LEFT:
                    newSecondPos = rotateUp();
                    this.rotationState = RotationState.UP;
                    break;
            }
        }

        else{
            switch(rotationState){
                case UP:
                    newSecondPos = rotateLeft();
                    this.rotationState = RotationState.LEFT;
                    break;

                case LEFT:
                    newSecondPos = rotateDown();
                    this.rotationState = RotationState.DOWN;
                    break;

                case DOWN:
                    newSecondPos = rotateRight();
                    this.rotationState = RotationState.RIGHT;
                    break;

                case RIGHT:
                    newSecondPos = rotateUp();
                    this.rotationState = RotationState.UP;
                    break;
            }
        }
        return newSecondPos;
    }

    // Reverts rotation state to what it was before
    public void revertRotationState(boolean clockwise){
        if(clockwise){
            switch(rotationState){
                case UP:
                    this.rotationState = RotationState.LEFT;
                    break;

                case LEFT:
                    this.rotationState = RotationState.DOWN;
                    break;

                case DOWN:
                    this.rotationState = RotationState.RIGHT;
                    break;

                case RIGHT:
                    this.rotationState = RotationState.UP;
                    break;
            }
        }

        else{
            switch(rotationState){
                case UP:
                    this.rotationState = RotationState.RIGHT;
                    break;

                case RIGHT:
                    this.rotationState = RotationState.DOWN;
                    break;

                case DOWN:
                    this.rotationState = RotationState.LEFT;
                    break;

                case LEFT:
                    this.rotationState = RotationState.UP;
                    break;
            }
        }
    }

    // Creates a new puyo pair in the middle of the first row on the screen
    public static PuyoPair spawnPuyoPair() {
        Position firstPos = new Position(2, 0);
        Position secondPos = new Position(3, 0);

        Puyo firstPuyo = new Puyo(firstPos);
        Puyo secondPuyo = new Puyo(secondPos);

        return new PuyoPair(firstPuyo, secondPuyo);
    }

    @Override
    public void draw(TextGraphics graphics, Position corner){
        firstPuyo.draw(graphics, null);
        secondPuyo.draw(graphics, null);
    }
}
