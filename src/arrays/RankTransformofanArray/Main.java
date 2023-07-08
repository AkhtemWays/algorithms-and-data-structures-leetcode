package arrays.RankTransformofanArray;

import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

    }

    public static int[] arrayRankTransform(int[] arr) {
        int[] copy = Arrays.stream(arr).distinct().toArray();
        Arrays.sort(copy);
        HashMap<Integer, Integer> ranks = new HashMap<>();
        int rank = 1;
        for (int num : copy) {
            ranks.put(num, rank++);
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = ranks.get(arr[i]);
        }
        return res;
    }
}
