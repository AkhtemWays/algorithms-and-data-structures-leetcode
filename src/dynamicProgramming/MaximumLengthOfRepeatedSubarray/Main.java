package dynamicProgramming.MaximumLengthOfRepeatedSubarray;

import java.util.*;

//class RemoverList {
//    HashMap<Integer, List<Integer>> elementIndices = new HashMap<>();
//    List<Integer> elements = new ArrayList<>();
//    int size = 0;
//
//    public void add
//}

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int[] nums1 = {1,2,3,2,1};
        int[] nums2 = {3,2,1,4,7};
        System.out.println(main.findLength(nums1, nums2)); // 3
        int[] nums12 = {0, 0, 0, 0, 0};
        int[] nums22 = {0, 0, 0, 0};
        System.out.println(main.findLength(nums12, nums22)); // 4
        int[] nums13 = {1, 0, 0, 0, 0};
        int[] nums23 = {0, 0, 0, 0, 1};
        System.out.println(main.findLength(nums13, nums23)); // 4
        int[] nums14 = {0, 0, 0, 0, 1};
        int[] nums24 = {1, 0, 0, 0, 0};
        System.out.println(main.findLength(nums14, nums24)); // 4
        int[] nums15 = {0,1,1,1,0};
        int[] nums25 = {1,1,1,1,1};
        System.out.println(main.findLength(nums15, nums25)); // 3
    }

    public int findLength(int[] A, int[] B) {
        int ans = 0;
        int[][] memo = new int[A.length + 1][B.length + 1];
        for (int i = A.length - 1; i >= 0; --i) {
            for (int j = B.length - 1; j >= 0; --j) {
                if (A[i] == B[j]) {
                    memo[i][j] = memo[i+1][j+1] + 1;
                    if (ans < memo[i][j]) ans = memo[i][j];
                }
            }
        }
        return ans;
    }
}
