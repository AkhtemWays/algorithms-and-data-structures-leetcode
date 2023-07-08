package trees.recoverTree;

import linkedlist.TreeNode;

public class OptimizedVersion {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        recoverTree(root);
        inorderPrint(root);
    }

    public static void recoverTree(TreeNode root) {
        int[] swappedNodes = getSwappedNodes(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (swappedNodes == null) return;
        swapNodeValues(root, swappedNodes);
    }

    private static int[] getSwappedNodes(TreeNode node, int leftBoundary, int rightBoundary) {
        if (node.val > leftBoundary && node.val <= rightBoundary) {
            int[] leftPath = null;
            int[] rightPath = null;
            if (node.left != null) {
                leftPath = getSwappedNodes(node.left, leftBoundary, node.val);
            }
            if (node.right != null) {
                rightPath = getSwappedNodes(node.right, node.val, rightBoundary);
            }
            return leftPath == null ? rightPath : leftPath;
        } else {
            if (node.val < leftBoundary) {
                return new int[]{node.val, leftBoundary};
            } else {
                return new int[]{node.val, rightBoundary};
            }
        }
    }

    private static void swapNodeValues(TreeNode node, int[] swappedNodes) {
        if (node == null) return;

        if (node.val == swappedNodes[0]) {
            node.val = swappedNodes[1];
        }
        else if (node.val == swappedNodes[1]) {
            node.val = swappedNodes[0];
        }
        swapNodeValues(node.left, swappedNodes);
        swapNodeValues(node.right, swappedNodes);
    }

    private static void inorderPrint(TreeNode node) {
        if (node == null) return;

        inorderPrint(node.left);
        System.out.println(node.val);
        inorderPrint(node.right);
    }
}
