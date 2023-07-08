package strings.DetectCapital;

public class Main {
    public static void main(String[] args) {

    }

    public static boolean detectCapitalUse(String word) {
        if (word.length() == 0) return false;
        if (Character.isUpperCase(word.charAt(0))) {
            if (word.length() == 1) return true;
            if (Character.isUpperCase(word.charAt(1))) {
                for (int i = 2; i < word.length(); i++) {
                    if (Character.isLowerCase(word.charAt(i))) return false;
                }
                return true;
            } else {
                for (int i = 2; i < word.length(); i++) {
                    if (Character.isUpperCase(word.charAt(i))) return false;
                }
                return true;
            }
        } else {
            for (int i = 1; i < word.length(); i++) {
                if (Character.isUpperCase(word.charAt(i))) return false;
            }
            return true;
        }
    }
}
