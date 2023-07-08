package backtracking.NumberofWaysofCuttingPizza;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        String[] pizza1 = {"A..","AAA","..."};
        String[] pizza2 = {"A..","AA.","..."};
        String[] pizza3 = {"A..","A..","..."};
        String[] pizza4 = {"..A.A.AAA...AAAAAA.AA..A..A.A......A.AAA.AAAAAA.AA","A.AA.A.....AA..AA.AA.A....AAA.A........AAAAA.A.AA.","A..AA.AAA..AAAAAAAA..AA...A..A...A..AAA...AAAA..AA","....A.A.AA.AA.AA...A.AA.AAA...A....AA.......A..AA.","AAA....AA.A.A.AAA...A..A....A..AAAA...A.A.A.AAAA..","....AA..A.AA..A.A...A.A..AAAA..AAAA.A.AA..AAA...AA","A..A.AA.AA.A.A.AA..A.A..A.A.AAA....AAAAA.A.AA..A.A",".AA.A...AAAAA.A..A....A...A.AAAA.AA..A.AA.AAAA.AA.","A.AA.AAAA.....AA..AAA..AAAAAAA...AA.A..A.AAAAA.A..","A.A...A.A...A..A...A.AAAA.A..A....A..AA.AAA.AA.AA.",".A.A.A....AAA..AAA...A.AA..AAAAAAA.....AA....A....","..AAAAAA..A..A...AA.A..A.AA......A.AA....A.A.AAAA.","...A.AA.AAA.AA....A..AAAA...A..AAA.AAAA.A.....AA.A","A.AAAAA..A...AAAAAAAA.AAA.....A.AAA.AA.A..A.A.A...","A.A.AA...A.A.AA...A.AA.AA....AA...AA.A..A.AA....AA","AA.A..A.AA..AAAAA...A..AAAAA.AA..AA.AA.A..AAAAA..A","...AA....AAAA.A...AA....AAAAA.A.AAAA.A.AA..AA..AAA","..AAAA..AA..A.AA.A.A.AA...A...AAAAAAA..A.AAA..AA.A","AA....AA....AA.A......AAA...A...A.AA.A.AA.A.A.AA.A","A.AAAA..AA..A..AAA.AAA.A....AAA.....A..A.AA.A.A...","..AA...AAAAA.A.A......AA...A..AAA.AA..A.A.A.AA..A.",".......AA..AA.AAA.A....A...A.AA..A.A..AAAAAAA.AA.A",".A.AAA.AA..A.A.A.A.A.AA...AAAA.A.A.AA..A...A.AAA..","A..AAAAA.A..A..A.A..AA..A...AAA.AA.A.A.AAA..A.AA..","A.AAA.A.AAAAA....AA..A.AAA.A..AA...AA..A.A.A.AA.AA",".A..AAAA.A.A.A.A.......AAAA.AA...AA..AAA..A...A.AA","A.A.A.A..A...AA..A.AAA..AAAAA.AA.A.A.A..AA.A.A....","A..A..A.A.AA.A....A...A......A.AA.AAA..A.AA...AA..",".....A..A...A.A...A..A.AA.A...AA..AAA...AA..A.AAA.","A...AA..A..AA.A.A.AAA..AA..AAA...AAA..AAA.AAAAA...","AA...AAA.AAA...AAAA..A...A..A...AA...A..AA.A...A..","A.AA..AAAA.AA.AAA.A.AA.A..AAAAA.A...A.A...A.AA....","A.......AA....AA..AAA.AAAAAAA.A.AA..A.A.AA....AA..",".A.A...AA..AA...AA.AAAA.....A..A..A.AA.A.AA...A.AA","..AA.AA.AA..A...AA.AA.AAAAAA.....A.AA..AA......A..","AAA..AA...A....A....AA.AA.AA.A.A.A..AA.AA..AAA.AAA","..AAA.AAA.A.AA.....AAA.A.AA.AAAAA..AA..AA.........",".AA..A......A.A.AAA.AAAA...A.AAAA...AAA.AAAA.....A","AAAAAAA.AA..A....AAAA.A..AA.A....AA.A...A.A....A..",".A.A.AA..A.AA.....A.A...A.A..A...AAA..A..AA..A.AAA","AAAA....A...A.AA..AAA..A.AAA..AA.........AA.AAA.A.","......AAAA..A.AAA.A..AAA...AAAAA...A.AA..A.A.AA.A.","AA......A.AAAAAAAA..A.AAA...A.A....A.AAA.AA.A.AAA.",".A.A....A.AAA..A..AA........A.AAAA.AAA.AA....A..AA",".AA.A...AA.AAA.A....A.A...A........A.AAA......A...","..AAA....A.A...A.AA..AAA.AAAAA....AAAAA..AA.AAAA..","..A.AAA.AA..A.AA.A...A.AA....AAA.A.....AAA...A...A",".AA.AA...A....A.AA.A..A..AAA.A.A.AA.......A.A...A.","...A...A.AA.A..AAAAA...AA..A.A..AAA.AA...AA...A.A.","..AAA..A.A..A..A..AA..AA...A..AA.AAAAA.A....A..A.A"};
        System.out.println(main.ways(pizza1, 3));
        System.out.println(main.ways(pizza2, 3));
        System.out.println(main.ways(pizza3, 1));
        System.out.println(main.ways(pizza4, 8));
    }

    Set<String> visited = new HashSet<>();
    public int ways(String[] pizza, int k) {
        int totalApplesLeft = calculateTotalApples(pizza);
        return dfs(pizza, k, totalApplesLeft);
    }

    private int dfs(String[] pizza, int k, int totalApplesLeft) {
        String serialized = serialize(pizza, k, totalApplesLeft);
        if (visited.contains(serialized)) {
            return 0;
        }
        visited.add(serialized);

        int count = 0;

        if (k == 1 && totalApplesLeft >= 1) {
            return 1;
        }

        int numRows = pizza.length;
        for (int i = 0; i < numRows; i++) {
            String[] bottomPiece = getBottomPieceOfPizza(i+1, pizza);
            int applesInCut = calculateTotalApples(bottomPiece);
            if (totalApplesLeft > applesInCut && applesInCut >= k-1) {
                int n = dfs(bottomPiece, k-1, applesInCut);
                String s = serialize(bottomPiece, k-1, applesInCut);
                visited.add(s);
                count += n;
            }
        }

        for (int j = 0; j < pizza[0].length(); j++) {
            String[] rightPiece = getRightPiece(j+1, pizza);
            int applesInCut = calculateTotalApples(rightPiece);
            if (totalApplesLeft > applesInCut && applesInCut >= k-1) {
                int n = dfs(rightPiece, k-1, applesInCut);
                String s = serialize(rightPiece, k-1, applesInCut);
                visited.add(s);
                count += n;
            }
        }
        return count;
    }

    private String[] getRightPiece(int start, String[] pizza) {
        String[] newPiece = new String[pizza.length];
        for (int i = 0; i < pizza.length; i++) {
            newPiece[i] = pizza[i].substring(start);
        }
        return newPiece;
    }

    private String[] getBottomPieceOfPizza(int start, String[] pizza) {
        String[] newPiece = new String[pizza.length-start];
        for (int i = start, k = 0; i < pizza.length; i++, k++) {
            newPiece[k] = String.valueOf(pizza[i]);
        }
        return newPiece;
    }

    private int calculateTotalApples(String[] pizza) {
        int count = 0;
        for (int i = 0; i < pizza.length; i++) {
            for (int j = 0; j < pizza[i].length(); j++) {
                if (pizza[i].charAt(j) == 'A') count++;
            }
        }
        return count;
    }

    private String serialize(String[] pizza, int k, int totalApplesLeft) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pizza.length; i++) {
            sb.append(pizza[i]).append(",");
        }
        sb.append("|").append(k).append("|").append(totalApplesLeft);
        return sb.toString();
    }
}
