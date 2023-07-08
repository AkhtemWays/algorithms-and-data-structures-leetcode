package backtracking.binaryTreeToLinkedList;

import java.util.Stack;

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
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);
        flattenV2(root);
        System.out.println(root.val);
        TreeNode right = root.right;
        while (right != null) {
            System.out.println(right.val);
            right = right.right;
        }
    }

    public static void flatten(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current_node = stack.pop();

            if (current_node.right != null) {
                stack.push(current_node.right);
            }

            if (current_node.left != null) {
                stack.push(current_node.left);
            }

            if (!stack.isEmpty()) {
                current_node.right = stack.peek();
            }

            current_node.left = null;
        }
    }

    public static void flattenV2(TreeNode root) {
        dfs(root);
    }

    public static TreeNode dfs(TreeNode node) {
        if (node == null) return null;

        TreeNode leftTail = dfs(node.left);
        TreeNode rightTail = dfs(node.right);

        if (leftTail != null) {
            leftTail.right = node.right;
            node.right = node.left;
            leftTail.left = null;
        }

        return rightTail != null ? rightTail : leftTail != null ? leftTail : node;

    }

}
