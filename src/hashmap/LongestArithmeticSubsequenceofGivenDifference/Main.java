package hashmap.LongestArithmeticSubsequenceofGivenDifference;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

    }

    public int longestSubsequence(int[] arr, int difference) {
        HashMap<Integer, Integer> values = new HashMap<>();
        ArrayList<Integer> l = new ArrayList<>();
        for (int i = arr.length-1; i >= 0; i--) {
            values.put(arr[i], values.getOrDefault(arr[i] + difference, 0) + 1);
        }
        return values.values().stream().max((a, b) -> a - b).get();
    }
}
