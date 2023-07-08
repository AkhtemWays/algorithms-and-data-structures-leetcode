package arrays.mergeArrays;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] nums1 = {0,0,3,0,0,0,0,0,0};
        int[] nums2 = {-1,1,1,1,2,3};
        merge(nums1, 3, nums2, 6);
        System.out.println(Arrays.toString(nums1));
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) return;
        int j = 0;
        int k = 0;
        for (int i = 0; i < nums1.length; i++) {
            if (j == nums2.length) {
                if (nums1[i] > nums1[m + k - 1]) {
                    int candidate = nums1[i];
                    nums1[i] = nums1[m + k - 1];
                    nums1[m + k - 1] = candidate;
                }
                continue;
            }
            if (nums1[i] > nums2[j]) {
                int candidate = nums1[i];
                nums1[i] = nums2[j];
                nums1[m + k] = candidate;
                j++;
                k++;
            } else if (nums1[i] == 0 && i >= m) {
                nums1[i] = nums2[j];
                j++;
                k++;
            }
        }
    }
}
