package design.DesignHitCounter;

import java.util.*;

class HitCounter {
    public static void main(String[] args) {
        HitCounter hitCounter = new HitCounter();
        hitCounter.hit(1);       // hit at timestamp 1.
        hitCounter.hit(2);       // hit at timestamp 2.
        hitCounter.hit(3);       // hit at timestamp 3.
        System.out.println(hitCounter.getHits(4));   // get hits at timestamp 4, return 3.
        hitCounter.hit(300);     // hit at timestamp 300.
        System.out.println(hitCounter.getHits(300)); // get hits at timestamp 300, return 4.
        System.out.println(hitCounter.getHits(301)); // get hits at timestamp 301, return 3.
    }
    private final HashMap<Integer, List<Integer>> hitsCount;
    public HitCounter() {
        hitsCount = new HashMap<>();
    }

    public void hit(int timestamp) {
        int bucket = timestamp / 300;
        hitsCount.computeIfAbsent(bucket, (k) -> new ArrayList<>()).add(timestamp);
    }

    public int getHits(int timestamp) {
        int bucket = timestamp / 300;
        List<Integer> hits = hitsCount.get(bucket);
        int sum = 0;
        if (hits != null) {
            int insertionIndex = binarySearch(hits, timestamp - 300);
            sum += hits.size() - insertionIndex;
        }
        List<Integer> anotherHits = hitsCount.get(bucket-1);
        if (anotherHits != null) {
            int insertionIndex2 = binarySearch(anotherHits, timestamp - 300);
            if (!anotherHits.isEmpty() && insertionIndex2 < anotherHits.size() && anotherHits.get(insertionIndex2) == timestamp - 300) {
                insertionIndex2 = binarySearch(anotherHits, timestamp - 299);
            }
            sum += anotherHits.size() - insertionIndex2;
        }

        return sum;
    }

    private int binarySearch(List<Integer> nums, int target) {
        int l = 0, r = nums.size();
        while (l < r) {
            int m = (l + r) / 2;
            if (nums.get(m) < target) l = m + 1;
            else r = m;
        }
        return l;
    }
}
