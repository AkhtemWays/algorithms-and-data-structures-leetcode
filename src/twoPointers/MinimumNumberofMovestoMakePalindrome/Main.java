package twoPointers.MinimumNumberofMovestoMakePalindrome;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        System.out.println(minMovesToMakePalindrome("aabb")); // 2
        System.out.println(minMovesToMakePalindrome("letelt")); // 2
        System.out.println(minMovesToMakePalindrome("aletelt")); // 5
        System.out.println(minMovesToMakePalindrome("laetelt")); // 4
    }

    public static int minMovesToMakePalindrome(String s) {
        int left = 0, right = s.length()-1;
        int swaps = 0;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                int k = -1;
                for (int i = right - 1; i >= left; i--) {
                    if (s.charAt(i) == s.charAt(left)) {
                        k = i;
                        break;
                    }
                }
                if (k == left) {
                    String leftPart = left == 0 ? "" : s.substring(0, left);
                    s = leftPart + s.charAt(left+1) + s.charAt(left) + s.substring(left+2);
                    swaps++;
                } else {
                    s = s.substring(0, k) + s.substring(k+1, right+1) + s.charAt(k) + s.substring(right+1);
                    swaps += right - k;
                }
            }
            left++;
            right--;
        }
        return swaps;
    }
}
