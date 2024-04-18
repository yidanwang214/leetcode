public class HouseRobber2 {
    // Time: O(n)
    // Space: O(n)
    // reference:
    // https://www.bilibili.com/video/BV1oM411B7xq/?spm_id_from=pageDriver&vd_source=c74db3b5742c8630d40add5529146de2
    public int rob(int[] nums) {
        // edge case
        if (nums.length == 1)
            return nums[0];

        int[] nums1, nums2;
        nums1 = Arrays.copyOfRange(nums, 0, nums.length - 1);
        nums2 = Arrays.copyOfRange(nums, 1, nums.length);
        return Math.max(helper(nums1), helper(nums2));
    }

    private int helper(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], (i - 2 >= 0 ? dp[i - 2] : 0) + nums[i]);
        }
        return dp[nums.length - 1];
    }
}
