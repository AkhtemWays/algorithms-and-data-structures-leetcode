package strings.FindAllAnagramsInAString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Active {
    public static void main(String[] args) {
        Active main = new Active();
        String s = "cbaebabacd";
        String p = "abc";
        main.findAnagrams(s, p).forEach(System.out::println);
    }

    public List<Integer> findAnagrams(String s, String p) {
        ArrayList<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;
        final int[] pAnagram = new int[26];
        for (int i = 0; i < p.length(); i++) pAnagram[p.charAt(i) - 'a']++;

        int startPtr = 0;
        int endPtr = p.length()-1;
        final int[] curAnagram = new int[26];
        for (int i = 0; i < endPtr+1; i++) curAnagram[s.charAt(i) - 'a']++;
        do {
            if (Arrays.compare(curAnagram, pAnagram) == 0) result.add(startPtr);
            curAnagram[s.charAt(startPtr++) - 'a']--;
            curAnagram[s.charAt(endPtr) - 'a']++;
        } while (endPtr < s.length());

        return result;
    }
}
