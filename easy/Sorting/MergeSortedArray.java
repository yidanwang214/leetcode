// https://leetcode.com/problems/merge-sorted-array/description/

public class MergeSortedArray {
    // reference:
    // https://leetcode.com/problems/merge-sorted-array/solutions/3436053/beats-100-best-c-java-python-and-javascript-solution-two-pointer-stl/
    // time: O(m+n)
    // space: O(1)
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        // we use j>=0 because if j < 0 while i > 0, that means all elements in nums2
        // are inserted to num1, and the remaining elements in nums1 are already sorted
        // due to its non-decreasing order
        while (j >= 0) {
            if (i >= 0 && nums1[i] >= nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

    }
}
