import java.util.HashSet;

public class LargestPositiveIntegerThatExistsWithItsNegative {
    /*
     * pseudo:
     * iterate through array using hashset, also keep track of the largest num,
     * if a number's additive inverse is in the map and if it's absolute value is
     * greater than the
     * larget num, update largest num
     */
    public int findMaxK(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int res = -1;
        for (int i : nums) {
            if (set.contains(-i)) {
                res = Math.max(Math.abs(i), res);
            } else if (!set.contains(i)) {
                set.add(i);
            }
        }
        return res;
    }
}
