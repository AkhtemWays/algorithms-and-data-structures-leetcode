package arrays.setMatrixZeroes;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }

    public static void setZeroes(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 0) {
                    nullifyCol(col, row, matrix);
                    nullifyRow(row, col, matrix);
                    matrix[row][col] = 0;
                } else if (matrix[row][col] == -1) {
                    nullifyCol(col, row, matrix);
                    matrix[row][col] = 0;
                } else if (matrix[row][col] == 2) {
                    matrix[row][col] = 0;
                }
            }
        }
    }

    private static void nullifyRow(int row, int startCol, int[][] matrix) {
        for (int col = startCol; col < matrix[row].length; col++) {
            if (matrix[row][col] == 0) matrix[row][col] = -1;
            else if (matrix[row][col] == 1) matrix[row][col] = 2;
        }
    }

    private static void nullifyCol(int col, int startRow, int[][] matrix) {
        for (int row = startRow; row < matrix.length; row++) {
            matrix[row][col] = -1;
        }
    }
}
