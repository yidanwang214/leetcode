// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
public class BestTimetoBuyandSellStock2 {
    // reference:
    // https://www.bilibili.com/video/BV1ho4y1W7QK/?spm_id_from=333.788.recommend_more_video.0&vd_source=c74db3b5742c8630d40add5529146de2
    // time: O(n)
    // space: O(n)
    int[] prices;
    int[][] memo;

    public int memoization(int[] prices) {
        this.prices = prices;
        this.memo = new int[prices.length][2];
        for (int i = 0; i < prices.length; i++)
            Arrays.fill(memo[i], -1);
        return dfs(prices.length - 1, 0);
    }

    public int dfs(int i, int isHold) {
        // base case
        if (i < 0) {
            if (isHold == 1)
                return Integer.MIN_VALUE;
            else
                return 0;
        }

        // check cache
        if (memo[i][isHold] != -1)
            return memo[i][isHold];

        // recursive case
        if (isHold == 1)
            memo[i][isHold] = Math.max(dfs(i - 1, isHold), dfs(i - 1, 0) - prices[i]);
        else
            memo[i][isHold] = Math.max(dfs(i - 1, isHold), dfs(i - 1, 1) + prices[i]);
        return memo[i][isHold];
    }

    // reference: https://www.bilibili.com/video/BV1D24y1Q7Ls/?spm_id_from=333.788
    // time: O(n)
    // space: O(n)
    public int dp(int[] prices) {
        int[][] dp = new int[prices.length + 1][2];
        dp[0][0] = 0;
        dp[0][1] = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            dp[i + 1][0] = Math.max(dp[i][0], dp[i][1] + prices[i]);
            dp[i + 1][1] = Math.max(dp[i][1], dp[i][0] - prices[i]);
        }
        return dp[prices.length][0];
    }
}
