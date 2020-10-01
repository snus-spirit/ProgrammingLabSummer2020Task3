package controller;

import javafx.scene.input.KeyCode;

public class Controller {

    public static KeyCode direction;

    public static Direction getDirection() {
        switch (direction) {
            case UP:
                return Direction.UP;
            case DOWN:
                return Direction.DOWN;
            case LEFT:
                return Direction.LEFT;
            case RIGHT:
                return Direction.RIGHT;
            default: return Direction.WAITING;
        }
    }
}
