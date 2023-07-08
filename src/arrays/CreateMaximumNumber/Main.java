package arrays.CreateMaximumNumber;

import java.util.*;

public class Main {
    class Node {
        int index;
        int val;
        Node(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }
    public static void main(String[] args) {
        Main main = new Main();
        int[] nums1 = {3,4,6,5};
        int[] nums2 = {9,1,2,5,8,3};
        int k = 5;
        System.out.println(Arrays.toString(main.maxNumber(nums1, nums2, k))); // 9 8 6 5 3
        int[] nums11 = {6,7};
        int[] nums21 = {6,0,4};
        System.out.println(Arrays.toString(main.maxNumber(nums11, nums21, k))); // 6 7 6 0 4
        int[] nums31 = {3,9};
        int[] nums32 = {8,9};
        System.out.println(Arrays.toString(main.maxNumber(nums31, nums32, 3))); // 9 8 9
        System.out.println(Arrays.toString(main.maxNumber(nums32, nums31, 3))); // 9 8 9
    }

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        List<Node> nodes1 = new ArrayList<>();
        List<Node> nodes2 = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            nodes1.add(new Node(i, nums1[i]));
        }
        for (int i = 0; i < nums2.length; i++) {
            nodes2.add(new Node(i, nums2[i]));
        }
        int[] result = new int[k];
        nodes1.sort((a, b) -> b.val - a.val);
        nodes2.sort((a, b) -> b.val - a.val);

        int i = 0, j = 0;
        int numsLeft1 = m, numsLeft2 = n;
        while (k > 0) {
            Node node1 = i < nums1.length ? nodes1.get(i) : new Node(-1, Integer.MIN_VALUE);
            Node node2 = j < nums2.length ? nodes2.get(j) : new Node(-1, Integer.MIN_VALUE);
            if (node1.val >= node2.val && (m - node1.index - 1 + numsLeft2) >= k-1) {
                numsLeft1 = m - node1.index - 1;
                i++;
                result[result.length-k] = node1.val;
            } else if (node2.val >= node1.val && (n - node2.index - 1 + numsLeft1) >= k-1) {
                numsLeft2 = n - node2.index - 1;
                j++;
                result[result.length-k] = node2.val;
            } else {
                i++;
                j++;
            }
            k--;
        }

        return result;
    }
}
