// https://leetcode.com/problems/set-mismatch/description/
public class SetMismatch {
    /*
     * optimal solution:
     * reference1: https://www.youtube.com/watch?v=d-ulaeRBA64
     * reference2: https://www.youtube.com/watch?v=AK6hOsLqn1I
     * 
     * nums: [4, 3, 3, 2]
     * i: 0 1 2 3
     * index: 3 -> [4, 3, 3, -2]
     * index: 2 -> [4, 3, -3, -2]
     * index: 2 -> [4, 3, -3, -2] res:[3]
     * index: 1 -> [4, -3, -3, -2]
     * 
     * 2nd pass: [4, -3, -3, -2]
     * i=0, which means index = i+1 =0+1 was't handled in the first pass
     * so 1 is the missing element
     * [2,3,2]
     * i=0, index=2-1=1, [2,-3,2]
     * i=1, index=3-1=2, [2,-3,-2]
     * i=2, index=2-1=1, [2,-3,-2] -> save i=2 to res[0] as duplicate element
     */
    public int[] findErrorNums(int[] nums) {
        int[] res = new int[2]; // [duplicate, missing]
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0)
                nums[index] = -nums[index];
            else
                res[0] = Math.abs(nums[i]);
        }
        // second pass
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                res[1] = (i + 1);
        }
        return res;
    }
}
