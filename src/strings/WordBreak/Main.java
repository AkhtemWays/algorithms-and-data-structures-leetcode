package strings.WordBreak;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String s = "leetcodee";
        List<String> wordDict = List.of("leet","code");
        System.out.println(wordBreak(s, wordDict));
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        int i = 0;
        while (i < s.length()) {
            boolean isSegmented = false;
            for (String word : wordDict) {
                if (checkWordSegmentation(word, i, s)) {
                    i += word.length();
                    isSegmented = true;
                    break;
                }
            }
            if (!isSegmented) return false;
        }
        return i == s.length();
    }

    private static boolean checkWordSegmentation(String word, int start, String s) {
        for (int i = 0; i < word.length(); i++) {
            if (start+i >= s.length() || s.charAt(start+i) != word.charAt(i)) return false;
        }
        return true;
    }
}
