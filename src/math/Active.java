package math;

public class Active {
    public static void main(String[] args) {
        String s = "Hello World";
        String s2 = "   fly me   to   the moon  ";
        String s3 = "luffy is still joyboy";
        System.out.println(lengthOfLastWord(s));
        System.out.println(lengthOfLastWord(s2));
        System.out.println(lengthOfLastWord(s3));
    }

    public static int lengthOfLastWord(String s) {
        int count = 0;
        int i = s.length()-1;
        while (i >= 0) {
            if (Character.isLetter(s.charAt(i))) {
                count++;
                while (--i >= 0) {
                    if (!Character.isLetter(s.charAt(i))) {
                        break;
                    }
                    count++;
                }
                break;
            }
            i--;
        }
        return count;
    }
}
