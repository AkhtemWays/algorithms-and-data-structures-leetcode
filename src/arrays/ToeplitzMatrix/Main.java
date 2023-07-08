package arrays.ToeplitzMatrix;

public class Main {
    public static void main(String[] args) {

    }

    public static boolean isToeplitzMatrix(int[][] matrix) {
        for (int j = 1; j < matrix[0].length-1; j++) {
            if (!isOkDiagonal(matrix, 0, j)) return false;
        }
        for (int i = 1; i < matrix.length-1; i++) {
            if (!isOkDiagonal(matrix, i, 0)) return false;
        }
        return isOkDiagonal(matrix, 0, 0);
    }

    private static boolean isOkDiagonal(int[][] matrix, int i, int j) {
        int val = matrix[i][j];
        i++;
        j++;
        while (i < matrix.length && j < matrix[0].length) {
            if (matrix[i][j] != val) return false;
            i++;
            j++;
        }
        return true;
    }
}
