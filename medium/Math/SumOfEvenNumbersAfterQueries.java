// https://leetcode.com/problems/sum-of-even-numbers-after-queries/description/
public class SumOfEvenNumbersAfterQueries {
    /*
     * nums[] queries
     * nums[]: 1,2,3,4
     * queries:(2,1) (5,3) (-1,0) (3,2)
     * answer: 4, 9, 0, 5
     * 
     * pesudo:
     * 1. create a list called res used to store the result
     * 2. get the sum of even numbers and odd numbers in nums by interating through
     * the nums
     * 3. iterate through queries:
     * if vali + nums[indexi] is odd:
     * if nums[indexi] is even, we subtract nums[indexi] from the sumEven, add the
     * (vali + nums[indexi]) to sumOdd, append the
     * else, simply add vali to sumOdd
     * update nums[indexi]
     * append sumEven to res
     * if vali + nums[indexi] is even :
     * if nums[indexi] is even, simply add vali to it sumEven
     * else subtract nums[indexi] from sumOdd, then add (vali + nums[indexi]) to
     * sumEven
     * nums[indexi]
     * append sumEven to res
     * 4. return res
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        // create res list, sumEven and sumOdd
        int[] res = new int[queries.length];
        int sumEven = 0;
        int sumOdd = 0;

        // get the sum of even numbers and sum of odd numbers by ietrating through nums
        for (int num : nums) {
            if ((num & 1) == 1)
                sumOdd += num;
            else
                sumEven += num;
        }

        // iterate through queries
        for (int i = 0; i < queries.length; i++) {
            int val = queries[i][0];
            int idx = queries[i][1];
            // get the current number we are accessing from nums
            int curNum = nums[idx];
            // if the sum of val and curNum is even
            if (((val + curNum) & 1) == 0) {
                // if curNum is even
                if ((curNum & 1) == 0) {
                    sumEven += val;
                } else {
                    // if curNum and val are odd, subtract curNum from sumOdd, and add the sum of
                    // curNum and val to sumEven
                    sumOdd -= curNum;
                    sumEven += (val + curNum);
                }
            } else { // if the sum of val and curNum is odd
                // if val is even, curNum is odd, we need to subract even from sumEven, and
                // add(val + curNum) to sumOdd
                if ((val & 1) == 1) {
                    sumEven -= curNum;
                    sumOdd += (curNum + val);
                } else {
                    // if val is odd, curNum is even, we simply add val to sumOdd
                    sumOdd += val;
                }
            }
            // apppend uodated sumEven to res and update value of the current index in nums
            res[i] = sumEven;
            nums[idx] = val + curNum;
        }

        return res;
    }
}
