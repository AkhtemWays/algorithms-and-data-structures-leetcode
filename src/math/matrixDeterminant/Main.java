package math.matrixDeterminant;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(determinant(matrix));
    }

    public static int determinant(int[][] matrix) {
        if (matrix.length == 1) return matrix[0][0];

        boolean plus = true;
        int result = 0;

        for (int i = 0; i < matrix[0].length; i++, plus = !plus) {
            int[][] subMatrix = extractSubMatrix(matrix, i);
            if (plus) result += matrix[0][i] * determinant(subMatrix);
            else result -= matrix[0][i] * determinant(subMatrix);
        }
        return result;
    }

    static int[][] extractSubMatrix(int[][] matrix, int idx) {
        int[][] subMatrix = new int[matrix.length - 1][matrix.length - 1];
        for (int i = 1; i < matrix.length; i++) {
            int colIdx = 0;
            for (int j = 0; j < matrix.length; j++) {
                if (j == idx) continue;
                subMatrix[i-1][colIdx++] = matrix[i][j];
            }
        }
        return subMatrix;
    }
}
