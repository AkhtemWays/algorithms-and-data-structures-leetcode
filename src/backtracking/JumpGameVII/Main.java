package backtracking.JumpGameVII;

public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) sb.append("0");
        System.out.println(canReach("011010", 2, 3));
        System.out.println(canReach("01101110", 2, 3));
        System.out.println(canReach(sb.toString(), 1, 49999));
        System.out.println(canReach(sb.toString(), 1, 99999));
        sb = new StringBuilder();
        for (int i = 0; i < 50000; i++) sb.append('0');
        for (int i = 0; i < 50000; i++) sb.append('1');
        sb.append('0');
        System.out.println(canReach(sb.toString(), 1, 49999));
        System.out.println(canReach(sb.toString(), 1, 99999));
    }

    public static boolean canReach(String s, int minJump, int maxJump) {
        if (s.charAt(s.length()-1) == '1') return false;
        boolean[] visited = new boolean[s.length()];
        return canReach(s, minJump, maxJump, 0, visited);
    }

    private static boolean canReach(String s, int minJump, int maxJump, int i, boolean[] visited) {
        if (s.length()-1 >= i + minJump && s.length()-1 <= i+maxJump) return true;
        if (visited[i]) return false;
        for (int jump = Math.min(i + maxJump, s.length()-1); jump >= i + minJump; jump--) {
            if (s.charAt(jump) == '0' && canReach(s, minJump, maxJump, jump, visited)) return true;
            visited[jump] = true;
        }
        visited[i] = true;
        return false;
    }
}
