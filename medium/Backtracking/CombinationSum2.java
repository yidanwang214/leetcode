
// https://leetcode.com/problems/combination-sum-ii/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSum2 {
    Set<List<Integer>> res = new HashSet<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(0, new ArrayList<>(), 0, target, candidates);
        return new ArrayList<>(res);
    }

    private void dfs(int offset, List<Integer> combo, int sum, int target, int[] candidates) {
        // base case
        if (sum == target) {
            res.add(new ArrayList<>(combo));
            return;
        }
        if (sum > target)
            return;

        // explore every posibility
        for (int i = offset; i < candidates.length; i++) {

            // skip duplicates
            if (i > offset && candidates[i] == candidates[i - 1]) {
                continue;
            }

            // Prune branches if adding the current element exceeds the target
            if (sum + candidates[i] > target) {
                return;
            }

            // save the current state
            combo.add(candidates[i]);
            sum += candidates[i];

            dfs(i + 1, combo, sum, target, candidates);

            // revert to previous state
            combo.remove(combo.size() - 1);
            sum -= candidates[i];
        }
    }
}
