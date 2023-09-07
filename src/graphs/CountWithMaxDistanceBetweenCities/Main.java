package graphs.CountWithMaxDistanceBetweenCities;


import java.util.*;

public class Main {
    private static void test1() {
        int[][] edges = {{1,2},{2,3},{2,4}};
        System.out.println(Arrays.toString(countSubgraphsForEachDiameter(4, edges)));
    }
    public static void main(String[] args) {
        test1();
    }

    private static Set<List<Integer>> result = new HashSet<>();

    public static int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        result = new HashSet<>();
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]-1].add(edge[1]-1);
            graph[edge[1]-1].add(edge[0]-1);
        }
        int[] res = new int[n-1];
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = i+1;
        for (int bitmask = 1; bitmask < (1 << n+1) - 1; bitmask++) {
            Set<Integer> subtree = new HashSet<>();
            for (int i = 0; i < n; i++) {
                if (((bitmask >> i) & 1) == 1) {
                    subtree.add(nums[i]);
                }
            }
            HashMap<Integer, Integer> cache = new HashMap<>();
            for (int node : subtree) {
                int maxDistance = dfs(node, -1, subtree, cache, graph);
                if (maxDistance > 1) {
                    res[maxDistance-2]++;
                }
            }
        }

        return res;
    }

    private static int dfs(int node, int parent, Set<Integer> subtree, HashMap<Integer, Integer> cache, List<Integer>[] graph) {
        if (cache.containsKey(node)) return cache.get(node);
        int answer = 0;
        for (int neighbour : graph[node-1]) {
            if (neighbour != parent && subtree.contains(neighbour)) {
                answer = Math.max(answer, 1 + dfs(neighbour, node, subtree, cache, graph));
            }
        }

        cache.put(node, answer);

        return answer;
    }
}
