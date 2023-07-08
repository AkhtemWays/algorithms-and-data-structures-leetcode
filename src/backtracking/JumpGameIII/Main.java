package backtracking.JumpGameIII;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        int[] arr = {4,2,3,0,3,1,2};
        int[] arr2 = {4,2,3,0,3,1,2};
        int[] arr3 = {3,0,2,1,2};
        System.out.println(canReach(arr, 5));
        System.out.println(canReach(arr2, 0));
        System.out.println(canReach(arr3, 2));
    }

    public static boolean canReach(int[] arr, int start) {
        return dfs(start, arr, new HashSet<>());
    }

    private static boolean dfs(int index, int[] arr, Set<Integer> visited) {
        if (arr[index] == 0) return true;
        if (visited.contains(index)) return false;

        visited.add(index);
        int forward = index + arr[index];
        int behind = index - arr[index];
        if (forward < arr.length) {
            if (dfs(forward, arr, visited)) return true;
        }
        if (behind >= 0) {
            if (dfs(behind, arr, visited)) return true;
        }
        return false;
    }
}
