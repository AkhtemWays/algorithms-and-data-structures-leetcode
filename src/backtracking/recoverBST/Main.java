package backtracking.recoverBST;

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
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.right = new TreeNode(1);
        recoverTree(root);
        System.out.println(inorderTraversal(root).toString());
    }

    public static void recoverTree(TreeNode root) {
        int[] swappedValues = findSwappedValues(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (swappedValues != null) {
            checkNodeValueSatisfaction(root, swappedValues);
        }
    }

    public static int[] findSwappedValues(TreeNode node, int left, int right) {
        if (node == null) return null;
        if (node.val > left && node.val < right) {
            int[] leftSide = findSwappedValues(node.left, left, node.val);
            int[] rightSide = findSwappedValues(node.right, node.val, right);
            if (leftSide != null) return leftSide;
            else return rightSide;
        }
        if (node.val < left) return new int[]{node.val, left};
        else if (node.val > right) return new int[]{node.val, right};
        return null;
    }

    public static void checkNodeValueSatisfaction(TreeNode node, int[] swappedValues) {
        if (node == null) return;
        if (node.val == swappedValues[0]) {
            node.val = swappedValues[1];
        }
        else if (node.val == swappedValues[1]) {
            node.val = swappedValues[0];
        }
        checkNodeValueSatisfaction(node.left, swappedValues);
        checkNodeValueSatisfaction(node.right, swappedValues);
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
