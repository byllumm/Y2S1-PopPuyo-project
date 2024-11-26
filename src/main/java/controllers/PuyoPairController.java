package controllers;

import elements.Puyo;
import utils.puyoutils.Position;
import utils.puyoutils.PuyoPair;

public class PuyoPairController {
    // Attributes
    private RotationState rotationState;
    private PuyoPair puyoPair;
    public static enum RotationState {
        RIGHT, UP, LEFT, DOWN;
    }


    // Constructor
    public PuyoPairController(PuyoPair pair) {
        this.rotationState = RotationState.RIGHT; //The second puyo starts on the right of the first one
        this.puyoPair = pair;
    }


    // Getter
    public RotationState getRotationState() {
        return rotationState;
    }


    // Setter
    public void setRotationState(RotationState rotationState) {
        this.rotationState = rotationState;
    }


    // Rotates puyo pair up, that is, moving the second puyo to above the first puyo
    public Position rotateUp() {
        int x = puyoPair.getFirstPos().getX();
        int y = puyoPair.getFirstPos().getY();
        return new Position(x, y - 1);
    }

    // Rotates puyo pair down, that is, moving the second puyo to below the first puyo
    public Position rotateDown() {
        int x = puyoPair.getFirstPos().getX();
        int y = puyoPair.getFirstPos().getY();
        return new Position(x, y + 1);
    }

    // Rotates puyo pair to the left, that is, moving the second puyo to the left of the first puyo
    public Position rotateLeft() {
        int x = puyoPair.getFirstPos().getX();
        int y = puyoPair.getFirstPos().getY();
        return new Position(x - 1, y);
    }

    // Rotates puyo pair to the right, that is, moving the second puyo to the right of the first puyo
    public Position rotateRight() {
        int x = puyoPair.getFirstPos().getX();
        int y = puyoPair.getFirstPos().getY();
        return new Position(x + 1, y);
    }

    // Makes current puyo pair fall down
    public void moveDown() {
        Position firstPos = puyoPair.getFirstPos();
        Position secondPos = puyoPair.getSecondPos();
        puyoPair.getFirstPuyo().getPosition().setY(firstPos.getY() + 1);
        puyoPair.getSecondPuyo().getPosition().setY(secondPos.getY() + 1);
    }

    // Moves current puyo pair 1 pixel to the left
    public void moveLeft() {
        Position firstPos = puyoPair.getFirstPos();
        Position secondPos = puyoPair.getSecondPos();
        puyoPair.getFirstPuyo().getPosition().setX(firstPos.getX() - 1);
        puyoPair.getSecondPuyo().getPosition().setX(secondPos.getX() - 1);
    }

    // Moves current puyo pair 1 pixel to the right
    public void moveRight() {
        Position firstPos = puyoPair.getFirstPos();
        Position secondPos = puyoPair.getSecondPos();
        puyoPair.getFirstPuyo().getPosition().setX(firstPos.getX() + 1);
        puyoPair.getSecondPuyo().getPosition().setX(secondPos.getX() + 1);
    }

    // Returns the position of the second puyo after rotating
    public Position rotate(boolean clockwise) {
        Position newSecondPos = new Position(0, 0);
        if (clockwise) {
            switch (rotationState) {
                case UP:    newSecondPos = rotateRight();   this.rotationState = RotationState.RIGHT; break;
                case RIGHT: newSecondPos = rotateDown();    this.rotationState = RotationState.DOWN; break;
                case DOWN:  newSecondPos = rotateLeft();    this.rotationState = RotationState.LEFT; break;
                case LEFT:  newSecondPos = rotateUp();      this.rotationState = RotationState.UP; break;
            }
        } else {
            switch (rotationState) {
                case UP:    newSecondPos = rotateLeft();    this.rotationState = RotationState.LEFT; break;
                case LEFT:  newSecondPos = rotateDown();    this.rotationState = RotationState.DOWN; break;
                case DOWN:  newSecondPos = rotateRight();   this.rotationState = RotationState.RIGHT; break;
                case RIGHT: newSecondPos = rotateUp();      this.rotationState = RotationState.UP; break;
            }
        }
        return newSecondPos;
    }

    // Reverts rotation state to what it was before
    public void revertRotationState(boolean clockwise) {
        if (clockwise) {
            switch (rotationState) {
                case UP:    this.rotationState = RotationState.LEFT; break;
                case LEFT:  this.rotationState = RotationState.DOWN; break;
                case DOWN:  this.rotationState = RotationState.RIGHT; break;
                case RIGHT: this.rotationState = RotationState.UP; break;
            }
        } else {
            switch(rotationState) {
                case UP:    this.rotationState = RotationState.RIGHT; break;
                case RIGHT: this.rotationState = RotationState.DOWN; break;
                case DOWN:  this.rotationState = RotationState.LEFT; break;
                case LEFT:  this.rotationState = RotationState.UP; break;
            }
        }
    }
}

