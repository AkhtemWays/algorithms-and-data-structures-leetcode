package trees.CousinsinBinaryTreeII;

import linkedlist.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static void test1() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(9);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(10);
        root.right.right = new TreeNode(7);
        System.out.println(replaceValueInTree(root));
    }
    private static void test2() {
        TreeNode root = new TreeNode(49);
        root.left = new TreeNode(40);
        root.right = new TreeNode(35);
        root.left.left = new TreeNode(42);
        root.left.right = new TreeNode(7);
        root.left.left.left = new TreeNode(50);
        root.left.right.right = new TreeNode(44);
        root.left.right.right.right = new TreeNode(27);
        root.left.right.right.right.left = new TreeNode(21);
        System.out.println(replaceValueInTree(root));
    }
    public static void main(String[] args) {
        test1();
//        test2();
    }

    public static TreeNode replaceValueInTree(TreeNode root) {
        HashMap<Integer, Integer> store = new HashMap<>();
        getDepth(root, 0, store);
        processStore(root, store);
        root.val = 0;
        return root;
    }

    private static void processStore(TreeNode root, HashMap<Integer, Integer> store) {
        if (root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int level = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            int levelSum = store.getOrDefault(level, 0);
            while (size-- > 0) {
                TreeNode node = q.poll();
                int childrenSum = 0;
                if (node.left != null) childrenSum += node.left.val;
                if (node.right != null) childrenSum += node.right.val;

                if (node.left != null) {
                    node.left.val = levelSum - childrenSum;
                    q.add(node.left);
                }
                if (node.right != null) {
                    node.right.val = levelSum - childrenSum;
                    q.add(node.right);
                }
            }
            level++;
        }
    }

    private static void getDepth(TreeNode root, int level, HashMap<Integer, Integer> store) {
        if (root == null) return;

        store.put(level, store.getOrDefault(level, 0) + root.val);
        getDepth(root.left, level + 1, store);
        getDepth(root.right, level + 1, store);
    }
}
