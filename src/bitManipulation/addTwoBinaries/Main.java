package bitManipulation.addTwoBinaries;

public class Main {
    public static void main(String[] args) {
        System.out.println(addBinary("11", "1"));
    }

    public static String addBinary(String a, String b) {
        int remainder = 0;
        StringBuilder res = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        while (i >= 0 || j >= 0) {
            int bit1 = i >= 0 && i < a.length()? Character.getNumericValue(a.charAt(i)) : 0;
            int bit2 = j >= 0 && j < b.length() ? Character.getNumericValue(b.charAt(j)) : 0;
            if ((bit1 & bit2) == 1) {
                res.append(remainder);
                remainder = 1;
                i--;
                j--;
                continue;
            }
            if ((bit1 ^ bit2) == 1) {
                res.append(remainder == 1 ? 0 : 1);
                i--;
                j--;
            } else {
                res.append(remainder);
                remainder = 0;
                i--;
                j--;
            }
        }
        if (remainder != 0) res.append(remainder);
        return res.reverse().toString();
    }
}
