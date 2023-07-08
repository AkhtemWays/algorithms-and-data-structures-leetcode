package design.Twitter;

import java.util.*;

class Tweet {
    int userId;
    int tweetId;
    public Tweet(int userId, int tweetId) {
        this.userId = userId;
        this.tweetId = tweetId;
    }
}

public class Twitter {
    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
        twitter.getNewsFeed(1).forEach(System.out::print); // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
        System.out.println();
        twitter.follow(1, 2);    // User 1 follows user 2.
        twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
        twitter.getNewsFeed(1).forEach(System.out::print);  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
        System.out.println();
        twitter.unfollow(1, 2);  // User 1 unfollows user 2.
        twitter.getNewsFeed(1).forEach(System.out::print);  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.
    }
    private final List<Set<Integer>> followersGraph;
    private final HashMap<Integer, List<Integer>> tweets;
    public Twitter() {
        this.followersGraph = new ArrayList<>();
        this.tweets = new HashMap<>();
        for (int i = 0; i <= 500; i++) {
            followersGraph.add(new HashSet<>());
            this.tweets.put(i, new ArrayList<>());
        }
    }

    public void postTweet(int userId, int tweetId) {
        this.tweets.get(userId).add(tweetId);
        for (int follower : this.followersGraph.get(userId)) {
            this.tweets.get(follower).add(tweetId);
        }
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        for (int i = this.tweets.get(userId).size() - 1; i >= Math.max(0, this.tweets.get(userId).size() - 11); i--) {
            res.add(this.tweets.get(userId).get(i));
        }
        return res;
    }

    public void follow(int followerId, int followeeId) {
        if (this.followersGraph.get(followeeId).contains(followerId)) {
            return;
        }
        this.followersGraph.get(followeeId).add(followerId);
        for (int i = this.tweets.get(followeeId).size() - 1; i >= Math.max(0, this.tweets.get(followeeId).size() - 11); i--) {
            this.tweets.get(followerId).add(this.tweets.get(followeeId).get(i));
        }
    }

    public void unfollow(int followerId, int followeeId) {
        if (!this.followersGraph.get(followeeId).contains(followerId)) {
            return;
        }
        this.followersGraph.get(followeeId).remove(followerId);
        smartRemove(this.tweets.get(followerId), this.tweets.get(followeeId));
    }

    private void smartRemove(List<Integer> followerTweets, List<Integer> followeeTweets) {
        int i = 0;
        int j = 0;
        int followeeTweet = followeeTweets.get(j);
        while (i < followerTweets.size()) {
            if (followeeTweet == followerTweets.get(i)) {
                j++;
                Collections.swap(followerTweets, i, followerTweets.size() - 1);
                followerTweets.remove(followerTweets.size() - 1);
            } else {
                i++;
            }
        }
    }
}
