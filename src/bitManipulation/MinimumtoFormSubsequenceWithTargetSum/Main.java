package bitManipulation.MinimumtoFormSubsequenceWithTargetSum;

import java.util.*;

public class Main {
    private static void test1() {
        List<Integer> nums = new ArrayList<>(List.of(1,2,8));
        System.out.println(minOperations(nums, 7));
    }
    private static void test2() {
        List<Integer> nums = new ArrayList<>(List.of(1,32,1,2));
        System.out.println(minOperations(nums, 12));
    }
    private static void test3() {
        List<Integer> nums = new ArrayList<>(List.of(1,32,1));
        System.out.println(minOperations(nums, 35));
    }
    private static void test4() {
        List<Integer> nums = new ArrayList<>(List.of(16,16,4));
        System.out.println(minOperations(nums, 3));
    }
    public static void main(String[] args) {
        test1(); // 1
        test2(); // 2
        test3(); // -1
        test4(); // 2
    }

    public static int minOperations(List<Integer> nums, int target) {
        long sum = 0;
        HashMap<Integer, Integer> state = new HashMap<>();
        for (int num : nums) {
            sum += num;
            state.put(num, state.getOrDefault(num, 0) + 1);
        }
        if (sum < target) return -1;

        List<Integer> targetState = new ArrayList<>();
        for (int i = 0; i < 31; i++) {
            if (((target >> i) & 1) == 1) {
                int num = (int) Math.pow(2, i);
                if (state.containsKey(num)) {
                    state.put(num, state.get(num) - 1);
                    if (state.get(num) == 0) state.remove(num);
                } else {
                    targetState.add(num);
                }
            }
        }

        int answer = 0;
        for (int num : targetState) {
            if (hasSubsequence(num, state)) {
                subtractFromState(num, state);
            } else {
                answer += operate(num, state);
            }
        }
        return answer;
    }

    private static int operate(int num, HashMap<Integer, Integer> state) {
        int k = -1;
        for (int i = 1; i < 32; i++) {
            if (state.containsKey(num << i)) {
                k = i;
                break;
            }
        }

        if (k == -1) throw new RuntimeException("impossible");

        int target = num << k;
        while (target != num) {
            state.put(target, state.get(target) - 1);
            if (state.get(target) == 0) state.remove(target);

            state.put(target >> 1, state.getOrDefault(target >> 1, 0) + 2);
            target >>= 1;
        }
        state.put(num, state.get(num) - 1);
        return k;
    }

    private static boolean hasSubsequence(int num, HashMap<Integer, Integer> state) {
        int div = num;
        while (div > 0 && num > 0) {
            if (state.containsKey(div)) {
                int necessary = num / div;
                int present = state.get(div);
                num -= div * Math.min(necessary, present);
            }
            div >>= 1;
        }
        return num == 0;
    }

    private static void subtractFromState(int num, HashMap<Integer, Integer> state) {
        int div = num;
        while (num > 0) {
            if (state.containsKey(div)) {
                int necessary = num / div;
                int present = state.get(div);
                num -= div * Math.min(necessary, present);
                state.put(div, state.get(div) - Math.min(present, necessary));
                if (state.get(div) == 0) state.remove(div);
            }
            div >>= 1;
        }
    }
}

