package trees.MaximumLevelSumofaBinaryTree;

import linkedlist.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {

    }

    public static int maxLevelSum(TreeNode root) {
        if (root == null) return -1;
        List<Integer> levelSums = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            int levelSum = 0;
            while (size-- > 0) {
                TreeNode node = q.poll();
                levelSum += node.val;
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            levelSums.add(levelSum);
        }
        int index = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < levelSums.size(); i++) {
            if (levelSums.get(i) > max) {
                max = levelSums.get(i);
                index = i;
            }
        }
        return index+1;
    }
}
