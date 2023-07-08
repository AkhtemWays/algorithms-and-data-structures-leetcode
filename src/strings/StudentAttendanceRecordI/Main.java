package strings.StudentAttendanceRecordI;

public class Main {
    public static void main(String[] args) {
        System.out.println(checkRecord("PPALLP"));
        System.out.println(checkRecord("PPALLL"));
        System.out.println(checkRecord("ALLAPPL"));
    }

    public static boolean checkRecord(String s) {
        return checkAbsence(s) && checkLateness(s);
    }

    private static boolean checkAbsence(String s) {
        int count = 0;
        for (char ch : s.toCharArray()) {
            if (ch == 'A') {
                count++;
            }
            if (count > 1) return false;
        }
        return true;
    }

    private static boolean checkLateness(String s) {
        int count = 0;
        for (char ch : s.toCharArray()) {
            if (ch == 'L') {
                count++;
            } else {
                count = 0;
            }
            if (count >= 3) return false;
        }
        return true;
    }
}
