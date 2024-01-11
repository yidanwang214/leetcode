
// https://leetcode.com/problems/number-of-ways-where-square-of-number-is-equal-to-product-of-two-numbers/description/
import java.util.HashMap;

public class TwoProduct1577 {
    /*
     * optimal solution:
     * https://leetcode.com/problems/number-of-ways-where-square-of-number-is-equal-
     * to-product-of-two-numbers/
     * Time: O(nm), n and m represent the length of num1 and num2 respectively
     * Space: O(1)
     * pseudo: nested loop + HashMap that keeps track of the occurence of each
     * number in the array in the nested loop
     * 1. First, iterate through nums1. For each element, compare if its square is
     * divisible by each element in nums2:
     * -> Look up the key (square/element) in a map. The value, representing the
     * occurrence of the key, indicates how many times the key has appeared before
     * the current number. The key's occurrence represents the possible combinations
     * we can get for the current element we are accessing in nums2.
     * 2. Second, iterate through nums2. For each element, compare if its squre is
     * divisable by each element in num1:
     * -> Look up the key (square/element, which is num's
     * multiplicative/contributing factor) in a map. The value, representing the
     * occurence of the key, indicates how many times the key has appeared before
     * the current number. The key's occurence represents the possible combinations
     * we can get for the current element we are accessing in nums1.
     * Attention: using type Long in code is important to accuracy of result!!!
     * 
     * eg:
     * nums1: [20]
     * nums2: [4,5,5,5,4,4]
     * map: (4,3)+(5,3)
     * res: 1+1+1+3+3
     */

    public int numTriplets(int[] nums1, int[] nums2) {
        long res = 0;
        for (long n : nums1) {
            res += twoProduct(n * n, nums2);
        }
        for (long n : nums2) {
            res += twoProduct(n * n, nums1);
        }
        return (int) res;
    }

    public long twoProduct(long square, int[] arr) {
        // key represents a product(square/arr[i]), and value represents the products
        // occurance before the number(arr[i]) we are accessing
        HashMap<Long, Long> map = new HashMap<>();
        // stores the number of combinations
        long cnt = 0;
        // iterate over the array
        for (long num : arr) {
            // if square is divisable by the num we are acessing
            if (square % num == 0) {
                // we look up the occurance of num's multiplicative/contributing factor in map.
                // The occurance represents before num, how many times its multiplicative factor
                // has appeared and indicates the number of combinations between the num and
                // it's multiplicative factor
                cnt += map.getOrDefault((square / num), 0L);
            }
            // update the occurance of num to the map
            map.merge(num, 1L, Long::sum);
        }
        return cnt;
    }
}
