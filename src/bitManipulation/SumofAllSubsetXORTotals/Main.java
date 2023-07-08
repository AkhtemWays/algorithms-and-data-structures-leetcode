package bitManipulation.SumofAllSubsetXORTotals;

public class Main {
    public static void main(String[] args) {
        int[] nums = {5,1,6};
        Main main = new Main();
        System.out.println(main.subsetXORSum(nums));
    }
    private int count = 0;

    public int subsetXORSum(int[] nums) {
        dfs(0, nums, 0);
        return count;
    }

    private void dfs(int i, int[] nums, int total) {
        if (i == nums.length) {
            count += total;
            return;
        }

        dfs(i+1, nums, total ^ nums[i]);
        dfs(i+1, nums, total);
    }
}
