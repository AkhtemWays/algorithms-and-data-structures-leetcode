package trees.FindScoreofanArrayAfterAllElements;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        int[] nums = {2,1,3,4,5,2};
        int[] nums2 = {2,3,5,1,3,2};
        int[] nums3 = {5,1,1,7,2,4};
        System.out.println(findScore(nums));
        System.out.println(findScore(nums2));
        System.out.println(findScore(nums3));
    }

    public static long findScore(int[] nums) {
        TreeSet<Integer>[] counts = new TreeSet[1000000];
        for (int i = 0; i < nums.length; i++) {
            if (counts[nums[i]] == null) {
                counts[nums[i]] = new TreeSet<>((a, b) -> a - b);
            }
            counts[nums[i]].add(i);
        }

        long score = 0;
        for (int min = 0; min < counts.length; min++) {
            if (counts[min] != null && counts[min].size() > 0) {
                int index = counts[min].first();
                score += min;
                counts[min].remove(index);
                if (index - 1 >= 0) {
                    counts[nums[index-1]].remove(index-1);
                }
                if (index + 1 < nums.length) {
                    counts[nums[index+1]].remove(index+1);
                }
                min--;
            }
        }
        return score;
    }
}
