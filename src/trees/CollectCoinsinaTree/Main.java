package trees.CollectCoinsinaTree;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static void test1() {
        int[] coins = {1,0,0,0,0,1};
        int[][] edges = {{0,1},{1,2},{2,3},{3,4},{4,5}};
        System.out.println(collectTheCoins(coins, edges));
    }
    private static void test2() {
        int[] coins = {1,0,1,0,1,0,1,0,1,0,1,0,1,1};
        int[][] edges = {{0,1},{0,2},{1,3},{1,4},{2,5},{3,6},{3,7},{3,8},{5,9},{5,10},{5,11},{10,12},{10,13}};
        System.out.println(collectTheCoins(coins, edges));
    }
    private static void test3() {
        int[] coins = {0,0,0,1,1,0,0,1};
        int[][] edges = {{0,1},{0,2},{1,3},{1,4},{2,5},{5,6},{5,7}};
        System.out.println(collectTheCoins(coins, edges));
    }
    public static void main(String[] args) {
        test1(); // 2
        test2(); // 6
        test3(); // 2
    }

    private static int[][] meta;
    public static int collectTheCoins(int[] coins, int[][] edges) {
        int n = coins.length;
        meta = new int[n][5];
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        dfs(0, -1, graph, coins);

        int answer = meta[0][4];
        for (int neighbour : graph[0]) {
            answer = Math.min(answer, dfs2(neighbour, 0, meta[0], graph));
        }
        return answer;
    }

    private static void dfs(int node, int parent, List<Integer>[] graph, int[] coins) {
        if (coins[node] == 1) {
            meta[node][0]++;
        }
        for (int neighbour : graph[node]) {
            if (neighbour != parent) {
                dfs(neighbour, node, graph, coins);
                meta[node][1] += meta[neighbour][0];
                meta[node][2] += meta[neighbour][1];
                meta[node][3] += meta[neighbour][2] + meta[neighbour][3];
                if (meta[neighbour][4] != 0) meta[node][4] += meta[neighbour][4] + 2;
                else if (meta[neighbour][3] > 0 || meta[neighbour][2] > 0) meta[node][4] += 2;
            }
        }
    }

    private static int dfs2(int node, int parent, int[] parentMeta, List<Integer>[] graph) {
        int answer = Integer.MAX_VALUE;
        int[] updatedChildMeta = getMetaFromParent(parentMeta, meta[node]);
        for (int neighbour : graph[node]) {
            if (neighbour != parent) {
                answer = Math.min(answer, dfs2(neighbour, node, updatedChildMeta, graph));
            }
        }
        return answer;
    }

    private static int[] getMetaFromParent(int[] parentMeta, int[] curChildMeta) {
        int[] updatedChildMeta = new int[5];
        updatedChildMeta[0] = curChildMeta[0];
        updatedChildMeta[3] += parentMeta[3] - curChildMeta[2];
        updatedChildMeta[2] += parentMeta[2] - curChildMeta[1];
        updatedChildMeta[1] += parentMeta[1] - curChildMeta[0];
        updatedChildMeta[4] = parentMeta[4] - 2 + curChildMeta[4];

        return updatedChildMeta;
    }
}
