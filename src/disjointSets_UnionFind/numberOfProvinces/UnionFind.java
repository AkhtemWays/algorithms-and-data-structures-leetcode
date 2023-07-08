package disjointSets_UnionFind.numberOfProvinces;

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
        int[][] isConnected = {{1,1,0},
                               {1,1,0},
                               {0,0,1}};
        int[][] isConnected2 = {{1,0,0},{0,1,0},{0,0,1}};
        System.out.println(findCircleNum(isConnected));
        System.out.println(findCircleNum(isConnected2));
    }

    public static int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind unionFind = new UnionFind(n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.numOfComponents;
    }
}
