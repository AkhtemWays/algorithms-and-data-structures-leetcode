package arrays.DailyTemperatures;

import java.util.Arrays;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        int[] nums = {73,74,75,71,69,72,76,73};
        System.out.println(Arrays.toString(dailyTemperatures(nums)));
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        Stack<TemperatureInfo> stack = new Stack<>();
        stack.add(new TemperatureInfo(temperatures[0], 0));
        int[] result = new int[temperatures.length];
        for (int i = 1; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > stack.peek().temperature) {
                TemperatureInfo temperatureInfo = stack.pop();
                result[temperatureInfo.index] = i - temperatureInfo.index;
            }
            stack.add(new TemperatureInfo(temperatures[i], i));
        }
        return result;
    }

    private static class TemperatureInfo {
        int index;
        int temperature;
        TemperatureInfo(int temperature, int index) {
            this.index = index;
            this.temperature = temperature;
        }
    }
}
