import java.util.Arrays;

public class IntegerBreak {

    /*
     * memoization
     * reference: https://www.youtube.com/watch?v=uJ7XF4D-kEk
     * main idea:
     * 1. non-binary decision tree can combine for loop with recursion
     * 2. suppose i = m+n, if i >= m*n, i is the breakdown number we want, else m*n
     * is the number we want
     * dp[i]: the max product we can get for num i
     * algo:
     * 1. create dp, fill dp will -1, and initialise dp[0]=0,dp[1]=0,dp[2]=1, which
     * stands for the max product 0, 1, and 2 can get, call dfs(n)
     * 2. in dfs:
     * a. base cond, if n < 2 return 0, cuz when n=0 and n=1, their max product is 0
     * b. check if dp[n] is cached
     * c. in for loop, check each number from 1 to n-1, compare (i * dfs(n-1)) and
     * the (i*(n-1)) to get the tmp max product, and compare the tmp max product
     * with maxProduct and cache the greater one for n
     * 3. return dfs(n)
     */

    class Solution {
        private int[] dp;

        public int integerBreak(int n) {
            this.dp = new int[n + 1];
            Arrays.fill(this.dp, -1);
            return dfs(n);
        }

        private int dfs(int n) {
            // when n=0 || n=1, the max product is 0
            if (n < 2)
                return 0;
            // check if max prodcut of n is cached
            if (dp[n] != -1)
                return dp[n];

            // check every number from 1 to n-1 to find the max product
            int maxProduct = 0;
            for (int i = 1; i < n; i++) {
                int tmpMax = Math.max(dfs(n - i), (n - i));
                maxProduct = Math.max(maxProduct, i * tmpMax);
            }
            dp[n] = maxProduct;
            return dp[n];
        }
    }

    /*
     * tabulation
     * dp[i]: the max product of breakdown nums for int i
     * dp[i] = max(j * dp[i-j], j * (i-j))
     */
class Solution {
    public int integerBreak(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;
        for(int i = 3; i <= n; i++){ // i stands for integer n
            for(int j = 1; j < i; j++){
                int max = Math.max(i-j, dp[i-j]);
                dp[i] = Math.max(dp[i], j*max);
            }
        }        
        return dp[n];
    }

    /*
     * 2 <= n <= 58
     * k >= 2
     * 
     * n = 2 -> 1+1 -> 1*1 = 1
     * n = 3 -> 1+2 -> 1*2 =2
     * -> 1+1+1 -> 1*1*1 = 1
     * -> 2+1 -> 2*1 = 2
     * n = 10 -> 2*5 -> 2^5 = 32
     * -> 3+3+4 -> 35
     * -> 3+3+2+2 -> 36
     * dfs(n, initNum) n = 2, initNum = 1, initNum < n
     * base cond: 1. initNum == n != gobalN, return n
     * 2. initNum > n || n == gobalN, return 0
     * |
     * 1(initNum) skip
     * 1*dfs(2-iniNum,initNum) 1*(n,initNum+1)
     * (1*dfs(2-1, 1)) 1*dfs(2, 2)
     * or Max of
     * 1*dfs(2-iniNum,iniNum+1)
     * (1*dfs(1,2))
     * 
     * retrun the max of right subtree and left subtree
     * 
     * 
     * dfs(3, 1)
     * | |
     * 1(init)*dfs(3-1,1+1)->dfs(2,2)<-2 dfs(3,1+1) -> dfs(3,2)
     * or | |
     * 1(init)*dfs(3-1,1)->dfs(2,1)-<1 2*dfs(3-2,2+1)<-0 dfs(3,3)
     * or 2*dfs(3-2,2)<-0 rerurn 0
     * dfs(2,2)<-2 dfs(2,1) <- 1
     * | | |
     * return 2  1*dfs(2-1,1+1) 1*dfs(2-1,1)
     * | |
     * dfs(1,2) dfs(1,1)
     * return 0 return 1
     * 
     * 
     * 
     * My solution: decision tree without cache
     * background knowledge:
     * to make the product as great as possible, we exclude 0 from the pos int set,
     * i.e, 4=0+4 but 0*4 = 0, and such a feature applies to all
     * algo:
     * 1. we pass the n to be break down and initial value (initVal) 1 to dfs,
     * because we want to explore as many different numbers, so we need to start
     * explore from 1, which leads to dfs(n, 1) in main function
     * 2. in dfs(n, initVal) decision tree, we have 3 choices:
     * a. initVal * dfs(n-initVal, initVal+1)
     * initVal means a breakdown number of n, n-initVal means the amount of num
     * available, initVal+1 stands for the initial value we will subtract from
     * remaining num, we increase initVal by 1 is because we want to explore every
     * possible breakdown number
     * b. initVal * dfs(n-initVal, initVal)
     * initVal is a current breakdown num we got, n-initVal means the amount of
     * remaining num, initVal means we also want to explore the possibily that there
     * can be multiple equal breakdown numbers in the result, such as 10=3+3+4
     * c. dfs(n, initVal+1)
     * this means we skip the current initial value, and goes directly to the next
     * possible initial value
     * 3. base cond
     * based on the decision tree, we have 2 base case:
     * a. n == initVal and initVal != globalN
     * the second half of the condition is because as we are incresing the initVal,
     * initVal might become equal to globalN, in that case, the number that will
     * multple initVal is going to be 0, which will lead to a min product,
     * contradictory to our expected result, so we want to exclude the scenario. the
     * first half of the condition is because we when n == initVal, we get nothing
     * to subtract to n anymore, so we know the currect initVal is the last number
     * in the breakdown num set, so we need to return initVal
     * b. n < initVal || initVal = globalN
     * n < initVal: if n < initVal, that means the the sum of breakdown nums we have
     * got along the path so far and initVal has exceed n, so the initVal won't be
     * an option and such path is invalid, we need to return 0
     * initVal = globalN: if initVal is equal to globalN, the number that is added
     * to intiVal can only be 0, which is an invalid answer
     * 
     * Time: 0(n^3)
     * Space: O(logn)
     * 
     */
    class Solution {
        private int n = 0;

        public int integerBreak(int n) {
            this.n = n;
            return dfs(n, 1);
        }

        private int dfs(int num, int initVal) {
            if (num == initVal && initVal != n)
                return num;
            if (num < initVal || initVal == n)
                return 0;

            return Math.max(
                    Math.max(initVal * dfs(num - initVal, initVal + 1), initVal * dfs((num - initVal), initVal)),
                    dfs(num, initVal + 1));
        }
    }
}
