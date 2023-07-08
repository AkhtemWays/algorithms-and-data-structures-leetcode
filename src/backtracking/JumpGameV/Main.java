package backtracking.JumpGameV;

public class Main {
    public static void main(String[] args) {
        int[] arr = {6,4,14,6,8,13,9,7,10,6,12};
        int[] arr2 = {3,3,3,3,3};
        int[] arr3 = {7,6,5,4,3,2,1};
        int[] arr4 = {22,29,52,97,29,75,78,2,92,70,90,12,43,17,97,18,58,100,41,32};
        System.out.println(maxJumps(arr, 2));
        System.out.println(maxJumps(arr2, 3));
        System.out.println(maxJumps(arr3, 1));
        System.out.println(maxJumps(arr4, 17));
    }

    public static int maxJumps(int[] arr, int d) {
        int maxJumps = 0;

        int[] memo = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            maxJumps = Math.max(maxJumps, dfs(i, arr, d, memo));
        }
        return maxJumps;
    }

    private static int dfs(int index, int[] arr, int d, int[] memo) {
        if (memo[index] != 0) return memo[index];
        int leftMaxJumps = traverseLeft(index, arr, d, memo);
        int rightMaxJumps = traverseRight(index, arr, d, memo);
        int res = Math.max(leftMaxJumps, rightMaxJumps);
        memo[index] = res;
        return res;
    }

    private static int traverseLeft(int index, int[] arr, int d, int[] memo) {
        int maxJumps = 1;
        for (int i = index - 1; i >= Math.max(index - d, 0); i--) {
            if (arr[i] < arr[index]) {
                maxJumps = Math.max(dfs(i, arr, d, memo) + 1, maxJumps);
            } else {
                break;
            }
        }

        return maxJumps;
    }

    private static int traverseRight(int index, int[] arr, int d, int[] memo) {
        int maxJumps = 1;
        for (int i = index + 1; i <= Math.min(index + d, arr.length-1); i++) {
            if (arr[i] < arr[index]) {
                maxJumps = Math.max(dfs(i, arr, d, memo) + 1, maxJumps);
            }
            else {
                break;
            }
        }

        return maxJumps;
    }
}
