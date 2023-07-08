package trees.CousinsinBinaryTree;

import linkedlist.TreeNode;

public class Main {
    public static void main(String[] args) {

    }

    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null || x == y) return false;

        int[] xMeta = dfs(root, null, 0, x);
        int[] yMeta = dfs(root, null, 0, y);
        if (xMeta == null || yMeta == null) return false;

        return xMeta[0] != yMeta[0] && xMeta[1] != yMeta[1] && xMeta[2] == yMeta[2];
    }

    private static int[] dfs(TreeNode node, TreeNode parent, int level, int target) {
        if (node == null) return null;

        if (node.val == target) {
            return new int[]{node.val, parent == null ? -1 : parent.val, level};
        }
        int[] leftMeta = dfs(node.left, node, level + 1, target);
        int[] rightMeta = dfs(node.right, node, level + 1, target);
        if (leftMeta == null && rightMeta == null) return null;
        return leftMeta == null ? rightMeta : leftMeta;
    }
}
