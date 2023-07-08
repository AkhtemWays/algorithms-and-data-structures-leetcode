package hashmap.LongestSubstringwithAtLeastKRepeatingCharacters;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.longestSubstring("abaaaccaaaaaa", 3));
    }

    public int longestSubstring(String s, int k) {
        HashMap<Character, Integer> characterCounts = new HashMap<>();
        for (char ch : s.toCharArray()) {
            characterCounts.put(ch, characterCounts.getOrDefault(ch, 0) + 1);
        }
        int maxNumUniqueChars = characterCounts.size();
        int res = 0;

        for (int numUniqueChars = 1, left = 0; numUniqueChars <= maxNumUniqueChars; numUniqueChars++, left = 0) {
            HashMap<Character, Integer> characterCounter = new HashMap<>();
            for (int right = 0; right < s.length(); right++) {
                characterCounter.put(s.charAt(right), characterCounter.getOrDefault(s.charAt(right), 0) + 1);
                while (characterCounter.size() > numUniqueChars) {
                    characterCounter.put(s.charAt(left), characterCounter.get(s.charAt(left)) - 1);
                    if (characterCounter.get(s.charAt(left)) == 0) {
                        characterCounter.remove(s.charAt(left));
                    }
                    left++;
                }
                if (characterCounter.values().stream().allMatch(val -> val >= k)) {
                    res = Math.max(res, right - left + 1);
                }
            }
        }
        return res;
    }
}
