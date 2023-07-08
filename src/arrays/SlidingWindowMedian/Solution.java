package arrays.SlidingWindowMedian;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(
            new Comparator<Integer>() {
                public int compare(Integer i1, Integer i2) {
                    return i2.compareTo(i1);
                }
            }
    );

    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length - k + 1;
        if (n <= 0) return new double[0];
        double[] result = new double[n];

        for (int i = 0; i <= nums.length; i++) {
            if (i >= k) {
                result[i - k] = getMedian();
                remove(nums[i - k]);
            }
            if (i < nums.length) {
                add(nums[i]);
            }
        }

        return result;
    }

    private void add(int num) {
        if (maxHeap.isEmpty()) {
            maxHeap.add(num);
        }
        if (num >= maxHeap.peek()) {
            minHeap.add(num);
        } else {
            maxHeap.add(num);
        }
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
        if (maxHeap.size() - minHeap.size() > 1) {
            minHeap.add(maxHeap.poll());
        }
    }

    private void remove(int num) {
        if (maxHeap.isEmpty()) return;
        if (num >= maxHeap.peek()) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
        if (maxHeap.size() - minHeap.size() > 1) {
            minHeap.add(maxHeap.poll());
        }
    }

    private double getMedian() {
        if (minHeap.isEmpty() && maxHeap.isEmpty()) return 0;

        if (minHeap.size() == maxHeap.size()) {
            return ((double) minHeap.peek() + (double) maxHeap.peek()) / 2.0D;
        }
        return maxHeap.peek();
    }
}
