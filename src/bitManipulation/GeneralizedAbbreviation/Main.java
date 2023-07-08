package bitManipulation.GeneralizedAbbreviation;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }

    public List<String> generateAbbreviations(String word) {
        int n = word.length();
        List<String> res = new ArrayList<>();
        for (int bitmask = 0; bitmask < 1 << n; bitmask++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (((bitmask >> i) & 1) == 1) {
                    if (sb.length() == 0) {
                        sb.append(1);
                        continue;
                    }
                    char ch = sb.charAt(sb.length()-1);
                    if (Character.isAlphabetic(ch)) {
                        sb.append(1);
                    } else if (ch == '9') {
                        sb.deleteCharAt(sb.length()-1).append(10);
                    } else {
                        sb.deleteCharAt(sb.length()-1).append(++ch);
                    }
                } else {
                    sb.append(word.charAt(i));
                }
            }
            res.add(sb.toString());
        }
        return res;
    }
}
