// question: if the nums = [1,2], which should we return -> stupid question, the length of nums is at least 4 due to the constrains 2 <= n <= 5000 and nums.length == 2 * n

import java.util.*;

public class NRepeatedElementInSize2NArray {
    /*
     * reference 1:
     * https://leetcode.com/problems/n-repeated-element-in-size-2n-array/editorial/
     * comments/409560
     * explanation: So if we think about the given array, we have n unique elements
     * plus n repeated elements.
     * 
     * Let say n repeated element x. And for simplicity, let say the rest of the
     * elements are from 1 to n. Then the array A would be one of the possible
     * permutations of (1, 2, 3, ..., n, x, x, ...., x) with n x's.
     * 
     * Except when A = [1, x, 2, x, 3, x, ..., n, x] or A = [x, 1, x, 2, x, 3, ...,
     * x, n], which is the second bullet point, there must appear [..., x, x, ...]
     * somewhere in the array, which is the first bullet point. Before and after [x,
     * x] could be either [a, b], [a, x], [x, a], or [x, x], where a and b are
     * random two numbers from 1 to n.
     * 
     * Therefore, if we check 4 consecutive elements, we will find at least one case
     * that has two same numbers in that 4 elements, which is the x we are looking
     * for. Hope this helped! *
     * 
     * 
     * reference 2:
     * https://leetcode.com/problems/n-repeated-element-in-size-2n-array/editorial/
     * comments/1381532
     * explanation: Simple way to understand is: Assume the repeating element is x
     * and
     * all other elements are 1,2,..,n. If you try to keep the x's as far as
     * possible
     * from each other, the best you can do is x,1,x,2,x,3,...,x,n or
     * 1,x,2,x,...,n,x.
     * So at least one subarray of size 3 should catch the duplicate. But there is
     * one
     * edge case [x,1,2,x]. Here, any subarray of size 3 will not be enough. Hence,
     * we choose to check all subarrays of size 4 and then we have no unhandled
     * edge cases. We are guaranteed to find the duplicate if we check all subarrays
     * of size 4.
     * To reduce the constant C of O(C*n) complexity further,
     * we can check all subarrays of size 3 for duplicates and then also
     * the first and last element of the full array separately. This can
     * catch all edge cases and be faster.
     */
    public int repeatedNTimes(int[] nums) {
        for (int k = 1; k <= 3; ++k)
            for (int i = 0; i < nums.length - k; ++i)
                if (nums[i] == nums[i + k])
                    return nums[i];
        throw null;
    }

    /*
     * reference to solution 3 on the link:
     * https://leetcode.com/problems/n-repeated-element-in-size-2n-array/solutions/
     * 208563/java-c-python-o-1-solution/
     * Time complexity amortized O(4)
     * space O(1)
     */
    public int repeatedNTimes2(int[] nums) {
        int i = 0, j = 0, n = nums.length;
        while (i == j || nums[i] != nums[j]) {
            i = (int) (Math.random() * n);
            j = (int) (Math.random() * n);
        }
        return nums[i];
    }

    /*
     * pedudo:
     * 1. from the 0th-index 3rd position scan the nums array, check if it is the
     * same as nums[i-1] or nums[i-2]
     * 2. after the iteration, if we still can't get the result, we can return
     * nums[0] for cases [1, 2, 3, 1], which the repeated element is sparsely
     * distributed in the array
     * Time: O(n)
     * Space: O(1)
     */
    public int repeatedNTimes3(int[] nums) {
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] || nums[i] == nums[i - 2])
                return nums[i];
        }
        return nums[0]; // for cases [1, 2, 3, 1];
    }

    /*
     * pseudo:
     * 1. sort
     * 2. access the value of index midPoint(num.length/4) and
     * midPoint(num.length/3)
     * 3. if the nums[midPoint] == nums[midPoint-1], return nums[midPoint-1]
     * if the nums[midPoint] == nums[midPoint+1], return nums[midPoint+1]
     * reason: [1, 2, 2, 2, 3, 5], [1, 1, 1, 2, 3, 5], [1, 2, 3, 5, 5, 5]
     * time: O(nlogn)
     * space: O(1)
     */
    public int repeatedNTimes4(int[] nums) {
        // sort array
        Arrays.sort(nums);
        // get mid point
        int mid = nums.length / 2;

        // compare if midPoint equal to the valuue before and after it
        // first, check if nums[mid-1] == nums[mid-2], cuz mid = 2n/2 = n, which is
        // always the start point of the right half of the array. so we need to also
        // check the left half of the array to handle cases such as [1, 1, 1, 2, 3, 5],
        // the end point of the left half of the array is nums[mid-1];
        if (nums[mid - 1] == nums[mid - 2])
            return nums[mid - 1];
        return nums[mid]; // equivalent of if(nums[mid] == nums[mid+1] || nums[mid] == nums[mid-1]) return
                          // nums[mid]

    }

    public static void main(String[] args) {
        for (int k = 1; k <= 3; ++k) {
            System.out.println("k: " + k);
        }
    }

}
