package greedy.SplitaStringinBalancedStrings;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
    }

    public int balancedStringSplit(String s) {
        int l = 0;
        int r = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'R') {
                r++;
            } else {
                l++;
            }
            if (l == r) count++;
        }
        return count;
    }
}
