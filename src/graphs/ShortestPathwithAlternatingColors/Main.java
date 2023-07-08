package graphs.ShortestPathwithAlternatingColors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static int[][] memo;
    public static void main(String[] args) {
        int[][] redEdges = {{0,1},{1,2}};
        int[][] blueEdges = {};
        int[][] redEdges2 = {{0,1}};
        int[][] blueEdges2 = {{2,1}};
        int[][] redEdges3 = {{0,1},{1,2},{2,3},{3,4}};
        int[][] blueEdges3 = {{1,2},{2,3},{3,1}};
        System.out.println(Arrays.toString(shortestAlternatingPaths(3, redEdges, blueEdges)));
        System.out.println(Arrays.toString(shortestAlternatingPaths(3, redEdges2, blueEdges2)));
        System.out.println(Arrays.toString(shortestAlternatingPaths(5, redEdges3, blueEdges3)));
    }

    public static int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<Integer>[] blueGraph = new List[n];
        List<Integer>[] redGraph = new List[n];
        for (int i = 0; i < n; i++) {
            blueGraph[i] = new ArrayList<>();
            redGraph[i] = new ArrayList<>();
        }
        for (int[] edge : redEdges) {
            redGraph[edge[0]].add(edge[1]);
        }
        for (int[] edge : blueEdges) {
            blueGraph[edge[0]].add(edge[1]);
        }
        memo = new int[n][2];
        for (int i = 1; i < n; i++) {
            memo[i][0] = Integer.MAX_VALUE;
            memo[i][1] = Integer.MAX_VALUE;
        }
        for (int node : redGraph[0]) {
            dfs(node, redGraph, blueGraph, false, 1);
        }
        for (int node : blueGraph[0]) {
            dfs(node, redGraph, blueGraph, true, 1);
        }
        int[] answer = new int[n];
        for (int i = 1; i < answer.length; i++) {
            answer[i] = Math.min(memo[i][0], memo[i][1]);
            if (answer[i] == Integer.MAX_VALUE) answer[i] = -1;
        }
        return answer;
    }

    private static void dfs(int cur, List<Integer>[] redGraph, List<Integer>[] blueGraph, boolean red, int count) {
        if ((red && memo[cur][0] <= count) || (!red && memo[cur][1] <= count)) return;
        memo[cur][red ? 0 : 1] = count;

        for (int node : red ? redGraph[cur] : blueGraph[cur]) {
            if (memo[cur][red ? 0 : 1] >= count + 1) {
                dfs(node, redGraph, blueGraph, !red, count + 1);
            }
            else if (memo[cur][red ? 0 : 1] >= count) {
                dfs(node, redGraph, blueGraph, !red, count + 1);
            }
        }
    }
}
