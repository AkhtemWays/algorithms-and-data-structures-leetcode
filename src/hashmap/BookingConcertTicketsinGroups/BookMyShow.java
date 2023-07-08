package hashmap.BookingConcertTicketsinGroups;

import java.util.Arrays;
import java.util.HashMap;

public class BookMyShow {
    private static void test1() {
        SegmentTreeApproach bms = new SegmentTreeApproach(2, 5); // There are 2 rows with 5 seats each
        System.out.println(Arrays.toString(bms.gather(4, 0))); // return [0, 0]
        System.out.println(Arrays.toString(bms.gather(2, 0))); // return []
        System.out.println(bms.scatter(5, 1)); // return True
        System.out.println(bms.scatter(5, 1)); // return False
    }

    private static void test2() {
        SegmentTreeApproach bms = new SegmentTreeApproach(5,9);
        System.out.println(Arrays.toString(bms.gather(10, 1))); // []
        System.out.println(bms.scatter(3, 3)); // true
        System.out.println(Arrays.toString(bms.gather(9,1))); // [1, 0]
        System.out.println(Arrays.toString(bms.gather(10,2))); // []
        System.out.println(Arrays.toString(bms.gather(2,0))); // [0, 3]
    }

    private static void test3() {
        SegmentTreeApproach bms = new SegmentTreeApproach(3, 7);
        System.out.println(bms.scatter(9, 0)); // false
        System.out.println(Arrays.toString(bms.gather(2, 2))); // [0, 0]
        System.out.println(Arrays.toString(bms.gather(8,2))); // []
    }

    private static void test4() {
        SegmentTreeApproach bms = new SegmentTreeApproach(19, 40);
        System.out.println(bms.scatter(34, 14)); // true
        System.out.println(bms.scatter(5, 5)); // true
        System.out.println(Arrays.toString(bms.gather(20, 6))); // [1, 0]
        System.out.println(Arrays.toString(bms.gather(3,3))); // [1, 20]
        System.out.println(Arrays.toString(bms.gather(50, 7))); // []
        System.out.println(Arrays.toString(bms.gather(16,5))); // [1, 23]
        System.out.println(Arrays.toString(bms.gather(12, 0))); // []
        System.out.println(bms.scatter(23, 14)); // true
        System.out.println(bms.scatter(36, 0)); // false
        System.out.println(bms.scatter(25, 12)); // true
    }

    private static void test5() {
        SegmentTreeApproach bms = new SegmentTreeApproach(19, 9);
        System.out.println(Arrays.toString(bms.gather(38, 8))); // []
        System.out.println(Arrays.toString(bms.gather(27,3))); // []
        System.out.println(bms.scatter(36, 14)); // true
        System.out.println(bms.scatter(46, 2)); // false
        System.out.println(Arrays.toString(bms.gather(12,5))); // []
        System.out.println(bms.scatter(12, 12)); // true
        System.out.println(bms.scatter(43, 12)); // true
        System.out.println(Arrays.toString(bms.gather(30,5))); // []
        System.out.println(bms.scatter(29, 6)); // false
        System.out.println(bms.scatter(37, 18)); // true
        System.out.println(Arrays.toString(bms.gather(6,16))); // [14, 2]
        System.out.println(bms.scatter(27, 4)); // false
        System.out.println(Arrays.toString(bms.gather(4,17))); // [15, 0]
        System.out.println(bms.scatter(14, 7)); // false
        System.out.println(Arrays.toString(bms.gather(11, 5))); // []
        System.out.println(Arrays.toString(bms.gather(22,8))); // []
    }

    private static void test6() {
        SegmentTreeApproach bms = new SegmentTreeApproach(3,999999999);
        System.out.println(bms.scatter(1000000000,2)); // true
        System.out.println(Arrays.toString(bms.gather(999999999,2))); // []
        System.out.println(Arrays.toString(bms.gather(999999999,2))); // []
        System.out.println(Arrays.toString(bms.gather(999999999,2))); // []
    }

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
        test6();
    }
    private final HashMap<Integer, int[]> free;
    private final int rows;
    private final int seats;
    private long capacityLeft;
    private int lastFreeRow;

    public BookMyShow(int n, int m) {
        this.capacityLeft = (long) n * m;
        this.free = new HashMap<>();
        rows = n;
        seats = m;
        lastFreeRow = 0;
    }

    private boolean canFullfillScatter(int k, int maxRow) {
        int seatsAvailable = 0;
        for (int row = lastFreeRow; row <= Math.min(maxRow, rows-1); row++) {
            if (free.containsKey(row)) {
                int[] seatsInterval = free.get(row);
                seatsAvailable += seatsInterval[1] - seatsInterval[0] + 1;
            } else {
                seatsAvailable += seats;
            }
            if (seatsAvailable >= k) return true;
        }
        return seatsAvailable >= k;
    }

    public int[] gather(int k, int maxRow) {
        if (lastFreeRow == rows || k > seats) return new int[0];
        for (int row = lastFreeRow; row <= Math.min(maxRow, rows-1); row++) {
            if (free.containsKey(row)) {
                int[] seatsInterval = free.get(row);
                int end = seatsInterval[1];
                int start = seatsInterval[0];
                if (end - start + 1 == k) {
                    free.remove(row);
                    lastFreeRow++;
                    capacityLeft -= k;
                    return new int[]{row, start};
                }
                else if (end - start + 1 > k) {
                    int[] updatedInterval = new int[]{start + k, end};
                    capacityLeft -= k;
                    free.put(row, updatedInterval);
                    return new int[]{row, start};
                }
            } else {
                int[] seatsInterval = new int[]{k, seats-1};
                capacityLeft -= k;
                free.put(row, seatsInterval);
                return new int[]{row, 0};
            }
        }
        return new int[0];
    }

    public boolean scatter(int k, int maxRow) {
        if (capacityLeft < k || !canFullfillScatter(k, maxRow)) return false;
        for (int row = lastFreeRow; row <= Math.min(maxRow, rows-1); row++) {
            if (free.containsKey(row)) {
                int[] seatsInterval = free.get(row);
                int end = seatsInterval[1];
                int start = seatsInterval[0];
                if (end - start + 1 == k) {
                    free.remove(row);
                    lastFreeRow++;
                    capacityLeft -= k;
                    return true;
                } else if (end - start + 1 > k) {
                    int[] updatedInterval = new int[]{start + k, end};
                    capacityLeft -= k;
                    free.put(row, updatedInterval);
                    return true;
                } else {
                    capacityLeft -= end - start + 1;
                    k -= end - start + 1;
                    free.remove(row);
                    lastFreeRow++;
                }
            } else {
                if (k >= seats) {
                    lastFreeRow++;
                    capacityLeft -= seats;
                    k -= seats;
                    if (k == 0) return true;
                } else {
                    int[] seatsInterval = new int[]{k, seats-1};
                    capacityLeft -= k;
                    free.put(row, seatsInterval);
                    return true;
                }
            }
        }
        return false;
    }
}
