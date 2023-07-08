package arrays.ContainsDuplicateIII;

import java.util.*;

class Node {
    int val;
    List<Integer> indices;
    public Node(int val) {
        this.val = val;
        indices = new ArrayList<>();
    }
}

public class Main {
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        int[] nums2 = {1, 5, 9, 1, 5, 9};
        int[] nums3 = {1,3,6,2};
        int[] nums4 = {10,100,11,9};
//        System.out.println(containsNearbyAlmostDuplicate(nums, 3, 0));
        System.out.println(containsNearbyAlmostDuplicate(nums2, 2, 3));
        System.out.println(containsNearbyAlmostDuplicate(nums3, 1, 2));
        System.out.println(containsNearbyAlmostDuplicate(nums4, 1, 2));
    }

    private static long getID(long x, long w) {
        return x < 0 ? (x + 1) / w - 1 : x / w;
    }

    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0) return false;
        Map<Long, Long> d = new HashMap<>();
        long w = (long)t + 1;
        for (int i = 0; i < nums.length; ++i) {
            long m = getID(nums[i], w);
            // check if bucket m is empty, each bucket may contain at most one element
            if (d.containsKey(m))
                return true;
            // check the neighbor buckets for almost duplicate
            if (d.containsKey(m - 1) && Math.abs(nums[i] - d.get(m - 1)) < w)
                return true;
            if (d.containsKey(m + 1) && Math.abs(nums[i] - d.get(m + 1)) < w)
                return true;
            // now bucket m is empty and no almost duplicate in neighbor buckets
            d.put(m, (long)nums[i]);
            if (i >= k) d.remove(getID(nums[i - k], w));
        }
        return false;
    }
}
