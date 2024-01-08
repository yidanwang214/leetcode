// https://leetcode.com/problems/max-sum-of-a-pair-with-equal-sum-of-digits/

public class MaxSumOfAPairWithEqualSumOfDigits {

    /*
     * optimal solution1: two sum + array
     * the maxSum can be 81 as nums[i] <= 10^9
     * reference:
     * https://leetcode.com/problems/max-sum-of-a-pair-with-equal-sum-of-digits/
     * solutions/2292559/two-sum/
     * time: O(n)
     * space: O(1)
     */
    public int maximumSum(int[] nums) {
        int[] sumArr = new int[82];
        int res = -1;
        for (Integer num : nums) {
            int digits = 0;
            for (int n = num; n != 0; n /= 10) {
                digits += n % 10;
            }
            if (sumArr[digits] != 0) {
                res = Math.max(res, sumArr[digits] + num);
            }
            sumArr[digits] = Math.max(sumArr[digits], num); // always save the biggest num in sumArr[digits], with which
                                                            // we get get the maxSum when we access it the second time
        }
        return res;
    }

    /*
     * optimal solution2: two sum + hashmap
     * the maxSum can be 81 as nums[i] <= 10^9
     * reference:
     * https://leetcode.com/problems/max-sum-of-a-pair-with-equal-sum-of-digits/
     * solutions/2292615/java-clean-simple/
     * solutions/2292559/two-sum/
     * time: O(n)
     * space: O(n)
     */

    // two sum + hashmap
    public int maximumSum(int[] nums) {
        HashMap<Integer, Integer> sumAndMaxNum = new HashMap<Integer, Integer>();
        int res = -1;
        for (int num : nums) {
            int digitSum = 0;
            for (int n = num; n != 0; n /= 10) {
                digitSum += n % 10;
            }
            if (!sumAndMaxNum.containsKey(digitSum)) {
                sumAndMaxNum.put(digitSum, num);
            } else {
                res = Math.max(res, sumAndMaxNum.get(digitSum) + num);
                sumAndMaxNum.put(digitSum, Math.max(sumAndMaxNum.get(digitSum), num));
            }
        }
        return res;
    }

    /*
     * my approach: hashmap
     * pseudo
     * 1: sort nums
     * 2. iterate through the numbers, calculate the sum of digits for each
     * element, and store the sum in a hashmap of type <Integer,
     * ArrayList<Integer>>. The Integer represents the sum of digits for each
     * element, while the ArrayList saves the indices of numbers whose digit sums
     * are equal. Use a varible maxSum to store the max value.
     * 2.1 if the sum of digits not in hashmap, then put the (sum, index) pair into
     * hashmap
     * 2.2 if the sum of digits already in hashmap
     * 2.2.1 if the size of sum's corresponding indices is 1, we simply add the new
     * index before or after the existing index depending on their values, and
     * compare the sum of the two indices' value in nums
     * with maxSum.
     * 2.2.2 if the size of sum's corresponding indices is 2, we compare the currect
     * index with the 2 inidces inside of hashmap
     * 2.2.2.1 if the current index is less than the first index in hashmap, don't
     * need to updae indices or minMax, go to the next iteration
     * 2.2.2.2 if the current index is greater than the first index in hashmap but
     * less than the second index, replace the first index and update maxSum
     * 2.2.2.3 if the current index if greater than the second indices in hashmap,
     * move the first index, add the current index at the end of the indices
     * arraylist and then update maxSum
     * 
     * If an entry's value has a size greater
     * than or equal to two, sort it. Then, obtain the sum of the last two indices'
     * values in nums. Use a variable 'max' to compare and save the greater number
     * time: O(nlog)+O(n)=O(nlogn), nlog for sorting, n accounts for the
     * comparisons performed 2n times, each time checking a number's index against
     * the indices of numbers with the same digit sum
     * space: O(n) for hashmap
     */

    // sort nums
    Arrays.sort(nums);

    // create hashmap
    HashMap<Integer, ArrayList<Integer>> sumFreq = new HashMap<Integer, ArrayList<Integer>>();

    // 7 4 9 9 7
    // [7, 13, 18, 36, 43]
    // ieterate nums
    int maxSum = Integer.MIN_VALUE;for(
    int i = 0;i<nums.length;i++)
    {
        int sum = getSum(nums[i]);
        // if the sum has already in hashmap
        if (sumFreq.containsKey(sum)) {
            ArrayList<Integer> indices = sumFreq.get(sum);
            // if the arraylist has only 1 index
            if (indices.size() == 0) {
                indices.add(i);
                continue;
            } else if (indices.size() == 1) {
                // make the array list sorted
                if (indices.get(0) < i) {
                    indices.add(i);
                } else if (indices.get(0) > i) {
                    indices.add(0, i);
                }
            } else if (indices.size() == 2) {
                if (indices.get(0) > i) {
                    continue;
                } else if (indices.get(0) < i && i < indices.get(1)) {
                    indices.set(0, i);
                } else if (i > indices.get(1)) {
                    indices.add(i);
                    indices.remove(0);
                }
            }
            // compare with existing maxSum
            maxSum = Math.max(maxSum, nums[indices.get(0)] + nums[indices.get(1)]);
        } else { // if the arraylist doesn't exist in the hashmap
            ArrayList<Integer> indices = new ArrayList<Integer>();
            indices.add(i);
            sumFreq.put(sum, indices);
        }
    }if(maxSum!=Integer.MIN_VALUE)return maxSum;return-1;
    }

    public int getSum(int num) {
        int res = 0;
        while (num != 0) {
            res += num % 10;
            num = num / 10;
        }
        return res;
    }

}
