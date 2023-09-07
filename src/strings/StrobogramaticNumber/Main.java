package strings.StrobogramaticNumber;

public class Main {
    public static void main(String[] args) {
        System.out.println(isStrobogrammatic("69"));
        System.out.println(isStrobogrammatic("101"));
    }

    public static boolean isStrobogrammatic(String num) {
        for (char ch : num.toCharArray()) {
            if (ch != '8' && ch != '9' && ch != '6' && ch != '1' && ch != '0') return false;
        }
        StringBuilder sb = new StringBuilder();
        for (char ch : num.toCharArray()) {
            if (ch == '6') {
                sb.append('9');
            } else if (ch == '9') {
                sb.append('6');
            } else {
                sb.append(ch);
            }
        }
        return new StringBuilder(num).reverse().toString().equals(sb.toString());
    }
}
