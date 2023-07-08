package trees.BinaryTreeZigzagLevelOrderTraversal;

import linkedlist.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        zigzagLevelOrder(root).forEach(System.out::println);
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> d = new LinkedList<>();
        d.addFirst(root);
        boolean leftToRight = true;
        while (!d.isEmpty()) {
            int size = d.size();
            List<Integer> curLevel = new ArrayList<>();
            if (leftToRight) {
                while (size-- > 0) {
                    TreeNode node = d.pollFirst();
                    curLevel.add(node.val);
                    if (node.left != null) d.addLast(node.left);
                    if (node.right != null) d.addLast(node.right);
                }
            } else {
                while (size-- > 0) {
                    TreeNode node = d.pollLast();
                    curLevel.add(node.val);
                    if (node.right != null) d.addFirst(node.right);
                    if (node.left != null) d.addFirst(node.left);
                }
            }
            leftToRight = !leftToRight;
            res.add(curLevel);
        }
        return res;
    }
}
