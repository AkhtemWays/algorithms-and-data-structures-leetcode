package strings.RobotReturntoOrigin;

public class Main {
    public static void main(String[] args) {

    }

    public static boolean judgeCircle(String moves) {
        int[] pos = new int[2];
        for (char move : moves.toCharArray()) {
            if (move == 'L') {
                pos[0]--;
            } else if (move == 'R') {
                pos[0]++;
            } else if (move == 'U') {
                pos[1]++;
            } else {
                pos[1]--;
            }
        }
        return pos[0] == 0 && pos[1] == 0;
    }
}
