// https://leetcode.com/problems/partition-array-such-that-maximum-difference-is-k/description/

import java.util.Arrays;

public class PartitionArrayMaxDiffK {

    // // brute force
    // // time complexity: O(nlogn) dominated by sorting
    // // space complexity: O(1)
    // public int partitionArray(int[] nums, int k) {
    // Arrays.sort(nums);
    // int subSeqNum = 0;
    // int min = nums[0];
    // for (int i = 1; i < nums.length; i++) {
    // if (nums[i] - min <= k) {
    // continue;
    // } else {
    // subSeqNum++;
    // min = nums[i];
    // }
    // }
    // return ++subSeqNum;
    // }
}
