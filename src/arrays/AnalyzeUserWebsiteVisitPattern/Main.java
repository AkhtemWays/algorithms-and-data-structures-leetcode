package arrays.AnalyzeUserWebsiteVisitPattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

    }

    public static List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        HashMap<String, List<String>> userVisits = new HashMap<>();
        for (int i = 0; i < username.length; i++) {
            if (userVisits.containsKey(username[i])) {
                userVisits.get(username[i]).add(website[i]);
            } else {
                ArrayList<String> l = new ArrayList<>();
                l.add(website[i]);
                userVisits.put(username[i], l);
            }
        }

        HashMap<String, Integer> websiteCounts = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : userVisits.entrySet().stream().filter(e -> e.getValue().size() > 2).collect(Collectors.toList())) {
            List<String> l = entry.getValue();
            for (int i = 0; i < l.size() - 2; i++) {
                for (int j = i + 1; j < l.size() - 1; j++) {
                    for (int k = j + 1; k < l.size(); k++) {
                        String s = l.get(i) + "_" + l.get(j) + "_" + l.get(k);
                        websiteCounts.put(s, websiteCounts.getOrDefault(s, 0) + 1);
                    }
                }
            }
        }

        String maxString = "";
        int count = -1;
        for (Map.Entry<String, Integer> entry : websiteCounts.entrySet()) {
            if (entry.getValue() > count) {
                maxString = entry.getKey();
                count = entry.getValue();
            }
        }
        String[] res = maxString.split("_");
        return List.of(res[0], res[1], res[2]);
    }
}
