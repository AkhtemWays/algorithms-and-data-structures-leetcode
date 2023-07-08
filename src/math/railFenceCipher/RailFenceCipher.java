package math.railFenceCipher;

import java.util.ArrayList;
import java.util.List;

public class RailFenceCipher {

    public static String encode(String s, int n) {
        if (s == null || s.length() == 0) return "";

        List<StringBuilder> sbList = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            sbList.add(new StringBuilder());
        }

        for (int i = 0; i < s.length(); i++) {
            int j = i % ((n - 1) * 2);
            j = j >= n ? n - (j % n) - 2 : j % n;
            sbList.get(j).append(s.charAt(i));
        }

        StringBuilder result = new StringBuilder();

        for (StringBuilder sb : sbList) {
            result.append(sb);
        }

        return result.toString();
    }

    public static String decode(String s, int n) {
        if (s == null || s.length() == 0) return "";

        int currRow = 0;
        int rowDirection = 1;
        int currIndex = 0;
        List<Character> chars = new ArrayList<>(s.length());

        for (int i = 0; i < s.length(); i++) chars.add(i, null);

        for (int i = 0; i < s.length(); i++) {
            chars.set(currIndex, s.charAt(i));

            currIndex += (n - 1) * 2;

            if (currRow > 0 && currRow < n - 1) {
                int addIndex = currIndex - (currRow * 2);

                if (addIndex < s.length() && (i + 1 < s.length())) chars.set(addIndex, s.charAt(++i));
            }

            if (currIndex >= s.length()) {
                if (currRow == 0) rowDirection = 1;
                if (currRow == n - 1) rowDirection = -1;
                currRow += rowDirection;
                currIndex = currRow;
            }
        }

        final StringBuilder sb = new StringBuilder();

        for (Character c : chars) {
            sb.append(c);
        }

        return sb.toString();
    }
}
