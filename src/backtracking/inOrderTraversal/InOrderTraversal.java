package backtracking.inOrderTraversal;

import java.util.ArrayList;
import java.util.List;

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

public class InOrderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(10);
        System.out.println(inorderTraversal(root).toString());
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traverseInOrder(root, result);
        return result;
    }

    public static void traverseInOrder(TreeNode node, List<Integer> result) {
        if (node == null) return;
        if (node.left != null) traverseInOrder(node.left, result);
        result.add(node.val);
        if (node.right != null) traverseInOrder(node.right, result);
    }
}
