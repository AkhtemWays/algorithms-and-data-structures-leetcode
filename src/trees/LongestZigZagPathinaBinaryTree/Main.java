package trees.LongestZigZagPathinaBinaryTree;

import linkedlist.TreeNode;

public class Main {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(1);
        root.right.right = new TreeNode(1);
        root.right.left = new TreeNode(1);
        root.right.right.right = new TreeNode(1);
        root.right.right.left = new TreeNode(1);
        root.right.right.left.right = new TreeNode(1);
        root.right.right.left.right.right = new TreeNode(1);
        System.out.println(longestZigZag(root));
    }

    public static int longestZigZag(TreeNode root) {
        if (root == null) return 0;
        return Math.max(dfs(root.left, false), dfs(root.right, true));
    }

    private static int dfs(TreeNode root, boolean isLeftDirection) {
        if (root == null) return 0;
        if (isLeftDirection) {
            return Math.max(dfs(root.left, false) + 1, dfs(root.right, true));
        }
        return Math.max(dfs(root.right, true) + 1, dfs(root.left, false));
    }
}
