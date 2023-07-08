package arrays.DetermineMatrixCanBeObtainedByRotation;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }

    public static boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        int[][] deg90 = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                deg90[j][i] = mat[i][j];
            }
        }
        int[][] deg180 = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                deg180[j][i] = deg90[i][j];
            }
        }
        int[][] deg270 = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                deg270[j][i] = deg180[i][j];
            }
        }
        return areEqual(mat, target) || areEqual(deg90, target) || areEqual(deg180, target) || areEqual(deg270, target);
    }

    private static boolean areEqual(int[][] mat, int[][] target) {
        for (int i = 0; i < mat.length; i++) {
            if (!Arrays.equals(mat[i], target[i])) return false;
        }
        return true;
    }
}
