package graphs.findTheTownJudge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        int[][] trust = {{1,3},{1,4},{2,3},{2,4},{4,3}};
        System.out.println(findJudge(trust.length + 1, trust));
    }

    public static int findJudge(int n, int[][] trust) {
        if (n - trust.length != 1) return -1;
        if (trust.length == 0 && n == 1) return 1;

        final Map<Integer, Set<Integer>> friendsRelation = new HashMap<>();
        for (int i = 0; i < trust.length; i++) {
            if (friendsRelation.containsKey(trust[i][0])) {
                Set<Integer> friends = friendsRelation.get(trust[i][0]);
                friends.add(trust[i][1]);
            } else {
                Set<Integer> friends = new HashSet<>();
                friends.add(trust[i][1]);
                friendsRelation.put(trust[i][0], friends);
            }
        }
        Set<Integer> res = friendsRelation.get(trust[0][0]);
        for (Set<Integer> friends : friendsRelation.values()) {
            res.retainAll(friends);
        }
        return res.stream().findAny().orElse(-1);
    }
}
