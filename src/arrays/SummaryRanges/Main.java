package arrays.SummaryRanges;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] values = {0,1,2,4,5,7};
        System.out.println(summaryRanges(values));
    }

    public static List<String> summaryRanges(int[] nums) {
        if (nums.length == 0) return List.of();
        List<String> res = new ArrayList<>();
        int start = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - 1 > nums[i-1]) {
                if (start == nums[i-1]) {
                    res.add(String.valueOf(start));
                } else {
                    res.add(start + "->" + nums[i-1]);
                }
                start = nums[i];
            }
        }
        if (start == nums[nums.length-1]) {
            res.add(String.valueOf(start));
        } else {
            res.add(start + "->" + nums[nums.length-1]);
        }
        return res;
    }
}
