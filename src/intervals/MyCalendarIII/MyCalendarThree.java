package intervals.MyCalendarIII;

import java.util.*;

public class MyCalendarThree {
    public static void main(String[] args) {
        MyCalendarThree myCalendarThree = new MyCalendarThree();
        System.out.println(myCalendarThree.book(10, 20)); // return 1
        System.out.println(myCalendarThree.book(50, 60)); // return 1
        System.out.println(myCalendarThree.book(10, 40)); // return 2
        System.out.println(myCalendarThree.book(5, 15)); // return 3
        System.out.println(myCalendarThree.book(5, 10)); // return 3
        System.out.println(myCalendarThree.book(25, 55)); // return 3
    }

    private final TreeMap<Integer, Integer> bst;
    public MyCalendarThree() {
        this.bst = new TreeMap<>();
    }

    public int book(int startTime, int endTime) {
        bst.put(startTime, bst.getOrDefault(startTime, 0) + 1);
        int answer = 0;
        for (Map.Entry<Integer, Integer> entry : bst.tailMap(startTime, true).entrySet()) {
            if (entry.getKey() <= endTime) {
                answer += entry.getValue();
            } else {
                break;
            }
        }
        return answer;
    }
}
