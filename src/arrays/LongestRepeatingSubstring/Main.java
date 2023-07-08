package arrays.LongestRepeatingSubstring;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(longestRepeatingSubstring("abcd"));
        System.out.println(longestRepeatingSubstring("abbaba")); // 2
        System.out.println(longestRepeatingSubstring("aabcaabdaab")); // 3
    }

    public static int longestRepeatingSubstring(String s) {
        if (s.length() == 0) return 0;
        int n = s.length(), base = 26, p = 5381;
        int lrs = 0;
        HashMap<Double, List<Point>> hashToString = new HashMap<>();

        double h = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int charValue = ch - 'a' + 1;
            h = h + ((charValue * Math.pow(base, i)) % p);
            Point point = new Point(0, i);
            if (hashToString.containsKey(h)) {
                hashToString.get(h).add(point);
            } else {
                hashToString.put(h, new ArrayList<>(List.of(point)));
            }

            double curHash = h;
            int j = 0;
            while (j < i) {
                char lastChar = s.charAt(j);
                int lastCharValue = lastChar - 'a' + 1;
                curHash = curHash - ((lastCharValue * Math.pow(base, i-j)) % p);
                j++;
                Point curPoint = new Point(j, i);
                if (hashToString.containsKey(curHash)) {
                    List<Point> prevIndices = hashToString.get(curHash);
                    for (Point prevPoint : prevIndices) {
                        if (stringsAreEqual(prevPoint.x, prevPoint.y, curPoint.x, curPoint.y, s)) {
                            lrs = Math.max(lrs, prevPoint.y - prevPoint.x);
                            break;
                        }
                    }
                    prevIndices.add(curPoint);
                } else {
                    hashToString.put(curHash, new ArrayList<>(List.of(curPoint)));
                }
            }
        }
        return lrs;
    }

    private static boolean stringsAreEqual(int start1, int end1, int start2, int end2, String s) {
        if (end1 - start1 != end2 - start2) return false;
        for (int i = 0; i < end1 - start1 + 1; i++) {
            if (s.charAt(start1+i) != s.charAt(start2+i)) return false;
        }
        return true;
    }
}
