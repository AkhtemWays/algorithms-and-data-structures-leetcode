package disjointSets_UnionFind.longestConsecutive;

import java.util.*;

class Node {
    Node parent;
    int val;
    Node(int val) {
        this.val = val;
    }
    Node(int val, Node parent) {
        this.val = val;
        this.parent = parent;
    }
}

class UnionFind {
    Node root;
    public boolean find(Node x) {
        Node cur = x;
        while (cur != null) {
            if (cur == root) {
                x.parent = root;
                return true;
            }
            cur = cur.parent;
        }
        return false;
    }

    public void union(Node x, Node y) {
        if (x == null) return;
        while (x.parent != null) {
            x = x.parent;
        }
        x.parent = y;
    }
    UnionFind(Node root) {
        this.root = root;
    }
}

public class Main {
    public static void main(String[] args) {
        int[] nums = {100,4,200,1,3,2};
        System.out.println(longestConsecutive(nums));
    }

    public static int longestConsecutive(int[] nums) {
        List<UnionFind> nodes = new ArrayList<>();
        Set<Integer> numSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            numSet.add(nums[i]);
            nodes.add(new UnionFind(new Node(nums[i], null)));
        }

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (!numSet.contains(num-1)) {
                Node root = nodes.get(i).root;
                int currentNumber = num + 1;
                while (numSet.contains(currentNumber)) {
                    root.parent = new Node(currentNumber);
                    root = root.parent;
                    currentNumber++;
                }
            }
        }
        int maxSize = 0;
        for (UnionFind unionFind : nodes) {
            int curSize = 1;
            Node root = unionFind.root;
            while (root.parent != null) {
                curSize++;
                root = root.parent;
            }
            if (maxSize < curSize) maxSize = curSize;
        }
        return maxSize;
    }
}
