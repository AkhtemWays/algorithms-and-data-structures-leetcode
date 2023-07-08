package backtracking.safariLine;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Main {
    private static List<String[]> getTestData() {

        String[] str1 = {"                      ",      // true
                         "   +-------+          ",
                         "   |      +++---+     ",
                         "X--+      +-+   X     "};
        String[] str2 = {"                     ",       // true
                         "    +-------------+  ",
                         "    |             |  ",
                         " X--+      X------+  ",
                         "                     "};
        String[] str3 = {"                    ",
                         "     +--------+     ",        // true
                         "  X--+        +--+  ",
                         "                 |  ",
                         "                 X  ",
                         "                    "};
        String[] str4 = {"     ",                       // true
                         "  X  ",
                         "  |  ",
                         "  |  ",
                         "  X  "};
        String[] str5 = {"           ",                 // true
                         "X---------X",
                         "           ",
                         "           "};
        String[] str6 = {"X-----|----X"};               // false
        String[] str7 = {" X  ",                        // false
                         " |  ",
                         " +  ",
                         " X  "};
        String[] str8 = {"   |--------+    ",           // false
                         "X---        ---+ ",
                         "               | ",
                         "               X "};
        String[] str9 = {"              ",              // false
                         "   +------    ",
                         "   |          ",
                         "X--+      X   ",
                         "              "};
        String[] str10 = {"      +------+",             // false
                          "      |      |",
                          "X-----+------+",
                          "      |       ",
                          "      X       "};
        return List.of(str1, str2, str3, str4, str5, str6, str7, str8, str9, str10);
    }

    private static char[][] makeGrid(String[] grid) {
        char[][] result = new char[grid.length][grid[0].length()];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = grid[i].charAt(j);
            }
        }
        return result;
    }

    private static Point findX(char[][] grid, Point exclude) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == exclude.x && j == exclude.y) continue;
                if (grid[i][j] == 'X') return new Point(i, j);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        List<String[]> testData = getTestData();
        for (int i = 0; i < testData.size(); i++) {
            System.out.println(i+1 + ": " + line(makeGrid(testData.get(i))));
        }

//        testData.forEach(grid -> line(makeGrid(grid)));
    }

    public static boolean line(final char[][] grid) {
        Point start = grid[0][0] == 'X' ? new Point(0, 0) : findX(grid, new Point(0, 0));
        if (start == null) return false;
        Point finish = findX(grid, start);
        if (finish == null) return false;
        Direction directionStart = getInitialDirection(start, grid);
        Direction directionFinish = getInitialDirection(finish, grid);
        boolean finishFoundStart = traverse(grid, directionStart, start);
        boolean finishFoundFinish = traverse(grid, directionFinish, finish);

        return finishFoundStart || finishFoundFinish;
    }

    private static boolean traverse(char[][] grid, Direction direction, Point start) {
        boolean finishFound = false;
        Set<Point> visited = new HashSet<>();
        Queue<Corner> q = new LinkedList<>();
        q.add(new Corner(direction, start));
        while (!q.isEmpty()) {
            Corner corner = q.poll();
            Point position = corner.position;
            visited.add(position);
            if (corner.direction == Direction.VERTICAL) {
                int i = position.x;
                while (--i >= 0) {
                    if (grid[i][position.y] == '|') continue;
                    else if (grid[i][position.y] == ' ' || grid[i][position.y] == '-') break;
                    else if (grid[i][position.y] == 'X') {
                        if (!visited.contains(new Point(i, position.y))) {
                            finishFound = true;
                            break;
                        } else {
                            return false;
                        }
                    }
                    else if (grid[i][position.y] == '+') {
                        if (!visited.contains(new Point(i, position.y))) {
                            q.add(new Corner(Direction.HORIZONTAL, new Point(i, position.y)));
                            break;
                        }
                    }
                }
                i = position.x;
                while (++i < grid.length) {
                    if (grid[i][position.y] == '|') continue;
                    else if (grid[i][position.y] == ' ' || grid[i][position.y] == '-') break;
                    else if (grid[i][position.y] == 'X') {
                        if (!visited.contains(new Point(i, position.y))) {
                            finishFound = true;
                            break;
                        } else {
                            return false;
                        }
                    }
                    else if (grid[i][position.y] == '+') {
                        if (!visited.contains(new Point(i, position.y))) {
                            q.add(new Corner(Direction.HORIZONTAL, new Point(i, position.y)));
                            break;
                        }
                    }
                }
            } else {
                int j = position.y;
                while (--j >= 0) {
                    if (grid[position.x][j] == '-') continue;
                    else if (grid[position.x][j] == ' ' || grid[position.x][j] == '|') break;
                    else if (grid[position.x][j] == 'X') {
                        if (!visited.contains(new Point(position.x, j))) {
                            finishFound = true;
                            break;
                        } else {
                            return false;
                        }
                    }
                    else if (grid[position.x][j] == '+') {
                        if (!visited.contains(new Point(position.x, j))) {
                            q.add(new Corner(Direction.VERTICAL, new Point(position.x, j)));
                            break;
                        }
                    }
                }
                j = position.y;
                while (++j < grid[0].length) {
                    if (grid[position.x][j] == '-') continue;
                    else if (grid[position.x][j] == ' ' || grid[position.x][j] == '|') break;
                    else if (grid[position.x][j] == 'X') {
                        if (!visited.contains(new Point(position.x, j))) {
                            finishFound = true;
                            break;
                        } else {
                            return false;
                        }
                    }
                    else if (grid[position.x][j] == '+') {
                        if (!visited.contains(new Point(position.x, j))) {
                            q.add(new Corner(Direction.VERTICAL, new Point(position.x, j)));
                            break;
                        }
                    }
                }
            }
        }

        return finishFound;
    }

    private static Direction getInitialDirection(Point point, char[][] grid) {
        if (point.x+1 < grid.length && (grid[point.x+1][point.y] == '|' || grid[point.x+1][point.y] == '+')) return Direction.VERTICAL;
        if (point.x-1 >= 0 && (grid[point.x-1][point.y] == '|' || grid[point.x-1][point.y] == '+')) return Direction.VERTICAL;
        if (point.y-1 >= 0 && (grid[point.x][point.y-1] == '-' || grid[point.x][point.y-1] == '+')) return Direction.HORIZONTAL;
        if (point.y+1 < grid[0].length && (grid[point.x][point.y+1] == '-' || grid[point.x][point.y+1] == '+')) return Direction.HORIZONTAL;
        return null;
    }

    private enum Direction {
        HORIZONTAL, VERTICAL
    }

    private static class Corner {
        Direction direction;
        Point position;

        Corner(Direction direction, Point position) {
            this.direction = direction;
            this.position = position;
        }
    }
}
