// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/

public class BestTimetoBuyAndSellStock {
    /*
     * dp 2d array of prices.length of number of subarray of length 2
     * reference:
     * https://www.bilibili.com/video/BV1Xe4y1u77q/?spm_id_from=333.788&vd_source=
     * c74db3b5742c8630d40add5529146de2
     * https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0122.
     * %E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%
     * 9C%BAII.md
     * 1. dp[i][0]: max profit of not having a stock until day i
     * dp[i][i]: max profit of having a stock until day i
     * 2. equation:
     * -> dp[i][0] = max(dp[i-1][0], dp[i-1][1]+prices[i]) -> +prices[i] means sell
     * a stock on i-th day
     * -> dp[i][1] = max(dp[i-1][1], -prices[i])
     * 3. initialisation:
     * -> dp[i][0] = 0
     * -> dp[i][1] = -prices[i]
     * 4. ietrate prices and fill the dp array forward
     * time: O(n)
     * space: O(n)
     */
    public int maxProfit(int[] prices) {
        // edge case
        if (prices.length == 1)
            return 0;

        // dp
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0; // max profit of not having any stock untill day 0 is 0
        dp[0][1] = -prices[0]; // max profit of having a stock untill day 0 is -prices[0]
        for (int i = 1; i < prices.length; i++) {
            // max profit of not having any stock untill day i
            // greater one between maxprofit on i-1-th day and sell a stock which is
            // included in maxprofit of having a stock
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // max profit of having a stock untill day i
            // greater one between maxprofit on i-1-th day and buying a stock on i-th day
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }

        return dp[prices.length - 1][0];
    }

    /*
     * brute force
     * prices = [4, 6, 7, 2, 5, 8, 1, 3, 9]
     * min = 4 4 4 2 2, 2, 1, 1, 1
     * dp[i] = [0, 2, 3, 3, 3, 6, 6, 2, 8]
     * 1. dp[i] = max profit until i
     * 2. equation: maintain a varaible mim keep track of the min stock price,
     * -> if min < dp[i], min = dp[i]
     * -> dp[i] = max(dp[i] - min, dp[i-1])
     * 3. initialise dp[0] to be 0, because untill day 0 we don't have any profit
     * 4. iterate the prices array and fill the dp array forward
     * Time: O(n)
     * Space: O(n) -> O(1)
     * 
     * prices = [7,6,4,3,1]
     * i = 1 2 3 4
     * min = 7 6 4 3 4
     * maxProfit=0 0 0 0 0
     */
    public int maxProfitBF(int[] prices) {
        int min = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - min);
        }
        return maxProfit;
    }

    /*
     * {1, 7, 4, 11} prices array -> prices difference array {0, 6, -3, 7}
     * start=0, end = 0;
     * Kadane's Algorithm: https://leetcode.com/problems/maximum-subarray/
     */
}
