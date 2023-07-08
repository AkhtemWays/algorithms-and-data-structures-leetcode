package trees.ClosestPositionofEachBit;

import java.util.Arrays;

import static java.lang.Math.max;
import static java.lang.Math.min;

class SegmentTree {
    int[] tree;
    SegmentTree(int[] nums) {
        tree = new int[nums.length * 4];
        this.build(nums, 0, nums.length-1, 1);
    }
    int query(int l, int r, int tl, int tr, int treeIndex) {
        if (l > r) return 0;

        if (tl == l && r == tr) return tree[treeIndex];

        int mid = tl + (tr - tl) / 2;
        return query(l, Math.min(r, mid), tl, mid, treeIndex * 2) | query(Math.max(l, mid+1), r, mid+1, tr, treeIndex * 2 + 1);
    }
    int build(int[] nums, int l, int r, int p) {
        if (l == r) {
            tree[p] = nums[l];
            return nums[l];
        } else {
            int mid = l + (r - l) / 2;
            return tree[p] = build(nums, l, mid,  2 * p) | build(nums, mid+1, r, 2 * p + 1);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int[] nums = {1,0,2,1,3};
        System.out.println(Arrays.toString(smallestSubarrays(nums)));
    }

    static int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        SegmentTree st = new SegmentTree(nums);
        for (int i = 0; i < nums.length; i++) {
            int j = i;
            while (st.query(i, j, 0, n-1, 1) < st.query(i, n-1, 0, n-1, 1)) {
                j++;
            }
            res[i] = j - i + 1;
        }

        return res;
    }
}
