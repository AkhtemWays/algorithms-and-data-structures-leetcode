package trees.heaps.TaskScheduler;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        char[] tasks = {'A','A','A','A','A','A','B','C','D','E','F','G'};
        char[] tasks2 = {'A','A','A','B','B','B'};
        System.out.println(main.leastInterval(tasks, 2)); // 16
        System.out.println(main.leastInterval(tasks2, 2)); // 8
        System.out.println(main.leastInterval(tasks2, 0)); // 6
    }

    public int leastInterval(char[] tasks, int n) {
        if (n == 0) return tasks.length;
        Comparator<Map.Entry<Character, Integer>> cmp = (a, b) -> a.getValue() - b.getValue() == 0 ? a.getKey() - b.getKey() : b.getValue() - a.getValue();
        PriorityQueue<Map.Entry<Character, Integer>> heap = new PriorityQueue<>(cmp);
        HashMap<Character, Integer> characterCounts = new HashMap<>();
        for (char ch : tasks) {
            characterCounts.put(ch, characterCounts.getOrDefault(ch, 0) + 1);
        }
        heap.addAll(characterCounts.entrySet());

        int timeCount = 0;
        while (!heap.isEmpty()) {
            int count = n;
            List<Map.Entry<Character, Integer>> nextBatch = new ArrayList<>();
            while (count > 0 && !heap.isEmpty()) {
                Map.Entry<Character, Integer> characterCount = heap.poll();
                if (characterCount.getValue() > 1) {
                    characterCount.setValue(characterCount.getValue()-1);
                    nextBatch.add(characterCount);
                }
                timeCount++;
                count--;
            }
            if (count > 0) {
                timeCount += count;
            }
            heap.addAll(nextBatch);
        }
        return timeCount;
    }
}
