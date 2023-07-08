package arrays.MajorityElementII;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] nums = {3,2,3};
        int[] nums2 = {1};
        int[] nums3 = {1, 2};
        int[] nums4 = {1,3,3,4};
        int[] nums5 = {2,1,1,3,1,4,5,6};
        int[] nums6 = {4,2,1,1};
        System.out.println(majorityElement(nums)); // [3]
        System.out.println(majorityElement(nums2)); // [1]
        System.out.println(majorityElement(nums3)); // [1, 2]
        System.out.println(majorityElement(nums4)); // [3]
        System.out.println(majorityElement(nums5)); // [1]
        System.out.println(majorityElement(nums6)); // [1]
    }



    public static List<Integer> majorityElement(int[] nums) {
        List<Integer> answer = new ArrayList<>();
        int k = nums.length / 3;
        Integer candidate1 = null, candidate2 = null;
        int c1 = 0, c2 = 0;
        for (int num : nums) {
            if (candidate1 != null && candidate1 == num) {
                c1++;
            }  else if (candidate2 != null && candidate2 == num) {
                c2++;
            }
            else if (candidate1 == null) {
                c1 = 1;
                candidate1 = num;
            } else if (candidate2 == null) {
                candidate2 = num;
                c2 = 1;
            } else {
                c1--;
                c2--;
            }
        }

        c1 = 0;
        c2 = 0;

        for (int num : nums) {
            if (candidate1 != null && candidate1 == num) c1++;
            if (candidate2 != null && candidate2 == num) c2++;
        }

        if (c1 > k) answer.add(candidate1);
        if (c2 > k) answer.add(candidate2);

        return answer;
    }
}
