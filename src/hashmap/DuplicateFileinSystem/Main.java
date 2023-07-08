package hashmap.DuplicateFileinSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String[] paths = {"root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)","root 4.txt(efgh)"};
        System.out.println(findDuplicate(paths));
    }

    public static List<List<String>> findDuplicate(String[] paths) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String path : paths) {
            String[] split = path.split(" ");
            String directory = split[0];
            for (int i = 1; i < split.length; i++) {
                String filenameWithContent = split[i];
                int startContent = filenameWithContent.lastIndexOf('(');
                String content = filenameWithContent.substring(startContent+1, filenameWithContent.length()-1);
                String filename = filenameWithContent.substring(0, startContent);
                map.computeIfAbsent(content, (c) -> new ArrayList<>()).add(directory + "/" + filename);
            }
        }
        return map.values().stream().filter(list -> list.size() > 1).collect(Collectors.toList());
    }
}
