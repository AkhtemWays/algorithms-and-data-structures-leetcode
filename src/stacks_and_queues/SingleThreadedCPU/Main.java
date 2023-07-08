package stacks_and_queues.SingleThreadedCPU;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static class Task {
        int index;
        int enqueueTime;
        int processingTime;
        Task(int index, int enqueueTime, int processingTime) {
            this.index = index;
            this.enqueueTime = enqueueTime;
            this.processingTime = processingTime;
        }
    }
    public static void main(String[] args) {
        int[][] tasks = {{1,2},{2,4},{3,2},{4,1}};
        int[][] tasks2 = {{7,10},{7,12},{7,5},{7,4},{7,2}};
        int[][] tasks3 = {{8, 7}, {1, 2}, {4, 1}};
        int[][] tasks4 = {{19,13},{16,9},{21,10},{32,25},{37,4},{49,24},{2,15},{38,41},{37,34},{33,6},{45,4},{18,18},{46,39},{12,24}};
        System.out.println(Arrays.toString(getOrder(tasks)));
        System.out.println(Arrays.toString(getOrder(tasks2)));
        System.out.println(Arrays.toString(getOrder(tasks3)));
        System.out.println(Arrays.toString(getOrder(tasks4))); // [6,1,2,9,4,10,0,11,5,13,3,8,12,7]
    }

    public static int[] getOrder(int[][] taskz) {
        PriorityQueue<Task> q = new PriorityQueue<>((a, b) -> a.processingTime == b.processingTime ? a.index - b.index : a.processingTime - b.processingTime);
        Task[] tasks = new Task[taskz.length];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new Task(i, taskz[i][0], taskz[i][1]);
        }
        Arrays.sort(tasks, Comparator.comparingInt(a -> a.enqueueTime));
        int[] res = new int[tasks.length];
        int k = 0, i = 0, time = 0;
        while (!q.isEmpty() || i < tasks.length) {
            if (q.isEmpty()) {
                int[] meta = fillQueue(i, q, tasks);
                i = meta[0];
                time = meta[1];
            }
            Task task = q.poll();
            res[k++] = task.index;
            time += task.processingTime;
            while (i < tasks.length && tasks[i].enqueueTime <= time) {
                q.add(tasks[i]);
                i++;
            }
        }
        return res;
    }

    private static int[] fillQueue(int i, PriorityQueue<Task> q, Task[] tasks) {
        int time = tasks[i].enqueueTime;
        while (i < tasks.length && tasks[i].enqueueTime == time) {
            q.add(tasks[i]);
            i++;
        }
        return new int[]{i, time};
    }
}
