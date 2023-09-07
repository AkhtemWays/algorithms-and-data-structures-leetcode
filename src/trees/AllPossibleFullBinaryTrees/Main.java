package trees.AllPossibleFullBinaryTrees;

import linkedlist.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(allPossibleFBT(7));
    }

    private static List<TreeNode> answer;
    private static TreeNode root;
    public static List<TreeNode> allPossibleFBT(int n) {
        root = new TreeNode(0);
        answer = new ArrayList<>();
        if (n % 2 == 0) return answer;
        dfs(root, n-1);
        return answer;
    }

    private static void dfs(TreeNode node, int deposit) {
        node.left = new TreeNode(0);
        node.right = new TreeNode(0);
        if (deposit - 2 > 0) {
            dfs(node.left, deposit-2);
            node.left.left = null;
            node.left.right = null;
            dfs(node.right, deposit-2);
        } else {
            answer.add(root.clone());
        }

        node.left = null;
        node.right = null;
    }


}
