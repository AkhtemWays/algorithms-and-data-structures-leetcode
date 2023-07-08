package stacks_and_queues.FruitIntoBaskets;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        int[] fruits1 = {1,2,1};
        int[] fruits2 = {0,1,2,2};
        int[] fruits3 = {1,2,3,2,2};
        int[] fruits4 = {3,3,3,1,2,1,1,2,3,3,4};
        int[] fruits5 = {1,0,1,4,1,4,1,2,3};
        System.out.println(totalFruit(fruits1)); // 3
        System.out.println(totalFruit(fruits2)); // 4
        System.out.println(totalFruit(fruits3)); // 4
        System.out.println(totalFruit(fruits4)); // 5
        System.out.println(totalFruit(fruits5)); // 5
    }

    public static int totalFruit(int[] fruits) {
        Queue<Integer> q = new LinkedList<>();
        int maxFruits = 0;
        HashMap<Integer, Integer> typeCounts = new HashMap<>();
        HashMap<Integer, Integer> lastPosition = new HashMap<>();
        for (int i = 0; i < fruits.length; i++) {
            typeCounts.put(fruits[i], typeCounts.getOrDefault(fruits[i], 0) + 1);
            if (typeCounts.size() == 3) {
                int typeToRemove = getTypeToRemove(lastPosition);
                while (typeCounts.get(typeToRemove) > 0) {
                    int key = q.peek();
                    typeCounts.put(key, typeCounts.get(key) - 1);
                    q.poll();
                }
                typeCounts.remove(typeToRemove);
            }
            q.add(fruits[i]);
            lastPosition.put(fruits[i], i);
            maxFruits = Math.max(maxFruits, q.size());
        }
        return maxFruits;
    }

    private static int getTypeToRemove(HashMap<Integer, Integer> lastPosition) {
        int key = lastPosition.entrySet().stream().min((entry1, entry2) -> entry1.getValue() - entry2.getValue()).get().getKey();
        lastPosition.remove(key);
        return key;
    }
}
