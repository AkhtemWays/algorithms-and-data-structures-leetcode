package trees.heaps.TopKFrequentWords;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] words = {"the","day","is","sunny","the","the","the","sunny","is","is"};
        System.out.println(topKFrequent(words, 4));
    }

    public static List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        HashMap<String, Integer> wordCounts = new HashMap<>();
        Comparator<Map.Entry<String, Integer>> cmp = (a, b) -> a.getValue() - b.getValue() == 0 ? a.getKey().compareTo(b.getKey()) : b.getValue() - a.getValue();
        PriorityQueue<Map.Entry<String, Integer>> heap = new PriorityQueue<>(cmp);

        for (String word : words) {
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }
        heap.addAll(wordCounts.entrySet());
        for (int i = 0; i < k; i++) res.add(heap.poll().getKey());
        return res;
    }
}
