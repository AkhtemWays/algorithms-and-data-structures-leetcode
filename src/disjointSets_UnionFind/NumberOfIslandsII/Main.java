package disjointSets_UnionFind.NumberOfIslandsII;

import java.util.*;

class UnionFind {
    int[] parents;
    int[] sizes;
    int numOfComponents;

    public UnionFind(int n) {
        parents = new int[n];
        sizes = new int[n];
        numOfComponents = 0;
        for (int i = 0; i < n; i++) {
            parents[i] = -1;
            sizes[i] = 0;
        }
    }

    public boolean createParent(int pos) {
        if (parents[pos] != -1) return false;
        parents[pos] = pos;
        sizes[pos] = 1;
        numOfComponents++;
        return true;
    }

    public int find(int node) {
        int root = node;
        while (root != parents[root]) {
            root = parents[root];
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
}

public class Main {
    public static void main(String[] args) {
        int[][] positions = {{0,0},{1,1},{0,1}};
        int[][] positions2 = {{0,0},{0,1},{1,2},{2,1}};
        int[][] positions3 = {{0,1},{1,2},{2,1},{1,0},{0,2},{0,0},{1,1}};
        int[][] positions4 = {{0,0},{0,1},{1,2},{1,2}};
        numIslands2(3, 3, positions2).forEach(System.out::print);
        System.out.println();
        numIslands2(3, 3, positions).forEach(System.out::print);
        System.out.println();
        numIslands2(3, 3, positions3).forEach(System.out::print);
        System.out.println();
        numIslands2(3, 3, positions4).forEach(System.out::print);
    }

    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        UnionFind unionFind = new UnionFind(m*n);
        List<Integer> result = new ArrayList<>();

        for (int[] position : positions) {
            int pos = position[0] * n + position[1];
            if (!unionFind.createParent(pos)) {
                result.add(unionFind.numOfComponents);
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
            result.add(unionFind.numOfComponents);
        }
        return result;
    }
}
