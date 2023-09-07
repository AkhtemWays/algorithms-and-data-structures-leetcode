package stacks_and_queues.KthSmallestPrimeFraction;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {

    }

    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        HashMap<Integer, Integer> indices = new HashMap<>();
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> {
            double first = a[0] / (double) a[1];
            double second = b[0] / (double) b[1];
            return Double.compare(first, second);
        });
        for (int i = 0; i < arr.length-1; i++) {
            indices.put(arr[i], arr.length-2);
            q.add(new int[]{arr[i], arr[arr.length-1]});
        }

        while (--k > 0) {
            int[] fraction = q.poll();
            int nextIndex = indices.get(fraction[0]);
            if (nextIndex != -1) {
                q.add(new int[]{fraction[0], arr[nextIndex]});
                indices.put(fraction[0], --nextIndex);
            }
        }

        return q.peek();
    }
}
