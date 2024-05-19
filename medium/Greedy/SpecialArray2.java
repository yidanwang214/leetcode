public class SpecialArray2 {
    // reference: https://leetcode.com/problems/special-array-ii/
    // Time: O(n)
    // Space: O(n)
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        boolean[] res = new boolean[queries.length];
        int[] count = new int[nums.length];
        count[0] = 1;
        for(int i = 1; i < nums.length; i++){
            if((nums[i]+nums[i-1]) % 2 == 0){
                count[i] = count[i-1]+1;
            } else count[i] = count[i-1];
        }
        
        for(int i = 0; i < queries.length; i++){
            int min = queries[i][0], max = queries[i][1];
            if(count[min] == count[max]) res[i] = true;
            else res[i] = false;
        }
        return res;
    }
}
