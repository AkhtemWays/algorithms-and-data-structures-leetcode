package strings.RansomNote;

import java.util.HashMap;
import java.util.Map;

public class RansomNote {
    public static void main(String[] args) {

    }

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] magazineLetterCounter = new int[26];
        for (char ch : magazine.toCharArray()) magazineLetterCounter[ch - 'a']++;

        for (char ch : ransomNote.toCharArray()) {
            if (magazineLetterCounter[ch - 'a'] > 0) {
                magazineLetterCounter[ch - 'a']--;
            } else {
                return false;
            }
        }
        return true;
    }
}
