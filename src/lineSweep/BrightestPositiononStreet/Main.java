package lineSweep.BrightestPositiononStreet;

import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        int[][] intervals = {{-3,2},{1,2},{3,3}};
        int[][] intervals2 = {{1,0},{0,1}};
        int[][] intervals3 = {{1,2}};
        int[][] intervals4 = {{-10,2},{0,3},{5,1}};
        int[][] intervals5 = {{1,1},{2,4},{-1,0},{-3,5},{1,2}};
        System.out.println(brightestPosition(intervals));
        System.out.println(brightestPosition(intervals2));
        System.out.println(brightestPosition(intervals3));
        System.out.println(brightestPosition(intervals4));
        System.out.println(brightestPosition(intervals5));
    }

    public static int brightestPosition(int[][] lights) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] light : lights) {
            int start = light[0]-light[1];
            int end = light[0]+light[1]+1;
            map.put(start, map.getOrDefault(start, 0) + 1);
            map.put(end, map.getOrDefault(end, 0) - 1);
        }
        int curFrequency = 0;
        int frequency = 0;
        int answer = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            curFrequency += entry.getValue();
            if (curFrequency > frequency) {
                frequency = curFrequency;
                answer = entry.getKey();
            }
        }
        return answer;
    }
}
