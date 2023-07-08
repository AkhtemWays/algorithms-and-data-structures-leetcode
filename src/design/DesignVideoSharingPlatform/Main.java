package design.DesignVideoSharingPlatform;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.PriorityQueue;

class VideoSharingPlatform {
    private final HashMap<Integer, Pair<String, int[]>> platform;
    private final PriorityQueue<Integer> pool;
    public VideoSharingPlatform() {
        this.platform = new HashMap<>();
        pool = new PriorityQueue<>();
        for (int i = 0; i < 100001; i++) {
            pool.add(i);
        }
    }

    public int upload(String video) {
        int videoId = pool.poll();
        int[] stats = new int[]{0, 0, 0};
        Pair<String, int[]> meta = new Pair<>(video, stats);
        platform.put(videoId, meta);
        return videoId;
    }

    public void remove(int videoId) {
        if (platform.containsKey(videoId)) {
            platform.remove(videoId);
            pool.add(videoId);
        }
    }

    public String watch(int videoId, int startMinute, int endMinute) {
        if (platform.containsKey(videoId)) {
            Pair<String, int[]> meta = platform.get(videoId);
            int[] stats = meta.getValue();
            String video = meta.getKey();
            stats[0]++;
            return video.substring(startMinute, endMinute+1);
        }
        return "-1";
    }

    public void like(int videoId) {
        if (platform.containsKey(videoId)) {
            int[] stats = platform.get(videoId).getValue();
            stats[1]++;
        }
    }

    public void dislike(int videoId) {
        if (platform.containsKey(videoId)) {
            int[] stats = platform.get(videoId).getValue();
            stats[2]++;
        }
    }

    public int[] getLikesAndDislikes(int videoId) {
        if (platform.containsKey(videoId)) {
            int[] stats = platform.get(videoId).getValue();
            return new int[]{stats[1], stats[2]};
        }
        return new int[]{-1};
    }

    public int getViews(int videoId) {
        if (platform.containsKey(videoId)) {
            return platform.get(videoId).getValue()[0];
        }
        return -1;
    }
}
