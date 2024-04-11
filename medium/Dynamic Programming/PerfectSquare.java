// https://leetcode.com/problems/perfect-squares/
public class PerfectSquare {
    public int numSquaresOptimalDP(int n) {
        // reference: https://www.youtube.com/watch?v=TXGvkQLVSgA
        /*
         * test case
         * input: n = 1 n = 2 n = 3 n = 4 n = 5 n = 6 n = 7 n = 8 n = 9 n = 10 n = 11 n
         * = 12 n = 13 n = 14 n = 15 n = 16
         * output: 1 2 3 1 2 3 4 2 1 2 3 3 2 3 3 1
         * n = 2, the greatest perfect squre for 2 is 1, dp[2]=dp[2-1]+1=dp[1]+1=2,
         * n = 3, the greatest perfect squre for 3 is 1, dp[2]=dp[3-1]+1=dp[2]+1=3
         * n = 4, the greatest perfect squre for 3 is 4, dp[2]=dp[4-4]+1=dp[0]+1=1, 0
         * dp[0] is the num 4(2^2) need to complement 4, +1 represents the num of
         * perfect squre(one 4) for 4
         */
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        // iterate every num from 2 to n
        for (int i = 2; i < n + 1; i++) {
            // ietrate j from 1 to the greatest perfect square that is less or equal to i
            for (int j = 1; j * j <= i; j++) {
                // j*j are the perfect squares less than i
                // each perfect squre represents 1 num that makes up i
                // so we need to find the how many numbers composing i's complement of squre
                // represents
                // adding 1 and dp[i-j*j] can give us the least num of perfect sqaure
                dp[i] = Math.min(dp[i - j * j] + 1, dp[i]);
            }
        }
        return dp[n];
    }

    /*
     * n: [1, 10^4]
     * test case
     * input: n = 1 n = 2 n = 3 n = 4 n = 5 n = 6 n = 7 n = 8 n = 9 n = 10 n = 11 n
     * = 12 n = 13 n = 14 n = 15 n = 16
     * output: 1 2 3 1 2 3 4 2 1 2 3 3 2 3 3 1
     * 
     * 1. dp[n]: min num of perfect sqaure numbers that num to n
     * 2. equation:
     * 【obeservation】: from the test case above, we can see the dp[n] is the min sum
     * of dp[i]+dp[n-1], where i is gte 1 and lt n
     * take 8 for example, we dp[8] can be dp[1]+dp[7]=5, dp[2]+dp[6]=5,
     * dp[3]+dp[5]=5, dp[4]+dp[4]=2, so the least num of perfec sqaure nums that num
     * to 8 is 2
     * if(set.contains(dp[n])) dp[n] = 1;
     * else {
     * for(int i = 0; i < n + 1; i++){
     * dp[n] = min(dp[i] + dp[n-i]);
     * }
     * }
     * return dp[n];
     * 3. initialisation: dp[0]=0, dp[1]=1, dp[i] = MAX
     * 4. ietration direction: outer loop travers every num from 1 to n forward,
     * inner loop can traverse forward or backward
     * 
     * Time: O(n^2) -> iteration
     * Space: O(n) -> dp array + hashset
     */

    public int numSquaresONSpace(int n) {
        // since 1 <= n <= 10^4, the perfect square we need ranges from 1 to 10^4
        // we put them in set to lookup if n is a perfect square
        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i <= 100; i++) {
            set.add(i * i);
        }

        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        // iterate every num from 2 to n
        for (int i = 1; i < n + 1; i++) {
            // if the cur num is a perfect num, the least num of perfect sqaure that sum to
            // n is 1
            if (set.contains(i))
                dp[i] = 1;
            else {

                // iterate the first or second half of the nums we scanned before
                // check if there is a combination of complement of n that can be less than
                // dp[i]
                for (int j = 1; j <= i / 2; j++) {
                    dp[i] = Math.min(dp[j] + dp[i - j], dp[i]);
                }
            }
        }
        return dp[n];
    }

    int[] dp;

    public int numSquares(int n) {
        this.dp = new int[n + 1];
        Arrays.fill(this.dp, Integer.MAX_VALUE);
        this.dp[0] = 0;
        this.dp[1] = 1;
        return dfs(n);
    }

    private int dfs(int n) {
        // base case
        if (n == 1 || n == 0)
            return dp[n];
        if (n < 0)
            return 0;

        // memo
        if (dp[n] != Integer.MAX_VALUE)
            return dp[n];

        for (int i = 1; i * i <= n; i++) {
            dp[n] = Math.min(dfs(n - i * i) + 1, dp[n]);
        }
        return dp[n];
    }
}
