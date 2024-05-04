public class MaximumSubarray {
    // kadane's algorithm: https://www.youtube.com/watch?v=86CQq3pKSUw
    // Time: 0(n)
    // Space: O(1)
    public int maxSubArray(int[] nums) {
        int gloMax = nums[0], curMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curMax = Math.max(nums[i], nums[i] + curMax);
            if (curMax > gloMax)
                gloMax = curMax;
        }
        return gloMax;
    }

}