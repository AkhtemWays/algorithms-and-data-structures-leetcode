package hashmap.alphabeticAnagrams;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }



    public BigInteger listPosition(String word) {
        List<String> l = new ArrayList<>();
        dfs("", word, l);
        Collections.sort(l);
        return null;
    }

    private static void dfs(String str, String word, List<String> l) {

    }
}
