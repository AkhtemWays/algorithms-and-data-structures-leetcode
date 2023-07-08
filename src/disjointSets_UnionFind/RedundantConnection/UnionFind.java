package disjointSets_UnionFind.RedundantConnection;

import java.util.Arrays;

public class UnionFind {
    int[] parents;
    int[] sizes;
    public int numOfComponents;

    public UnionFind(int n) {
        parents = new int[n];
        sizes = new int[n];
        numOfComponents = n;
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            sizes[i] = 1;
        }
    }
    public int find(int node) {
        int root = node;
        while (root != parents[root]) {
            root = parents[root];
        }

        int cur = node;
        while (cur != root) {
            int curParent = parents[cur];
            parents[cur] = root;
            cur = curParent;
        }

        return root;
    }

    public boolean union(int x, int y) {
        int xParent = find(x);
        int yParent = find(y);

        if (xParent == yParent) return false;

        if (sizes[xParent] > sizes[yParent]) {
            parents[yParent] = xParent;
            sizes[xParent] += sizes[yParent];
        } else {
            parents[xParent] = yParent;
            sizes[yParent] += sizes[xParent];
        }
        numOfComponents--;
        return true;
    }

    public static void main(String[] args) {
        int[][] edges = {{1,2},{1,3},{2,3}};
        int[][] edges2 = {{1,2},{2,3},{3,4},{1,4},{1,5}};
        System.out.println(Arrays.toString(findRedundantConnection(edges)));
        System.out.println(Arrays.toString(findRedundantConnection(edges2)));
    }

    public static int[] findRedundantConnection(int[][] edges) {
        UnionFind unionFind = new UnionFind(edges.length+1);
        for (int[] edge : edges) {
            if (!unionFind.union(edge[0], edge[1])) return edge;
        }
        return new int[2];
    }
}

