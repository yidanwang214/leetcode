// https://leetcode.com/problems/coin-change-ii/description/

public class CoinChange2 {
    /*
     * test case: coins=[1, 3, 5], amount = 6
     * 0 1 2 3 4 5 6
     * 0 1 0 0 0 0 0 0
     * 1 1 1 1 1 1 1 1
     * 3 1 1 1 2 2 2 3
     * 5 1 1 1 2 2 3 4
     * 6 = 1+1+1+1+1+1
     * 6 = 1+1+1+3
     * 6 = 3+3
     * 6 = 5+1
     * sort the coins first
     */
    /*
     * dp
     * 1. dp[j]: the num of combinations of coins that make up j amount
     * 2. equation: dp[j] = dp[j-coins[i]] + dp[j]
     * ...dp[j] represents the num of combination to make up amount using coins
     * previous to i
     * ...dp[j-coins[i]] represents the num of combination using j-coins[i] amount
     * of coins
     * 3. initialisation: dp[0]=1 <- 1 combination (empty array) to make up $0
     * 4. iteration direction: nested loops, the outer loop scan coins forward, the
     * inner loop scan dp[j] forward because this is unbounded knapsack, every item
     * can be used more than once
     * Time: O(coins.length * amount)
     * Space: O(coins.length * amount)
     */
    // public int changedp(int amount, int[] coins) {
    // // initialise dp array
    // int[] dp = new int[amount+1];
    // dp[0] = 1;

    // // update dp
    // for(int i = 0; i < coins.length; i++){
    // for(int j = coins[i]; j < amount+1; j++){
    // dp[j] = dp[j] + dp[j-coins[i]];
    // }
    // }
    // return dp[amount];
    // }

    /*
     * memo
     * dp[i][j] the num of combinations of using i coins to make up j amount of
     * moeny
     * coins = [5,3,1], amoutn =6 -> output 4
     * time: O(amount * coins.length)
     * space: O(amount * coins.length)
     * reference:
     * https://leetcode.com/problems/coin-change-ii/solutions/675444/java-3-dp-
     * solutions-o-amount-in-space-clean-concise/
     */

    int[][] dp;
    int[] coins;

    public int change(int amount, int[] coins) {
        this.dp = new int[coins.length][amount + 1];
        this.coins = coins;
        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j < amount + 1; j++) {
                if (j == 0)
                    dp[i][j] = 1;
                else
                    dp[i][j] = -1;
            }
        }
        dfs(0, amount);
        return dp[0][amount];
    }

    private int dfs(int i, int leftAmount) {
        // base case
        // when we using coins[i] coins and the leftAmount is 0, there is 1 combination
        // that can make up the amount
        if (i < coins.length && leftAmount == 0) {
            dp[i][0] = 1;
            return dp[i][0];
        }
        // when we reach the coins.length-th item, out of bound, no combination can make
        // up i
        if (i >= coins.length)
            return 0;
        // when leftAmount < 0, no such combination of coins on the path
        if (leftAmount < 0)
            return 0;

        if (dp[i][leftAmount] != -1)
            return dp[i][leftAmount];

        dp[i][leftAmount] = dfs(i, leftAmount - coins[i]) + dfs(i + 1, leftAmount);
        return dp[i][leftAmount];

    }
    /*
     * test case: input = [5, 3, 1], amount = 6
     * 0 1 2 3 4 5 6
     * 0 1 1 -1 -1 -1 -1 4
     * 1 1 1 1 2 3
     * 2 1 1 1 1
     * dfs(0,6)=4
     * |
     * dp[0,6]=dfs(0,1)=1 +dfs(1,6)=3
     * |1 |
     * dp[0][1]=dfs(0,-4)+dfs(1,1)=1 dp[1][6]=dfs(1,3)+dfs(2,3)=3
     * 0 |1 |2 |1
     * dfs[1][1]=dfs(1,-2)+dfs(2,1)=1 dp[1][3]=dfs(1,0)+dfs(2,3)=2
     * | |1 |1 |1
     * 0 dp[2][1]=dfs(2,0)+dfs(3,0)=1 1|dp[2][3]=dfs(2,2)+dfs(3,2)=1
     * | | |1 |0
     * 1 0 dp[2][2]=dfs(2,1)+dfs(3,2)=1
     * |1 |0
     * dp[2][1]=dfs(2,0)+dfs(3,1)=1
     * |1 |0
     */

    /*
     * backtracking: TLE
     */
    // int res = 0;
    // int[] coins;
    // public int change(int amount, int[] coins) {
    // this.coins = coins;
    // bt(0, amount);
    // return this.res;
    // }
    // private void bt(int startIndex, int leftAmount){
    // // base case
    // if(leftAmount == 0){
    // this.res++;
    // return;
    // }

    // if(leftAmount < 0) return;

    // // recursive case
    // for(int i = startIndex; i < this.coins.length; i++){
    // bt(i, leftAmount-this.coins[i]);
    // }
    // }
}
