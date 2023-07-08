package backtracking.validateBST;

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

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(10);
        System.out.println(isValidBST(root));
    }

    public static boolean isValidBST(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        arrayFromBST(root, result);
        for (int i = 0; i < result.size(); i++) {
            if (i + 1 < result.size() && result.get(i + 1) <= result.get(i)) {
                return false;
            }
        }
        return true;
    }

    public static void arrayFromBST(TreeNode node, List<Integer> result) {
        if (node == null) return;
        if (node.left != null) arrayFromBST(node.left, result);
        result.add(node.val);
        if (node.right != null) arrayFromBST(node.right, result);
    }
}
