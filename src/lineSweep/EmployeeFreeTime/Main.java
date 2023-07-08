package lineSweep.EmployeeFreeTime;

import java.util.*;

class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
}

public class Main {
    public static void main(String[] args) {

    }

    public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        Set<Integer> times = new HashSet<>();
        for (List<Interval> employee : schedule) {
            for (Interval interval : employee) {
                times.add(interval.start);
                times.add(interval.end);
            }
        }

        List<Integer> sortedTimes = new ArrayList<>(times);
        Collections.sort(sortedTimes);
        List<Interval> answer = new ArrayList<>();
        for (int i = 1; i < sortedTimes.size(); i++) {
            Interval cur = new Interval(sortedTimes.get(i-1), sortedTimes.get(i));
            if (!isOverlap(schedule, cur)) answer.add(cur);
        }
        return answer;
    }

    private static boolean isOverlap(List<List<Interval>> schedule, Interval cur) {
        for (List<Interval> employee : schedule) {
            int idx = binarySearch(employee, cur.start);
            if (overlap(employee.get(idx), cur)) return true;
        }
        return false;
    }

    private static int binarySearch(List<Interval> intervals, int target) {
        int left = 0, right = intervals.size();
        while (left < right) {
            int mid = left + right / 2;
            Interval interval = intervals.get(mid);
            if (interval.start <= target && target < interval.end) return mid;
            else if (target >= interval.end) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private static boolean overlap(Interval interval1, Interval interval2) {
        if (interval1.start >= interval2.start && interval1.start < interval2.end) return true;
        if (interval1.end > interval2.start && interval1.end <= interval2.end) return true;
        return false;
    }
}
