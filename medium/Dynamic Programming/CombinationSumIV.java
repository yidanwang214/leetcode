// https://leetcode.com/problems/combination-sum-iv/editorial/
public class CombinationSumIV {
    /*
     * memo
     * dp[i]: the number of possible combinations for target i
     * reference1: https://www.youtube.com/watch?v=dpq9n85yJ-s
     * reference2:
     * https://leetcode.com/problems/combination-sum-iv/solutions/85036/1ms-java-dp-
     * solution-with-detailed-explanation/
     * Time: O(tagret) <- calculate target num of subproblems
     * Space: O(target) <- length of recursive stack is at most target, dp aray
     * takes target length
     */
    private int[] dp;
    private int[] nums;

    public int combinationSum4Memo(int[] nums, int target) {
        this.nums = nums;
        dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        return dfs(target);
    }

    private int dfs(int target) {
        // if target < 0
        if (target < 0)
            return 0;
        if (target == 0)
            return 1;
        if (dp[target] != -1)
            return dp[target];
        // explore different possibilities
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += dfs(target - nums[i]);
        }
        dp[target] = res;
        return dp[target];
    }

    /*
     * tabulation
     * reference:
     * https://www.bilibili.com/video/BV1V14y1n7B6/?spm_id_from=pageDriver&vd_source
     * =c74db3b5742c8630d40add5529146de2
     * iterate over items before knapsack, the result we get is the COMBINATION of
     * putting items into the knapsack
     * iterate over knapsack before items, the result we get is the PERMUTATION of
     * putting items into the knapsack
     * 关于组合和排列的问题还是有些不解。以下仅为自己的理解：先遍历物品后遍历背包是这样，比如，外层循环固定coins【1】，在内层循环遍历背包时，
     * 随着背包不断增加，coins【1】可以重复被添加进来，而由于外层循环固定了，因此coins【2】只能在下一次外层循环添加进不同大小的背包中，这么看的话，
     * coins【i+1】只能在coins【i】之后了；如果先遍历背包后遍历物品，那么外层循环先固定背包大小j，然后在大小为j的背包中循环遍历添加物品，
     * 然后在下次外层循环背包大小变为j+1，此时仍要执行内层循环遍历添加物品，也就会出现在上一轮外层循环中添加coins【2】的基础上还能再添加coins【1】
     * 的情况，那么就有了coins【1】在coins【2】之后的情况了。
     * 1. dp[j]: the num of combinations of i previous items that add up to j
     * 2. equation dp[j] = dp[j]+dp[j-nums[i]]
     * 3. initialisation: dp[0]=1
     * 4. ORDER!! iterate knapsack capacity before items, so that we can get
     * permutations
     * Time: O(nums.length * target)
     * Space: O(target)
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        // iterate over capacity first
        for (int j = 1; j < target + 1; j++) {
            for (int i = 0; i < nums.length; i++) {
                if (j - nums[i] >= 0)
                    dp[j] += dp[j - nums[i]];
            }
        }
        return dp[target];
    }
}
