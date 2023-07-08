package arrays.MinimumRoundstoCompleteAllTasks;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int[] tasks = {2,3, 3};
        int[] tasks2 = {5, 5, 5, 5};
        int[] tasks3 = {2,2,3,3,2,4,4,4,4,4};
        System.out.println(minimumRounds(tasks));
        System.out.println(minimumRounds(tasks2));
        System.out.println(minimumRounds(tasks3));
    }

    public static int minimumRounds(int[] tasks) {
        HashMap<Integer, Integer> difficultyCounts = new HashMap<>();
        for (int task : tasks) {
            difficultyCounts.put(task, difficultyCounts.getOrDefault(task, 0) + 1);
        }

        int res = 0;
        for (Map.Entry<Integer, Integer> entry : difficultyCounts.entrySet()) {
            if (entry.getValue() == 1) return -1;
            res += entry.getValue() / 3;
            if (entry.getValue() % 3 > 0) res++;
        }
        return res;
    }
}
