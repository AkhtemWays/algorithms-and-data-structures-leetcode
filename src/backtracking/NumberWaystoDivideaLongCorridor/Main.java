package backtracking.NumberWaystoDivideaLongCorridor;

public class Main {
    public static void main(String[] args) {
        System.out.println(numberOfWays("SSPPSPS"));
        System.out.println(numberOfWays("SPPSSSSPPS"));
    }

    private static int MOD = 100_000_000_7;
    public static int numberOfWays(String s) {
        int[] meta = getMeta(s);
        int seatsLeft = meta[0];
        int i = meta[1];
        if (i == -1 || seatsLeft % 2 == 1) return 0;

        int count = 1;
        int countP = 1;
        int answer = 1;
        while (seatsLeft > 0) {
            if (s.charAt(i) == 'S') {
                count++;
                seatsLeft--;
            } else if (count == 2) {
                countP++;
            }

            if (count == 3) {
                answer = (answer * countP) % MOD;
                countP = 1;
                count = 1;
            }
            i++;
        }
        return answer;
    }

    private static int[] getMeta(String s) {
        int count = 0;
        int start = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'S') {
                if (start == -1) start = i;
                count++;
            }
        }
        return new int[]{count, start};
    }
}
