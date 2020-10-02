package model;

import controller.Controller;

import java.util.Arrays;
import java.util.Random;

public class Logic {

    public static int score = 0;
    private int[][] field = new int[Constants.X_LENGTH][Constants.Y_LENGTH];

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
            Arrays.fill(field[i], 0);
        }
        newCell();
        newCell();
    }

    public int getState(int x, int y) {
        return this.field[x][y];
    }

    private void setState(int x, int y, int newValue) {
        this.field[x][y] = newValue;
    }

    public void newCell() {
        int cellValue = (new Random().nextInt(100) < 10) ? 4 : 2;
        int randomX = new Random().nextInt(Constants.X_LENGTH);
        int randomY = new Random().nextInt(Constants.Y_LENGTH);

        int count = 1;
        while (count < Constants.FIELD_SIZE) {
            if (getState(randomX, randomY) == 0) {
                setState(randomX, randomY, cellValue);
                break;
            } else if (randomX + 1 < Constants.X_LENGTH) {
                randomX++;
            } else if (randomY + 1 < Constants.Y_LENGTH){
                randomY++;
            } else {
                randomX = 0;
                randomY = 0;
            }
            count++;
        }
    }

    private  int [] getColumn(int y) {
        int[] result = new int[Constants.X_LENGTH];
        for (int i = 0; i < Constants.X_LENGTH; i++) {
            result[i] = field[i][y];
        }
        return result;
    }

    private  int [] getLine(int x) {
        int[] result = new int[Constants.X_LENGTH];
        for (int i = 0; i < Constants.X_LENGTH; i++) {
            result[i] = field[x][i];
        }
        return result;
    }

    private  void setColumn(int y, int[] array) {
        for (int i = 0; i < Constants.X_LENGTH; i++) {
            setState(i, y, array[i]);
        }
    }

    private  void setLine(int x, int[] array) {
        for (int i = 0; i < Constants.X_LENGTH; i++) {
            setState(x, i, array[i]);
        }
    }

    /**
     * Меняет поле в зависимости от нажатой клавиши
     * turns - количество поворотов для положения "влево"
     */
    public void rotator() {
        int turns = 0;
        switch (Controller.getDirection()) {
            case UP:
                turns = 3;
                break;
            case RIGHT:
                turns = 2;
                break;
            case DOWN:
                turns = 1;
                break;
            case LEFT:
                turns = 0;
                break;
        }
        int i;
        for (i = 0; i < turns; i++) {
            field = turner();
        }

        for (i = 0; i < Constants.X_LENGTH; i++) {
            setLine(i, adder(getLine(i)));
        }

        for (i = 4 - turns; i > 0 && i != 4; i--){
            field = turner();
        }
    }

    /**
     * Меняет порядок в массиве на обратный
     * @param row входной массив
     * @return обратный порядок массива
     */
    private int[] reverser(int [] row) {
        int[] array = new int[row.length];
        for (int l = 0; l < row.length; l++) {
            array[l] = row[row.length - l - 1];
        }
        return array;
    }

    /**
     * Поворачивает двумерный массив на 90 градусов по часовой стрелке
     * @return повернутый двумерный массив
     */
    public int[][] turner() {
        int[][] result = new int[Constants.X_LENGTH][Constants.X_LENGTH];
        for (int i = 0; i < Constants.Y_LENGTH; i++) {
            result[i] = reverser(getColumn(i));
        }
        return result;
    }

    /**
     * Согласно логике игры складывает и перемещает данные массива
     * @param row входной массив
     * @return массив с измененными данными
     */
    private  int [] adder(int [] row) {

        int[] rowWOZero = new int[row.length];
        Arrays.fill(rowWOZero, 0);

        int q = 0;
        for (int value : row) {
            if (value != 0) {
                rowWOZero[q] = value;
                q++;
            }
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
        if (contains(2048)) return true;
        if (contains(0)) return false;

        int[] row;
        for (int i = 0; i < Constants.X_LENGTH; i++) {
            row = getLine(i);
            for (int j = 0; j < row.length - 1; j++) {
                if (row[j] == row[j + 1]) return false;
            }
        }
        for (int i = 0; i < Constants.X_LENGTH; i++) {
            row = getColumn(i);
            for (int j = 0; j < row.length - 1; j++) {
                if (row[j] == row[j + 1]) return false;
            }
        }
        return true;
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