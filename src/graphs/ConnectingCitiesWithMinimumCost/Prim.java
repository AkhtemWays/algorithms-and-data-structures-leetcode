package graphs.ConnectingCitiesWithMinimumCost;

import java.util.*;

public class Prim {
    private static void test1() {
        int[][] connections = {{1,2,5},{1,3,6},{2,3,1}};
        System.out.println(minimumCost(3, connections));
    }

    private static void test2() {
        int[][] connections = {{1,2,3},{3,4,4}};
        System.out.println(minimumCost(4, connections));
    }

    public static void main(String[] args) {
        test1();
        test2();
    }

    public static int minimumCost(int n, int[][] connections) {
        return prim(n, connections);
    }

    private static int prim(int n, int[][] connections) {
        List<int[]>[] adj = new List[n+1];
        for (int i = 0; i < n+1; i++) adj[i] = new ArrayList<>();
        for (int[] connection : connections) {
            int from = connection[0];
            int to = connection[1];
            int weight = connection[2];
            adj[from].add(new int[]{to, weight});
            adj[to].add(new int[]{from, weight});
        }
        int answer = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        q.add(new int[]{1, 0});
        int numVisited = 0;
        boolean[] visited = new boolean[n+1];
        while (!q.isEmpty()) {
            int[] nodeWithWeight = q.poll();
            int weight = nodeWithWeight[1];
            int node = nodeWithWeight[0];
            if (!visited[node]) {
                answer += weight;
                visited[node] = true;
                numVisited++;
                q.addAll(adj[node]);
            }
        }
        return numVisited == n ? answer : -1;
    }
}
