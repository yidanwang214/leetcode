// https://leetcode.com/problems/partition-equal-subset-sum/description/

public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int half = 0;
        for (int i = 0; i < nums.length; i++)
            half += nums[i];
        if ((half & 1) != 0)
            return false;

        half = half / 2;
        int[] dp = new int[half + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = half; j >= nums[i]; j--) {
                // when the knapsack volumn is j, the max value is dp[j]
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
                if (j == half && dp[half] == half)
                    return true;
            }
        }
        return false;
    }
}

/*
 * nums = [1,5,11,5]
 * dp=[0,1,1,1,1,5,6,6,6,6,6,11]
 * 
 * i=0, nums[0]=1
 * -> j=11, dp[11]=1
 * -> j=10, dp[10]=1
 * -> ...
 * -> j=1, dp[1]=1
 * 
 * i=1, nums[1]=5
 * -> j=11, dp[11]=dp[6]+nums[1]=1+5=6
 * -> j=10, dp[10]=dp[5]+nums[1]=1+5=6
 * ->...
 * -> j=6,dp[6]=dp[1]+nums[1]=1+5=6
 * ->j=5,dp[5]=dp[0]+num[1]=0+5=5
 * 
 * i=2, nums[2]=11
 * ->j=11, dp[11-nums[2]]+nums[2]=0+11=11
 * 
 * i=3, nums[3]=5
 * -> j=11, max(dp[11]=11, dp[11-nums[3]]+nums[3]=11)=11
 * -> j=10, max(dp[10]=6, dp[10-nums[3]]+nums[3])=10
 * -> j=9, max(dp[9]=6, dp[9-nums[3]]+nums[3]=6)=6
 * -> j=8, max(dp[8]=6, dp[8-nums[3]]+nums[3]=6)=6
 * -> ...
 * -> j=5, max(dp[5]=5, dp[5-nums[5]]+nums[3]=5)=5
 */