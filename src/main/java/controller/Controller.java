package controller;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.Logic;
import view.Drawer;

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

    public static void action(Scene scene, Logic logic, Stage stage) {
        scene.setOnKeyPressed(e -> {
            Controller.direction = e.getCode();
            if (Controller.getDirection() != Direction.WAITING) {
                logic.rotator();
                logic.newCell();
                Drawer.drawUpdate(logic);
            }
            if (logic.endChecker()) {
                Drawer.endScene(stage);
            }
        });
    }
}
