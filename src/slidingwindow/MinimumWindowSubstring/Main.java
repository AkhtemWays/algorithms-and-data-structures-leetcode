package slidingwindow.MinimumWindowSubstring;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC")); // "BANC"
        System.out.println(minWindow("a", "a")); // "a"
        System.out.println(minWindow("a", "aa")); // ""
    }

    public static String minWindow(String s, String t) {
        if (t.length() > s.length() || s.length() == 0) return "";
        int[] pattern = new int[58];
        int k = 0;
        for (char ch : t.toCharArray()) {
            pattern[(ch - 65)]++;
            if (pattern[ch-65] == 1) k++;
        }

        int[] window = new int[58];


        int[] answer = {-1, -1};
        int numElementsDone = 0;
        for (int right = 0, left = 0; right < s.length(); right++) {
            while (numElementsDone == k) {
                updateAnswer(left, right, answer);
                int index = s.charAt(left)-65;
                window[index]--;
                left++;
                right--;
                if (window[index] < pattern[index]) {
                    numElementsDone--;
                }
            }
            int index = s.charAt(right)-65;
            window[index]++;
            if (window[index] == pattern[index]) {
                numElementsDone++;
            }
        }

        if (answer[0] == -1) return "";
        return s.substring(answer[0], answer[1]);
    }

    private static void updateAnswer(int left, int right, int[] answer) {
        if (answer[0] == -1) {
            answer[0] = left;
            answer[1] = right;
        } else {
            int bestLeft = answer[0];
            int bestRight = answer[1];
            int bestDiff = bestRight - bestLeft;
            int diff = right - left;
            if (diff < bestDiff) {
                answer[0] = left;
                answer[1] = right;
            }
        }
    }
}
