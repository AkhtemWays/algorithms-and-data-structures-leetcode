package arrays.LuckyNumbersinaMatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }

    public static List<Integer> luckyNumbers(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (isLucky(matrix, i, j)) res.add(matrix[i][j]);
            }
        }
        return res;
    }

    private static boolean isLucky(int[][] matrix, int i, int j) {
        int val = matrix[i][j];
        int minRow = Arrays.stream(matrix[i]).min().getAsInt();
        if (val == minRow) {
            int max = Integer.MIN_VALUE;
            for (int k = 0; k < matrix[0].length; k++) {
                if (matrix[i][k] > max) {
                    max = matrix[i][k];
                }
            }
            return max == val;
        }
        return false;
    }
}
