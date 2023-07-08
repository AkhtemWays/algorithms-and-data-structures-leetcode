package backtracking.maximumDepthTree;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
}

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        System.out.println(isBalanced(root));
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return getHeight(root, 0);
    }

    public static int getHeight(TreeNode node, int height) {
        if (node == null) return height;
        return Math.max(getHeight(node.left, height + 1), getHeight(node.right, height + 1));
    }

    public static boolean isBalanced(TreeNode root) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return true;

        return Math.abs(getHeight(root.left, 1) - getHeight(root.right, 1)) <= 1;
    }
}
