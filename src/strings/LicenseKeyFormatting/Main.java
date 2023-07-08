package strings.LicenseKeyFormatting;

public class Main {
    public static void main(String[] args) {
        System.out.println(licenseKeyFormatting("5F3Z-2e-9-w", 4));
        System.out.println(licenseKeyFormatting("2-5g-3-J", 2));
        System.out.println(licenseKeyFormatting("--a-a-a-a--", 2));
    }

    public static String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        StringBuilder cur = new StringBuilder();
        for (int i = s.length()-1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch != '-') {
                if (Character.isAlphabetic(ch)) {
                    cur.append(Character.toUpperCase(ch));
                } else {
                    cur.append(ch);
                }
            }
            if (cur.length() == k) {
                sb.append(cur);
                sb.append('-');
                cur = new StringBuilder();
            }
        }
        sb.append(cur);
        String res = sb.reverse().toString();
        return res.isEmpty() || res.charAt(0) != '-' ? res : res.substring(1);
    }
}
