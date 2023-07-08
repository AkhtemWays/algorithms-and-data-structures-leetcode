package hashmap.ShortestWordDistance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] wordsDict = {"practice", "makes", "perfect", "coding", "makes"};
        System.out.println(shortestDistance(wordsDict, "makes", "coding"));
    }

    public static int shortestDistance(String[] wordsDict, String word1, String word2) {
        int i = -100000;
        int j = -999999;
        int minDistance = Integer.MAX_VALUE;
        for (int k = 0; k < wordsDict.length; k++) {
            String word = wordsDict[k];
            if (word.equals(word1)) {
                i = k;
            } else if (word.equals(word2)) {
                j = k;
            }
            minDistance = Math.min(minDistance, Math.abs(i - j));
        }
        return minDistance;

    }
}
