package design.TweetCountsPerFrequency;

import java.math.BigInteger;
import java.util.*;

class TweetCounts {
    public static void main(String[] args) {
        TweetCounts tweetCounts = new TweetCounts();
        tweetCounts.recordTweet("tweet3", 0);
        tweetCounts.recordTweet("tweet3", 60);
        tweetCounts.recordTweet("tweet3", 10);
        tweetCounts.recordTweet("tweet3", 10);
        System.out.println(tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59)); // return [2]; chunk [0,59] had 2 tweets
        System.out.println(tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60)); // return [2,1]; chunk [0,59] had 2 tweets, chunk [60,60] had 1 tweet
        tweetCounts.recordTweet("tweet3", 120);                            // New tweet "tweet3" at time 120
        System.out.println(tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 210));  // return [4]; chunk [0,210] had 4 tweets
    }
    private final HashMap<String, TreeMap<Integer, Integer>> system;
    public TweetCounts() {
        this.system = new HashMap<>();
    }

    public void recordTweet(String tweetName, int time) {
        if (system.containsKey(tweetName)) {
            TreeMap<Integer, Integer> times = system.get(tweetName);
            times.put(time, times.getOrDefault(time, 0) + 1);
        } else {
            TreeMap<Integer, Integer> times = new TreeMap<>();
            times.put(time, 1);
            system.put(tweetName, times);
        }
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        int interval;
        switch(freq) {
            case "minute":
                interval = 60;
                break;
            case "hour":
                interval = 3600;
                break;
            default:
                interval = 86400;
                break;
        }
        List<Integer> result = new ArrayList<>();
        TreeMap<Integer, Integer> times = system.get(tweetName);

        for (int start = startTime; start <= endTime; start += Math.min(endTime - start + 1, interval)) {
            int end = Math.min(endTime, start + interval - 1);
            result.add(getOnInterval(start, end, times));
        }
        return result;
    }

    private static int getOnInterval(int start, int end, TreeMap<Integer, Integer> times) {
        int result = 0;
        for (Map.Entry<Integer, Integer> entry : times.subMap(start, end+1).entrySet()) {
            result += entry.getValue();
        }
        return result;
    }
}
