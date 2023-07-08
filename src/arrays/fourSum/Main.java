package arrays.fourSum;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int[] nums2 = {1,0,-1,0,-2,-1,1,2};
        int[] nums3 = {2,2,2,2,2};
        int[] nums4 = {1000000000,1000000000,1000000000,1000000000};
        List<List<Integer>> result = fourSum(nums2, 0);
        List<List<Integer>> result2 = fourSum(nums3, 8);
        List<List<Integer>> result3 = fourSum(nums4, -294967296);
        result.forEach(combination -> System.out.println(combination.toString()));
        result2.forEach(combination -> System.out.println(combination.toString()));
        result3.forEach(combination -> System.out.println(combination.toString()));
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        Set<List<Integer>> visited = new HashSet<>();
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length-3; i++) {
            for (int j = i + 1; j < nums.length-2; j++) {
                long sum = nums[i] + nums[j];
                int k = j + 1, m = nums.length-1;
                sum += nums[k];
                sum += nums[m];
                while (m > k) {
                    if (sum == target) {
                        List<Integer> cur = List.of(nums[i], nums[j], nums[k], nums[m]);
                        if (!visited.contains(cur)) {
                            visited.add(cur);
                            result.add(cur);
                        }
                        while (k+1 < m && nums[k] == nums[k+1]) {
                            k++;
                        }
                        sum -= nums[k++];
                        sum += nums[k];
                    } else if (sum < target) {
                        while (k+1 < m && nums[k] == nums[k+1]) {
                            k++;
                        }
                        sum -= nums[k++];
                        sum += nums[k];
                    } else {
                        while (m-1 > k && nums[m-1] == nums[m]) {
                            m--;
                        }
                        sum -= nums[m--];
                        sum += nums[m];
                    }
                }
            }
        }
        return result;
    }

//    private static boolean lastIsEqual(Set<List<Integer>> res, List<Integer> cur) {
//        if (res.isEmpty()) return false;
//        List<Integer> last = res.get(res.size()-1);
//        return last.equals(cur);
//    }
}
