// https://leetcode.com/problems/last-stone-weight-ii/

/*
// definition:
// dp[j]: when the capacity of the knapsack is j, the max value the knapsack has
//       -> when the sum of selected elements is j, the max sum of the combonation

// equation:
// dp[j] = max(dp[j], dp[j-nums[i]]+nums[i])

// initialisation:
// dp[j] = 0

// order:
// -> outer loop: forward order
// -> inner loop: reverse order

// time: O((sum/2)*n) -> the sum of the array * the length of the array
// space: O(sum/2) -> the size of the dp array

// algo
// divide the sum of the array into half
// see if we can get dp[j] where j == half and dp[j] ==half
 */

public class LastStoneWeight2 {
    public int lastStoneWeightII(int[] stones) {
        // calculate half
        int sum = 0;
        for (int i = 0; i < stones.length; i++)
            sum += stones[i];
        int half = sum / 2;

        // create dp
        int[] dp = new int[half + 1];
        // ieterate through every stone
        for (int i = 0; i < stones.length; i++) {
            // iterate every possible weight from half to the weight of current stone
            // (stone[j])
            for (int j = half; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }

        // get the difference of the dp[half+1] and (half-dp[half+1])
        // the result is the smallest possbile weight of the left stone
        System.out.println(dp[half] + " " + half + " " + dp.length);
        return Math.abs((sum - dp[half]) - dp[half]);
    }
}
