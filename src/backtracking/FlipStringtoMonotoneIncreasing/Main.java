package backtracking.FlipStringtoMonotoneIncreasing;

public class Main {
    public static void main(String[] args) {
        System.out.println(minFlipsMonoIncr("00110")); // 1
        System.out.println(minFlipsMonoIncr("010110")); // 2
        System.out.println(minFlipsMonoIncr("00011000")); // 2
        System.out.println(minFlipsMonoIncr("0011000010")); // 3
    }

    public static int minFlipsMonoIncr(String s) {
        int ans = 0, onesCount = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '0') {
                ans = Math.min(onesCount, ans + 1);
            } else {
                ++onesCount;
            }
        }
        return ans;
    }
}
