// https://leetcode.com/problems/climbing-stairs/description/
// easy dynamic programming
public class ClimingStairs {

    // memoization
    // time: O(n)
    // space: O(n)
    public int[] memo;

    public int climbStairs(int n) {
        if (n == 1 || n == 2)
            return n;
        memo = new int[n + 1];
        memo[1] = 1;
        memo[2] = 2;
        return memoization(n, memo);
    }

    public int memoization(int n, int[] arr) {
        if (arr[n] != 0)
            return arr[n];
        arr[n] = memoization(n - 1, arr) + memoization(n - 2, arr);
        return arr[n];
    }

    // tabulation
    // time: O(n)
    // space: O(1)
    public int climbStairs(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;

        int pre1 = 1;
        int pre2 = 2;
        int res = 1 + 2;
        int i = 3;
        while (i < n) {
            pre1 = pre2;
            pre2 = res;
            res = pre1 + pre2;
            i++;
        }
        return res;
    }

    // tabulation
    // time: O(n)
    // space: O(n)
    public int climbStairs(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
