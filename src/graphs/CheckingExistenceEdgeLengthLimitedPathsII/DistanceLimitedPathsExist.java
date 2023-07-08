package graphs.CheckingExistenceEdgeLengthLimitedPathsII;

import java.util.Arrays;
import java.util.Comparator;

public class DistanceLimitedPathsExist {
    public static void main(String[] args) {
        int[][] edgeList = {{0, 2, 4}, {0, 3, 2}, {1, 2, 3}, {2, 3, 1}, {4, 5, 5}};
        DistanceLimitedPathsExist distanceLimitedPathsExist = new DistanceLimitedPathsExist(6, edgeList);
        distanceLimitedPathsExist.query(2, 3, 2); // return true. There is an edge from 2 to 3 of distance 1, which is less than 2.
        distanceLimitedPathsExist.query(1, 3, 3); // return false. There is no way to go from 1 to 3 with distances strictly less than 3.
        distanceLimitedPathsExist.query(2, 0, 3); // return true. There is a way to go from 2 to 0 with distance < 3: travel from 2 to 3 to 0.
        distanceLimitedPathsExist.query(0, 5, 6); // return false. There are no paths from 0 to 5.
    }
    private final int[] parent;
    private final int[] rank;
    private final int[] weights;
    final int imax = Integer.MAX_VALUE;
    public DistanceLimitedPathsExist(int n, int[][] edgeList) {
        parent = new int[n+1];
        rank = new int[n+1];
        weights = new int[n+1];
        Arrays.fill(weights, 0);
        Arrays.fill(rank, 1);
        for(int i = 0; i < n; i++) parent[i] = i;
        Arrays.sort(edgeList, Comparator.comparingInt(e -> e[2]));
        for(int[] edge : edgeList) union(edge[0], edge[1], edge[2]);
    }
    public boolean query(int p, int q, int limit) {
        int parentP = find(p, limit);
        int parentQ = find(q, limit);
        return parentP == parentQ;
    }
    int find(int node, int limit) {
        if(parent[node] == node || weights[node] >= limit) return node;
        return find(parent[node], limit);
    }
    void union(int node1, int node2, int weight) {
        int parent1 = find(node1, imax), parent2 = find(node2, imax);
        // merge by size    150 ms
        if(parent1 != parent2) {
            if(rank[parent1] <= rank[parent2]) {
                parent[parent1] = parent2;
                rank[parent2] += rank[parent1];
                this.weights[parent1] = weight;
            } else {
                parent[parent2] = parent1;
                rank[parent1] += parent2;
                this.weights[parent2] = weight;
            }
        }
    }
}