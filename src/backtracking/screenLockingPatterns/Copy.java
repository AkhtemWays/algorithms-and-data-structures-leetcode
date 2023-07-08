package backtracking.screenLockingPatterns;

import java.util.*;

public class Copy {
    static Set<Character> possible = new HashSet<>(
            List.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I')
    );
    private static final Map<Character, Set<Character>> store = getInitialStore();
    private static Map<Character, Set<Character>> getInitialStore() {
        Map<Character, Set<Character>> store = new HashMap<>();

        store.put('A', new HashSet<>(List.of('B', 'D', 'E', 'F', 'H')));
        store.put('B', new HashSet<>(List.of('A', 'C', 'D', 'E', 'F', 'G', 'I')));
        store.put('C', new HashSet<>(List.of('B', 'E', 'F', 'D', 'H')));
        store.put('D', new HashSet<>(List.of('A', 'B', 'C', 'E', 'G', 'H', 'I')));
        store.put('E', new HashSet<>(List.of('A', 'B', 'C', 'D', 'F', 'G', 'H', 'I')));
        store.put('F', new HashSet<>(List.of('A', 'B', 'C', 'E', 'G', 'H', 'I')));
        store.put('G', new HashSet<>(List.of('D', 'B', 'E', 'F', 'H')));
        store.put('H', new HashSet<>(List.of('G', 'D', 'E', 'F', 'I', 'A', 'C')));
        store.put('I', new HashSet<>(List.of('H', 'E', 'F', 'D', 'B')));

        return store;
    }

    private static Map<Character, Map<Character, Character>> getMap() {
        Map<Character, Map<Character, Character>> map = new HashMap<>();

        HashMap<Character, Character> unreachableToMutualA = new HashMap<>();
        unreachableToMutualA.put('C', 'B');
        unreachableToMutualA.put('G', 'D');
        unreachableToMutualA.put('I', 'E');
        map.put('A', unreachableToMutualA);

        HashMap<Character, Character> unreachableToMutualB = new HashMap<>();
        unreachableToMutualB.put('H', 'E');
        map.put('B', unreachableToMutualB);

        HashMap<Character, Character> unreachableToMutualC = new HashMap<>();
        unreachableToMutualC.put('A', 'B');
        unreachableToMutualC.put('G', 'E');
        unreachableToMutualC.put('I', 'F');
        map.put('C', unreachableToMutualC);

        HashMap<Character, Character> unreachableToMutualD = new HashMap<>();
        unreachableToMutualD.put('F', 'E');
        map.put('D', unreachableToMutualD);

        HashMap<Character, Character> unreachableToMutualE = new HashMap<>();
        map.put('E', unreachableToMutualE);

        HashMap<Character, Character> unreachableToMutualF = new HashMap<>();
        unreachableToMutualF.put('D', 'E');
        map.put('F', unreachableToMutualF);

        HashMap<Character, Character> unreachableToMutualG = new HashMap<>();
        unreachableToMutualG.put('A', 'D');
        unreachableToMutualG.put('C', 'E');
        unreachableToMutualG.put('I', 'H');
        map.put('G', unreachableToMutualG);

        HashMap<Character, Character> unreachableToMutualH = new HashMap<>();
        unreachableToMutualH.put('B', 'E');
        map.put('H', unreachableToMutualH);

        HashMap<Character, Character> unreachableToMutualI = new HashMap<>();
        unreachableToMutualI.put('A', 'E');
        unreachableToMutualI.put('C', 'F');
        unreachableToMutualI.put('G', 'H');
        map.put('I', unreachableToMutualI);
        return map;
    }

    private static HashMap<Character, Boolean> getInitialVisited() {
        HashMap<Character, Boolean> visited = new HashMap<>();

        for (char ch : possible) {
            visited.put(ch, false);
        }
        return visited;
    }


    public static int calculateCombinations(char startPosition, int patternLength) {
        if (patternLength <= 0 || patternLength > 9) return 0;

        return dfs(startPosition, patternLength, 1, getInitialVisited());
    }

    private static int dfs(char ch, int patternLength, int patternCounter, HashMap<Character, Boolean> visited) {
        if (visited.get(ch)) return 0;
        if (patternCounter == patternLength) return 1;
        visited.put(ch, true);

        Set<Character> adjacents = store.get(ch);
        int result = 0;
        for (char character : adjacents) {
            result += dfs(character, patternLength, patternCounter + 1, new HashMap<>(visited));
        }
        Set<Character> reachableUnreachable = findReachableUnreachable(visited, ch);
        for (char reachableUnreachablePoint : reachableUnreachable) {
            result += dfs(reachableUnreachablePoint, patternLength, patternCounter + 1, new HashMap<>(visited));
        }
        return result;
    }

    private static Set<Character> findReachableUnreachable(HashMap<Character, Boolean> visited, char from) {
        Map<Character, Map<Character, Character>> map = getMap();
        Set<Character> unreachable = map.get(from).keySet();
        Set<Character> reachableUnreachable = new HashSet<>();
        for (char to : unreachable) {
            char mutualPoint = map.get(from).get(to);
            if (visited.get(mutualPoint)) reachableUnreachable.add(to);
        }
        return reachableUnreachable;
    }
}
