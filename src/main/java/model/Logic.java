package model;

import controller.Controller;
import controller.Direction;
import java.util.Random;

public class Logic {

    public static int score = 0;
    public int[][] field = new int[Constants.X_LENGTH][Constants.Y_LENGTH];

    public Logic(){ }

    public Logic(int[][] array) {
        for (int i = 0; i < array.length; i++ ) {
            for (int j = 0; j < array.length; j++ ) {
                setState(i, j, array[i][j]);
            }
        }
    }

    public  void startGame() {
        for (int i = 0; i < Constants.X_LENGTH; i++) {
            for (int l = 0; l < Constants.Y_LENGTH; l++) {
                setState(i, l, 0);
            }
        }
        newCell();
        newCell();
    }

    public int getState(int x, int y) {
        return this.field[x][y];
    }

    private   void setState(int x, int y, int newValue) {
        this.field[x][y] = newValue;
    }

    public void newCell() {

        int cellValue = (new Random().nextInt(100) <= 10) ? 4 : 2;
        int randomX = new Random().nextInt(Constants.X_LENGTH);
        int randomY = new Random().nextInt(Constants.Y_LENGTH);

        int count = 0;
        while (count < Constants.FIELD_SIZE) {
            if (getState(randomX, randomY) == 0) {
                setState(randomX, randomY, cellValue);
                break;
            } else if (randomX + 1 < Constants.X_LENGTH) {
                randomX++;
            } else {
                randomX = 0;
                if (randomY + 1 < Constants.Y_LENGTH) {
                    randomY++;
                } else randomY = 0;
            }
            count++;
        }
    }

    private  int [] getColumn(int y) {
        int[] array = new int[Constants.X_LENGTH];
        for (int i = 0; i < Constants.X_LENGTH; i++) {
            array[i] = getState(i, y);
        }
        return array;
    }

    private  int [] getLine(int x) {
        int[] array = new int[Constants.Y_LENGTH];
        for (int i = 0; i < Constants.Y_LENGTH; i++) {
            array[i] = getState(x, i);
        }
        return array;
    }

    private  void setColumn(int y, int[] array) {
        for (int i = 0; i < Constants.X_LENGTH; i++) {
            setState(i, y, array[i]);
        }
    }

    private  void setLine(int x, int[] array) {
        for (int i = 0; i < Constants.Y_LENGTH; i++) {
            setState(x, i, array[i]);
        }
    }

    /**
     * Изменяет порядок цифр row на обратный
     * @param row i-ий столбец field
     * @return Возвращает измененный row
     */
    private int[] reverseChanger(int [] row) {
        int[] array = new int[row.length];

        for (int l = 0; l < row.length; l++) {
            array[l] = row[row.length - l - 1];
        }
        return array;
    }

    /**
     * Согласно направлению применяет или не применяет метод reverseChanger
     * С помощью метода adder, меняет значение ячеек поля после сдвига
     * @return Возвращает true, если были совершенны какие нибудь действия
     */
    public void shifter() {
        Direction direction = Controller.getDirection();
        switch (direction) {
            case UP:
            case DOWN:
                for (int i = 0; i < Constants.Y_LENGTH; i++) {
                    int[] row = getColumn(i);

                    if (direction == Direction.DOWN) {
                        row = reverseChanger(row);
                    }
                    int[] result = adder(row);

                    if (direction == Direction.DOWN) {
                        result = reverseChanger(result);
                    }
                    setColumn(i, result);
                }
                break;
            case LEFT:
            case RIGHT:
                for (int i = 0; i < Constants.X_LENGTH; i++) {
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
    private  int [] adder(int [] row) {

        int[] rowWOZero = new int[row.length];

        int q = 0;
        for (int value : row) {
            if (value != 0) {
                rowWOZero[q] = value;
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
                result[q] = rowWOZero[i] * 2;
                score += result[q];
                i++;
            } else {
                result[q] = rowWOZero[i];
            }
            q++;
        }
        return result;
    }

    public int[][] getField() {
        return this.field;
    }

    public boolean contains(int value) {
        for (int i = 0; i < Constants.X_LENGTH; i++) {
            for (int j = 0; j < Constants.Y_LENGTH; j++) {
                if (getState(i, j) == value) return true;
            }
        }
        return false;
    }

    public boolean endChecker() {
        if (contains(0)) return true;

        int[] row;
        for (int i = 0; i < Constants.X_LENGTH; i++) {
            row = getLine(i);
            for (int j = 0; j < row.length - 1; j++) {
                if (row[j] == row[j + 1]) return true;
            }
        }
        for (int i = 0; i < Constants.Y_LENGTH; i++) {
            row = getColumn(i);
            for (int j = 0; j < row.length - 1; j++) {
                if (row[j] == row[j + 1]) return true;
            }
        }
        return false;
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
}