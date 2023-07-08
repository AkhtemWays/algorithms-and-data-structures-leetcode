package arrays.TheSkylineProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DivideAndConquerApproach {
    public static void main(String[] args) {
        DivideAndConquerApproach dac = new DivideAndConquerApproach();
        int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
        System.out.println(dac.getSkyline(buildings));
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        return divideAndConquer(buildings, 0, buildings.length-1);
    }

    private List<List<Integer>> divideAndConquer(int[][] buildings, int left, int right) {
        if (left == right) {
            List<List<Integer>> answer = new ArrayList<>();
            int[] building = buildings[left];
            answer.add(List.of(building[0], building[2]));
            answer.add(List.of(building[1], 0));
            return answer;
        }

        int mid = (right - left)/2 + left;
        List<List<Integer>> leftSkyline = divideAndConquer(buildings, left, mid);
        List<List<Integer>> rightSkyline = divideAndConquer(buildings, mid+1, right);
        return mergeSkylines(leftSkyline, rightSkyline);
    }

    private List<List<Integer>> mergeSkylines(List<List<Integer>> leftSkyline, List<List<Integer>> rightSkyline) {
        int leftHeight = 0, rightHeight = 0;
        int leftIdx = 0, rightIdx = 0;
        int curX, curY;
        List<List<Integer>> answer = new ArrayList<>();

        while (leftIdx < leftSkyline.size() && rightIdx < rightSkyline.size()) {
            int leftX = leftSkyline.get(leftIdx).get(0);
            int rightX = rightSkyline.get(rightIdx).get(0);

            if (leftX < rightX) {
                curX = leftX;
                leftHeight = leftSkyline.get(leftIdx).get(1);
                curY = Math.max(leftHeight, rightHeight);
                leftIdx++;
            }

            else if (leftX > rightX) {
                curX = rightX;
                rightHeight = rightSkyline.get(rightIdx).get(1);
                curY = Math.max(leftHeight, rightHeight);
                rightIdx++;
            }

            else {
                curX = leftX;
                leftHeight = leftSkyline.get(leftIdx).get(1);
                rightHeight = rightSkyline.get(rightIdx).get(1);
                curY = Math.max(leftHeight, rightHeight);
                leftIdx++;
                rightIdx++;
            }

            if (answer.isEmpty() || answer.get(answer.size() - 1).get(1) != curY) {
                answer.add(List.of(curX, curY));
            }
        }

        while (leftIdx < leftSkyline.size()) {
            answer.add(leftSkyline.get(leftIdx++));
        }

        while (rightIdx < rightSkyline.size()) {
            answer.add(rightSkyline.get(rightIdx++));
        }

        return answer;
    }
}
