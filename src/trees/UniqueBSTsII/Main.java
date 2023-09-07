package trees.UniqueBSTsII;

import linkedlist.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println(generateTrees(1));
        System.out.println(generateTrees(2));
        System.out.println(generateTrees(3));
    }

    public static List<TreeNode> generateTrees(int n) {
        List<Integer> root = new ArrayList<>();
        for (int i = 1; i <= n; i++) root.add(i);

        return build(root);
    }

    private static List<TreeNode> build(List<Integer> nodes) {
        if (nodes.size() == 1) return new ArrayList<>(List.of(new TreeNode(nodes.get(0))));

        List<TreeNode> answer = new ArrayList<>();
        for (int rootValue : nodes) {
            TreeNode root = new TreeNode(rootValue);
            List<Integer> leftNodes = nodes.stream().filter(node -> rootValue > node).collect(Collectors.toList());
            List<Integer> rightNodes = nodes.stream().filter(node -> rootValue < node).collect(Collectors.toList());

            List<TreeNode> leftSubTrees = build(leftNodes);
            List<TreeNode> rightSubTrees = build(rightNodes);

            if (leftSubTrees.isEmpty()) {
                for (TreeNode rightSubTree : rightSubTrees) {
                    root.right = rightSubTree;
                    answer.add(root.clone());
                }
            } else if (rightSubTrees.isEmpty()) {
                for (TreeNode leftSubTree : leftSubTrees) {
                    root.left = leftSubTree;
                    answer.add(root.clone());
                }
            } else {
                for (TreeNode leftSubTree : leftSubTrees) {
                    for (TreeNode rightSubTree : rightSubTrees) {
                        root.left = leftSubTree;
                        root.right = rightSubTree;
                        answer.add(root.clone());
                    }
                }
            }
        }

        return answer;
    }
}
