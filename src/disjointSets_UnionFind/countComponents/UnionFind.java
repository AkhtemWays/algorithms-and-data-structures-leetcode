package disjointSets_UnionFind.countComponents;

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

    public void union(int x, int y) {
        int xParent = find(x);
        int yParent = find(y);

        if (xParent == yParent) return;

        if (sizes[xParent] > sizes[yParent]) {
            parents[yParent] = xParent;
            sizes[xParent] += sizes[yParent];
        } else {
            parents[xParent] = yParent;
            sizes[yParent] += sizes[xParent];
        }
        numOfComponents--;
    }

    public static void main(String[] args) {
        int[][] edges = {{0,1},{0,2},{0,3},{1,4}};
        int[][] edges2 = {{0,1},{1,2},{2,3},{1,3},{1,4}};
        System.out.println(countComponents(5, edges));
        System.out.println(countComponents(5, edges2));
    }

    public static int countComponents(int n, int[][] edges) {
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            unionFind.union(edge[0], edge[1]);
        }
        return unionFind.numOfComponents;
    }
}
