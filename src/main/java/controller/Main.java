package controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Logic;
import view.Drawer;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Drawer.stageDrawer(primaryStage);
        Scene scene = new Scene(Drawer.drawStart());
        primaryStage.setScene(scene);
        primaryStage.show();

        Logic logic = new Logic();
        logic.startGame();
        Drawer.drawUpdate(logic);

        Controller.action(scene, logic, primaryStage);
    }

}
