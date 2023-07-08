package trees.MergeBSTstoCreateSingleBST;

import linkedlist.TreeNode;

import java.util.*;

public class Main {
    private static void test1() {
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(5);
        TreeNode root3 = new TreeNode(5);
        root3.left = new TreeNode(4);
        List<TreeNode> trees = new ArrayList<>(List.of(root1, root2, root3));
        TreeNode root = canMerge(trees);
        System.out.println(root);
    }
    private static void test2() {
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(8);
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(6);
        List<TreeNode> trees = new ArrayList<>(List.of(root1, root2));
        TreeNode root = canMerge(trees);
        System.out.println(root);
    }
    private static void test3() {
        TreeNode root1 = new TreeNode(1);
        root1.right = new TreeNode(3);
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(1);
        TreeNode root3 = new TreeNode(4);
        root3.left = new TreeNode(2);
        List<TreeNode> trees = new ArrayList<>(List.of(root1, root2, root3));
        TreeNode root = canMerge(trees);
        System.out.println(root);
    }
    private static void test4() {
        TreeNode root1 = new TreeNode(2);
        root1.right = new TreeNode(3);
        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(3);
        TreeNode root3 = new TreeNode(3);
        root3.left = new TreeNode(2);
        List<TreeNode> trees = new ArrayList<>(List.of(root1, root2, root3));
        TreeNode root = canMerge(trees);
        System.out.println(root);
    }
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }

    public static TreeNode canMerge(List<TreeNode> trees) {
        Set<Integer> roots = new HashSet<>();
        HashMap<Integer, TreeNode> nodeMap = new HashMap<>();
        for (int i = 0; i < trees.size(); i++) {
            TreeNode root = trees.get(i);
            roots.add(root.val);
            nodeMap.put(root.val, root);
        }

        for (TreeNode root : trees) {
            if (root.left != null) roots.remove(root.left.val);
            if (root.right != null) roots.remove(root.right.val);
        }

        if (roots.size() != 1) return null;
        int rootValue = roots.stream().findAny().get();
        TreeNode root = nodeMap.get(rootValue);
        Set<Integer> visited = new HashSet<>();
        visited.add(root.val);
        build(root, nodeMap, visited);
        return isValidBST(root) && visited.size() == trees.size() ? root : null;
    }

    private static void build(TreeNode node, HashMap<Integer, TreeNode> nodeMap, Set<Integer> visited) {
        if (node.left != null && nodeMap.containsKey(node.left.val) && !visited.contains(node.left.val)) {
            visited.add(node.left.val);
            node.left = nodeMap.get(node.left.val);
            build(node.left, nodeMap, visited);
        }
        if (node.right != null && nodeMap.containsKey(node.right.val) && !visited.contains(node.right.val)) {
            visited.add(node.right.val);
            node.right = nodeMap.get(node.right.val);
            build(node.right, nodeMap, visited);
        }
    }

    public static boolean isValidBST(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        arrayFromBST(root, result);
        for (int i = 0; i < result.size(); i++) {
            if (i + 1 < result.size() && result.get(i + 1) <= result.get(i)) {
                return false;
            }
        }
        return true;
    }

    public static void arrayFromBST(TreeNode node, List<Integer> result) {
        if (node == null) return;
        if (node.left != null) arrayFromBST(node.left, result);
        result.add(node.val);
        if (node.right != null) arrayFromBST(node.right, result);
    }
}
