package disjointSets_UnionFind.GraphValidTree;


public class UnionFind {
    int[] parents;
    int[] sizes;
    int numOfComponents;

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
        int[][] edges = {{0,1},{0,2},{0,3},{1,4}};
        int[][] edges2 = {{0,1},{1,2},{2,3},{1,3},{1,4}};
        int[][] edges3 = {{0,1},{2,3}};
        System.out.println(validTree(5, edges));
        System.out.println(validTree(5, edges2));
        System.out.println(validTree(5, edges3));
    }

    public static boolean validTree(int n, int[][] edges) {
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            if (!unionFind.union(edge[0], edge[1])) return false;
        }
        return unionFind.numOfComponents == 1;
    }
}
