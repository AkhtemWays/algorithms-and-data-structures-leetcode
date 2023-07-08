package arrays.medianOfTwoSortedArrays;

public class Main {
    public static void main(String[] args) {
        int[] nums1 = {1, 3, 5, 7};
        int[] nums2 = {2, 4, 6};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] A = nums1;
        int[] B = nums2;
        int total = A.length + B.length;
        int half = total / 2;

        if (B.length < A.length) {
            int[] tmp = B;
            B = A;
            A = tmp;
        }

        int l = 0, r = A.length - 1;

        while (true) {
            int i = l + r;
            int j = half - i - 2;

            int Aleft = i >= 0 ? A[i] : Integer.MIN_VALUE;
            int Aright = i + 1 < A.length ? A[i + 1] : Integer.MAX_VALUE;
            int Bleft = j >= 0 ? B[j] : Integer.MIN_VALUE;
            int Bright = j + 1 < B.length ? B[j + 1] : Integer.MAX_VALUE;

            if (Aleft <= Bright && Bleft <= Aright) {
                if (total % 2 == 1) {
                    return Math.min(Aright, Bright);
                }
                return ((double) Math.max(Aleft, Bleft) + (double) Math.min(Aright, Bright)) / 2;
            } else if (Aleft > Bright) {
                r = i - 1;
            } else {
                l = i + 1;
            }
        }
    }
}
