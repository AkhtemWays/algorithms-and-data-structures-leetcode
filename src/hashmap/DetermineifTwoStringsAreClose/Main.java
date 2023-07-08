package hashmap.DetermineifTwoStringsAreClose;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(closeStrings("abbzzca", "babzzcz"));
    }

    public static boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) return false;
        if (word1.isEmpty()) return true;
        int[] word1Count = new int[26];
        int[] word2Count = new int[26];
        for (int i = 0; i < word1.length(); i++) {
            char ch1 = word1.charAt(i);
            char ch2 = word2.charAt(i);
            word1Count[ch1 - 'a']++;
            word2Count[ch2 - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if ((word1Count[i] == 0 && word2Count[i] != 0) || (word1Count[i] != 0 && word2Count[i] == 0)) return false;
        }
        Arrays.sort(word1Count);
        Arrays.sort(word2Count);
        return Arrays.equals(word1Count, word2Count);
    }
}
