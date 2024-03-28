// https://leetcode.com/problems/path-sum/

public class PathSum {
    /*
     * intput:
     * 5
     * 4 3
     * 2 1
     * 
     * targetSum: 8
     * output: true
     * 
     * algo:
     * dfs(root, target, sum) -> sum is initialised to be 0
     * // base case
     * if the root is null, return
     * // how do we know if we has reached a leaf
     * if the root has no children
     * sum += root.val
     * if(sum == target) res = true;
     * return;
     * if(sum + root.val < target){
     * dfs(root.left, target, sum + root.val);
     * dfs(root.right, target, sum+ root.val);
     * }
     * 
     * time: O(n) -> in worse case we need to check every path, aka visit every node
     * in the tree
     * space: O(n) -> in worse case there will be n recursive stack
     * 
     */
    // my approach 1
    private boolean res = false;

    public boolean hasPathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum, 0);
        return res;
    }

    private void dfs(TreeNode root, int target, int sum) {
        if (res == true)
            return;

        // base cond
        if (root == null)
            return;

        // reach a leaf node
        if (root.left == null && root.right == null) {
            sum += root.val;
            if (sum == target) {
                res = true;
            }
            return;
        }

        dfs(root.left, target, sum + root.val);
        dfs(root.right, target, sum + root.val);
    }

    // my approach 2
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return dfs(root, targetSum, 0);
    }

    private boolean dfs(TreeNode root, int target, int sum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null) {
            sum += root.val;
            if (sum == target)
                return true;
            return false;
        }
        return dfs(root.left, target, sum + root.val) || dfs(root.right, target, sum + root.val);
    }
}
