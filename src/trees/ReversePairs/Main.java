package trees.ReversePairs;

import java.util.*;
import java.util.stream.Collectors;

class SegmentTree {
    Random random = new Random();
    private final int[] tree;
    private final int n;
    SegmentTree(int n) {
        this.n = n;
        this.tree = new int[4 * n];
    }

    public int sum(int left, int right, int l, int r, int treeIndex) {
        if (l > right || left > r) return 0;
        if (l >= left && r <= right) return tree[treeIndex];

        int mid = l + (r - l) / 2;
        return sum(left, right, l, mid, treeIndex * 2) + sum(left, right, mid+1, right, treeIndex * 2 + 1);
    }

    public void update(int l, int r, int treeIndex, int i) {
        if (l == r) {
            tree[treeIndex]++;
        } else {
            int mid = l + (r - l) / 2;
            if (l <= i && i <= mid) {
                update(l, mid, treeIndex * 2, i);
            } else {
                update(mid+1, r, treeIndex * 2 + 1, i);
            }
            tree[treeIndex] = tree[treeIndex * 2] + tree[treeIndex * 2 + 1];
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int[] nums = {1,3,2,3,1};
        System.out.println(reversePairs(nums));
    }

    public static int reversePairs(int[] nums) {
        HashMap<Integer, Integer> valueIndex = new HashMap<>();
        TreeMap<Integer, Integer> counts = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            counts.put(nums[i], counts.getOrDefault(nums[i], 0) + 1);
        }

        List<Integer> uniqueValues = new ArrayList<>(counts.keySet()).stream().sorted().collect(Collectors.toList());

        for (int i = 0; i < uniqueValues.size(); i++) {
            valueIndex.put(uniqueValues.get(i), i);
        }

        int n = counts.size();
        SegmentTree st = new SegmentTree(n);

        int count = 0;
        for (int i = nums.length-1; i >= 0; i--) {
            int right = (nums[i] & 1) == 0 ? (nums[i] / 2) - 1 : nums[i] / 2;
            Integer searchPoint = counts.floorKey(right);
            if (searchPoint != null) {
                count += st.sum(0, valueIndex.get(searchPoint), 0, n-1, 1);
            }
            st.update(0, n-1, 1, valueIndex.get(nums[i]));
        }
        return count;
    }
}
