package graphs.MostStonesRemovedwithSameRoworColumn;

class UnionFind {
    int[] parents;
    int[] ranks;
    int numComponents;
    UnionFind(int n) {
        parents = new int[n];
        ranks = new int[n];
        numComponents = n;
        for (int i = 0; i < n; i++) parents[i] = i;
    }

    public int find(int node) {
        int root = node;
        while (root != parents[root]) {
            root = parents[root];
        }

        while (node != root) {
            node = parents[node];
            parents[node] = root;
        }

        return root;
    }

    public void union(int stone1, int stone2) {
        int parent1 = find(stone1);
        int parent2 = find(stone2);

        if (parent2 == parent1) return;

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

public class UnionFindApproach {
    public static void main(String[] args) {
        int[][] stones = {{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}};
        // [1, 1, 0]
        // [1, 0, 1]
        // [0, 1, 1]
        System.out.println(removeStones(stones));
    }

    private static boolean shareSameRowOrColumn(int[] stone1, int[] stone2) {
        return stone1[0] == stone2[0] || stone1[1] == stone2[1];
    }

    public static int removeStones(int[][] stones) {
        int n = stones.length;
        UnionFind unionFind = new UnionFind(n);

        for (int i = 0; i < n-1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (shareSameRowOrColumn(stones[i], stones[j])) {
                    unionFind.union(i, j);
                }
            }
        }

        return n - unionFind.numComponents;
    }
}
