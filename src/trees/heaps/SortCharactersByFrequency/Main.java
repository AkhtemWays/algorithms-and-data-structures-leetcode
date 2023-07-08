package trees.heaps.SortCharactersByFrequency;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {
    private static class Node {
        char ch;
        int count;
        Node(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.frequencySort("cccaaa"));
        System.out.println(main.frequencySort("tree"));
        System.out.println(main.frequencySort("aAbb"));
    }

    public String frequencySort(String s) {
        HashMap<Character, Integer> characterCounts = new HashMap<>();
        Comparator<Map.Entry<Character, Integer>> cmp = (a, b) -> a.getValue() - b.getValue() == 0 ? a.getKey() - b.getKey() : b.getValue() - a.getValue();
        PriorityQueue<Map.Entry<Character, Integer>> heap = new PriorityQueue<>(cmp);
        for (char ch : s.toCharArray()) {
            if (characterCounts.containsKey(ch)) {
                characterCounts.put(ch, characterCounts.get(ch) + 1);
            } else {
                characterCounts.put(ch, 1);
            }
        }

        heap.addAll(characterCounts.entrySet());

        StringBuilder res = new StringBuilder();
        while (!heap.isEmpty()) {
            Map.Entry<Character, Integer> characterCount = heap.poll();
            res.append(String.valueOf(characterCount.getKey()).repeat(characterCount.getValue()));
        }
        return res.toString();
    }
}
