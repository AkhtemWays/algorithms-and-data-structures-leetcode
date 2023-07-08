package backtracking.RecoveraTreeFromPreorderTraversal;

import linkedlist.TreeNode;

public class Main {
    public static void main(String[] args) {
        TreeNode root = recoverFromPreorder("1-2--3--4-5--6--7");
        System.out.println(root);
        TreeNode root2 = recoverFromPreorder("1-2--3---4-5--6---7");
        System.out.println(root2);
        TreeNode root3 = recoverFromPreorder("1-401--349---90--88");
        System.out.println(root3);
    }

    public static TreeNode recoverFromPreorder(String preorder) {
        int[] meta = getValue(0, preorder);
        TreeNode root = new TreeNode(meta[1]);
        dfs(meta[0], preorder, 0, root);
        return root;
    }

    private static int dfs(int i, String preorder, int curLevel, TreeNode parent) {
        if (i == preorder.length()) return i;

        int[] metaLevelLeft = getLevel(i, preorder);
        int levelLeft = metaLevelLeft[1];
        int c = metaLevelLeft[0];
        if (levelLeft == curLevel + 1) {
            int[] metaValueLeft = getValue(c, preorder);
            int j = metaValueLeft[0];
            int valueLeft = metaValueLeft[1];
            parent.left = new TreeNode(valueLeft);
            int k = dfs(j, preorder, curLevel+1, parent.left);
            int[] metaLevelRight = getLevel(k, preorder);
            int levelRight = metaLevelRight[1];
            int l = metaLevelRight[0];
            if (levelRight == curLevel + 1) {
                int[] metaValueRight = getValue(l, preorder);
                int valueRight = metaValueRight[1];
                int o = metaValueRight[0];
                parent.right = new TreeNode(valueRight);
                return dfs(o, preorder, curLevel+1, parent.right);
            } else {
                return k;
            }
        } else {
            return i;
        }
    }

    private static int[] getLevel(int i, String preorder) {
        int level = 0;
        while (i < preorder.length() && preorder.charAt(i) == '-') {
            level++;
            i++;
        }
        return new int[]{i, level};
    }

    private static int[] getValue(int i, String preorder) {
        int j = i;
        while (j < preorder.length() && preorder.charAt(j) != '-') {
            j++;
        }
        return new int[]{j, Integer.parseInt(preorder.substring(i, j))};
    }
}
