package arrays.BoatstoSavePeople;

public class Main {
    public static void main(String[] args) {
        int[] people = {2, 4}; // 2
        int[] people2 = {3,5,3,4}; // 4
        int[] people3 = {3,2,2,1}; // 3
        int[] people4 = {1, 2}; // 1
        System.out.println(numRescueBoats(people, 5));
        System.out.println(numRescueBoats(people2, 5));
        System.out.println(numRescueBoats(people3, 3));
        System.out.println(numRescueBoats(people4, 3));
    }

    public static int numRescueBoats(int[] people, int limit) {
        int[] peopleCount = new int[limit+1];
        for (int person : people) {
            peopleCount[person]++;
        }

        int left = 1, right = limit, boats = 0;
        while (left <= right) {
            while (left < right && peopleCount[left] == 0) left++;
            while (right > left && peopleCount[right] == 0) right--;
            if (left == right) {
                if (peopleCount[left] == 1) return boats + 1;
                else if (peopleCount[left] == 0) return boats;
            }
            int sum = left + right;
            if (sum <= limit) {
                peopleCount[left]--;
            }
            peopleCount[right]--;
            boats++;
        }
        return boats;
    }
}
