package backtracking.KnightProbabilityinChessboard;

public class Main {
    int[][] direction = new int[][]{{2,1},{-2,1},{2,-1},{-2,-1},{1,2},{1,-2},{-1,2},{-1,-2}};
    int N;
    int r;
    int c;
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.knightProbability(3, 2, 0, 0));
    }

    public double knightProbability(int N, int K, int r, int c) {
        this.N = N;
        double[][][] dp = new double[K+1][N][N];
        double cnt = 0;
        this.r = r;
        this.c = c;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[K][i][j] = 1 / 8.0;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cnt += dfs(i, j, K, dp);
            }
        }

        return cnt;
    }

    private double dfs(int i, int j, int steps, double[][][] dp) {
        if (steps == 0) {
            if (i == r && j == c) return 1.0 / 8.0;
            return 0.0;
        }
        if (dp[steps][i][j] != 0) return dp[steps][i][j];
        double cnt = 0D;

        for (int[] dir : direction) {
            int curI = i - dir[0];
            int curJ = i - dir[1];
            if (isOnBoard(curI, curJ)) {
                cnt += dfs(curI, curJ, steps-1, dp);
            }
        }
        return dp[steps][i][j] = cnt;
    }

    private boolean isOnBoard(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < N;
    }
}
