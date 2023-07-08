package trees.CountofSmallerNumbersAfterSelf.SegmentTreeApproach;


import java.util.ArrayList;
import java.util.List;

class SegmentTree {
    private final int[] tree;
    private final int n;
    SegmentTree(int[] buckets) {
        n = buckets.length;
        tree = new int[n * 4];
        build(buckets, 0, n-1, 1);
    }

    private void build(int[] buckets, int l, int r, int treeIndex) {
        if (l == r) {
            tree[treeIndex] = buckets[l];
        } else {
            int mid = l + (r - l) / 2;
            build(buckets, l, mid, treeIndex * 2);
            build(buckets, mid+1, r, treeIndex * 2 + 1);
            tree[treeIndex] = tree[treeIndex * 2] + tree[treeIndex * 2 + 1];
        }
    }

    public void update(int i, int l, int r, int treeIndex) {
        if (l == r) {
            tree[treeIndex]--;
        } else {
            int mid = l + (r - l) / 2;
            if (i >= l && i <= mid) {
                update(i, l, mid, treeIndex * 2);
            } else {
                update(i, mid+1, r, treeIndex * 2 + 1);
            }
            tree[treeIndex] = tree[treeIndex * 2] + tree[treeIndex * 2 + 1];
        }
    }

    public int query(int left, int right, int l, int r, int treeIndex) {
        if (right < l || r < left) return 0;
        if (left <= l && r <= right) return tree[treeIndex];

        int mid = l + (r - l) / 2;
        int p1 = query(left, right, l, mid, treeIndex * 2);
        int p2 = query(left, right, mid+1, r, treeIndex * 2 + 1);
        return p1 + p2;
    }
}

public class Main {
    private static final int offset = 10001;
    public static void main(String[] args) {
        int[] nums = {5,2,6,1}; // 2,1,1,0
        int[] nums2 = {5,4,5,2,6,5,6,1}; // [3, 2, 2, 1, 2, 1, 1, 0]
        System.out.println(countSmaller(nums));
        System.out.println(countSmaller(nums2));
    }

    public static List<Integer> countSmaller(int[] nums) {
        int n = offset * 2;
        int[] buckets = new int[n];
        for (int num : nums) {
            buckets[num + offset]++;
        }

        SegmentTree st = new SegmentTree(buckets);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int smaller = st.query(0, nums[i] + offset-1, 0, n-1, 1);
            res.add(smaller);
            st.update(nums[i] + offset, 0, n-1, 1);
        }
        return res;
    }
}
