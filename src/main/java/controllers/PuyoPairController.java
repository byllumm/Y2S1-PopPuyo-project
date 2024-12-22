package controllers;

import utils.puyoutils.Position;
import utils.puyoutils.PuyoPair;

public class PuyoPairController {
    // Attributes
    private RotationState rotationState;
    private PuyoPair puyoPair;
    public static enum RotationState {
        RIGHT, UP, LEFT, DOWN;
    }

    private static final RotationState[] ROTATION_ORDER = {
            RotationState.UP, RotationState.RIGHT, RotationState.DOWN, RotationState.LEFT
    };

    // Constructor
    public PuyoPairController(PuyoPair pair) {
        this.rotationState = RotationState.RIGHT; //The second puyo starts on the right of the first one
        this.puyoPair = pair;
    }


    // Getters
    public RotationState getRotationState() {
        return rotationState;
    }


    // Setters
    public void setRotationState(RotationState rotationState) {
        this.rotationState = rotationState;
    }


    // Class Methods

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
        puyoPair.setFirstPos(new Position(puyoPair.getFirstPos().getX(), puyoPair.getFirstPos().getY() + 1));
        puyoPair.setSecondPos(new Position(puyoPair.getSecondPos().getX(), puyoPair.getSecondPos().getY() + 1));
    }

    // Moves current puyo pair 1 pixel to the left
    public void moveLeft() {
        puyoPair.setFirstPos(new Position(puyoPair.getFirstPos().getX() - 1, puyoPair.getFirstPos().getY()));
        puyoPair.setSecondPos(new Position(puyoPair.getSecondPos().getX() - 1, puyoPair.getSecondPos().getY()));
    }

    // Moves current puyo pair 1 pixel to the right
    public void moveRight() {
        puyoPair.setFirstPos(new Position(puyoPair.getFirstPos().getX() + 1, puyoPair.getFirstPos().getY()));
        puyoPair.setSecondPos(new Position(puyoPair.getSecondPos().getX() + 1, puyoPair.getSecondPos().getY()));
    }

    // Find the index in the ROTATION_ORDER
    private int findIndex(RotationState state) {
        for(int i = 0; i < ROTATION_ORDER.length; i++) {
            if(ROTATION_ORDER[i] == state) return i;
        }
        return -1; // Shouldn't happen
    }

    // Helper method to find the next state
    private RotationState getNextState(RotationState current, boolean clockwise) {
        int index = (clockwise ? 1: -1) + findIndex(current);
        index = (index + ROTATION_ORDER.length) % ROTATION_ORDER.length;
        return ROTATION_ORDER[index];
    }

    // Returns the position of the second puyo after rotating
    public Position rotate(boolean clockwise) {
        RotationState newState = getNextState(rotationState, clockwise);
        Position newSecondPos = new Position(0, 0);

        switch(newState) {
            case UP: newSecondPos = rotateUp(); break;
            case RIGHT: newSecondPos = rotateRight(); break;
            case DOWN: newSecondPos = rotateDown(); break;
            case LEFT: newSecondPos = rotateLeft(); break;
        }

        this.rotationState = newState;
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

