import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSum3 {
    // time: O(C(9,k)) <- reference:
    // https://leetcode.com/problems/combination-sum-iii/solutions/60614/simple-and-clean-java-code-backtracking/comments/800790
    // space: the deepest level that recursion can go is k??
class Solution {
    Set<List<Integer>> res = new HashSet<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        dfs(1, new ArrayList<>(), 0, k, n);
        return new ArrayList<>(res);
    }
    private void dfs(int offset, List<Integer> combo, int sum, int k, int n){
        // base case
        if(sum == n && combo.size() == k){
            res.add(new ArrayList<>(combo));
            return;
        }
        if(sum > n || combo.size() > k) return;

        // explore possibility
        for(int i = offset; i <= 9 && combo.size() <= k; i++){
            // save current state
            if(sum + i > n) return;
            combo.add(i);
            // bt
            dfs(i+1, combo, sum+i, k, n);
            // revert
            combo.remove(combo.size()-1);
        }
    }
}
