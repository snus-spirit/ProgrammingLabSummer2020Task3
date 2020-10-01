package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Constants;
import model.Logic;
import model.Logicv2;
import java.io.*;

public class Drawer {

    public static Label[][] labelArray =
            new Label[Constants.X_LENGTH][Constants.Y_LENGTH];
    public static Label score = new Label();


    public static void stageDrawer(Stage stage) {
        InputStream iconStream =
                Drawer.class.getResourceAsStream("/image.jpg");
        Image image = new javafx.scene.image.Image(iconStream);
        stage.getIcons().add(image);
        stage.setTitle("lll.2048");
    }

    public static Pane drawStart() {
        Pane root = new Pane();
        Pane pane = new Pane();
        for (int i = 0; i < Constants.X_LENGTH; i++) {
            for (int j = 0; j < Constants.Y_LENGTH; j++) {
                labelArray[i][j] = new Label();
                labelArray[i][j].setFont(new Font("/font.ttf", 20));
                labelArray[i][j].setStyle("-fx-background-color: #fff4b1;");
                labelArray[i][j].setAlignment(Pos.CENTER);
                labelArray[i][j].setPrefSize(70, 70);
                labelArray[i][j].setLayoutX(10 + 100 * i);
                labelArray[i][j].setLayoutY(10 + 100 * j);
                pane.getChildren().add(labelArray[i][j]);
            }
        }
        pane.setStyle("-fx-background-color: #2f4f4f;");
        pane.setPrefSize(100 * Constants.X_LENGTH - 10,
                100 * Constants.Y_LENGTH - 10);
        pane.setLayoutY(110);

        Label image = new Label();
        image.setText("2048");
        image.setFont(new Font("/font.ttf", 26));
        image.setStyle("-fx-background-color: #ffd700;");
        image.setPrefSize(110, 110);
        image.setAlignment(Pos.CENTER);

        score.setText("score\n0");
        score.setFont(new Font("/font.ttf", 20));
        score.setStyle("-fx-background-color: #696969;");
        score.setPrefSize(90, 60);
        score.setAlignment(Pos.CENTER);
        score.setLayoutX((Constants.X_LENGTH - 1)  * 100);

        root.setStyle("-fx-background-color: #808080;");
        root.getChildren().add(image);
        root.getChildren().add(pane);
        root.getChildren().add(score);
        return root;
    }

    public static void drawUpdate(Logic logic) {
        for (int i = 0; i < Constants.X_LENGTH; i++) {
            for (int j = 0; j < Constants.Y_LENGTH; j++) {

                labelArray[i][j].setText((logic.getState(j, i) != 0)
                        ? Integer.toString(logic.getState(j, i)) : "");

                switch (logic.getState(j, i)) {
                    case 0: labelArray[i][j].setStyle("-fx-background-color: #fff4b1;");
                        break;
                    case 2: labelArray[i][j].setStyle("-fx-background-color: #fff094;");
                        break;
                    case 4:labelArray[i][j].setStyle("-fx-background-color: #ffe757;");
                        break;
                    case 8:labelArray[i][j].setStyle("-fx-background-color: #f4ff27;");
                        break;
                    case 16:labelArray[i][j].setStyle("-fx-background-color: #ff8a46;");
                        break;
                    case 32:labelArray[i][j].setStyle("-fx-background-color: #ff670f;");
                        break;
                    case 64:labelArray[i][j].setStyle("-fx-background-color: #e65400;");
                        break;
                    case 128:labelArray[i][j].setStyle("-fx-background-color: #ff5442;");
                        break;
                    case 256:labelArray[i][j].setStyle("-fx-background-color: #ff2e1a;");
                        break;
                    case 512:labelArray[i][j].setStyle("-fx-background-color: #f01600;");
                        break;
                    case 1024:labelArray[i][j].setStyle("-fx-background-color: #49ff59;");
                        break;
                    case 2048:labelArray[i][j].setStyle("-fx-background-color: #ffd700;");
                }
            }
        }
        score.setText("score\n" + Logic.score);
    }

    private static Scene endSceneDrawer() {
        Pane pane = new Pane();
        pane.setPrefSize(300, 300);
        pane.setStyle("-fx-background-color: #808080;");

        score.setStyle("-fx-background-color: #f4ff27;");
        score.setPrefSize(150, 150);
        score.setLayoutX(75);
        score.setLayoutY(75);
        score.setAlignment(Pos.CENTER);
        score.setFont(new Font("/font.ttf", 26));

        pane.getChildren().add(score);
        return new Scene(pane);
    }

    public static void endScene(Stage stage) {
        stage.close();
        stage.setScene(endSceneDrawer());
        stage.toFront();
        stage.show();
    }
}
