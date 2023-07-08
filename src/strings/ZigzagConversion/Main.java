package strings.ZigzagConversion;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static enum Direction {
        ZIG, ZAG
    }
    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3)); // PAHNAPLSIIGYIR
        System.out.println(convert("PAYPALISHIRING", 4)); // PINALSIGYAHRPI
    }

//    P   A   H   N
//    A P L S I I G
//    Y   I   R

    public static String convert(String s, int numRows) {
        if (numRows == 1) return s;
        Direction direction = Direction.ZIG;
        List<Character>[] res = new List[numRows];
        for (int i = 0; i < numRows; i++) res[i] = new ArrayList<>();

        for (int i = 0, row = 0, col = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (direction == Direction.ZIG) {
                res[row].add(ch);
                if (row == numRows - 1) {
                    direction = Direction.ZAG;
                    row--;
                    col++;
                } else {
                    row++;
                }
            } else {
                res[row].add(ch);
                if (row == 0) {
                    direction = Direction.ZIG;
                    row++;
                } else {
                    row--;
                    col++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].size(); j++) {
                sb.append(res[i].get(j));
            }
        }
        return sb.toString();
    }
}
