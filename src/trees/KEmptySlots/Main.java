package trees.KEmptySlots;

import java.util.TreeSet;

class SegmentTree {
    int[] tree;

    SegmentTree(int n) {
        this.tree = new int[4 * n];
    }

    public int query(int left, int right, int l, int r, int treeIndex) {
        if (left > r || right < l) return 0;
        if (l >= left && r <= right) return tree[treeIndex];

        int mid = l + (r - l) / 2;
        return query(left, right, l, mid, treeIndex * 2) + query(left, right, mid+1, r, treeIndex * 2 + 1);
    }

    public void update(int treeIndex, int index, int l, int r) {
        if (l == r) {
            tree[treeIndex] += 1;
        } else {
            int mid = l + (r - l) / 2;
            if (index >= l && index <= mid) {
                update(treeIndex * 2, index, l, mid);
            } else {
                update(treeIndex * 2 + 1, index, mid+1, r);
            }
            tree[treeIndex] = tree[treeIndex * 2] + tree[treeIndex * 2 + 1];
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int[] bulbs = {1, 3, 2};
        int[] bulbs2 = {1,2,3};
        int[] bulbs3 = {3,9,2,8,1,6,10,5,4,7}; // [1, 1, 1, 0, 0, 1, 0, 1, 1, 0]
        System.out.println(kEmptySlots(bulbs, 1));
        System.out.println(kEmptySlots(bulbs2, 1));
        System.out.println(kEmptySlots(bulbs3, 1));
    }

    public static int kEmptySlots(int[] bulbs, int k) {
        int n = bulbs.length;
        TreeSet<Integer> bst = new TreeSet<>();
        bst.add(bulbs[0]);
        for (int i = 1; i < n; i++) {
            int idx = bulbs[i];
            Integer leftBorder = bst.lower(idx);
            Integer rightBorder = bst.higher(idx);
            if (leftBorder != null && idx - leftBorder - 1 == k) return i+1;
            if (rightBorder != null && rightBorder - idx - 1 == k) return i+1;
            bst.add(idx);
        }
        return -1;
    }
}
