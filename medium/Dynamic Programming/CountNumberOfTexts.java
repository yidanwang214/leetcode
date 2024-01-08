public class CountNumberOfTexts {
    // https://www.youtube.com/watch?v=QVf9HK3YyTE
    // time: O(n)
    // space: O(n) -> O(1) https://www.youtube.com/watch?v=n82BgEqSCkg
    public int countTexts(String pressedKeys) {
        int mod = 1000000007;
        int len = pressedKeys.length();
        long[] dp = new long[len + 1]; // total number of possible msgs at i-1th index
        dp[0] = 1;

        for (int i = 1; i < len + 1; i++) {
            char curNum = pressedKeys.charAt(i - 1);
            /*
             * eg:
             * pressedKeys: 2 3 4 4 4 4
             * dp: 1 dp[0] = dp[1] = dp[2] dp[3]+dp[2] dp[4]+dp[2] dp[5]+dp[3]
             * index: 0 1 2 3 4 5 6
             */

            /*
             * the current number apppears for the first time (at index 0), this is going to
             * be the only handling to the first letter, no matter it is repetive or not
             * for numbers at other indices, by default we first assign the value of last
             * index to current number, becasue dp[i-1] is one constituent of the sum no
             * matter it is repetitive or not
             */
            dp[i] = dp[i - 1];
            // check if curNum is the same as prevNum
            if (i >= 2 && curNum == pressedKeys.charAt(i - 2))
                dp[i] = dp[i] + dp[i - 2] % mod;
            else
                continue;
            // check if the curNum is the same as the number before the prevNum
            if (i >= 3 && curNum == pressedKeys.charAt(i - 3))
                dp[i] = dp[i] + dp[i - 3] % mod;
            else
                continue;

            // handle edge cases
            if ((curNum == '7' || curNum == '9') && i >= 4 && curNum == pressedKeys.charAt(i - 4))
                dp[i] = dp[i] + dp[i - 4] % mod;

        }
        return (int) (dp[len] % mod);
    }
}
