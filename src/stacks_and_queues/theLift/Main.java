package stacks_and_queues.theLift;

import java.util.*;
import java.util.stream.Collectors;

class Tester {
    boolean test1() {
        final int[][] queues = {
                new int[0], // G
                new int[]{0}, // 1
                new int[0], // 2
                new int[0], // 3
                new int[]{2}, // 4
                new int[]{3}, // 5
                new int[0], // 6
        };
        final int[] expected = new int[]{0,5,4,3,2,1,0};
        return test(Main.theLift(queues, 5), expected);
    }

    boolean test2() {
        final int[][] queues = {
                new int[0], // G
                new int[0], // 1
                new int[]{5,5,5}, // 2
                new int[0], // 3
                new int[0], // 4
                new int[0], // 5
                new int[0], // 6
        };
        final int[] expected = new int[]{0,2,5,0};
        return test(Main.theLift(queues, 5), expected);
    }

    boolean test3() {
        final int[][] queues = {
                new int[0], // G
                new int[0], // 1
                new int[]{1,1}, // 2
                new int[0], // 3
                new int[0], // 4
                new int[0], // 5
                new int[0], // 6
        };
        final int[] expected = new int[]{0,2,1, 0};
        return test(Main.theLift(queues, 5), expected);
    }

    public static boolean test(int[] actual, int[] expected) {
        if (actual.length != expected.length) return false;
        for (int i = 0; i < expected.length; i++) if (expected[i] != actual[i]) return false;
        return true;
    }

    boolean test4() {
        final int[][] queues = {
                new int[0], // G
                new int[]{3}, // 1
                new int[]{4}, // 2
                new int[0], // 3
                new int[]{5}, // 4
                new int[0], // 5
                new int[0], // 6
        };
        final int[] expected = new int[]{0,1,2,3,4,5,0};
        return test(Main.theLift(queues, 5), expected);
    }
}

public class Main {

    public static void main(String[] args) {
        Tester tester = new Tester();
//        System.out.println(tester.test1());
        System.out.println(tester.test2());
        System.out.println(tester.test3());
        System.out.println(tester.test4());
    }

    public static int[] theLift(final int[][] queuesArr, final int capacity) {
        Lift lift = new Lift(queuesArr, capacity);

        lift.move();
        return lift.getStoppedStages();
    }

    private static class Lift {
        private final TreeSet<Integer> queue;
        private final List<List<Integer>> stages;
        private final int capacity;
        private final Integer BASE_STAGE = 0;
        private final int LAST_STAGE;
        private final List<Integer> stoppedStages;
        private Direction direction;
        private int curStage = 0;

        private static enum Direction {
            UP, DOWN
        }
        public Lift(int[][] queuesArr, int capacity) {
            this.stages = getStages(queuesArr);
            this.capacity = capacity;
            LAST_STAGE = stages.size() - 1;
            this.stoppedStages = new ArrayList<>();
            this.queue = new TreeSet<>();
            this.direction = Direction.UP;
        }

        private int[] getStoppedStages() {
            return stoppedStages.stream().mapToInt(Integer::intValue).toArray();
        }

        public static List<List<Integer>> getStages(int[][] queuesArr) {
            List<List<Integer>> queues = new ArrayList<>();
            for (int floor = 0; floor < queuesArr.length; floor++) {
                List<Integer> q = new ArrayList<>();
                for (int person : queuesArr[floor]) q.add(person);
                queues.add(q);
            }
            return queues;
        }

        public void move() {
            while (peopleAreHere()) {
                goUp();
                goDown();
            }
        }

        private boolean peopleAreHere() {
            for (List<Integer> q : stages) if (q.size() > 0) return true;
            return false;
        }

        private int getStageToGo() {
            int stage = stages.size() - 1;
            while (stage != -1) {
                if (stages.get(stage).size() > 0) return stage;
                stage--;
            }
            return stage;
        }

        private void fillQueue(int stage) {
            List<Integer> peopleIndices = getPeopleStages(stage);
            int i = peopleIndices.size() - 1;
            while (queue.size() < capacity && i >= 0) {
                queue.add(stages.get(stage).remove(i--));
            }
        }

        private List<Integer> getPeopleStages(int stage) {
            List<Integer> res = new ArrayList<>();
            List<Integer> people = stages.get(stage);
            for (int i = 0; i < people.size(); i++) {
                if ((direction == Direction.UP && people.get(i) > stage) || (direction == Direction.DOWN && people.get(i) < stage)) {
                    res.add(i);
                }
            }
            return res;
        }

        private void goUp() {
            int stageToGo = stages.size();
            while (curStage <= stageToGo) {
                int finalCurStage = curStage;
                Integer anyoneOnThisStageToTake = stages.get(curStage).stream().filter(person -> person > finalCurStage).findAny().orElse(null);
                if (queue.contains(curStage) || anyoneOnThisStageToTake != null) {
                    stoppedStages.add(curStage);
                    while (queue.contains(curStage)) queue.remove(curStage);
                    fillQueue(curStage);
                }
                curStage++;
            }
            direction = Direction.DOWN;
        }

        private void goDown() {
            while (curStage >= BASE_STAGE) {
                int finalCurStage = curStage;
                Integer anyoneOnThisStageToTake = stages.get(curStage).stream().filter(person -> person < finalCurStage).findAny().orElse(null);
                if (queue.contains(curStage) || anyoneOnThisStageToTake != null) {
                    stoppedStages.add(curStage);
                    while (queue.contains(curStage)) queue.remove(curStage);
                    fillQueue(curStage);
                }
                curStage--;
            }
            direction = Direction.UP;
        }
    }
}
