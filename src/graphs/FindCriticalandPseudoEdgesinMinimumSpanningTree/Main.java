package graphs.FindCriticalandPseudoEdgesinMinimumSpanningTree;

import java.util.*;

public class Main {
    private static void test1() {
        int[][] edges = {{0,1,1},{1,2,1},{2,3,2},{0,3,2},{0,4,3},{3,4,3},{1,4,6}};
        System.out.println(findCriticalAndPseudoCriticalEdges(5, edges));
    }
    private static void test2() {
        int[][] edges = {{0,1,1},{1,2,1},{2,3,1},{0,3,1}};
        System.out.println(findCriticalAndPseudoCriticalEdges(4, edges));
    }
    private static void test3() {
        int[][] edges = {{0,1,3}};
        System.out.println(findCriticalAndPseudoCriticalEdges(2, edges));
    }
    public static void main(String[] args) {
        test1(); // [[0, 1], [3, 2, 4, 5]]
        test2(); // [[], [0, 3, 1, 2]]
        test3(); // [[0], []]
    }
    private static class Edge {
        int from;
        int to;
        int weight;
        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (this.hashCode() != obj.hashCode()) return false;
            if (obj instanceof Edge) {
                Edge other = (Edge) obj;
                return this.from == other.from && this.weight == other.weight && this.to == other.to;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to, weight);
        }
    }
    private static HashMap<Edge, int[]> MSTedgesCount;
    public static List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        initMSTedgesCount(edges); // here we initialize each edge and set it's count to zero
        for (int startNode = 0; startNode < n; startNode++) {
            prim(startNode, n); // just launch prim for every node
        }
        List<List<Integer>> answer = new ArrayList<>(List.of(new ArrayList<>(), new ArrayList<>()));
        for (int[] meta : MSTedgesCount.values()) {
            int index = meta[0];
            int count = meta[1];
            if (count == n) answer.get(0).add(index); // critical edge found
            else if (count > 0)answer.get(1).add(index); // pseudo-critical edge found
        }
        return answer;
    }

    private static void prim(int startNode, int n) {
        List<int[]>[] adj = buildAdj(n);
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[2] - b[2] == 0 ? a[3] - b[3] : a[2] - b[2]); // sort by weights first and then by counts
        Set<Integer> unvisited = new HashSet<>();
        for (int node = 0; node < n; node++) {
            unvisited.add(node);
        }
        q.add(new int[]{startNode, startNode, 0, 0});
        while (!q.isEmpty() && !unvisited.isEmpty()) {
            int[] meta = q.poll();
            int from = meta[0];
            int to = meta[1];
            int weight = meta[2];
            if (unvisited.contains(to)) {
                if (from != to) { // if this condition is true - we know that this edge exists
                    Edge edge = new Edge(Math.min(from, to), Math.max(from, to), weight); // min max for from and to is used to avoid duplication
                    MSTedgesCount.get(edge)[1]++;
                }
                unvisited.remove(to);
                for (int[] neighbour : adj[to]) {
                    q.add(new int[]{to, neighbour[0], neighbour[1], neighbour[2]});
                }
            }
        }
    }

    private static List<int[]>[] buildAdj(int n) {
        List<int[]>[] adj = new List[n];
        for (Map.Entry<Edge, int[]> entry : MSTedgesCount.entrySet()) {
            Edge edge = entry.getKey();
            int[] meta = entry.getValue();
            if (adj[edge.from] == null) adj[edge.from] = new ArrayList<>();
            if (adj[edge.to] == null) adj[edge.to] = new ArrayList<>();
            adj[edge.from].add(new int[]{edge.to, edge.weight, meta[1]});
            adj[edge.to].add(new int[]{edge.from, edge.weight, meta[1]});
        }
        return adj;
    }

    private static void initMSTedgesCount(int[][] edges) {
        MSTedgesCount = new HashMap<>();
        for (int index = 0; index < edges.length; index++) {
            int[] e = edges[index];
            int from = e[0];
            int to = e[1];
            int weight = e[2];
            Edge edge = new Edge(Math.min(from ,to), Math.max(from ,to), weight);
            MSTedgesCount.put(edge, new int[]{index, 0});
        }
    }
}
