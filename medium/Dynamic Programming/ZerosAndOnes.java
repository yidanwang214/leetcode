public class ZerosAndOnes {

    /*
     * Test case:
     * strs = ["10","0001","111001","1","0"], m(0) = 5, n(1) = 3
     * output: 4
     * 
     * [0, 0, 0, 0] m(0) = 5, n(1) = 3
     * [0, 0, 0, 0]
     * [0, 0, 0, 0]
     * [0, 0, 0, 0]
     * [0, 0, 0, 0]
     * [0, 0, 0, 0]
     * 
     * [0, 0, 0, 0] m(0) = 5, n(1) = 3, s:10, num0=1, num1=1, bound:[1][1]
     * [0, 1, 1, 1]
     * [0, 1, 1, 1]
     * [0, 1, 1, 1]
     * [0, 1, 1, 1]
     * [0, 1, 1, 1]
     * 
     * [0, 0, 0, 0] m(0) = 5, n(1) = 3, s:0001, num0=3, num1=1, bound:[3][1]
     * [0, 1, 1, 1]
     * [0, 1, 1, 1]
     * [0, 1, 2, 2]
     * [0, 1, 2, 2]
     * [0, 1, 2, 2]
     * 
     * [0, 0, 0, 0] m(0) = 5, n(1) = 3, s:111001, num0=2, num1=4, bound:[2][4]
     * [0, 1, 1, 1] can't be uupdate because the inital value of n(1)=3 < bound[4]
     * [0, 1, 1, 1]
     * [0, 1, 2, 2]
     * [0, 1, 2, 2]
     * [0, 1, 2, 2]
     * 
     * [0, 1, 1, 1] m(0) = 5, n(1) = 3, s:1, num0=0, num1=1, bound:[0][1]
     * [0, 1, 2, 2]
     * [0, 1, 2, 2]
     * [0, 1, 2, 3]
     * [0, 1, 2, 3]
     * [0, 1, 2, 3]
     * 
     * [0, 1, 1, 1] m(0) = 5, n(1) = 3, s:0, num0=1, num1=0, bound:[1][0]
     * [1, 2, 2, 2]
     * [1, 2, 3, 3]
     * [1, 2, 3, 3]
     * [1, 2, 3, 4]
     * [1, 2, 3, 4]
     * 
     * 
     * Input: strs = ["10","0","1"], m = 1, n = 1
     * Output: 2 <- ["0", "1"]
     */

    /*
     * dp: 2-Dimensional 0/1 knapsack problem
     * 1. dp[i][j]: the max length of subset when we select previous k items with a
     * capacity of i 0s and j 1s
     * 2. equation: 1D 0/1 knapsack problem -> dp[j] = max(dp[j],
     * dp[j-weight[i]]+value[i])
     * 2D 0/1 kanpsack problem -> dp[i][j] = max(dp[i][j], dp[i-x][j-y]+1) x and y
     * are the num of 0s and 1s in current string(item)
     * 3. initialisation: dp[i][j]=0
     * 4. ietration order: scan every str in strs array forward, iterate the i and j
     * backwards to ensure every element can be used only once
     * Time: O(strs.length * m * n) -> iterate every str in strs array, and in eac
     * iteration we update the dp table
     * Space: O(m * n) -> size of the dp table
     */
    public int findMaxFormDP(String[] strs, int m, int n) {
        // dp[i][j]: the max num of strs when the capacity is that there are at most m
        // 0's and n 1's
        int[][] dp = new int[m + 1][n + 1];
        for (int k = 0; k < strs.length; k++) {
            // count the num of 0's and 1's in current str
            String s = strs[k];
            int num0 = 0, num1 = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '0')
                    num0++;
                else
                    num1++;
            }

            // fill dp by iterating backwards
            for (int i = m; i >= num0; i--) {
                for (int j = n; j >= num1; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - num0][j - num1] + 1);
                }
            }
        }
        return dp[m][n];
    }

    /*
     * memoization[strs.length][m+1][n+1]
     * Time: O(str.length * m * n) -> maxSubset makes recursive calls, but memo
     * ensures each subproblem is only excuted once; Within each subproblem, the
     * calculation for each subproblem(counting 0s, 1sin a string and comparing
     * options) takes constant time
     * Space: O(str.length * m * n) -> the recurisve call is bounded by str.length,
     * so the 3d memo occupies the most space
     * maxSubset(int index, left0, left1){
     * // base case
     * if(left0 == 0 && left1 == 0) return 0;
     * if(index >= strs.length) return 0;
     * 
     * // recursive case
     * str = strs[i];
     * cur0 = the num of 0 in str;
     * cur1 = the num of 1 in str;
     * if(left0 - cur0 >= 0 && left1 - cur1 >= 0){
     * return max(maxSubset(index+1, left0-cur0, left1-cur1)+1, maxSubset(index+1,
     * left0, left1));
     * } else -> left0 - cur0 < 0 || left1 - cur1 < 0
     * else if(left0-cur0 < 0){
     * return maxSubset(index+1, left0, left1);
     * } else // left1 - cur1 < 0
     * return maxSubset(index+1, left0, left1);
     * }
     */

    /*
     * memo= [-1, -1, -1, -1]
     * dfs(0, 9, 3)
     * str=111
     * left0=9
     * left1=0
     * 0+1
     * max(dfs(1,9,0)+1, dfs(1,9,3))
     * | |
     * str=1000
     * left0=7
     * left1=-1
     * memo[1]=-1,
     * dfs(2,9,0) memo[1]=0
     * | ^0
     * str=1000
     * left0=7
     * left1=-1
     * memo[1]=-1,
     * dfs(3,9,0) memo[2]=0
     * | ^0
     * str=1000
     * left0=7
     * left1=-1
     * memo[1]=-1,
     * dfs(4,9,0) memo[3]=0
     * | ^0
     * return 0
     */
    String[] strs;
    int[][][] memo;

    public int findMaxFormTB(String[] strs, int m, int n) {
        this.strs = strs;
        this.memo = new int[strs.length][m + 1][n + 1];
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < m + 1; j++) {
                for (int k = 0; k < n + 1; k++) {
                    memo[i][j][k] = -1;
                }
            }
        }
        return maxSubset(0, m, n);
    }

    private int maxSubset(int index, int left0, int left1) {
        // base case
        if (left0 == 0 && left1 == 0)
            return 0;
        if (index >= strs.length)
            return 0;
        if (memo[index][left0][left1] != -1)
            return memo[index][left0][left1];

        // recursive case
        String str = strs[index];
        int cur0 = 0, cur1 = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0')
                cur0++;
            else
                cur1++;
        }

        if (left0 - cur0 >= 0 && left1 - cur1 >= 0) {
            memo[index][left0][left1] = Math.max(maxSubset(index + 1, left0 - cur0, left1 - cur1) + 1,
                    maxSubset(index + 1, left0, left1));
        } else
            memo[index][left0][left1] = maxSubset(index + 1, left0, left1);
        return memo[index][left0][left1];
    }
}
