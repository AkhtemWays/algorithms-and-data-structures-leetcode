package backtracking.MaximumStrictlyIncreasingCellsinaMatrix;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Main {
    private static void test1() {
        int[][] mat = {{3,1},{3,4}};
        System.out.println(maxIncreasingCells(mat));
    }
    private static void test2() {
        int[][] mat = {{1,1},{1,1}};
        System.out.println(maxIncreasingCells(mat));
    }
    private static void test3() {
        int[][] mat = {{3,1,6},{-9,5,7}};
        System.out.println(maxIncreasingCells(mat));
    }
    private static void test5() {
        int[][] mat = {{9,-3,-2, 0,-3},
                       {-1,-4,7, 5, 9},
                       {1, 8, 0,-7,-6}};
        int[][] memo = {{0, 2, 0,0, 3},
                        {0, 1, 0,0, 0},
                        {0, 0, 0,1, 2}};
        System.out.println(maxIncreasingCells(mat));
    }
    private static void test4() {
        int c = 100000;
        int[][] mat = new int[1][c];
        for (int i = 0; i < c; i++) {
            mat[0][i] = i+1;
        }
        System.out.println(maxIncreasingCells(mat));
    }
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }

    private static int n;
    private static int m;
    public static int maxIncreasingCells(int[][] mat) {
        n = mat.length;
        m = mat[0].length;
        int[][] memo = new int[n][m];
        TreeSet<int[]>[] columns = new TreeSet[m];
        TreeSet<int[]>[] rows = new TreeSet[n];
        Comparator<int[]> comparator = (a, b) -> a[0] - b[0] == 0 ? b[2] - a[2] : a[0] - b[0];
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int i = 0; i < n; i++) {
            rows[i] = new TreeSet<>(comparator);
            for (int j = 0; j < m; j++) {
                rows[i].add(new int[]{mat[i][j], j, Integer.MIN_VALUE});
                if (columns[j] == null) columns[j] = new TreeSet<>(comparator);
                columns[j].add(new int[]{mat[i][j], i, Integer.MIN_VALUE});
                q.add(new int[]{i, j, mat[i][j]});
            }
        }

        int answer = 0;
        while (!q.isEmpty()) {
            int[] entry = q.poll();
            int row = entry[0];
            int col = entry[1];
            int val = entry[2];

            int cur = 1;
            int[] lowerOnRow = rows[row].lower(new int[]{val, 0, Integer.MIN_VALUE});
            if (lowerOnRow != null) cur = Math.max(cur, 1 + memo[row][lowerOnRow[1]]);
            int[] lowerOnCol = columns[col].lower(new int[]{val, 0, Integer.MIN_VALUE});
            if (lowerOnCol != null) cur = Math.max(cur, 1 + memo[lowerOnCol[1]][col]);


            memo[row][col] = cur;
            answer = Math.max(answer, cur);
        }
        return answer;
    }
}
