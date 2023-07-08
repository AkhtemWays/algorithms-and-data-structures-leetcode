package trees.PathSumIV;

import linkedlist.TreeNode;

import java.util.Arrays;

public class Main {
    private static int total = 0;
    public static void main(String[] args) {
        int[] nums = {113,215,221};
        int[] nums2 = {113,221};
        System.out.println(pathSum(nums));
        System.out.println(pathSum(nums2));
    }

    public static int pathSum(int[] nums) {
        total = 0;
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs, (a, b) -> a.charAt(0) - b.charAt(0) == 0 ? a.charAt(1) - b.charAt(1) : a.charAt(0) - b.charAt(0));
        int[] tree = new int[nums.length];
        buildBinaryTree(0, 1, 1, strs, tree);
        getPathSum(0, tree, 0);
        return total;
    }

    private static void getPathSum(int i, int[] tree, int sum) {
        sum += tree[i];
        int leftChild = i * 2 + 1;
        int rightChild = leftChild + 1;
        if (leftChild < tree.length) {
            getPathSum(leftChild, tree, sum);
            if (rightChild < tree.length) {
                getPathSum(rightChild, tree, sum);
            }
        } else {
            total += sum;
        }
    }

    private static void buildBinaryTree(int i, int d, int positionsLeft, String[] nodes, int[] tree) {
        if (i == tree.length) return;
        if (positionsLeft == 0) {
            buildBinaryTree(i, d+1, d*2, nodes, tree);
            return;
        }

        tree[i] = Character.getNumericValue(nodes[i].charAt(2));
        buildBinaryTree(i+1, d, positionsLeft-1, nodes, tree);
    }
}
