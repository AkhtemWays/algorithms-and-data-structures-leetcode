package graphs.PossibleBipartition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class UnionFind {
    int[] parents;
    int[] ranks;
    UnionFind(int n) {
        parents = new int[n+1];
        ranks = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
            ranks[i] = 1;
        }
    }

    public int find(int node) {
        int root = node;
        while (parents[root] != root) {
            root = parents[root];
        }

        while (node != root) {
            node = parents[node];
            parents[node] = root;
        }

        return root;
    }

    public void union(int node1, int node2) {
        int parent1 = find(node1);
        int parent2 = find(node2);

        if (parent1 == parent2) return;

        if (ranks[parent1] > ranks[parent2]) {
            parents[parent2] = parent1;
            ranks[parent1] += ranks[parent2];
        } else {
            parents[parent1] = parent2;
            ranks[parent2] += ranks[parent1];
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] dislikes = {{1, 2}, {3,4}, {5,6}, {7, 4}, {4, 1}};
        System.out.println(possibleBipartition(7, dislikes));
    }

    public static boolean possibleBipartition(int n, int[][] dislikes) {
        List<Integer>[] adj = new List[n+1];
        for (int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();
        for (int[] dislike : dislikes) {
            adj[dislike[0]].add(dislike[1]);
            adj[dislike[1]].add(dislike[0]);
        }

        UnionFind unionFind = new UnionFind(n);


        for (int node = 1; node <= n; node++) {
            for (int neighbour : adj[node]) {
                if (unionFind.find(node) == unionFind.find(neighbour)) return false;
                unionFind.union(neighbour, adj[node].get(0));
            }
        }
        return true;
    }
}
