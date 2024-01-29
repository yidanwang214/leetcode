import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSum {
    Set<List<Integer>> res = new HashSet<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(0, new ArrayList<>(), 0, candidates, target);
        return new ArrayList<>(res);
    }

    private void dfs(int offset, List<Integer> combo, int sum, int[] candidates, int target) {
        // base case
        if (sum > target)
            return;
        else if (sum == target) {
            res.add(new ArrayList<>(combo));
            return;
        }

        // explore all posibilities
        for (int i = offset; i < candidates.length && sum <= target; i++) {
            if (sum + candidates[i] > target)
                return;
            // save current state
            combo.add(candidates[i]);
            sum += candidates[i];

            // System.out.println("i: " + i + ", offset: " + offset + ", sum: " + sum + ",
            // combo:" + combo.toString());

            // backtrack
            if (sum <= target)
                dfs(i, combo, sum, candidates, target);

            // revert to current state
            sum -= candidates[i];
            combo.remove(combo.size() - 1);
        }
    }
}
