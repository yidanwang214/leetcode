// https://leetcode.com/problems/house-robber/description/

public class HouseRobber {
    // 1. dp[i]: max money robbed from 0 to i
    // 2. dp[i] = max(dp[i-2]+num[i], dp[i-1])
    // 3. initialise dp[0] = nums[0]
    // 4. forward
    // time: O(n)
    // space: O(n)
    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(i - 1 >= 0 ? dp[i - 1] : 0, (i - 2 >= 0 ? dp[i - 2] : 0) + nums[i]);
        }
        return dp[nums.length - 1];
    }

    // 1. dp[i] max money robbed from i and onward
    // 2. dp[i] = max(nums[i]+dp[i+2], dp[i+1])
    // 3. intialise default value to be -1
    // 4. ietrate nums and fill dp array backwards
    // time: O(n)
    // space: O(n)
    public int robBackwordDP(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            dp[i] = Math.max(nums[i] + (i + 2 >= nums.length ? 0 : dp[i + 2]), i + 1 >= nums.length ? 0 : dp[i + 1]);
        }
        return dp[0];
    }

    // decision tree
    // dp[i]: the max money robbed from index i
    // time: O(n)
    // space: O(n)
    int[] dp;
    int[] nums;

    public int robmemo(int[] nums) {
        this.nums = nums;
        dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return dfs(0);
    }

    private int dfs(int i) {
        // base case
        if (i >= nums.length)
            return 0;

        if (dp[i] != -1)
            return dp[i];

        dp[i] = Math.max(nums[i] + dfs(i + 2), dfs(i + 1));
        return dp[i];
    }
}
