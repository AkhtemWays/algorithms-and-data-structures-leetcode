package hashmap.LongestSubstringwithAtMostKDistinctCharacters;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.lengthOfLongestSubstringKDistinct("eceba", 2)); // 3
        System.out.println(main.lengthOfLongestSubstringKDistinct("aa", 1)); // 2
        System.out.println(main.lengthOfLongestSubstringKDistinct("ab", 1)); // 1
    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int n = s.length();
        if (n * k == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;

        Map<Character, Integer> rightmostPosition = new LinkedHashMap<>();

        int maxLength = 1;

        while (right < n) {
            Character character = s.charAt(right);
            if (rightmostPosition.containsKey(character)) {
                rightmostPosition.remove(character);
            }
            rightmostPosition.put(character, right++);

            if (rightmostPosition.size() == k + 1) {
                Map.Entry<Character, Integer> leftmost =
                        rightmostPosition.entrySet().iterator().next();
                rightmostPosition.remove(leftmost.getKey());
                left = leftmost.getValue() + 1;
            }

            maxLength = Math.max(maxLength, right - left);
        }
        return maxLength;
    }
}
