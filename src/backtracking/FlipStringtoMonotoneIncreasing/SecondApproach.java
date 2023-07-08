package backtracking.FlipStringtoMonotoneIncreasing;

public class SecondApproach {
    public static void main(String[] args) {
        System.out.println(minFlipsMonoIncr("00110")); // 1
        System.out.println(minFlipsMonoIncr("010110")); // 2
        System.out.println(minFlipsMonoIncr("00011000")); // 2
        System.out.println(minFlipsMonoIncr("0011000010")); // 3
    }

    public static int minFlipsMonoIncr(String s) {
        if (s.length() == 0) return 0;
        int left = s.charAt(0) == '0' ? 0 : 1;
        int right = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '0') right++;
        }
        int answer = Integer.MAX_VALUE;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                left++;
            } else {
                right--;
            }
            answer = Math.min(answer, left + right);
        }
        return answer;
    }
}
