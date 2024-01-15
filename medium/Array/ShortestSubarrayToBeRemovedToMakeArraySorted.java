// https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted/
public class ShortestSubarrayToBeRemovedToMakeArraySorted {
    /*
     * reference: https://www.youtube.com/watch?v=pNNvmjNJcu8
     * A subarray is a CONTIGUOUS subsequence of the array.
     * arr = [13,0,14,7,18,18,18,16,8,15,20]
     * output is 8 [0,14,7,18,18,18,16,8], not 5 [0, 7, 16, 8, 15] because the
     * subarray has to be CONTIGUOUS!
     * 
     * main idea: find the increasing subarrays from left and from right
     * respectively, to make the subarrays as long as possible and also make the
     * greatest value in the left sub array is not more than the least value in the
     * right subarray
     * 1. [0:i] non-decreasing
     * 2. [j:n-1] non-decreasing
     * 3. arr[i] <= arr[j]
     * [X X X] [X X X X] [X X X]
     * 0 i j n-1
     * 
     * pseudocode: 2 pointer, i from left, i from right
     * 1. iterate arr from right using j and make sure every scanned element fulfill
     * the condition that arr[j-1]<= arr[j]
     * 2. iterate arr from left using i with the condition that arr[i]<=arr[i+1]
     * -> inside of the array, while arr[i]>arr[j], we need to rigth shift
     * j/decrease j to find the point where arr[i]<=arr[j]
     * update res
     * 
     * Time: O(n)
     * Space: O(1)
     */
    public int findLengthOfShortestSubarray(int[] arr) {
        int len = arr.length;
        int res = len - 1; // suppose we are deleting all elements in arr
        int j = len - 1; // left pointer

        // geth the start point of right non-decreasing subarray
        while (j > 0 && arr[j - 1] <= arr[j])
            j--;
        // if arr is already non-decreasing, we got nothing to remove
        if (j == 0)
            return j;
        // since elements from j to the end of the array are non-decreasing, the num of
        // elements we might wanna remove equals to the num of elements right of j,
        // which is j
        res = Math.min(res, j);

        // ietrate arr from right using i
        // i stands for the first element in unsorted array
        for (int i = 0; i < len - 1; i++) {
            // when we reach the last element of the rigth half non-decreasing array
            if (i - 1 >= 0 && arr[i] < arr[i - 1])
                break;
            // right shift j if the arr[j] is greater than the value in arr[i]
            while (j < len && arr[i] > arr[j])
                j++;
            // since either i or j or both of them have changed, we need to update res, j
            // means there are j num of elements before j, i means there are i-1 num of
            // non-decreasing elements until i, so j-i-i is the num of unsorted elements
            res = Math.min(res, j - i - 1);
        }
        return res;
    }
}
// [6,3,10,11,15,20,13,3,18,12]
// 0 1 2 3 4 5 6 7 8 9
// j
// i