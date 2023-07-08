package arrays.FindMedianfromDataStream;

import java.util.*;

class MedianFinder {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(1);    // arr = [1]
        medianFinder.addNum(2);    // arr = [1, 2]
        System.out.println(medianFinder.findMedian()); // return 1.5 (i.e., (1 + 2) / 2)
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(4);
        medianFinder.addNum(5);
        medianFinder.addNum(6);
        medianFinder.addNum(7);  // arr[1, 2, 3]
        System.out.println(medianFinder.findMedian()); // return 2.0
    }
    private final PriorityQueue<Integer> rightPart;
    private final PriorityQueue<Integer> leftPart;

    public MedianFinder() {
        this.leftPart = new PriorityQueue<>((a, b) -> b - a);
        this.rightPart = new PriorityQueue<>();
    }

    public void addNum(int num) {
        this.leftPart.add(num);
        this.rightPart.add(this.leftPart.poll());
        if (leftPart.size() < rightPart.size()) leftPart.add(rightPart.poll());
    }

    public double findMedian() {
        if (leftPart.size() == 0) return 0;
        return leftPart.size() > rightPart.size() ? leftPart.peek() : ((double) leftPart.peek() + rightPart.peek()) / 2;
    }

    private boolean sizeEven() {
        return (leftPart.size() + rightPart.size()) % 2 == 0;
    }
}
