package trees.recoverTree;

import linkedlist.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        recoverTree(root);
        inorderPrint(root);
    }

    public static void recoverTree(TreeNode root) {
        List<TreeNode> values = new ArrayList<>();
        inorder(root, values);
        int first = -1;
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).val > values.get(i+1).val) {
                first = i;
                break;
            }
        }
        for (int second = first+1; second < values.size(); second++) {
            Collections.swap(values, first, second);
            if (check(values, first) && check(values, second) && values.get(first).val < values.get(second).val) {
                swap(values, first, second);
                return;
            }
            Collections.swap(values, first, second);
        }
    }

    private static boolean check(List<TreeNode> nodes, int i) {
        TreeNode node = nodes.get(i);
        boolean firstTest = i-1 < 0 || node.val > nodes.get(i-1).val;
        boolean secondTest = i+1 >= nodes.size() || node.val < nodes.get(i+1).val;
        return firstTest && secondTest;
    }

    private static void swap(List<TreeNode> nodes, int i, int j) {
        TreeNode node1 = nodes.get(i);
        TreeNode node2 = nodes.get(j);
        int node1Val = node1.val;
        node1.val = node2.val;
        node2.val = node1Val;
    }

    private static void inorder(TreeNode node, List<TreeNode> values) {
        if (node == null) return;

        inorder(node.left, values);
        values.add(node);
        inorder(node.right, values);
    }

    private static void inorderPrint(TreeNode node) {
        if (node == null) return;

        inorderPrint(node.left);
        System.out.println(node.val);
        inorderPrint(node.right);
    }
}
