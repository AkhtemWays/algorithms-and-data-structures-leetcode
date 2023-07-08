package strings.FindKLengthSubstringsWithNoRepeatedCharacters;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println(numKLenSubstrNoRepeats("havefunonleetcode", 5));
    }
    public static int numKLenSubstrNoRepeats(String s, int k) {
        int count = 0;
        if (k > s.length()) return count;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < k; i++) set.add(s.charAt(i));
        for (int left = 0, right = k; right < s.length(); right++, left++) {
            if (set.size() == k) count++;
            set.remove(s.charAt(left));
            set.add(s.charAt(right));
        }
        return count;
    }
}
