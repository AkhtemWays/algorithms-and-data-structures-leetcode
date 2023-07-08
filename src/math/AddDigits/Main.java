package math.AddDigits;

public class Main {
    public static void main(String[] args) {

    }

    public int addDigits(int num) {
        String str = String.valueOf(num);
        if (str.length() == 1) return num;

        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            res += Character.getNumericValue(str.charAt(i));
        }
        return addDigits(res);
    }
}
