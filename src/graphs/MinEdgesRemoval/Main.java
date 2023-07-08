package graphs.MinEdgesRemoval;

import java.util.*;

class UnionFind {
    int[] parents;
    int[] ranks;
    int numComponents;

    UnionFind(int n) {
        parents = new int[n];
        ranks = new int[n];
        numComponents = n;
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            ranks[i] = 1;
        }
    }

    public int find(int node) {
        int root = node;
        while (parents[node] != node) {
            node = parents[node];
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

        numComponents--;

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
    static class Node {
        int value;
        int indegree;
        Node(int value, int indegree) {
            this.value = value;
            this.indegree = indegree;
        }
    }
    public static void main(String[] args) {
        int[][] edges = {{0, 1},
                {6, 1},
                {2, 4},
                {2, 3},
                {3, 4}};
        System.out.println(numEdges(7, edges, 6));
    }

    public static int numEdges(int n, int[][] edges, int k) {
        Set<Integer>[] adj = new Set[n];
        for (int i = 0; i < n; i++) adj[i] = new HashSet<>();
        UnionFind unionFind = new UnionFind(n);

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
            unionFind.union(edge[0], edge[1]);
        }

        int componentsLeft = k - unionFind.numComponents - 1;
        //      value  Node
        HashMap<Integer, Node> map = new HashMap<>();

        TreeSet<Node> bst = new TreeSet<>(Comparator.comparingInt(a -> a.indegree));

        for (int value = 0; value < n; value++) {
            if (!adj[value].isEmpty()) {
                Node node = new Node(value, adj[value].size());
                map.put(node.value, node);
                bst.add(node);
            }
        }

        int answer = 0;

        while (componentsLeft > 0) {
            Node node = bst.first();
            node.indegree--;
            if (node.indegree == 0) {
                componentsLeft--;
                bst.remove(node);
            } else {
                bst.add(node);
            }
            Set<Integer> neighbours = adj[node.value];
            int neighbourValue = neighbours.stream().findAny().get();
            Node neighbour = map.get(neighbourValue);
            neighbour.indegree--;
            if (neighbour.indegree == 0) {
                bst.remove(neighbour);
            } else {
                bst.add(neighbour);
            }
            adj[neighbourValue].remove(node.value);
            answer++;
        }
        return answer;
    }
}
