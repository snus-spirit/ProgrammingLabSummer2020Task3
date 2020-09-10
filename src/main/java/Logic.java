import java.util.Random;
public class Logic {

    private int score = 0;
    public static boolean end = false;
    private static int[][] field = new int[Constants.X_LENGTH][Constants.Y_LENGTH];

    public static void startGame() {
        for (int i = 0; i < Constants.X_LENGTH; i++) {
            for (int l = 0; l < Constants.Y_LENGTH; l++) {
                setState(i, l, 0);
            }
        }
        newCell();
        newCell();
    }

    public static int getState(int x, int y) {
        return field[x][y];
    }

    public static void setState(int x, int y, int newValue) {
        field[x][y] = newValue;
    }

    public static void newCell() {

        int cellValue = (new Random().nextInt(100) < 17) ? 4 : 2;// 17% - шанс
        int randomX = new Random().nextInt(Constants.X_LENGTH);
        int randomY = new Random().nextInt(Constants.Y_LENGTH);

        boolean end = false;
        int count = 0;

        while (!end) {
            if (getState(randomX, randomY) == 0) {
                setState(randomX, randomY, cellValue);
                end = true;
            } else if (randomX + 1 < Constants.X_LENGTH) {
                randomX++;
            } else {
                randomX = 0;
                if (randomY + 1 < Constants.Y_LENGTH) {
                    randomY++;
                } else randomY = 0;
            }
            count++;
            if (count == 15) break;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] array: field) {
            for (int value: array) {
                sb.append(value);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return (sb.toString());
    }

    /*Метод --1 обратный порядок для up/down left/right ->
        массив без 0 -> сложение
      Метод --2 3с(Сместить -> Сложить -> Сместить)
     */

    private static int [] getColumn(int x) {
        int[] array = new int[Constants.X_LENGTH];
        for (int i = 0; i < Constants.X_LENGTH; i++) {
            array[i] = getState(x, i);
        }
        return array;
    }

    private static int [] getLine(int y) {
        int[] array = new int[Constants.Y_LENGTH];
        for (int i = 0; i < Constants.Y_LENGTH; i++) {
            array[i] = getState(i, y);
        }
        return array;
    }

    private static void setColumn(int x, int[] array) {
        for (int i = 0; i < Constants.X_LENGTH; i++) {
            setState(x, i, array[i]);
        }
    }

    private static void setLine(int y, int[] array) {
        for (int i = 0; i < Constants.Y_LENGTH; i++) {
            setState(i, y, array[i]);
        }
    }

    /**
     * Изменяет порядок цифр row на обратный
     * @param row i-ий столбец field
     * @return Возвращает измененный row
     */
    private static int[] reverseChanger(int [] row) {
        int[] array = new int[row.length];

        for (int l = 0; l < row.length; l++) {
            array[l] = row[row.length - l - 1];
        }
        return array;
    }

    /**
     * Согласно направлению применяет или не применяет метод reverseChanger
     * С помощью метода adder, меняет значение ячеек поля после сдвига
     * @param direction Направление сдвига
     * @return Возвращает true, если были совершенны какие нибудь действия
     */
    public static void shifter(Direction direction) {
        boolean didAnything;

        switch (direction) {
            case UP:
            case DOWN:
                for (int i = 0; i < Constants.X_LENGTH; i++) {
                    int[] row = getColumn(i);

                    if (direction == Direction.UP) {
                        row = reverseChanger(row);
                    }
                    int[] result = adder(row);

                    if (direction == Direction.UP) {
                        result = reverseChanger(result);
                    }
                    setColumn(i, result);
                }
                break;

            case LEFT:
            case RIGHT:
                for (int i = 0; i < Constants.Y_LENGTH; i++) {
                    int[] row = getLine(i);

                    if (direction == Direction.RIGHT) {
                        row = reverseChanger(row);
                    }
                    int[] result = adder(row);

                    if (direction == Direction.RIGHT) {
                        result = reverseChanger(result);
                    }
                    setLine(i, result);
                }
                break;
        }
    }

    /**
     * Создает новое поле без нулей и складывает числа согласно логике
     * @param row Ряд чисел из метода shifter, которые нужно сдвинуть и сложить
     * @return Возвращает ряд, измененный согласно логике
     */
    private static int [] adder(int [] row) {
        int[] rowWOZero = new int[row.length];
        boolean checkEnd = true;
        int q = 0;
        for (int i = 0; i < row.length; i++) {
            if (row[i] != 0) {
                if(q != i) {
                    checkEnd = false;
                }
                rowWOZero[q] = row[i];
                q++;
            }
        }
        for (int i = q; i < rowWOZero.length; i++) {
            rowWOZero[i] = 0;
        }

        q = 0;
        int [] result = new int[rowWOZero.length];
        for (int i = 0; i < rowWOZero.length; i++) {
            if ((i + 1 < rowWOZero.length) && (rowWOZero[i] == rowWOZero[i + 1])
                    && (rowWOZero[i] != 0)) {
                checkEnd = false;
                result[q] = rowWOZero[i] * 2;
                i++;
                if (result[q] == 2048) end = true;
            } else {
                result[q] = rowWOZero[i];
            }
            q++;
        }
        for (int i = q; i < rowWOZero.length; i++) {
            rowWOZero[i] = 0;
        }
        end |= checkEnd;
        return result;
    }
}
