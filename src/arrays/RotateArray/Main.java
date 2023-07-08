package arrays.RotateArray;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        int[] nums2 = {-1, 10, 4, 11};
        Main main = new Main();
//        main.rotate(nums, 3);
        main.rotate(nums2, 2);
        for (int num : nums) System.out.print(num + " ");
        System.out.println();
        for (int num : nums2) System.out.print(num + " ");
    }

    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[current];
            do {
                int next = (current + k) % nums.length;
                int tmp = nums[next];
                nums[next] = prev;
                prev = tmp;
                current = next;
                count++;
            } while (current != start);
        }
    }
}
