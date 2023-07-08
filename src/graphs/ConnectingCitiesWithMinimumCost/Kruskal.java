package graphs.ConnectingCitiesWithMinimumCost;

import java.util.Arrays;
import java.util.Comparator;

class UnionFind {
    int[] parent;
    int[] rank;
    public UnionFind(int n) {
        parent = new int[n+1];
        rank = new int[n+1];
        for (int i = 0; i < n+1; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }
    public boolean union(int node1, int node2) {
        int parent1 = find(node1);
        int parent2 = find(node2);

        if (parent1 == parent2) return false;

        if (rank[parent1] > rank[parent2]) {
            rank[parent1]++;
            parent[parent2] = parent1;
        } else {
            rank[parent2]++;
            parent[parent1] = parent2;
        }
        return true;
    }

    public int find(int node) {
        int root = node;
        while (root != parent[root]) {
            root = parent[root];
        }

        int cur = node;
        while (cur != root) {
            int curParent = parent[cur];
            parent[cur] = root;
            cur = curParent;
        }

        return root;
    }
}

public class Kruskal {
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
        Arrays.sort(connections, Comparator.comparingInt(a -> a[2]));
        UnionFind unionFind = new UnionFind(n);
        int minCost = 0;
        int numComponents = n;
        for (int[] connection : connections) {
            int node1 = connection[0];
            int node2 = connection[1];
            int weight = connection[2];
            boolean addEdge = unionFind.union(node1, node2);
            if (addEdge) {
                minCost += weight;
                numComponents--;
            }
        }
        return numComponents == 1 ? minCost : -1;
    }
}
