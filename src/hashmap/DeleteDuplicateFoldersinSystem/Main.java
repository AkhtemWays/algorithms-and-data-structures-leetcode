package hashmap.DeleteDuplicateFoldersinSystem;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<List<String>> paths = new ArrayList<>(List.of(
                        new ArrayList<>(List.of("a")),
                        new ArrayList<>(List.of("c")),
                        new ArrayList<>(List.of("a","b")),
                        new ArrayList<>(List.of("c","b")),
                        new ArrayList<>(List.of("a","b","x")),
                        new ArrayList<>(List.of("a","b","x","y")),
                        new ArrayList<>(List.of("w")),
                        new ArrayList<>(List.of("w","y"))
        ));
        List<List<String>> paths2 = new ArrayList<>(List.of(
                new ArrayList<>(List.of("a")),
                new ArrayList<>(List.of("c")),
                new ArrayList<>(List.of("d")),
                new ArrayList<>(List.of("a","b")),
                new ArrayList<>(List.of("c", "b")),
                new ArrayList<>(List.of("d", "a"))
        ));
        List<List<String>> paths3 = new ArrayList<>(List.of(
                new ArrayList<>(List.of("a", "b")),
                new ArrayList<>(List.of("c", "d")),
                new ArrayList<>(List.of("c")),
                new ArrayList<>(List.of("a"))
        ));
        System.out.println(deleteDuplicateFolder(paths)); // [[c], [c, b], [a], [a, b]]
        System.out.println(deleteDuplicateFolder(paths2)); // [["d"],["d","a"]]
        System.out.println(deleteDuplicateFolder(paths3));
    }

    public static List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        paths.sort((a,b) -> a.size() - b.size());
        Set<Integer> excludedIndices = getExcludedIndices(paths);
        HashMap<String, List<String>> prefixMap = new HashMap<>();
        prefixMap.put("/", new ArrayList<>());
        for (int k = 0; k < paths.size(); k++) {
            if (excludedIndices.contains(k)) {
                continue;
            }
            List<String> path = paths.get(k);
            String str = String.join("/", path);
            StringBuilder leftPart = new StringBuilder(str);
            StringBuilder rightPart = new StringBuilder();
            for (int i = str.length() - 1; i >= 0; i--) {
                char ch = leftPart.charAt(i);
                leftPart.deleteCharAt(i);
                if (ch == '/') {
                    prefixMap.computeIfAbsent(rightPart.reverse().toString(), (key) -> new ArrayList<>()).add(leftPart.toString());
                    rightPart.reverse();
                }
                rightPart.append(ch);
            }
            prefixMap.get("/").add(rightPart.reverse().toString());
        }
        for (Map.Entry<String, List<String>> entry : prefixMap.entrySet().stream().filter(entry -> entry.getValue().size() > 1 && !entry.getKey().equals("/")).collect(Collectors.toList())) {
            List<String> prefixes = entry.getValue();
            for (String prefix : prefixes) {
                String[] split = prefix.split("/");
                if (split.length == 1) {
                    continue;
                }

                String additionPrefix = split[split.length-2];
                StringBuilder rest = new StringBuilder();
                StringBuilder removalPrefix = new StringBuilder(split[split.length-1] + "/" + entry.getKey());
                prefixMap.remove(removalPrefix.toString());
                removalPrefix.reverse();
                removalPrefix.append("/").append(split[split.length-2]);
                prefixMap.remove(removalPrefix.reverse().toString());

                for (int i = split.length-3; i >= 0; i--) {
                    rest.append(split[i]);
                    rest.append("/");
                    String removal = rest.toString() + removalPrefix;
                    prefixMap.remove(removal);
                }
                if (rest.length() != 0) {
                    prefixMap.computeIfAbsent(additionPrefix, (key) -> new ArrayList<>()).add(rest.substring(0, rest.length()-1));
                }
            }
            prefixMap.remove(entry.getKey());
        }
        return computeResult(prefixMap);
    }

    private static List<List<String>> computeResult(HashMap<String, List<String>> prefixMap) {
        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : prefixMap.entrySet().stream().filter(entry -> !entry.getKey().equals("/") && !entry.getValue().isEmpty()).collect(Collectors.toList())) {
            for (String prefix : entry.getValue()) {
                List<String> cur = new ArrayList<>();
                String[] split = prefix.split("/");
                for (int i = 0; i < split.length; i++) {
                    cur.add(split[i]);
                    result.add(List.copyOf(cur));
                }
                cur.add(entry.getKey());
                result.add(List.copyOf(cur));
            }
        }
        return result;
    }

    private static Set<Integer> getExcludedIndices(List<List<String>> paths) {
        Set<Integer> excludedIndices = new HashSet<>();
        HashMap<String, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < paths.size(); i++) {
            List<String> path = paths.get(i);
            StringBuilder current = new StringBuilder();
            for (String directory : path) {
                current.append(directory);
                String str = current.toString();
                if (indexMap.containsKey(str)) {
                    int excludedIndex = indexMap.get(str);
                    excludedIndices.add(excludedIndex);
                }
                current.append("/");
            }
            if (current.length() != 0) {
                indexMap.put(current.substring(0, current.length()-1), i);
            }
        }
        return excludedIndices;
    }
}
