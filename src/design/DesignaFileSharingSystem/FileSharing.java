package design.DesignaFileSharingSystem;

import java.util.*;
import java.util.stream.Collectors;

public class FileSharing {
    public static void main(String[] args) {
        FileSharing fileSharing = new FileSharing(4); // We use the system to share a file of 4 chunks.

        System.out.println(fileSharing.join(List.of(1, 2)));    // A user who has chunks [1,2] joined the system, assign id = 1 to them and return 1.

        System.out.println(fileSharing.join(List.of(2, 3)));    // A user who has chunks [2,3] joined the system, assign id = 2 to them and return 2.

        System.out.println(fileSharing.join(List.of(4)));       // A user who has chunk [4] joined the system, assign id = 3 to them and return 3.

        System.out.println(fileSharing.request(1, 3));   // The user with id = 1 requested the third file chunk, as only the user with id = 2 has the file, return [2] . Notice that user 1 now has chunks [1,2,3].

        System.out.println(fileSharing.request(2, 2));   // The user with id = 2 requested the second file chunk, users with ids [1,2] have this chunk, thus we return [1,2].

        fileSharing.leave(1);        // The user with id = 1 left the system, all the file chunks with them are no longer available for other users.

        System.out.println(fileSharing.request(2, 1));   // The user with id = 2 requested the first file chunk, no one in the system has this chunk, we return empty list [].

        fileSharing.leave(2);        // The user with id = 2 left the system.

        System.out.println(fileSharing.join(List.of()));        // A user who doesn't have any chunks joined the system, assign id = 1 to them and return 1. Notice that ids 1 and 2 are free and we can reuse them.
    }
    private final HashMap<Integer, Set<Integer>> userChunks = new HashMap<>();
    private final PriorityQueue<Integer> idPool = new PriorityQueue<>();
    private final HashMap<Integer, List<Integer>> chunksBelongings = new HashMap<>();

    public FileSharing(int m) {
        for (int userId = 1; userId <= 10001; userId++) {
            idPool.add(userId);
        }
        for (int chunkId = 1; chunkId <= m; chunkId++) {
            chunksBelongings.put(chunkId, new ArrayList<>());
        }
    }

    public int join(List<Integer> ownedChunks) { // O(log(n)) + O(min(100, m))
        int userId = idPool.poll();
        userChunks.put(userId, new HashSet<>(ownedChunks));
        for (int chunkId : ownedChunks) {
            chunksBelongings.get(chunkId).add(userId);
        }
        return userId;
    }

    public void leave(int userID) { // O(log(n)) + O(min(100, m))
        Set<Integer> chunks = userChunks.remove(userID);
        for (int chunkId : chunks) {
            List<Integer> chunkBelongings = chunksBelongings.get(chunkId);
            int index = chunkBelongings.indexOf(userID);
            smartRemove(chunkBelongings, index);
        }
        idPool.add(userID);
    }

    public List<Integer> request(int userID, int chunkID) { // O(n)
        chunksBelongings.get(chunkID).add(userID);
        List<Integer> usersWithChunkId = chunksBelongings.get(chunkID);
        userChunks.get(userID).add(chunkID);
        return usersWithChunkId;
    }

    private void smartRemove(List<Integer> list, int index) {
        Collections.swap(list, index, list.size() - 1);
        list.remove(list.size() - 1);
    }
}
