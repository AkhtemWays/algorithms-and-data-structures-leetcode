package backtracking.RestoreIPAddresses;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("25525511135")); // ["255.255.11.135","255.255.111.35"]
        System.out.println(restoreIpAddresses("0000")); // [0.0.0.0]
        System.out.println(restoreIpAddresses("101023")); // ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
    }

    public static List<String> restoreIpAddresses(String s) {
        List<String> answer = new ArrayList<>();
        if (s.length() < 4) return answer;
        dfs(1, "" + s.charAt(0), s, answer, new StringBuilder(), 0);
        return answer;
    }

    private static void dfs(int i, String cur, String s, List<String> answer, StringBuilder ip, int numOctets) {
        int length = s.length();
        if (numOctets > 4 || !validOctet(cur)) return;
        if (i == length) {
            if (numOctets == 3 && cur.length() != 0) {
                ip.append(cur);
                answer.add(ip.toString());
            }
            return;
        }

        int curLength = cur.length();
        ip.append(cur).append('.');
        dfs(i+1, "" + s.charAt(i), s, answer, ip, numOctets + 1);
        ip.delete(Math.max(ip.length()-curLength, 0), ip.length());
        dfs(i+1, cur + s.charAt(i), s, answer, ip, numOctets);
    }

    private static boolean validOctet(String octet) {
        if (octet.charAt(0) == '0') {
            if (octet.length() == 1) return true;
            return false;
        }
        int value = Integer.parseInt(octet);
        return !(value > 255);
    }
}
