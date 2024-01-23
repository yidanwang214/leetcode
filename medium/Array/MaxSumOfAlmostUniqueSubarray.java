// https://leetcode.com/problems/maximum-sum-of-almost-unique-subarray/

import java.util.HashMap;
import java.util.List;

public class MaxSumOfAlmostUniqueSubarray {
    /*
     * nums = [2, 6, 7, 3, 1, 7], m = 3, k = 4
     * [2, 6, 7, 3] [6, 7, 3, 1][7, 3, 1, 7]
     * 
     * nums = [5,9,9,2,4,5,4], m = 1, k = 3
     * [5, 9, 9] [9, 9, 2][9, 2, 4][]
     * 
     * pesudo:
     * iterate over nums
     * -> extract arr[i:i+k-1]
     * -> for each element in subarray[i:i+k-1], use HashMap to check if each
     * element is already in HashMap, if not, save the element to HashMap, and
     * increment var uniq; else save element into the hashMap
     * -> check if uniq == m, if true, get the sum of the subarray and compare with
     * res, else go to the next iteration
     */
    public long maxSum(List<Integer> nums, int m, int k) {

        long res = 0;
        int len = nums.size();
        HashMap<Integer, Integer> map = new HashMap<>();
        long sum = 0;

        // iterate through nums
        for (int i = 0; i + k - 1 < len; i++) {
            List<Integer> sub = nums.subList(i, i + k);
            if (i == 0) {
                for (Integer n : sub) {
                    // if(!map.containsKey(n)){
                    // map.put(n);
                    // }
                    map.merge(n, 1, Integer::sum);
                    sum += (long) n;
                }
            } else {
                int newNum = sub.get(sub.size() - 1);
                // if(!map.containsKey(newNum)){
                // map.add(newNum);
                // }
                map.merge(newNum, 1, Integer::sum);
                sum += (long) newNum;
            }
            if (map.size() >= m)
                res = Math.max(res, sum);
            sum = sum - (long) sub.get(0);
            if (map.get(sub.get(0)) == 1) {
                map.remove(sub.get(0));
            } else {
                map.put(sub.get(0), map.get(sub.get(0)) - 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> ls = new List<>();
        ls.subList(0, 0)
    }
}
