package trees.MinimumEdgeWeightEquilibriumQueriesinaTree;

import java.util.*;

public class Main {
    private static void test1() {
        int[][] edges = {{0,1,1},{1,2,1},{2,3,1},{3,4,2},{4,5,2},{5,6,2}};
        int[][] queries = {{0,3},{3,6},{2,6},{0,6}};
        System.out.println(Arrays.toString(minOperationsQueries(7, edges, queries)));
    }
    private static void test2() {
        int[][] edges = {{1,2,6},{1,3,4},{2,4,6},{2,5,3},{3,6,6},{3,0,8},{7,0,2}};
        int[][] queries = {{4,6},{0,4},{6,5},{7,4}};
        System.out.println(Arrays.toString(minOperationsQueries(8, edges, queries)));
    }
    private static void test3() {
        int[][] edges = {{6,2,1},{5,2,1},{4,2,2},{7,4,4},{1,7,4},{0,1,4},{3,2,2},{8,3,1}};
        int[][] queries = {{6,8},{4,4},{7,4},{2,4},{6,2},{1,1},{6,8},{5,7},{4,2},{2,6}};
        System.out.println(Arrays.toString(minOperationsQueries(9, edges, queries)));
    }
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }
    private static int[] depth;
    private static int[][] weightCounts;
    private static int[][] up;
    private static int LOG;
    private static final int MAX_WEIGHT = 27;


    public static int[] minOperationsQueries(int n, int[][] edges, int[][] queries) {
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }

        LOG = 0;
        for (int i = 0; (1 << i) <= n; i++) {
            LOG++; // get minimum logarithm for jumping
        }

        up = new int[n][LOG+1];
        for (int j = 0; j < n; j++) {
            Arrays.fill(up[j], -1);
        }
        depth = new int[n];
        weightCounts = new int[n][MAX_WEIGHT];
        dfs(0, -1, graph);

        Queue<int[]> nodes = new LinkedList<>();
        nodes.add(new int[]{0, -1}); // use 0 as root always
        // I used bfs here because my goal was to set -1 for jumps where a node doesn't exist
        while (!nodes.isEmpty()) {
            int[] meta = nodes.poll();
            int node = meta[0], parent = meta[1];
            up[node][0] = parent;
            if (node != 0) {
                for (int j = 1; j <= LOG; j++) {
                    if (up[node][j-1] == -1) {
                        break; // no point to go upwards further
                    }
                    up[node][j] = up[ up[node][j-1] ][j-1];
                }
            }
            for (int[] neigh : graph[node]) {
                if (neigh[0] != parent) {
                    nodes.add(new int[]{neigh[0], node});
                }
            }
        }

        int[] answer = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int[] query = queries[q];
            int u = query[0], v = query[1];
            int lca = lca(u, v);
            int[] uweight = Arrays.copyOf(weightCounts[u], MAX_WEIGHT);
            int[] vweight = Arrays.copyOf(weightCounts[v], MAX_WEIGHT);
            int[] lcaweight = Arrays.copyOf(weightCounts[lca], MAX_WEIGHT);
            int[] result = new int[MAX_WEIGHT];
            for (int i = 0; i < MAX_WEIGHT; i++) {
                result[i] = uweight[i] + vweight[i] - 2 * lcaweight[i];
            }
            answer[q] = Arrays.stream(result).sum() - Arrays.stream(result).max().getAsInt();
        }

        return answer;
    }

    private static int lca(int a, int b) {
        if (depth[a] < depth[b]) return lca(b, a);
        int k = depth[a] - depth[b];
        for (int j = LOG - 1; j >= 0; j--) {
            if ((k & (1 << j)) > 0) {
                a = up[a][j];
            }
        }

        if (a == b) return a;

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[a][j] != up[b][j]) {
                a = up[a][j];
                b = up[b][j];
            }
        }

        return up[a][0];
    }

    private static void dfs(int node, int parent, List<int[]>[] graph) {
        for (int[] neighbourWeight : graph[node]) {
            int neighbour = neighbourWeight[0], w = neighbourWeight[1];
            if (neighbour != parent) {
                depth[neighbour] = depth[node] + 1;
                weightCounts[neighbour] = Arrays.copyOf(weightCounts[node], MAX_WEIGHT);
                weightCounts[neighbour][w]++;
                dfs(neighbour, node, graph);
            }
        }
    }
}
