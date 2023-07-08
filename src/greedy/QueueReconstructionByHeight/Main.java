package greedy.QueueReconstructionByHeight;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[][] people = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
    }

    public static int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0) return new int[0][0];
        Arrays.sort(people, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : b[0] - a[0]);
        List<int[]> result = new ArrayList<>();
        for (int[] person : people) {
            result.add(person[1], person);
        }
        return result.toArray(new int[people.length][2]);
    }
}
