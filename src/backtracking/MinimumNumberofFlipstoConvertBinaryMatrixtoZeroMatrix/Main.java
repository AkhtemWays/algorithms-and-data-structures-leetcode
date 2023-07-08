package backtracking.MinimumNumberofFlipstoConvertBinaryMatrixtoZeroMatrix;

import java.util.*;

public class Main {
    private static void test1() {
        int[][] mat = {{0,0},{0,1}};
        System.out.println(minFlips(mat));
    }
    private static void test2() {
        int[][] mat = {{0}};
        System.out.println(minFlips(mat));
    }
    private static void test3() {
        int[][] mat = {{1,0,0},{1,0,0}};
        System.out.println(minFlips(mat));
    }
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    private static int n;
    private static int m;
    private static int[][] target;
    private static int[][] deltas = {{0,-1},{0,1},{1,0},{-1,0},{0,0}};
    private static int[][] initialState;
    public static int minFlips(int[][] mat) {
        HashMap<Integer, Integer> cache = new HashMap<>();
        n = mat.length;
        m = mat[0].length;
        target = new int[n][m];
        initialState = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                initialState[i][j] = mat[i][j];
            }
        }
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                flip(mat, i, j);
                int res = 1 + dfs(mat, cache, new HashSet<>());
                if (res != -1) {
                    answer = Math.min(answer, res);
                }
                flip(mat, i, j);
            }
        }
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private static int dfs(int[][] mat, HashMap<Integer, Integer> cache, HashSet<Integer> seen) {
        if (equals(mat, target)) return 0;
        if (equals(mat, initialState)) return -1;
        int mask = getMask(mat);
        if (cache.containsKey(mask)) return cache.get(mask);

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                flip(mat, i, j);
                int m = getMask(mat);
                if (!seen.contains(m)) {
                    seen.add(m);
                    int res = dfs(mat, cache, seen);
                    if (res != -1) {
                        answer = Math.min(answer, 1 + res);
                    }
                    seen.remove(m);
                }
                flip(mat, i, j);
            }
        }
        int result = answer == Integer.MAX_VALUE ? -1 : answer;
        cache.put(mask, result);
        return result;
    }

    private static void flip(int[][] mat, int i, int j) {
        Arrays.stream(deltas).forEach(delta -> {
            int y = i - delta[0];
            int x = j - delta[1];
            if (isDefinedAt(y, x)) {
                mat[y][x] ^= 1;
            }
        });
    }

    private static boolean isDefinedAt(int i, int j) {
        return i < n && i >= 0 && j < m && j >= 0;
    }

    private static int getMask(int[][] mat) {
        int mask = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mask |= (mat[i][j] << (i * m + j));
            }
        }
        return mask;
    }

    private static boolean equals(int[][] mat, int[][] targetState) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] != targetState[i][j]) return false;
            }
        }
        return true;
    }
}
