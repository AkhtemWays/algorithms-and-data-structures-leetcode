package backtracking.pathSum;

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
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(4);
        hasPathSum(root, 12);
    }

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && root.val == targetSum) return true;
        List<Integer> sums = new ArrayList<>();
        getSums(root, 0, sums);
        return sums.stream().anyMatch(e -> e == targetSum);
    }

    public static void getSums(TreeNode node, int count, List<Integer> sums) {
        if (node == null) return;
        count += node.val;
        if (node.left == null && node.right == null) {
            sums.add(count);
        } else {
            getSums(node.left, count, sums);
            getSums(node.right, count, sums);
        }
    }
}
