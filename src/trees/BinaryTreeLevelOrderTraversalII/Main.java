package trees.BinaryTreeLevelOrderTraversalII;

import linkedlist.TreeNode;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        levelOrderBottom(root).forEach(System.out::println);
    }

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> curLevel = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = q.poll();
                curLevel.add(node.val);
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            res.add(curLevel);
        }
        Collections.reverse(res);
        return res;
    }
}
