package arrays.NextGreaterElementI;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};
        System.out.println(Arrays.toString(main.nextGreaterElement(nums1, nums2)));
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> nextBiggers = new HashMap<>();
        int[] res = new int[nums1.length];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < nums2.length; i++) {
            if (st.isEmpty() || st.peek() > nums2[i]) {
                st.add(nums2[i]);
                continue;
            }
            while (!st.isEmpty() && st.peek() < nums2[i]) {
                nextBiggers.put(st.pop(), nums2[i]);
            }
            st.add(nums2[i]);
        }
        while (!st.isEmpty()) {
            nextBiggers.put(st.pop(), -1);
        }
        for (int i = 0; i < nums1.length; i++) {
            res[i] = nextBiggers.get(nums1[i]);
        }

        return res;
    }
}
