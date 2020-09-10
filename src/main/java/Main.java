import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.InputStream;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root =
//                FXMLLoader.load(getClass().getResource("/graphics.fxml"));
//        Scene scene = new Scene(root, 600, 450);

        InputStream iconStream =
                getClass().getResourceAsStream("/R-.jpg");
        Image image = new javafx.scene.image.Image(iconStream);
        primaryStage.getIcons().add(image);
        primaryStage.setTitle("lll.2048");

        Logic.startGame();
        Scene scene = new Scene(drawer(), 450, 450);

        primaryStage.setScene(scene);
        primaryStage.toFront();
        primaryStage.show();

        if (!Logic.end) {
            scene.setOnKeyPressed(event -> Controller.keyEvent = event);
            System.out.println(Controller.keyEvent.getCode());
            Logic.shifter(Controller.getDirection());
            Logic.newCell();
            Logic.newCell();
            scene = new Scene(drawer());
            primaryStage.setScene(scene);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Pane drawer() {
        Group group = new Group();

        for (int i = 0; i < Constants.X_LENGTH; i++) {
            for (int j = 0; j < Constants.Y_LENGTH; j++) {
                group.getChildren().add(Controller.labelBuilder(i, j));
            }
        }
        Pane pane = new Pane();
        pane.setMinSize(450, 450);
        pane.getChildren().add(group);
        return pane;
    }
}
