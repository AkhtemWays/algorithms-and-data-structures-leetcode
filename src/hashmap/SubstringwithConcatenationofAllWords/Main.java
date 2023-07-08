package hashmap.SubstringwithConcatenationofAllWords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] words = {"foo","bar"};
        String[] words2 = {"word","good","best","word"};
        String[] words3 = {"bar","foo","the"};
        String[] words4 = {"word","good","best","good"};
        String[] words5 = {"aa","aa","aa"};
        System.out.println(findSubstring("barfoothefoobarman", words)); // [0, 9]
        System.out.println(findSubstring("wordgoodgoodgoodbestword", words2)); // []
        System.out.println(findSubstring("barfoofoobarthefoobarman", words3)); // [6, 9, 12]
        System.out.println(findSubstring("wordgoodgoodgoodbestword", words4)); // [8]
        System.out.println(findSubstring("aaaaaaaaaaaa", words5)); 
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> answer = new ArrayList<>();
        if (words.length == 0) return answer;
        int k = words[0].length();

        int concatenatedSubstringSize = words[0].length() * words.length;
        if (concatenatedSubstringSize > s.length()) return answer;
        for (int i = 0; i <= s.length() - concatenatedSubstringSize; i++) {
            if (find(i, s, getWordsCopy(words), concatenatedSubstringSize, k)) {
                answer.add(i);
            }
        }
        return answer;
    }

    private static boolean find(int i, String s, HashMap<String, Integer> words, int concatenatedSubstringSize, int k) {
        StringBuilder sb = new StringBuilder();
        for (int j = i; j < i + concatenatedSubstringSize; j++) {
            if ((j - i) % k == 0) {
                if (j != i) {
                    String word = sb.toString();
                    if (words.containsKey(word)) {
                        words.put(word, words.get(word)-1);
                        if (words.get(word) == 0) words.remove(word);
                    } else {
                        return false;
                    }
                }
                sb = new StringBuilder();
            }
            sb.append(s.charAt(j));
        }
        return words.containsKey(sb.toString());
    }

    private static HashMap<String, Integer> getWordsCopy(String[] words) {
        HashMap<String, Integer> wordsCopy = new HashMap<>();
        for (String word : words) {
            wordsCopy.put(word, wordsCopy.getOrDefault(word, 0) + 1);
        }
        return wordsCopy;
    }
}
