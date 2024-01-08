// https://leetcode.com/problems/maximum-sum-with-exactly-k-elements/description/
public class MaxSumWithExactlyKElements {

    // 1. sort array
    // 2. get greates number from the array
    // 3. return (number+(number+k-1))*k/2
    // time: O(nlogn) -> can be optimised to O(n) by scan the array and get the
    // maxNum
    // space: O(1)
    public int maximizeSum(int[] nums, int k) {
        Arrays.sort(nums);
        int num = nums[nums.length - 1];
        return (num + (num + k - 1)) * k / 2;
    }
}
