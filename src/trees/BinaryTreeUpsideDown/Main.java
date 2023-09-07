package trees.BinaryTreeUpsideDown;

import linkedlist.TreeNode;

public class Main {
    private static void test1() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        TreeNode res = upsideDownBinaryTree(root);
        System.out.println(res);
    }
    public static void main(String[] args) {
        test1();
    }

    public static TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null) return null;
        return dfs(null, root);
    }

    private static TreeNode dfs(TreeNode parent, TreeNode node) {
        if (node == null) return null;
        else if (node.left == null && node.right == null) {
            node.right = parent;
            node.left = parent == null ? null : parent.right;
            return node;
        } else {
            TreeNode returnNode = dfs(node, node.left);
            node.right = parent;
            node.left = parent == null ? null : parent.right;
            return returnNode;
        }
    }
}
