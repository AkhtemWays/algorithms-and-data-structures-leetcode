package disjointSets_UnionFind.MaxAreaOfIsland;

class UnionFind {
    int[] parents;
    int[] sizes;
    int numOfComponents;
    int maxSize = 0;

    public UnionFind(int n) {
        parents = new int[n];
        sizes = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = -1;
            sizes[i] = 0;
        }
    }

    public boolean createParent(int pos) {
        if (parents[pos] != -1) return false;
        parents[pos] = pos;
        sizes[pos] = 1;
        maxSize = Math.max(1, maxSize);
        return true;
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
        maxSize = Math.max(Math.max(sizes[yParent], sizes[xParent]), maxSize);
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] grid = {{0,0,1,0,0,0,0,1,0,0,0,0,0},
                        {0,0,0,0,0,0,0,1,1,1,0,0,0},
                        {0,1,1,0,1,0,0,0,0,0,0,0,0},
                        {0,1,0,0,1,1,0,0,1,0,1,0,0},
                        {0,1,0,0,1,1,0,0,1,1,1,0,0},
                        {0,0,0,0,0,0,0,0,0,0,1,0,0},
                        {0,0,0,0,0,0,0,1,1,1,0,0,0},
                        {0,0,0,0,0,0,0,1,1,0,0,0,0}};
        System.out.println(maxAreaOfIsland(grid));
    }

    public static int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0) return 0;
        int n = grid[0].length;
        int m = grid.length;
        UnionFind unionFind = new UnionFind(m * n);
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 0) continue;
                int pos = row * n + col;
                if (!unionFind.createParent(pos)) {
                    continue;
                }
                int top = (pos-n >= 0) ? pos-n : -1;
                int bottom = (pos+n < m*n) ? pos+n : -1;
                int left = ((pos-1) % n == n-1 && pos-1 >= 0) ? -1 : pos-1;
                int right = ((pos+1) % n != 0 && pos+1 < m*n) ? pos+1 : -1;
                if (top != -1 && unionFind.parents[top] != -1) {
                    unionFind.union(pos, top);
                }
                if (bottom != -1 && unionFind.parents[bottom] != -1) {
                    unionFind.union(pos, bottom);
                }
                if (left != -1 && unionFind.parents[left] != -1) {
                    unionFind.union(pos, left);
                }
                if (right != -1 && unionFind.parents[right] != -1) {
                    unionFind.union(pos, right);
                }
            }
        }
        return unionFind.maxSize;
    }
}
