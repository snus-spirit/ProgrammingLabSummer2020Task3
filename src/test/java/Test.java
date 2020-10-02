import controller.Controller;
import javafx.scene.input.KeyCode;
import model.LogicOld;
import java.util.Arrays;
import model.Logic;
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
    int[][] testField = new int[][]{
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
        LogicOld logicOld = new LogicOld(testField);
        logicOld.shifter();
        assertTrue(Arrays.deepEquals(logicOld.getField(), testUp));
    }

    @Test
    void Down() {
        Controller.direction = KeyCode.DOWN;
        LogicOld logicOld = new LogicOld(testField);
        logicOld.shifter();
        assertTrue(Arrays.deepEquals(logicOld.getField(), testDown));
    }

    @Test
    void Left() {
        Controller.direction = KeyCode.LEFT;
        LogicOld logicOld = new LogicOld(testField);
        logicOld.shifter();
        assertTrue(Arrays.deepEquals(logicOld.getField(), testLeft));
    }

    @Test
    void Right() {
        Controller.direction = KeyCode.RIGHT;
        LogicOld logicOld = new LogicOld(testField);
        logicOld.shifter();
        assertTrue(Arrays.deepEquals(logicOld.getField(), testRight));
    }

    @Test
    void UpV2() {
        Controller.direction = KeyCode.UP;
        Logic logic = new Logic(testField);
        logic.rotator();
        assertTrue(Arrays.deepEquals(logic.getField(), testUp));
    }

    @Test
    void DownV2() {
        Controller.direction = KeyCode.DOWN;
        Logic logic = new Logic(testField);
        logic.rotator();
        assertTrue(Arrays.deepEquals(logic.getField(), testDown));
    }

    @Test
    void LeftV2() {
        Controller.direction = KeyCode.LEFT;
        Logic logic = new Logic(testField);
        logic.rotator();
        assertTrue(Arrays.deepEquals(logic.getField(), testLeft));
    }

    @Test
    void RightV2() {
        Controller.direction = KeyCode.RIGHT;
        Logic logic = new Logic(testField);
        logic.rotator();
        assertTrue(Arrays.deepEquals(logic.getField(), testRight));
    }

    @Test
    void endChecker() {
        Logic logic = new Logic(win);
        assertTrue(logic.endChecker());
        logic = new Logic(lose);    
        assertTrue(logic.endChecker());
    }
}
