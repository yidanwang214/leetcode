import java.util.HashMap;

public class ContinuousSubarraySum {
    /*
     * I can't think of the O(n) solution :(
     * reference: https://www.youtube.com/watch?v=OKcrLfR-8mE
     * algo:
     * 1. create a hashmap that stores the remainder of prefix sum and the end index
     * of the prefix sum
     * i.e. in array [2, 7, 4], k = 5, the hashmap saves (2,0),(4,1),(3,2)
     * 2. insert (0,-1) to hashmap to handle edge case where the first element is
     * divisable by k
     * 3. scan through the array, update sum by arr[i], get the remainder of sum
     * divided by k:
     * -> if remainder doesn't exist in map, put (remainder, i) into map
     * -> else if remiander exists in map and the difference between the existing
     * key's value and i is greater than 1 return true
     * 4. return false
     * 
     * Time: O(n)
     * Space: O(n)
     * 
     * 
     * edge case: nums = [5,0,0,0], k = 3, output: true <-- [0, 0, 0]
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        // create hashmap and sum
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        // scan through array nums
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int r = sum % k;
            if (!map.containsKey(r))
                map.put(r, i);
            else if (i - map.get(r) > 1)
                return true;
        }
        return false;
    }
}
