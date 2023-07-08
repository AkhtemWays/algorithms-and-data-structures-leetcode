package greedy.LargestNumber;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int[] nums = {3,30,34,5,9};
        int[] nums2 = {10, 2};
        int[] nums3 = {0, 0, 0};
        int[] nums5 = {0, 0};
        int[] nums4 = {0};
        int[] nums6 = {1, 0, 0};
        System.out.println(largestNumber(nums));
        System.out.println(largestNumber(nums2));
        System.out.println(largestNumber(nums3));
        System.out.println(largestNumber(nums4));
        System.out.println(largestNumber(nums5));
        System.out.println(largestNumber(nums6));
    }

    public static String largestNumber(int[] nums) {
        List<String> numbers = Arrays.stream(nums).mapToObj(String::valueOf).collect(Collectors.toList());
        numbers.sort(Main::compareStrings);
        String res = String.join("", numbers);
        if (res.charAt(0) == '0' && res.length() > 1 && res.charAt(res.length()-1) == '0' && res.charAt(res.length()-2) == '0') {
            for (int i = res.length() - 1; i >= 1; i--) {
                if (res.charAt(i) == '0' && res.charAt(i-1) != '0') return res.substring(0, i+1);
            }
            return res.substring(0, 1);
        }
        return res;
    }

    private static int compareStrings(String a, String b) {
        String res1 = a.concat(b);
        String res2 = b.concat(a);
        Long res = Long.parseLong(res2) - Long.parseLong(res1);
        if (res > 0) return 1;
        else if (res < 0) return -1;
        return 0;
    }
}
