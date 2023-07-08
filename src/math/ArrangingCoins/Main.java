package math.ArrangingCoins;

public class Main {
    public static void main(String[] args) {
        System.out.println(arrangeCoins(10));
    }

    public static int arrangeCoins(int n) {
        int coins = 0;
        int stairs = 0;
        for (int coin = 1; coin <= n; coin++) {
            coins += coin;
            stairs++;
            if (coins >= n) {
                int diff = coins - n;
                if (diff == 0) return stairs;

                for (int c = 1; c <= coins; c++) {
                    diff -= c;
                    stairs--;
                    if (diff == 0) return stairs;
                    if (diff < 0) return stairs + 1;
                }
            }
        }
        return 0;
    }
}
