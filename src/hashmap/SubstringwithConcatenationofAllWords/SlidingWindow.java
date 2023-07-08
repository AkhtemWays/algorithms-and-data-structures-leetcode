package hashmap.SubstringwithConcatenationofAllWords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class SlidingWindow {
    public static void main(String[] args) {
        String[] words = {"foo","bar"};
        String[] words2 = {"word","good","best","word"};
        String[] words3 = {"bar","foo","the"};
        String[] words4 = {"word","good","best","good"};
        String[] words5 = {"aa","aa","aa"};
        SlidingWindow main = new SlidingWindow();
        System.out.println(main.findSubstring("barfoothefoobarman", words)); // [0, 9]
        System.out.println(main.findSubstring("wordgoodgoodgoodbestword", words2)); // []
        System.out.println(main.findSubstring("barfoofoobarthefoobarman", words3)); // [6, 9, 12]
        System.out.println(main.findSubstring("wordgoodgoodgoodbestword", words4)); // [8]
        System.out.println(main.findSubstring("aaaaaaaaaaaa", words5));
    }
    private HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
    private int n;
    private int wordLength;
    private int substringSize;
    private int k;

    private void slidingWindow(int left, String s, List<Integer> answer) {
        HashMap<String, Integer> current = new HashMap<>();
        int wordsUsed = 0;
        int excessiveWords = 0;
        for (int right = left + wordLength; right <= n; right += wordLength) {
            String word = s.substring(right - wordLength, right);
            if (!wordCount.containsKey(word)) {
                current.clear();
                left = right;
                wordsUsed = 0;
                excessiveWords = 0;
            } else {
                current.put(word, current.getOrDefault(word, 0) + 1);
                if (current.get(word) > wordCount.get(word)) {
                    excessiveWords++;
                } else {
                    wordsUsed++;
                }

                if (right - left > substringSize) {
                    String leftMostWord = s.substring(left, left + wordLength);
                    left += wordLength;
                    if (current.get(leftMostWord) > wordCount.get(leftMostWord)) {
                        excessiveWords--;
                    } else {
                        wordsUsed--;
                    }
                    current.put(leftMostWord, current.get(leftMostWord) - 1);
                }

                if (excessiveWords == 0 && wordsUsed == k) {
                    answer.add(left);
                }
            }
        }
    }

    public List<Integer> findSubstring(String s, String[] words) {
        n = s.length();
        k = words.length;
        wordLength = words[0].length();
        substringSize = wordLength * k;

        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < wordLength; i++) {
            slidingWindow(i, s, answer);
        }

        return answer;
    }
}