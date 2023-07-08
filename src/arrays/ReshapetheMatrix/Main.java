package arrays.ReshapetheMatrix;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] mat = {{1,2},{3,4}};
        System.out.println(Arrays.deepToString(matrixReshape(mat, 1, 4)));
    }

    public static int[][] matrixReshape(int[][] mat, int r, int c) {
        int n = mat.length;
        int m = mat[0].length;
        if (n * m != r * c) return mat;

        int l = 0;
        int[][] res = new int[r][c];
        for (int i = 0, k = 0; i < n; i++) {
            for (int j = 0; j < m; j++, l++) {
                if (l == c) {
                    l = 0;
                    k++;
                }
                res[k][l] = mat[i][j];
            }
        }
        return res;
    }
}
