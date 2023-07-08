package strings.PartitionLabels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        partitionLabels("ababcbacadefegdehijhklij").forEach(System.out::println);
        partitionLabels("eccbbbbdec").forEach(System.out::println);
    }

    public static List<Integer> partitionLabels(String s) {
        HashMap<Character, Integer> counts = getCounts(s);
        List<Integer> partitionSizes = new ArrayList<>();
        HashMap<Character, Integer> partitionCounts = new HashMap<>();
        int sizeCounter = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!partitionCounts.containsKey(ch) && canFinishPartition(counts, partitionCounts)) {
                partitionSizes.add(sizeCounter);
                partitionCounts = new HashMap<>();
                partitionCounts.put(ch, 1);
                sizeCounter = 1;
            } else {
                sizeCounter++;
                partitionCounts.put(ch, partitionCounts.getOrDefault(ch, 0) + 1);
            }
        }
        if (sizeCounter > 0) partitionSizes.add(sizeCounter);
        return partitionSizes;
    }

    private static HashMap<Character, Integer> getCounts(String s) {
        HashMap<Character, Integer> counts = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            counts.put(s.charAt(i), counts.getOrDefault(s.charAt(i), 0) + 1);
        }
        return counts;
    }

    private static boolean canFinishPartition(HashMap<Character, Integer> counts, HashMap<Character, Integer> partitionCounts) {
        if (partitionCounts.size() == 0) return false;
        for (char ch : partitionCounts.keySet()) {
            if (!partitionCounts.get(ch).equals(counts.get(ch))) return false;
        }
        return true;
    }
}
