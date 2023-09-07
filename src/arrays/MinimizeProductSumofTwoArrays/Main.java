package arrays.MinimizeProductSumofTwoArrays;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

    }

    public int minProductSum(int[] nums1, int[] nums2) {
        int n = nums2.length;

        Arrays.sort(nums2);
        int[] nums2Descending = new int[n];
        for (int i = 0; i < n; i++) {
            nums2Descending[i] = nums2[n-1-i];
        }
        Arrays.sort(nums1);

        int productSum = 0;
        for (int i = 0; i < n; i++) {
            productSum += nums2Descending[i] * nums1[i];
        }
        return productSum;
    }
}
