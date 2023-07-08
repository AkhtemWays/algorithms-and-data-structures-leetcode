package backtracking.preOrderTraversal;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
}

public class Main {

    public static void main(String[] args) {

    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traverse(root, result);
        return result;
    }

    public static void traverse(TreeNode node, List<Integer> result) {
        if (node == null) return;

        result.add(node.val);
        traverse(node.left, result);
        traverse(node.right, result);
    }
}
