package trees.MaximumSumBSTinBinaryTree;

import linkedlist.TreeNode;

import java.util.HashMap;

class Solution {
    private static void test1() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(5);
        root.right.right.right = new TreeNode(6);
        root.right.right.left = new TreeNode(4);
        System.out.println(maxSumBST(root));
    }
    private static void test2() {
        TreeNode root = new TreeNode(-4);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(-5);
        System.out.println(maxSumBST(root));
    }

    public static void main(String[] args) {
        test1();
        test2();
    }
    private static int answer;
    static class Meta {
        boolean isBst;
        int sum;
        int min;
        int max;
        Meta(boolean isBst, int sum, int min, int max) {
            this.isBst = isBst;
            this.sum = sum;
            this.min = min;
            this.max = max;
        }
    }
    public static int maxSumBST(TreeNode root) {
        answer = Integer.MIN_VALUE;
        fillMetadata(root);
        return Math.max(answer, 0);
    }

    private static Meta fillMetadata(TreeNode node) {
        Meta leftMeta = null, rightMeta = null;
        if (node.left != null) {
            leftMeta = fillMetadata(node.left);
        }
        if (node.right != null) {
            rightMeta = fillMetadata(node.right);
        }

        Meta meta = getParentMeta(node, leftMeta, rightMeta);
        if (meta.isBst) {
            answer = Math.max(answer, meta.sum);
        }

        return meta;
    }

    private static Meta getParentMeta(TreeNode parent, Meta leftMeta, Meta rightMeta) {
        boolean isBst = true;
        int sum = parent.val;
        int min = parent.val;
        int max = parent.val;

        if (leftMeta != null) {
            sum += leftMeta.sum;
            min = Math.min(min, leftMeta.min);
            max = Math.max(max, leftMeta.max);
            if (leftMeta.max >= parent.val || !leftMeta.isBst) {
                isBst = false;
            }
        }

        if (rightMeta != null) {
            sum += rightMeta.sum;
            min = Math.min(min, rightMeta.min);
            max = Math.max(max, rightMeta.max);
            if (rightMeta.min <= parent.val || !rightMeta.isBst) {
                isBst = false;
            }
        }
        return new Meta(isBst, sum, min, max);
    }
}
