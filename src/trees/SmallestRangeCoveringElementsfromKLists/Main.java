package trees.SmallestRangeCoveringElementsfromKLists;

import javafx.util.Pair;

import java.util.*;

public class Main {

    private static void test2() {
        List<List<Integer>> input = new ArrayList<>(List.of(
                List.of(73,94,94,96),
                List.of(11,13,76,79,90),
                List.of(-2,6,11,12,12,13,15)
        ));
        System.out.println(Arrays.toString(smallestRange(input)));
    }

    private static void test1() {
        List<List<Integer>> input = new ArrayList<>(List.of(
                List.of(4,10,15,24,26), List.of(0,9,12,20), List.of(5,18,22,30)
        ));
        System.out.println(Arrays.toString(smallestRange(input)));
    }

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static class Meta {
        int arrayPosition;
        int elementPosition;
        int value;
        Meta(int arrayPosition, int elementPosition, int value) {
            this.value = value;
            this.arrayPosition = arrayPosition;
            this.elementPosition = elementPosition;
        }
    }

    public static int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<Meta> q = new PriorityQueue<>((a, b) -> a.value - b.value == 0 ? a.arrayPosition - b.arrayPosition : a.value - b.value);
        int k = nums.size();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            int value = nums.get(i).get(0);
            max = Math.max(max, value);
            Meta meta = new Meta(i, 0, value);
            q.add(meta);
        }

        int[] answer = {0, Integer.MAX_VALUE};
        while (true) {
            Meta min = q.poll();
            int[] range = {min.value, max};
            updateAnswer(range, answer);

            if (min.elementPosition + 1 == nums.get(min.arrayPosition).size()) {
                break;
            } else {
                int value = nums.get(min.arrayPosition).get(min.elementPosition + 1);
                Meta meta = new Meta(min.arrayPosition, min.elementPosition + 1, value);
                q.add(meta);
                max = Math.max(max, value);
            }
        }
        return answer;
    }

    private static void updateAnswer(int[] range, int[] answer) {
        if (range[1] - range[0] < answer[1] - answer[0]) {
            answer[0] = range[0];
            answer[1] = range[1];
        }
    }
}
