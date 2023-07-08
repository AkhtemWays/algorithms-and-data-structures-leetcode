package backtracking.NQueensII;

public class Main {
    public static void main(String[] args) {
        System.out.println(totalNQueens(8));
    }

    public static int totalNQueens(int n) {
        int[][] board = new int[n][n];
        return dfs(0, 0, board, 0, n);
    }

    private static int dfs(int i, int j, int[][] board, int queens, int n) {
        if (queens == n) return 1;
        if (j == n) return dfs(i+1, 0, board, queens, n);
        if (i == n) return 0;

        int cnt = 0;
        if (canPlace(i, j, board)) {
            board[i][j] = 1;
            cnt += dfs(i, j+1, board, queens+1, n);
            board[i][j] = 0;
        }
        cnt += dfs(i, j+1, board, queens, n);
        return cnt;
    }

    private static boolean canPlace(int i, int j, int[][] board) {
        int n = board.length;
        for (int x = 0; x < n; x++) {
            if (board[i][x] == 1) return false;
            if (board[x][j] == 1) return false;
        }

        for (int x = j, y = i; x < n && y < n; x++, y++) {
            if (board[y][x] == 1) return false;
        }
        for (int x = j, y = i; x >= 0 && y >= 0; x--, y--) {
            if (board[y][x] == 1) return false;
        }
        for (int x = j, y = i; x >= 0 && y < n; x--, y++) {
            if (board[y][x] == 1) return false;
        }
        for (int x = j, y = i; x < n && y >= 0; x++, y--) {
            if (board[y][x] == 1) return false;
        }
        return true;
    }
}
