import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Subset2 {
    // iterative apporach
    // nums has to be sorted
    // time: O(n * 2^n)
    // space: O(n * 2^n)
    Set<List<Integer>> res = new HashSet<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums); // solve test case: [4, 4, 4, 1, 4]
        dfs(new ArrayList<>(), nums, 0);
        return new ArrayList<>(res);
    }

    private void dfs(List<Integer> subset, int[] nums, int idx) {
        // base cond
        if (idx >= nums.length)
            return;

        if (idx == 0)
            res.add(subset);

        // explore every possible subset
        for (int i = idx; i < nums.length; i++) {
            // save current state
            subset.add(nums[i]);
            // System.out.println(subset.toString() + ", i: " + i + ", idx: " + idx);
            res.add(new ArrayList<>(subset));
            // backtrack
            dfs(subset, nums, i + 1);/* i+1 not idx+1!!!!! */
            // revert to current state
            subset.remove(Integer.valueOf(nums[i]));
        }
    }
}
