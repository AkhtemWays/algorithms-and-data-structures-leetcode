package graphs.MostStonesRemovedwithSameRoworColumn;

import java.util.ArrayList;
import java.util.List;

public class Other {
    public static void main(String[] args) {

    }

    final int K = 10001;
    void dfs(List<Integer>[] adj, boolean[] visited, int src) {
        // Mark the stone as visited
        visited[src] = true;

        // Iterate over the adjacent, and iterate over it if not visited yet
        for (int adjacent : adj[src]) {
            if (!visited[adjacent]) {
                dfs(adj, visited, adjacent);
            }
        }
    }

    int removeStones(int[][] stones) {
        List<Integer>[] adj = new ArrayList[2 * K + 1];
        for (int i = 0; i < 2 * K + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < stones.length; i++) {
            int x = stones[i][0];
            int y = stones[i][1] + K;
            adj[x].add(y);
            adj[y].add(x);
        }

        boolean[] visited = new boolean[2 * K + 1];
        int componentCount = 0;
        for (int i = 0; i < 2 * K + 1; i++) {
            if (!visited[i] && adj[i].size() > 0) {
                // If the stone is not visited yet,
                // Start the DFS and increment the counter
                componentCount++;
                dfs(adj, visited, i);
            }
        }

        // Return the maximum stone that can be removed
        return stones.length - componentCount;
    }
}
