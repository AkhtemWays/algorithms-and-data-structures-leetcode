package arrays.TheSkylineProblem;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class SweepLineBruteforce {
    public static void main(String[] args) {
        SweepLineBruteforce bruteforce = new SweepLineBruteforce();
        int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
        System.out.println(bruteforce.getSkyline(buildings));
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        SortedSet<Integer> edgesSet = new TreeSet<>();
        for (int[] building : buildings) {
            edgesSet.add(building[0]);
            edgesSet.add(building[1]);
        }

        List<Integer> positions = new ArrayList<>(edgesSet);
        List<List<Integer>> answer = new ArrayList<>();

        for (int position : positions) {
            int maxHeight = 0;

            for (int[] building : buildings) {
                int left = building[0];
                int right = building[1];
                int height = building[2];

                if (left <= position && position < right) {
                    maxHeight = Math.max(maxHeight, height);
                }
            }

            if (answer.isEmpty() || answer.get(answer.size() - 1).get(1) != maxHeight) {
                answer.add(List.of(position, maxHeight));
            }
        }
        return answer;
    }
}
