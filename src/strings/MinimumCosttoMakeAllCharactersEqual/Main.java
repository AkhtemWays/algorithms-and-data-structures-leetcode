package strings.MinimumCosttoMakeAllCharactersEqual;

public class Main {
    public static void main(String[] args) {
        System.out.println(minimumCost("0011")); // 2
        System.out.println(minimumCost("010101")); // 9
        System.out.println(minimumCost("00000011")); // 2
        System.out.println(minimumCost("0101010")); // 12
        System.out.println(minimumCost("1010101")); // 12
    }

    public static long minimumCost(String s) {
        if (s.length() % 2 == 0) {
            long answer1 = getCost(s.substring(0, s.length() / 2), '0') + getCost(new StringBuilder(s.substring(s.length()/2)).reverse().toString(), '0');
            long answer2 = getCost(s.substring(0, s.length() / 2), '1') + getCost(new StringBuilder(s.substring(s.length()/2)).reverse().toString(), '1');
            return Math.min(answer2, answer1);
        } else {
            long answer1 = Math.min(getCost(s.substring(0, s.length()/2), '0') + getCost(new StringBuilder(s.substring(s.length()/2)).reverse().toString(), '0'), getCost(s.substring(0, s.length()/2+1), '0') + getCost(new StringBuilder(s.substring(s.length()/2+1)).reverse().toString(), '0'));
            long answer2 = Math.min(getCost(s.substring(0, s.length()/2), '1') + getCost(new StringBuilder(s.substring(s.length()/2)).reverse().toString(), '1'), getCost(s.substring(0, s.length()/2+1), '1') + getCost(new StringBuilder(s.substring(s.length()/2+1)).reverse().toString(), '1'));
            return Math.min(answer2, answer1);
        }
    }

    private static long getCost(String s, char convertionValue) {
        long answer = 0;
        int left = -1, right = 0;
        while (right < s.length()) {
            if (s.charAt(right) != convertionValue) {
                answer += right - left - 1;
                left = right;
            }
            right++;
        }
        return answer + right - left -1;
    }
}
