// https://leetcode.com/problems/combinations/
// time: O(n!)
// space: O(k)

import java.util.ArrayList;
import java.util.List;

public class Combination {

    List<List<Integer>> res;
    List<Integer> path;

    public List<List<Integer>> combine(int n, int k) {
        res = new ArrayList<>();
        path = new ArrayList<>();

        dfs(n, k, 1);
        return res;
    }

    private void dfs(int n, int k, int startIndex) {
        // base case
        if (path.size() >= k) {
            res.add(new ArrayList<>(path));
            return;
        }

        // search every posibility
        for (int i = startIndex; i <= n; i++) {
            // memorise current state
            path.add(i);
            // go down the path to the next availbale choice and generate combination
            dfs(n, k, i + 1);
            // restore current state
            path.remove(path.size() - 1);
        }
    }
}
