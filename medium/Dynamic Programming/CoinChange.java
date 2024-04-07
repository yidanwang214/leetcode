// https://leetcode.com/problems/coin-change/description/
public class CoinChange {
    class Solution {
        /*
         * 1. dp[j]: the min num of coins needed for j amount of money
         * 2. equation: dp[j] = min(dp[j], dp[j-coins[i]]);
         * 3. initialisation: dp[0] = 0 -> 0 num of coin needed for 0$,
         * dp[1]..dp[len-1]=INF
         * 4. direction: outer loop forward to iterate every coin, inner loop forward to
         * use every coin more than once
         * Time: O(coins.length * amount)
         * Space: O(coins.length * amount)
         */

        // tabulation
        int[] dp; // the min num of coins needed to get to a certain amount

        public int coinChange(int[] coins, int amount) {
            this.dp = new int[amount + 1];
            Arrays.fill(this.dp, Integer.MAX_VALUE);
            this.dp[0] = 0;

            // iterate every coins
            for (int i = 0; i < coins.length; i++) {
                for (int j = coins[i]; j <= amount; j++) {
                    if (dp[j - coins[i]] != Integer.MAX_VALUE) {
                        /*
                         * choose the smaller one between the min num of coins needed for the amount in
                         * the last state
                         * and the min num of coins neded for the (amount - the current coins).
                         * using dp[j-1] to update dp[j] is incorrect in that dp[j-1] stands for the min
                         * num of coins needed for the amount 1 less than the current amount,
                         * but the current denominations of the current coin is not necessarily 1.
                         */
                        dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                    }
                }
            }
            return (this.dp[amount] == Integer.MAX_VALUE ? -1 : this.dp[amount]);
        }
        /*
         * coins = [5, 2, 1], amount = 11
         * 0 1 2 3 4 5 6 7 8 9 10 11
         * dp = [0, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF]
         * coin=5, [0, INF, INF, INF, INF, 1, INF, INF, INF, INF, 2, INF]
         * coin=2, [0, INF, 1, INF, 2, 1, 3, 2, 4, 3, 2, 5]
         * coin=1, [0, 1, 2, 3, 2, 1, 2, 2, 3, 3, 2, 3]
         * 
         * coins = [2], amount = 3
         */

        // memoization
        // https://www.youtube.com/watch?v=xxrv-uJdV8Y
        // time: O(amount * coins.length): In the worst case, it might consider all coin
        // denominations for each value from 1 to amount, amount * coins.length
        // recursive calls.
        // space: O(amount) for dp arrray, the recurisve stack is at most 0(amount)
        private int[] dp; // dp[i]: the min num of coins needed to make amount i
        private int amount;
        private int[] coins;

        public int coinChange(int[] coins, int amount) {
            // initialisation
            this.amount = amount;
            this.coins = coins;
            this.dp = new int[amount + 1];
            Arrays.fill(this.dp, Integer.MAX_VALUE);
            this.dp[0] = 0;

            dfs(amount);
            // if the number of coins needed to represent amount money is Integer.MAX_VALUE,
            // there is no such combination of coins that can make up this amount
            return (dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount]);
        }

        private int dfs(int amount) {
            // base case
            if (amount == 0)
                return 0;
            if (dp[amount] != Integer.MAX_VALUE)
                return dp[amount]; // Memoization

            int minCoins = Integer.MAX_VALUE;

            for (int coin : coins) {
                if (amount - coin >= 0) {
                    int coinsNeeded = dfs(amount - coin);
                    if (coinsNeeded != -1) {
                        minCoins = Math.min(minCoins, coinsNeeded + 1);
                    }
                }
            }

            dp[amount] = (minCoins == Integer.MAX_VALUE ? -1 : minCoins); // Memoization
            return dp[amount];
        }
    }
}
