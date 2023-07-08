package backtracking.LastDayWhereYouCanStillCross;

public class Main {
    public static void main(String[] args) {
        test1(); // 2
        test2(); // 1
        test3(); // 3
    }
    private static void test1() {
        int[][] cells = {{1,1},{2,1},{1,2},{2,2}};
        System.out.println(latestDayToCross(2, 2, cells));
    }
    private static void test2() {
        int[][] cells = {{1,1},{1,2},{2,1},{2,2}};
        System.out.println(latestDayToCross(2, 2, cells));
    }
    private static void test3() {
        int[][] cells = {{1,2},{2,1},{3,3},{2,2},{1,1},{1,3},{2,3},{3,2},{3,1}};
        System.out.println(latestDayToCross(3, 3, cells));
    }
    private static final int[][] deltas = {{0,1},{0,-1},{1,0}};
    private static int n;
    private static int m;

    public static int latestDayToCross(int row, int col, int[][] cells) {
        n = row;
        m = col;
        int left = 0, right = cells.length;
        while (left < right) {
            int mid = (left + right) / 2;
            int[][] matrix = getMatrix(cells, mid);
            if (canReach(matrix)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left-1;
    }

    private static int[][] getMatrix(int[][] cells, int x) {
        int[][] matrix = new int[n][m];
        for (int i = 0; i < x; i++) {
            int[] cell = cells[i];
            matrix[cell[0]-1][cell[1]-1] = 1;
        }
        return matrix;
    }

    private static boolean canReach(int[][] matrix) {
        for (int start = 0; start < matrix[0].length; start++) {
            if (matrix[0][start] == 0 && dfs(matrix, 0, start, -1, -1)) return true;
        }
        return false;
    }

    private static boolean dfs(int[][] matrix, int i, int j, int parentI, int parentJ) {
        if (i >= n-1) return true;
        for (int[] delta : deltas) {
            int curI = i + delta[0];
            int curJ = j + delta[1];
            if (isDefined(curI, curJ) && matrix[curI][curJ] == 0 && curI != parentI && curJ != parentJ && dfs(matrix, curI, curJ, i, j)) return true;
        }
        return false;
    }

    private static boolean isDefined(int i, int j) {
        return i < n && i >= 0 && j < m && j >= 0;
    }
}
