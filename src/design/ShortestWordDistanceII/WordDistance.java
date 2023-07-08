package design.ShortestWordDistanceII;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordDistance {
    public static void main(String[] args) {
        String[] wordsDict = {"practice", "makes", "perfect", "coding", "makes"};
        WordDistance wordDistance = new WordDistance(wordsDict);
        System.out.println(wordDistance.shortest("coding", "practice")); // return 3
        System.out.println(wordDistance.shortest("makes", "coding"));    // return 1
    }
    private final HashMap<String, List<Integer>> wordIndices;
    private final HashMap<String, Integer> cache;
    public WordDistance(String[] wordsDict) {
        this.wordIndices = new HashMap<>();
        this.cache = new HashMap<>();
        for (int i = 0; i < wordsDict.length; i++) {
            if (wordIndices.containsKey(wordsDict[i])) {
                wordIndices.get(wordsDict[i]).add(i);
            } else {
                wordIndices.put(wordsDict[i], new ArrayList<>(List.of(i)));
            }
        }
    }

    public int shortest(String word1, String word2) {
        String combination = word1 + "|" + word2;
        if (cache.containsKey(combination)) return cache.get(combination);
        List<Integer> word1Indices = wordIndices.get(word1);
        List<Integer> word2Indices = wordIndices.get(word2);
        int i = 0, j = 0;
        int minDistance = Integer.MAX_VALUE;
        while (i < word1Indices.size() && j < word2Indices.size()) {
            int curI = word1Indices.get(i);
            int curJ = word2Indices.get(j);
            int cur = Math.abs(curI - curJ);
            if (cur < minDistance) {
                minDistance = cur;
            }
            int nextJ = j + 1 < word2Indices.size() ? word2Indices.get(j+1) : Integer.MAX_VALUE;
            int nextI = i + 1 < word1Indices.size() ? word1Indices.get(i+1) : Integer.MAX_VALUE;
            int jShift = Math.abs(curI - nextJ);
            int iShift = Math.abs(curJ - nextI);
            if (iShift < jShift && iShift < minDistance) {
                i++;
            } else if (jShift < iShift && jShift < minDistance) {
                j++;
            } else {
                i++;
                j++;
            }
        }
        cache.put(combination, minDistance);
        return minDistance;
    }
}
