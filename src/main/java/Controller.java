import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class Controller {
    public static javafx.scene.input.KeyEvent keyEvent;
    @FXML
    private static GridPane table;
    @FXML
    private static Pane pane;
    @FXML
    private static Label label12;


//    public static void initialize() {
//        for (int i = 0; i < Constants.X_LENGTH; i++){
//            for (int j = 0; j < Constants.Y_LENGTH; j++) {
//
//                System.out.println(label12 == null);
//                table.add(labelBuilder(i, j),i , j);
//            }
//        }
//    }
    public static Label labelBuilder(int i, int j){
       Label label = new Label();
        label.setText(Integer.toString(Logic.getState(i, j)));
        switch (Logic.getState(i, j)) {
            case 0: label.setStyle("-fx-background-color: #fff4b1;");
                break;
            case 2: label.setStyle("-fx-background-color: #ffe757;");
                break;
            case 4:label.setStyle("-fx-background-color: #ffdf1f;");
                break;
            case 8:label.setStyle("-fx-background-color: #f4ff27;");
                break;
            case 16:label.setStyle("-fx-background-color: #ff8a46;");
                break;
            case 32:label.setStyle("-fx-background-color: #ff670f;");
                break;
            case 64:label.setStyle("-fx-background-color: #e65400;");
                break;
            case 128:label.setStyle("-fx-background-color: #ff5442;");
                break;
            case 256:label.setStyle("-fx-background-color: #ff2e1a;");
                break;
            case 512:label.setStyle("-fx-background-color: #f01600;");
                break;
            case 1024:label.setStyle("-fx-background-color: #49ff59;");
                break;
            case 2048:label.setStyle("-fx-background-color: #00f516;");
        };
        label.setFont(new Font("/fonttt.ttf", 26));
        label.setLayoutX(15 + 100 * i);
        label.setLayoutY(15 + 100 * j);
        label.setPrefSize(90, 90);
        label.setAlignment(Pos.CENTER);
        return label;
    }

    public static Direction getDirection() {
        System.out.println(1);
        switch (keyEvent.getCode()) {
            case UP:
                return Direction.UP;
            case DOWN:
                return Direction.DOWN;
            case LEFT:
                return Direction.LEFT;
            case RIGHT:
                return Direction.RIGHT;
        }
        return Direction.AWAITING;
    }


}
