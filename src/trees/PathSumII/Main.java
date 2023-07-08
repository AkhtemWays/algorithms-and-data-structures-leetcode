package trees.PathSumII;

import linkedlist.TreeNode;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right = new TreeNode(4);
        root.right.left = new TreeNode(13);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);
        System.out.println(pathSum(root, 22));
    }

    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, 0, targetSum, res, new ArrayList<>());
        return res;
    }

    private static void dfs(TreeNode node, int sum, int targetSum, List<List<Integer>> res, List<Integer> cur) {
        if (node == null) return;
        sum += node.val;
        cur.add(node.val);
        if (node.left == null && node.right == null) {
            if (sum == targetSum) {
                res.add(List.copyOf(cur));
            }
        } else {
            dfs(node.left, sum, targetSum, res, cur);
            dfs(node.right, sum, targetSum, res, cur);
        }
        cur.remove(cur.size()-1);
    }
}
