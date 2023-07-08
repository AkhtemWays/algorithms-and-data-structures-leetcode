package strings.LengthofLastWord;

public class Main {
    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("Hello World"));
    }

    public static int lengthOfLastWord(String s) {
        int index = -1;
        for (int i = s.length()-1; i >= 0; i--) {
            if (Character.isLetter(s.charAt(i))) {
                index = i;
                break;
            }
        }
        int count = 0;
        if (index == -1) return 0;
        for (int i = index; i >= 0; i--, count++) {
            if (s.charAt(i) == ' ') {
                return count;
            }
        }
        return count;
    }
}
