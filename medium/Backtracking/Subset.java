import java.util.ArrayList;
import java.util.List;
// https://leetcode.com/problems/subsets/

/*
my approach/ iterative approach:
Time: 2^n
Space: O(n* 2^n) there are 2^n subsets, and each subset can have at most n elements.
 */
public class Subset {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs(new ArrayList<>(), nums, 0);
        return res;
    }

    private void dfs(List<Integer> subset, int[] nums, int startIndex) {
        // base cond
        if (startIndex >= nums.length)
            return;
        if (subset.size() == 0)
            res.add(subset);

        for (int i = startIndex; i < nums.length; i++) {
            // store the current state
            subset.add(nums[i]);
            res.add(new ArrayList<>(subset));
            // backtrack
            dfs(subset, nums, i + 1);
            // restore the previous state
            subset.remove(Integer.valueOf(nums[i]));
        }
    }

    /*
     * recurisve approach
     * reference:
     * https://leetcode.com/problems/subsets-ii/solutions/388566/subsets-i-ii-java-
     * solution-with-detailed-explanation-and-comments-recursion-iteration/
     * Time: O(2^n). dfs() is called twice for each element in the array, once when
     * incuding the element and once when excluding it.
     * Space: O(N*2^N). dominated by space for storing subsets in the result list.
     * Each of the generated subsets is being stored in the subsets list, which will
     * eventually contain 2n sets, each of size somewhere between 0 and n (with most
     * of the sets containing around n / 2 elements) - so the space complexity is
     * actually O(n * 2^n). Remeber, the result is a nested list, there will be 2^n
     * elements in res, the longest element is an array of size n.
     * ^reference:
     * https://stackoverflow.com/questions/29225659/is-the-space-complexity-of-this-
     * subset-algorithm-actually-on#:~:text=Each%20of%20the%20generated%20subsets,O(
     * n%202n).
     * 
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> numList = new ArrayList<>();

        result.add(new ArrayList<>()); // empty set
        dfs(0, nums, numList, result);
        return result;
    }

    private void dfs(int offset, int[] nums, List<Integer> numList, List<List<Integer>> result) {
        if (offset >= nums.length) {
            return;
        }
        int val = nums[offset];
        // pick / include current element
        numList.add(val);
        dfs(offset + 1, nums, numList, result);
        // add to result
        result.add(new ArrayList<>(numList));
        // not pick / exclude current element
        numList.remove(numList.size() - 1);
        dfs(offset + 1, nums, numList, result);
    }
}
