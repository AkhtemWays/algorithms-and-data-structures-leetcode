package trees.KthAncestorofaTreeNode;

public class TreeAncestor {
    private static void test1() {
        int[] parent = {-1, 0, 0, 1, 1, 2, 2};
        TreeAncestor treeAncestor = new TreeAncestor(7, parent);
        System.out.println(treeAncestor.getKthAncestor(3, 1)); // returns 1 which is the parent of 3
        System.out.println(treeAncestor.getKthAncestor(5, 2)); // returns 0 which is the grandparent of 5
        System.out.println(treeAncestor.getKthAncestor(6, 3)); // returns -1 because there is no such ancestor
    }
    private static void test2() {
        int[] parent = {-1,2,3,0};
        TreeAncestor treeAncestor = new TreeAncestor(4, parent);
        System.out.println(treeAncestor.getKthAncestor(2, 3)); // returns -1
        System.out.println(treeAncestor.getKthAncestor(2, 2)); // returns 0
        System.out.println(treeAncestor.getKthAncestor(2, 1)); // returns 3
    }
    private static void test3() {
        int[] parent = {-1,0,0,0,3};
        TreeAncestor treeAncestor = new TreeAncestor(5, parent);
        System.out.println(treeAncestor.getKthAncestor(1, 5)); // returns -1
        System.out.println(treeAncestor.getKthAncestor(3, 2)); // returns -1
        System.out.println(treeAncestor.getKthAncestor(0, 1)); // returns -1
        System.out.println(treeAncestor.getKthAncestor(3, 1)); // returns 0
        System.out.println(treeAncestor.getKthAncestor(3, 5)); // returns -1
    }
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }
    private int LOG;
    private int[][] ancestors;
    public TreeAncestor(int n, int[] parent) {
        LOG = 0;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        ancestors = new int[n][LOG];
        for (int i = 0; i < n; i++) {
            ancestors[i][0] = parent[i];
        }

        for (int node = 1; node < n; node++) {
            for (int j = 1; j < LOG; j++) {
                if (ancestors[node][j-1] == -1) {
                    ancestors[node][j] = -1;
                } else {
                    ancestors[node][j] = ancestors[ ancestors[node][j-1] ][j-1];
                }
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        for (int i = 0; i < LOG; i++) {
            if (((k >> i) & 1) == 1) {
                node = ancestors[node][i];
                k -= 1 << i;
            }
            if (node == 0 && k > 0) return -1;
        }

        return node;
    }
}
