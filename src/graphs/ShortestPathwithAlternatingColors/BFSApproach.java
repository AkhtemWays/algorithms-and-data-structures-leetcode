package graphs.ShortestPathwithAlternatingColors;

import java.util.*;

public class BFSApproach {
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
        Queue<Integer> redQueue = new LinkedList<>();
        Queue<Integer> blueQueue = new LinkedList<>();
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
        for (int node : redGraph[0]) redQueue.offer(node);
        for (int node : blueGraph[0]) blueQueue.offer(node);
        memo = new int[n][2];
        for (int i = 1; i < n; i++) {
            memo[i][0] = Integer.MAX_VALUE;
            memo[i][1] = Integer.MAX_VALUE;
        }
        int count = 1;
        while (!redQueue.isEmpty() || !blueQueue.isEmpty()) {
            int redSize = redQueue.size();
            int blueSize = blueQueue.size();
            while (redSize-- > 0) {
                int curNode = redQueue.poll();
                memo[curNode][0] = count;
                for (int neighbour : blueGraph[curNode]) {
                    if (memo[neighbour][1] >= count+1) {
                        blueQueue.offer(neighbour);
                    }
                }
            }
            while (blueSize-- > 0) {
                int curNode = blueQueue.poll();
                memo[curNode][1] = count;
                for (int neighbour : redGraph[curNode]) {
                    if (memo[neighbour][0] >= count + 1) {
                        redQueue.offer(neighbour);
                    }
                }
            }
            count++;
        }
        int[] answer = new int[n];
        for (int i = 1; i < answer.length; i++) {
            answer[i] = Math.min(memo[i][0], memo[i][1]);
            if (answer[i] == Integer.MAX_VALUE) answer[i] = -1;
        }
        return answer;
    }
}
