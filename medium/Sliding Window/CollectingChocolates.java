// https://leetcode.com/problems/collecting-chocolates/description/
/*
reference: https://www.youtube.com/watch?v=rjfWqr-eFLI
nums: size n, the cost of collecting different chocolates
[20, 1, 15] min[m, m, m, m]
 0   1   2  -> sum = 0, min[20, 1, 15], sum = 36, res= 36

[20, 1, 15] 
 2   0   1  -> sum = 5, min[1, 1, 15], sum=5+1+1+15 = 22, res = 22

[20, 1, 15]
 1   2   0  -> sum = 10, min[1, 1, 1], sum= 10 + 1 + 1 + 1 = 13, res = 13

algo:
1. create an array min of the same size as nums, and fill it with Max_Value
2. rotate the array for r times, where r ranges from 0 to the size of nums, initial cost for each rotation is cost = r * cost for rotation;
---inside of each rotation, get the smaller value between mins[i] and nums[(i+r)%size of the array], which means we compare the historic smallest cost for i-th type of choco with the the cost of i-th type of choco in r-th rotation, this way we can make sure the values saved in mins are smallest value for each type of choco
 */

import java.util.Arrays;

public class CollectingChocolates {
    public long minCost(int[] nums, int x) {
        long res = Long.MAX_VALUE;
        int len = nums.length;

        // historic smallest cost for i-th type of choco
        int[] historyMin = new int[len];
        Arrays.fill(historyMin, Integer.MAX_VALUE);

        // rotation
        for (int r = 0; r < len; r++) {
            // for each rotation, the initial value of sum is rounds * cost for 1 rotation
            long sum = (long) r * x;
            for (int i = 0; i < len; i++) {
                historyMin[i] = Math.min(historyMin[i], nums[(i + r) % len]);
                sum += historyMin[i];
            }
            res = Math.min(res, sum);
        }
        return res;
    }
}
