package trees.RangeSumQuery2DMutable;

class FenwickTree {
    int[][] BIT;
    int[][] matrix;
    int n;
    int m;

    public FenwickTree(int[][] matrix) {
        this.matrix = matrix;
        n = matrix.length;
        m = matrix[0].length;
        BIT = new int[n+1][m+1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                update(i, j, matrix[i][j]);
            }
        }
    }

    public void update(int i, int j, int val) {
        i++;
        j++;
        for (int x = j; x <= m; x += x & (-x)) {
            for (int y = i; y <= n; y += y & (-y)) {
                BIT[y][x] += val;
            }
        }
    }

    public void set(int i, int j, int val) {
        int diff = val - matrix[i][j];
        matrix[i][j] = val;
        update(i, j, diff);
    }

    public int sum(int i, int j) {
        i++;
        j++;
        int sum = 0;
        for (int x = j; x > 0; x -= x & (-x)) {
            for (int y = i; y > 0; y -= y & (-y)) {
                sum += BIT[y][x];
            }
        }
        return sum;
    }

    public int sum(int row1, int col1, int row2, int col2) {
        return sum(row2, col2) - sum(row1-1, col1-1);
    }
}

public class NumMatrix {
    private final FenwickTree ft;
    public static void main(String[] args) {
        int[][] matrix = {{3, 0, 1, 4, 2},
                          {5, 6, 3, 2, 1},
                          {1, 2, 0, 1, 5},
                          {4, 1, 0, 1, 7},
                          {1, 0, 3, 0, 5}};
        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println(numMatrix.sum(2, 1, 4, 3)); // return 8 (i.e. sum of the left red rectangle)
        numMatrix.update(3, 2, 2);       // matrix changes from left image to right image
        System.out.println(numMatrix.sum(2, 1, 4, 3)); // return 10 (i.e. sum of the right red rectangle)
    }

    public NumMatrix(int[][] matrix) {
        ft = new FenwickTree(matrix);
    }

    public void update(int row, int col, int val) {
        ft.set(row, col, val);
    }

    public int sum(int row1, int col1, int row2, int col2) {
        return ft.sum(row2, col2) + ft.sum(row1-1, col1-1) - ft.sum(row2, col1 - 1) - ft.sum(row1 - 1, col2);
    }
}
