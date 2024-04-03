public class TargetSum {

    class Solution {
        // 代码随想录:
        // https://www.bilibili.com/video/BV1o8411j73x/?spm_id_from=333.788&vd_source=c74db3b5742c8630d40add5529146de2
        /*
         * left + right = sum, left - right = target
         * -> left + (left - target) = sum
         * -> left = (sum + target) / 2
         */
        // 1. def -> dp[j]: dp[j] ways of making the total of elements equal to j
        // 2. equation -> dp[j] += dp[j-num[i]]
        /*
         * i.e, j=7, dp[7] represents dp[7] ways of making the total equal to 7;
         * nums[i]=3, the number we have now is 3,
         * dp[7-3] = dp[4] represents dp[4] ways of making total equal to 4,
         * if we know how many ways we can total to 4 (dp[4]), then adding 3 to dp[4] is
         * the same as dp[4]
         * 
         * more specificly, if there are 5 way to make total 4 (dp[4]=5),
         * if we wanna know how many ways do we need to make total 7 (fill dp[7]),
         * we can transfer the value of dp[4] to dp[7], cause 4+3=7, the number of
         * making total = 7 is inherits the number of making total = 4 when the number
         * we have is 3
         */
        // 3. initialisation: dp[0] = 1
        // if nums=[0], target = 0, then 1 ways of making total equal to 0
        // if nums=[0,0,0,0,0], target = 0 -> dp[0]:1, final dp[0]=32
        // 4. iteration order:
        // out loop: iterate every element in nums, forward
        // inner loop: ietrate from left subset to nums[i], backward, ensures every
        // element in nums used only once

        // time: O(m * n), m is the length of nums array, n is value of left
        // space: O(n): n is the value of left
        public int findTargetSumWays(int[] nums, int target) {
            // get sum
            int sum = 0;
            for (int num : nums)
                sum += num;

            // edge case
            if (target > sum || target < -sum)
                return 0;

            // calculate left subset, which is capacirty of the bag we wanna fill
            int left = 0;
            if ((sum + target) % 2 == 0)
                left = (sum + target) / 2;
            else
                return 0;

            // create dp
            int[] dp = new int[left + 1];
            dp[0] = 1;
            for (int i = 0; i < nums.length; i++) {
                for (int j = left; j >= nums[i]; j--) {
                    dp[j] += dp[j - nums[i]];
                }
            }
            return dp[left];
        }
        /*
         * nums: [1,1,1,1,1], target: 3, left = 4, dp = 1, 0, 0, 0, 0
         * i = 0, nums[0]=1, dp = 1, 1, 0, 0, 0
         * i = 1, nums[1]=1, dp = 1, 2, 1, 0, 0
         * i = 2, nums[2]=1, dp = 1, 3, 3, 1, 0
         * i = 3, nums[3]=1, dp = 1, 4, 6, 4, 1
         * i = 4, nums[4]=1, dp = 1, 5, 10, 10, 5
         */

        // 花花酱：https://www.youtube.com/watch?v=r6Wz4W1TbuI
        // range: [-sum, sum], dp size = 2sum+1
        // 1. def: dp[i][j]: dp[i][j] ways of making total equal to j when we selecting
        // previous i elements of nums array
        // 2. equation: dp[j] = dp[j-num[i]] + dp[j+num[i]] <- pascal's triangle
        // 3. intialisation: dp[0] = 1 -> 1 way of making total to be 0
        // 4. iteration order:
        // -> outloop, forward
        // -> innerloop, forward
        // time: O(n * m), n is the length of nums array, m is 2*sum+1 (length of dp
        // array)
        // space: O(2*sum+1), the length of dp array
        public int findTargetSumWays2(int[] nums, int target) {
            // calculate sum
            int sum = 0;
            for (int num : nums)
                sum += num;

            // handle edge case
            if (target < -sum || target > sum)
                return 0;

            // initialise dp
            int[][] dp = new int[nums.length + 1][2 * sum + 1];
            dp[0][sum] = 1;
            // since we use 0 to represent -sum, we need to calcualte offset
            int offset = sum;

            // ietrate every num in nums array
            // for(int i = 1; i < nums.length; i++){
            // // iterate every sum(size) of dp
            // for(int j = 0; j < 2*sum+1; j++){
            // dp[i][j] = (j-nums[i] >= 0 ? dp[i][j-nums[i]] : 0) + (j+nums[i] < 2*sum+1 ?
            // dp[i][j+nums[i]] : 0);
            // }
            // }

            for (int i = 0; i < nums.length; i++) {
                for (int j = nums[i]; j < 2 * sum + 1 - nums[i]; j++) {
                    if (dp[i][j] != 0) {
                        dp[i + 1][j + nums[i]] += dp[i][j];
                        dp[i + 1][j - nums[i]] += dp[i][j];
                    }
                }
            }
            return dp[nums.length][target + sum];
        }

        // decision tree
        private int len;
        private int[] nums;

        public int findTargetSumWays3(int[] nums, int target) {
            this.len = nums.length;
            this.nums = nums;
            return dfs(0, nums[0], target) + dfs(0, -nums[0], target);
        }

        private int dfs(int index, int sum, int target) {
            if (index == len - 1 && sum == target)
                return 1;
            if (index >= len - 1)
                return 0;

            return dfs(index + 1, sum + nums[index + 1], target) + dfs(index + 1, sum - nums[index + 1], target);

        }
    }
}