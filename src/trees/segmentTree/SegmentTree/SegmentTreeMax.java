package trees.segmentTree.SegmentTree;

import java.util.*;

public class SegmentTreeMax {
    int[] tree;
    int n;

    public SegmentTreeMax(int[] nums) {
        n = nums.length;
        tree = new int[n * 2];
        buildTree(nums);
    }

    private void buildTree(int[] nums) {
        for (int i = n, j = 0;  i < 2 * n; i++,  j++)
            tree[i] = nums[j];
        for (int i = n - 1; i > 0; --i)
            tree[i] = tree[leftChild(i)] + tree[rightChild(i)];
    }

    private int leftChild(int parentIndex) {
        return 2*parentIndex;
    }

    private int rightChild(int parentIndex) {
        return 2*parentIndex+1;
    }


    void update(int pos, int val) {
        pos += n;
        tree[pos] = val;
        while (pos > 0) {
            int leftChild = pos;
            int rightChild = pos;
            if (pos % 2 == 0) {
                rightChild = pos + 1;
            } else {
                leftChild = pos - 1;
            }

            int newSum = tree[leftChild] + tree[rightChild];
            pos /= 2; // set parent
            tree[pos] = newSum;
        }
    }

    public int query(int l, int r) {
        l += n;
        r += n;
        int sum = 0;
        while (l <= r) {
            if (l % 2 == 1) {
                sum += tree[l];
                l++;
            }
            if (r % 2 == 0) {
                sum += tree[r];
                r--;
            }
            l /= 2;
            r /= 2;
        }
        return sum;
    }

    public static void main(String[] args) {
//		int[] ar = new int[]{6, 10, 5, 2, 7, 1, 0, 9};
//		int[] ar = new int[]{12, 7, 1, 0, 9};
        int[] ar = {6,10,5,2,7,1,0,4};
        SegmentTreeMax tree = new SegmentTreeMax(ar);
        System.out.println(Arrays.toString(tree.tree));
        for (int i = 0; i < ar.length; i++) {
            System.out.println(tree.query(0, i));
        }
    }
}
