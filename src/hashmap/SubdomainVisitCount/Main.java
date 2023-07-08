package hashmap.SubdomainVisitCount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String[] domains = {"9001 discuss.leetcode.com"};
        String[] domains2 = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        System.out.println(subdomainVisits(domains));
        System.out.println(subdomainVisits(domains2));
    }

    public static List<String> subdomainVisits(String[] cpdomains) {
        HashMap<String, Integer> counts = new HashMap<>();
        for (String domainWithCount : cpdomains) {
            String[] domainAndCount = domainWithCount.split(" ");
            String domain = domainAndCount[1];
            int count = Integer.parseInt(domainAndCount[0]);
            StringBuilder sb = new StringBuilder();
            for (int i = domain.length()-1; i >= 0; i--) {
                char ch = domain.charAt(i);
                if (ch == '.') {
                    String reversed = sb.reverse().toString();
                    counts.put(reversed, counts.getOrDefault(reversed, 0) + count);
                    sb.reverse();
                }
                sb.append(ch);
            }
            String reversed = sb.reverse().toString();
            counts.put(reversed, counts.getOrDefault(reversed, 0) + count);
        }

        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            res.add(entry.getValue() + " " + entry.getKey());
        }
        return res;
    }
}
