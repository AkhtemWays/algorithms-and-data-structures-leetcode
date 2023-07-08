package trees.heaps.FindKPairswithSmallestSums;

import java.util.*;

public class Main {
    private static void test1() {
        int[] nums1 = {1,7,11};
        int[] nums2 = {2, 4, 6};
        kSmallestPairs(nums1, nums2, 4).forEach(pair -> System.out.println(pair.get(0) + " " + pair.get(1)));
    }
    private static void test2() {
        int[] nums1 = {1, 2};
        int[] nums2 = {3};
        kSmallestPairs(nums1, nums2, 3).forEach(pair -> System.out.println(pair.get(0) + " " + pair.get(1)));
    }
    public static void main(String[] args) {
        test1();
        System.out.println();
        test2();
    }

    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if (nums1.length > nums2.length) return kSmallestPairs(nums2, nums1, k);
        List<List<Integer>> res = new ArrayList<>();
        Comparator<List<Integer>> comp = Comparator.comparingInt(a -> (a.get(2)));
        PriorityQueue<List<Integer>> heap = new PriorityQueue<>(comp);
        for (int i = 0; i < nums1.length; i++) {
            heap.add(List.of(i, 0, nums1[i] + nums2[0]));
        }

        while (k-- > 0 && !heap.isEmpty()) {
            List<Integer> prev = heap.poll();
            res.add(List.of(nums1[prev.get(0)], nums2[prev.get(1)]));
            if (prev.get(1) + 1 < nums2.length) {
                List<Integer> cur = List.of(prev.get(0), prev.get(1) + 1, nums1[prev.get(0)] + nums2[prev.get(1) + 1]);
                heap.add(cur);
            }
        }

        return res;
    }
}
