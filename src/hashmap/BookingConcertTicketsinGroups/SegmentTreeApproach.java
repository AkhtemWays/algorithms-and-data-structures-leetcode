package hashmap.BookingConcertTicketsinGroups;

import java.util.Arrays;

public class SegmentTreeApproach {

    private static void test1() {
        SegmentTreeApproach bms = new SegmentTreeApproach(2, 5); // There are 2 rows with 5 seats each
        System.out.println(Arrays.toString(bms.gather(4, 0))); // return [0, 0]
        System.out.println(Arrays.toString(bms.gather(2, 0))); // return []
        System.out.println(bms.scatter(5, 1)); // return True
        System.out.println(bms.scatter(5, 1)); // return False
    }

    private static void test2() {
        SegmentTreeApproach bms = new SegmentTreeApproach(6,9);
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
        test2();
//        test3();
//        test4();
//        test5();
//        test6();
    }
    /**
     Segment tree class to store sum of a range and maximum available seats in a row
     **/
    static class SegTree{
        long sumTree[]; // store sum of seats in a range
        long maxTree[]; // store maximum seats in a range
        int m, n;
        public SegTree(int n, int m) {
            this.m = m;
            this.n = n;
            maxTree = new long[4*n];
            sumTree = new long[4*n];
            build(0, 0, n-1, m);
        }

        private void build(int index, int lo, int hi, long val){
            if(lo == hi) {
                maxTree[index] = val; // initialize segement tree with initial seat capacity
                sumTree[index] = val; // initialize "sum" with initial seat capacity of a row
                return;
            }
            int mid = (lo + hi)/2;
            build(2*index +1, lo, mid, val); // build left sub tree
            build(2*index +2, mid+1, hi, val); // build right sub tree
            maxTree[index] = Math.max(maxTree[2*index + 1], maxTree[2*index + 2]); // maximum seats in a row for subtrees
            sumTree[index] = sumTree[2*index + 1] + sumTree[2*index + 2]; // sum of seats in a range
        }

        private void update(int index, int lo, int hi, int pos, int val){
            /**
             Method to update segment tree based on the available seats in a row
             **/
            if(lo == hi){
                maxTree[index] = val;
                sumTree[index] = val;
                return;
            }
            int mid = (lo + hi) / 2;
            if (pos <= mid) {  // position to update is in left
                update(2 * index + 1, lo, mid, pos, val);
            } else { // position to update is in right
                update(2 * index + 2,  mid+1, hi, pos, val);
            }
            // update segment tree and "sum" based on the update in "pos" index
            maxTree[index] = Math.max(maxTree[2*index + 1] , maxTree[2*index + 2]);
            sumTree[index] = sumTree[2*index + 1] + sumTree[2*index + 2];
        }

        public void update(int pos, int val){
            update(0, 0, n - 1 , pos, val);
        }

        public int gatherQuery(int k, int maxRow){
            return gatherQuery(0, 0, n - 1 , k, maxRow);
        }

        private int gatherQuery(int index, int lo, int hi, int k, int maxRow){
            /**
             Method to check if seats are available in a single row
             **/
            if(maxTree[index] < k || lo > maxRow)
                return -1;
            if(lo == hi) return lo;
            int mid = (lo + hi) / 2;
            int c = gatherQuery(2*index + 1, lo, mid, k, maxRow);
            if(c == -1){
                c = gatherQuery(2*index + 2, mid +1, hi, k, maxRow);
            }
            return c;
        }

        public long sumQuery(int k, int maxRow){
            return sumQuery(0, 0, n-1, k, maxRow);
        }

        private long sumQuery(int index, int lo, int hi, int l, int r){
            if(lo > r || hi < l ) return 0;  // not in range
            if(lo == l && hi == r) return sumTree[index]; // in range
            int mid = (lo + hi)/2;
            return sumQuery(2*index+1, lo, mid, l, r) + sumQuery(2*index+2, mid+1, hi, l, r);
        }
    }

    SegTree segTree;
    int[] rowSeats; // stores avaiable seats in a row, helps to find the vacant seat in a row

    public SegmentTreeApproach(int n, int m) {
        segTree = new SegTree(n, m);
        rowSeats = new int[n];
        Arrays.fill(rowSeats, m);  // initialize vacant seats count to "m" for all the rows
    }


    public int[] gather(int k, int maxRow) {
        int row = segTree.gatherQuery(k, maxRow); // find row which has k seats
        if(row == -1) return new int[]{}; // can't find a row with k seats
        int col = segTree.m - rowSeats[row]; // find column in the row which has k seats
        rowSeats[row] -= k; // reduce the seats
        segTree.update(row, rowSeats[row]); // update the segment tree
        return new int[]{row, col};

    }

    public boolean scatter(int k, int maxRow) {
        long sum = segTree.sumQuery(0, maxRow); // find the sum for the given range [0, maxRow]
        if(sum < k) return false; // can't find k seats in [0, maxRow]

        for(int i=0; i<=maxRow && k !=0 ; i++){
            if(rowSeats[i] > 0){                       // if current row has seats then allocate those seats
                long t = Math.min(rowSeats[i], k);
                rowSeats[i] -= t;
                k -= t;
                segTree.update(i,rowSeats[i]);  // update the segment tree
            }
        }
        return true;
    }
}
