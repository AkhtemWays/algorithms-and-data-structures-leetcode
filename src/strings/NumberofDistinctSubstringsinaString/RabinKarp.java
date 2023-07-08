package strings.NumberofDistinctSubstringsinaString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RabinKarp {
    private static int count = 0;
    public static void main(String[] args) {
        System.out.println(countDistinct("aabbaba"));
        System.out.println(countDistinct("abcdefg"));
    }

    public static int countDistinct(String s) {
        count = 0;
        HashMap<Long, HashMap<Integer, List<int[]>>> uniqueSubstrings = new HashMap<>();
        int prime = 100_000_000_9;
        int base = 26;
        for (int i = 0; i < s.length(); i++) {
            String cur = s.charAt(i) + "";
            long hash = getHash(cur);
            check(hash, uniqueSubstrings, s, i, i);
            for (int j = i + 1; j < s.length(); j++) {
                hash = (hash * base) % prime;
                hash = (hash + (s.charAt(j) - 'a' + 1)) % prime;
                check(hash, uniqueSubstrings, s, i, j);
            }
        }
        return count;
    }

    private static void check(long hash, HashMap<Long, HashMap<Integer, List<int[]>>> uniqueSubstrings, String s, int start, int end) {
        if (uniqueSubstrings.containsKey(hash)) {
            HashMap<Integer, List<int[]>> sizedIntervals = uniqueSubstrings.get(hash);
            List<int[]> intervals = sizedIntervals.computeIfAbsent(end-start, (k) -> new ArrayList<>());
            if (!containExact(intervals, s, start, end)) {
                count++;
                intervals.add(new int[]{start, end});
            }
        } else {
            List<int[]> intervals = new ArrayList<>();
            intervals.add(new int[]{start, end});
            HashMap<Integer, List<int[]>> sizedIntervals = new HashMap<>();
            sizedIntervals.put(end-start, intervals);
            uniqueSubstrings.put(hash, sizedIntervals);
            count++;
        }
    }

    private static long getHash(String s) {
        int prime = 100_000_000_9;
        long hash = 0;
        int base = 26;
        for (int i = 0; i < s.length(); i++) {
            hash = (hash + ((s.charAt(i) - 'a' + 1) * ((long) Math.pow(base, s.length()-1-i))) % prime) % prime;
        }
        return hash;
    }

    private static boolean containExact(List<int[]> intervals, String s, int start, int end) {
        int[] curInterval = new int[]{start, end};
        return intervals.stream().anyMatch(interval -> areIdentical(s, curInterval, interval));
    }

    private static boolean areIdentical(String s, int[] interval1, int[] interval2) {
        if (interval1[1] - interval1[0] != interval2[1] - interval2[0]) return false;
        for (int i = interval1[0], k = interval2[0]; i <= interval1[1]; i++, k++){
            if (s.charAt(i) != s.charAt(k)) return false;
        }
        return true;
    }
}
