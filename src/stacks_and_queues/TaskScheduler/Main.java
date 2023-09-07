package stacks_and_queues.TaskScheduler;

import java.util.*;

public class Main {
    private static void test1() {
        char[] tasks = {'A','A','A','B','B','B'};
        System.out.println(leastInterval(tasks, 2));
    }
    private static void test2() {
        char[] tasks = {'A','A','A','A','A','A','B','C','D','E','F','G'};
        System.out.println(leastInterval(tasks, 2));
    }
    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static class Task {
        char name;
        int count;
        int cooldownEndTime;
        Task(char name, int count, int cooldownEndTime) {
            this.name = name;
            this.count = count;
            this.cooldownEndTime = cooldownEndTime;
        }
    }
    public static int leastInterval(char[] tasks, int n) {
        int time = 0;
        Set<Integer> s = new HashSet<>();
        PriorityQueue<Task> pq = new PriorityQueue<>((a, b) -> {
            if (a.cooldownEndTime == b.cooldownEndTime) return b.count - a.count;
            return a.cooldownEndTime - b.cooldownEndTime;
        });

        HashMap<Character, Integer> counts = new HashMap<>();

        for (char task : tasks) {
            counts.put(task, counts.getOrDefault(task, 0) + 1);
        }

        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            pq.add(new Task(entry.getKey(), entry.getValue(), 0));
        }

        while (!pq.isEmpty()) {
            Task task = pq.poll();
            if (task.count != 0) {
                time = Math.max(task.cooldownEndTime, time + 1);
                task.count--;
                task.cooldownEndTime = time + n + 1;
                pq.add(task);
            }
        }

        return time;
    }
}
