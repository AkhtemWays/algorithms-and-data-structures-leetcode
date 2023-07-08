package trees.RobotCollisions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        test1(); // [2,17,9,15,10]
        test2(); // [14]
        test3(); // []
        test4(); // [2]
        test5(); // [179,964]
        test6(); // [31]
    }

    private static void test1() {
        int[] positions = {5,4,3,2,1};
        int[] healths = {2,17,9,15,10};
        String directions = "RRRRR";
        System.out.println(survivedRobotsHealths(positions, healths, directions));
    }
    private static void test2() {
        int[] positions = {3,5,2,6};
        int[] healths = {10,10,15,12};
        String directions = "RLRL";
        System.out.println(survivedRobotsHealths(positions, healths, directions));
    }
    private static void test3() {
        int[] positions = {1,2,5,6};
        int[] healths = {10,10,11,11};
        String directions = "RLRL";
        System.out.println(survivedRobotsHealths(positions, healths, directions));
    }
    private static void test4() {
        int[] positions = {2};
        int[] healths = {2};
        String directions = "L";
        System.out.println(survivedRobotsHealths(positions, healths, directions));
    }
    private static void test5() {
        int[] positions = {5,61,98,82};
        int[] healths = {180,104,8,965};
        String directions = "RLLR";
        System.out.println(survivedRobotsHealths(positions, healths, directions));
    }
    private static void test6() {
        int[] positions = {30,41,35,18};
        int[] healths = {13,33,9,25};
        String directions = "LLRR";
        System.out.println(survivedRobotsHealths(positions, healths, directions));
    }

    private static class Robot {
        char direction;
        int position;
        int health;
        Robot(char direction, int position, int health) {
            this.direction = direction;
            this.position = position;
            this.health = health;
        }
    }
    public static List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        Comparator<Robot> comparator = Comparator.comparingInt(a -> a.position);
        TreeSet<Robot> rightRobots = new TreeSet<>(comparator);
        TreeSet<Robot> leftRobots = new TreeSet<>(comparator);
        int n = healths.length;
        for (int i = 0; i < n; i++) {
            char direction = directions.charAt(i);
            if (direction == 'R') {
                rightRobots.add(new Robot(direction, positions[i], healths[i]));
            } else {
                leftRobots.add(new Robot(direction, positions[i], healths[i]));
            }
        }

        collide(rightRobots.higher(new Robot('R', 0, 1)), rightRobots, leftRobots);

        TreeSet<Robot> remainingRobots = new TreeSet<>(comparator);
        remainingRobots.addAll(rightRobots);
        remainingRobots.addAll(leftRobots);
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Robot robot = remainingRobots.ceiling(new Robot('R', positions[i], 1));
            if (robot != null && robot.position == positions[i]) answer.add(robot.health);
        }
        return answer;
    }

    private static void collide(Robot robot, TreeSet<Robot> rightRobots, TreeSet<Robot> leftRobots) {
        if (robot == null) return;
        Robot rightRobot = rightRobots.higher(robot);
        Robot leftRobot = leftRobots.higher(robot);

        if (leftRobot == null) return;

        while (leftRobot != null && (rightRobot == null || rightRobot.position > leftRobot.position)) {
            if (robot.health > leftRobot.health) {
                leftRobots.remove(leftRobot);
                robot.health--;
                leftRobot = leftRobots.higher(robot);
            } else if (robot.health < leftRobot.health) {
                rightRobots.remove(robot);
                leftRobot.health--;
                collide(rightRobots.higher(robot), rightRobots, leftRobots);
                return;
            } else {
                rightRobots.remove(robot);
                leftRobots.remove(leftRobot);
                collide(rightRobots.higher(robot), rightRobots, leftRobots);
                return;
            }
        }
        collide(rightRobot, rightRobots, leftRobots);
        collide(robot, rightRobots, leftRobots);
    }
}
