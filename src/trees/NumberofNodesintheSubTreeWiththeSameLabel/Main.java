package trees.NumberofNodesintheSubTreeWiththeSameLabel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[][] edges = {{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}};
        int[][] edges2 = {{0,2},{0,3},{1,2}};
        System.out.println(Arrays.toString(countSubTrees(7, edges, "abaedcd")));
        System.out.println(Arrays.toString(countSubTrees(4, edges2, "aeed")));
    }

    public static int[] countSubTrees(int n, int[][] edges, String labels) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        int[] answer = new int[n];

        int[] rootLabelsCount = dfs(adj, 0, -1, answer, labels);
        answer[0] = rootLabelsCount[labels.charAt(0) - 'a'];
        return answer;
    }

    private static int[] dfs(List<Integer>[] adj, int cur, int prev, int[] answer, String labels) {
        int[] currentCounts = new int[26];
        for (int neighbour : adj[cur]) {
            if (neighbour == prev) continue;
            merge(currentCounts, dfs(adj, neighbour, cur, answer, labels));
        }
        currentCounts[labels.charAt(cur) - 'a']++;
        answer[cur] = currentCounts[labels.charAt(cur) - 'a'];
        return currentCounts;
    }

    private static void merge(int[] currentCounts, int[] newCounts) {
        for (int i = 0; i < newCounts.length; i++) {
            currentCounts[i] += newCounts[i];
        }
    }
}
