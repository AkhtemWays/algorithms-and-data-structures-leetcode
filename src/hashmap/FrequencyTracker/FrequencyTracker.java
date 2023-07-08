package hashmap.FrequencyTracker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FrequencyTracker {
    private static void test1() {
        FrequencyTracker frequencyTracker = new FrequencyTracker();
        frequencyTracker.add(3); // The data structure now contains [3]
        frequencyTracker.add(3); // The data structure now contains [3, 3]
        System.out.println(frequencyTracker.hasFrequency(2)); // Returns true, because 3 occurs twice
    }
    private static void test2() {
        FrequencyTracker frequencyTracker = new FrequencyTracker();
        frequencyTracker.add(1); // The data structure now contains [1]
        frequencyTracker.deleteOne(1); // The data structure becomes empty []
        System.out.println(frequencyTracker.hasFrequency(1)); // Returns false, because the data structure is empty
    }
    private static void test3() {
        FrequencyTracker frequencyTracker = new FrequencyTracker();
        frequencyTracker.hasFrequency(2); // Returns false, because the data structure is empty
        frequencyTracker.add(3); // The data structure now contains [3]
        System.out.println(frequencyTracker.hasFrequency(1)); // Returns true, because 3 occurs once
    }
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        System.out.println(1 << 17);
    }

    HashMap<Integer, Set<Integer>> freqs = new HashMap<>();
    HashMap<Integer, Integer> numFrequencies = new HashMap<>();
    public FrequencyTracker() {

    }

    public void add(int number) {
        if (numFrequencies.containsKey(number)) {
            int numFrequency = numFrequencies.get(number);
            if (!freqs.containsKey(numFrequency)) freqs.put(numFrequency, new HashSet<>());
            if (!freqs.containsKey(numFrequency+1)) freqs.put(numFrequency+1, new HashSet<>());

            freqs.get(numFrequency).remove(number);
            freqs.get(numFrequency+1).add(number);
            numFrequencies.put(number, numFrequency + 1);
        } else {
            numFrequencies.put(number, 1);
            freqs.computeIfAbsent(1, (k) -> new HashSet<>()).add(number);
        }
    }

    public void deleteOne(int number) {
        if (!numFrequencies.containsKey(number)) return;
        int numFrequency = numFrequencies.get(number);
        if (numFrequency == 0) return;

        numFrequencies.put(number, numFrequency-1);
        if (!freqs.containsKey(numFrequency)) freqs.put(numFrequency, new HashSet<>());
        if (!freqs.containsKey(numFrequency-1)) freqs.put(numFrequency-1, new HashSet<>());
        freqs.get(numFrequency).remove(number);
        freqs.get(numFrequency-1).add(number);
    }

    public boolean hasFrequency(int frequency) {
        if (freqs.containsKey(frequency)) return !freqs.get(frequency).isEmpty();
        return false;
    }
}
