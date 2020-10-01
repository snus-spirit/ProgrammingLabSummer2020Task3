import controller.Controller;
import javafx.scene.input.KeyCode;
import model.Logic;
import java.util.Arrays;
import model.Logicv2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    int[][] win = new int[][]{
            {2048, 2, 2, 2},
            {1024, 2, 2, 2},
            {2, 2, 0, 2},
            {2, 0, 2, 2}
    };

    int[][] lose = new int[][]{
            {4, 2, 4, 2},
            {2, 4, 2, 4},
            {4, 2, 4, 2},
            {2, 4, 2, 4}

    };
    int[][] testField = new int[][] {
            {2, 0, 4, 0},
            {0, 2, 0, 2},
            {2, 2, 4, 4},
            {0, 0, 2, 0}
    };

    int[][] testDown = new int[][]{
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 8, 2},
            {4, 4, 2, 4}

    };
    int[][] testUp = new int[][]{
            {4, 4, 8, 2},
            {0, 0, 2, 4},
            {0, 0, 0, 0},
            {0, 0, 0, 0}

    };
    int[][] testLeft = new int[][]{
            {2, 4, 0, 0},
            {4, 0, 0, 0},
            {4, 8, 0, 0},
            {2, 0, 0, 0}

    };
    int[][] testRight = new int[][]{
            {0, 0, 2, 4},
            {0, 0, 0, 4},
            {0, 0, 4, 8},
            {0, 0, 0, 2}

    };

    @Test
    void Up() {
        Controller.direction = KeyCode.UP;
        Logic logic = new Logic(testField);
        logic.shifter();
        assertTrue(Arrays.deepEquals(logic.getField(), testUp));
    }

    @Test
    void Down() {
        Controller.direction = KeyCode.DOWN;
        Logic logic = new Logic(testField);
        logic.shifter();
        assertTrue(Arrays.deepEquals(logic.getField(), testDown));
    }

    @Test
    void Left() {
        Controller.direction = KeyCode.LEFT;
        Logic logic = new Logic(testField);
        logic.shifter();
        assertTrue(Arrays.deepEquals(logic.getField(), testLeft));
    }

    @Test
    void Right() {
        Controller.direction = KeyCode.RIGHT;
        Logic logic = new Logic(testField);
        logic.shifter();
        assertTrue(Arrays.deepEquals(logic.getField(), testRight));
    }
}
