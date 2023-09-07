package graphs.RemoveMaxNumberofEdgestoKeepGraphFullyTraversive;

import java.util.*;

class UnionFind {
    int[] parents;
    int[] sizes;
    int numOfComponents;

    public UnionFind(int n) {
        parents = new int[n];
        sizes = new int[n];
        numOfComponents = n;
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            sizes[i] = 0;
        }
    }

    public int find(int node) {
        int root = node;
        while (root != parents[root]) {
            root = parents[root];
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
}

public class Main {
    private static void test1() {
        int[][] edges = {{3,1,2},{3,2,3},{1,1,3},{1,2,4},{1,1,2},{2,3,4}};
        System.out.println(maxNumEdgesToRemove(4, edges));
    }
    public static void main(String[] args) {
        test1();
    }

    public static int maxNumEdgesToRemove(int n, int[][] edges) {
        Arrays.sort(edges, (a, b) -> b[0] - a[0]);
        UnionFind alice = new UnionFind(n);
        UnionFind bob = new UnionFind(n);
        int minEdges = 0;
        for (int[] edge : edges) {
            int type = edge[0], u = edge[1] - 1, v = edge[2] - 1;
            if (type == 3) {
                boolean aliceDecision = alice.union(u, v), bobDecision = bob.union(u, v);
                if (aliceDecision || bobDecision) {
                    minEdges++;
                }
            } else if (type == 2) {
                if (bob.union(u, v)) {
                    minEdges++;
                }
            } else {
                if (alice.union(u, v)) {
                    minEdges++;
                }
            }
        }

        return alice.numOfComponents == 1 && bob.numOfComponents == 1 ? edges.length - minEdges : -1;
    }
}
