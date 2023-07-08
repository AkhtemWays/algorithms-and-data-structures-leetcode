package trees.ClosestSearchTreeValueII;

import linkedlist.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(1);
        System.out.println(closestKValues(root,2D, 3));
    }

    public static List<Integer> closestKValues(TreeNode root, double target, int k) {
        TreeMap<Double, Integer> bst = new TreeMap<>();
        dfs(root, target, k, bst);
        return new ArrayList<>(bst.values());
    }

    private static void dfs(TreeNode node, double target, int k, TreeMap<Double, Integer> bst) {
        if (node == null) return;
        double curDiff = Math.abs(target - node.val);
        if (bst.size() < k) {
            bst.put(curDiff, node.val);
        } else {
            double biggestDiff = bst.lastEntry().getKey();
            if (biggestDiff > curDiff) {
                bst.remove(biggestDiff);
                bst.put(curDiff, node.val);
            }
        }

        dfs(node.left, target, k, bst);
        dfs(node.right, target, k, bst);
    }
}
