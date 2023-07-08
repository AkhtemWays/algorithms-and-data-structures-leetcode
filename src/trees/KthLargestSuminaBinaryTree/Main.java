package trees.KthLargestSuminaBinaryTree;

import linkedlist.TreeNode;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }

    public long kthLargestLevelSum(TreeNode root, int k) {
        List<Integer> levelSums = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            int levelSum = 0;
            while (size-- > 0) {
                TreeNode node = q.poll();
                levelSum += node.val;
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            levelSums.add(levelSum);
        }
        int n = levelSums.size();
        if (k > n) return -1;
        Collections.sort(levelSums);
        return levelSums.get(n - k);
    }
}
