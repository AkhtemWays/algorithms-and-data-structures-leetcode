package trees.MaximumProductofSplittedBinaryTree;

import linkedlist.TreeNode;

import java.util.HashMap;

public class Main {
    private static int test1() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        return maxProduct(root);
    }

    private static int test2() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(6);
        return maxProduct(root);
    }

    public static void main(String[] args) {
        System.out.println(test1()); // 110
        System.out.println(test2()); // 90
    }
    private static long answer = Long.MIN_VALUE;
    private static int MOD = 100_000_000_7;

    public static int maxProduct(TreeNode root) {
        answer = Integer.MIN_VALUE;
        if (root == null) return 0;
        HashMap<TreeNode, Long> sums = new HashMap<>();
        findSums(root, sums);
        return (int) (answer % MOD);
    }


    private static long findSums(TreeNode node, HashMap<TreeNode, Long> sums) {
        if (node == null) return 0;
        long sum = 0;

        long leftSum = findSums(node.left, sums);
        long rightSum = findSums(node.right, sums);
        sum += leftSum + rightSum + node.val;
        sums.put(node, sum);
        return sum;
    }
}
