package dynamicProgramming.MinimumTimetoReachDestinationWithoutDrowning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class Main {
    private static void test1() {
        List<List<String>> land = new ArrayList<>(List.of(
                List.of("D",".","*"),
                List.of(".",".","."),
                List.of(".","S",".")
        ));
        System.out.println(minimumSeconds(land));
    }
    private static void test2() {
        List<List<String>> land = new ArrayList<>(List.of(
                List.of("D",".",".",".","*","."),
                List.of(".","X",".","X",".","."),
                List.of(".",".",".",".","S",".")
        ));
        System.out.println(minimumSeconds(land));
    }
    private static void test3() {
        List<List<String>> land = new ArrayList<>(List.of(
                List.of(".",".","X",".","X",".",".",".",".","."),
                List.of("X",".",".","X",".",".",".",".",".","X"),
                List.of("X",".",".","X",".","X",".",".",".","."),
                List.of(".",".","X",".",".",".",".",".",".","."),
                List.of(".",".","X","X","X","D",".",".",".","."),
                List.of(".",".","X",".",".",".",".",".",".","S"),
                List.of(".",".",".",".","X","X",".","X",".","."),
                List.of(".",".","X",".",".",".",".",".",".","."),
                List.of(".",".",".",".",".",".",".",".",".","X"),
                List.of("X","X",".",".",".","X","X","X","X",".")
        ));
        System.out.println(minimumSeconds(land));
    }
    private static void test4() {
        List<List<String>> land = new ArrayList<>(List.of(
                List.of(".","S"),
                List.of(".","D")
        ));
        System.out.println(minimumSeconds(land));
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }
    private static int[][] deltas = {{0,1},{0,-1},{1,0},{-1,0}};
    private static int n;
    private static int m;
    private static class Meta {
        List<int[]> waterCells;
        int[] start;
        int[] destination;
        Meta(List<int[]> waterCells, int[] start, int[] destination) {
            this.waterCells = waterCells;
            this.start = start;
            this.destination = destination;
        }
    }

    public static int minimumSeconds(List<List<String>> land) {
        n = land.size(); m = land.get(0).size();
        Meta meta = getMeta(land);
        int[][] floodedTime = getFloodedTime(meta.waterCells, land);
        floodedTime[meta.destination[0]][meta.destination[1]] = Integer.MAX_VALUE;

        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        dp[meta.start[0]][meta.start[1]] = Integer.MAX_VALUE - 10000;
        int answer = dfs(meta.start, meta.destination, 0, dp, floodedTime, land);
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private static int dfs(int[] cur, int[] destination, int steps, int[][] dp, int[][] floodedTime, List<List<String>> land) {
        int i = cur[0], j = cur[1];
        if (Arrays.equals(cur, destination)) return steps;

        int answer = Integer.MAX_VALUE;
        for (int[] delta : deltas) {
            int curI = i - delta[0], curJ = j - delta[1];
            if (isDefinedAt(curI, curJ) && steps + 1 < floodedTime[curI][curJ] && !land.get(curI).get(curJ).equals("X") && dp[curI][curJ] > dp[i][j] + 1) {
                dp[curI][curJ] = dp[i][j] + 1;
                answer = Math.min(answer, dfs(new int[]{curI, curJ}, destination, steps + 1, dp, floodedTime, land));
            }
        }

        return answer;
    }

    private static int[][] getFloodedTime(List<int[]> waterCells, List<List<String>> land) {
        int[][] floodedTime = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(floodedTime[i], Integer.MAX_VALUE-10);
        }
        for (int[] waterCell : waterCells) {
            floodedTime[waterCell[0]][waterCell[1]] = 0;
            flood(waterCell, floodedTime, land);
        }
        return floodedTime;
    }

    private static void flood(int[] cell, int[][] floodedTime, List<List<String>> land) {
        int i = cell[0], j = cell[1];
        for (int[] delta : deltas) {
            int curI = i - delta[0], curJ = j - delta[1];
            if (isDefinedAt(curI, curJ) && floodedTime[curI][curJ] > floodedTime[i][j] + 1) {
                String place = land.get(curI).get(curJ);
                if (!place.equals("X") && !place.equals("D")) {
                    floodedTime[curI][curJ] = floodedTime[i][j] + 1;
                    flood(new int[]{curI, curJ}, floodedTime, land);
                }
            }
        }
    }

    private static Meta getMeta(List<List<String>> land) {
        List<int[]> flooded = new ArrayList<>();
        int[] start = null, destination = null;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                String str = land.get(i).get(j);
                if (str.equals("S")) start = new int[]{i, j};
                else if (str.equals("D")) destination = new int[]{i, j};
                else if (str.equals("*")) flooded.add(new int[]{i, j});
            }
        }

        return new Meta(flooded, start, destination);
    }

    private static boolean isDefinedAt(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }
}
