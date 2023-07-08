package trees.simpleBinaryTree.BinaryTreeMaximumPathSum;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Main {
    private static int[] max = {0};
    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.right = new TreeNode(7);
        root.right.left = new TreeNode(15);
        TreeNode root2 = new TreeNode(-3);
        TreeNode root3 = new TreeNode(-2);
        root3.left = new TreeNode(-1);
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(-2);
        root4.right = new TreeNode(3);
        TreeNode root5 = new TreeNode(-1);
        root5.left = new TreeNode(-2);
        root5.right = new TreeNode(10);
        root5.left.left = new TreeNode(-6);
        root5.right.right = new TreeNode(-6);
        root5.right.left = new TreeNode(-3);
//        System.out.println(maxPathSum(root));
//        System.out.println(maxPathSum(root2));
//        System.out.println(maxPathSum(root3));
//        System.out.println(maxPathSum(root4));
        System.out.println(maxPathSum(root5));
    }

    public static int maxPathSum(TreeNode root) {
        if (root == null) return max[0];
        dfs(root);
        return max[0];
    }

    private static int dfs(TreeNode node) {
        if (node == null) return 0;

        int leftMax = dfs(node.left);
        int rightMax = dfs(node.right);
        leftMax = Math.max(0, leftMax);
        rightMax = Math.max(0, rightMax);
        max[0] = Math.max(max[0], max[0] + leftMax + rightMax);
        return max[0] + Math.max(leftMax, rightMax);
    }
}
