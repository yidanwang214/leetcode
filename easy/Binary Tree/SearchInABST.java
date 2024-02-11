// https://leetcode.com/problems/search-in-a-binary-search-tree/description/

public class SearchInABST {
    /*
     * input:
     * 5
     * 2 8
     * 1 4 7 10
     * 3 6 9 val=9
     * 
     * output: 9
     * 
     * input:
     * 5
     * 2 8
     * 1 4 7 10 val=11
     * 3 6 9
     * 
     * output: null
     * 
     * algo
     * dfs(root):
     * // base case
     * if(root == null) return null
     * 
     * if(root.val == k) return root;
     * 
     * if(k < root.val) return dfs(root.left)
     * else return dfs(root.right)
     * 
     * time: O(logn) in average case, O(n) in worst case
     * space: O(logn) in average case, O(n) in worst case due to recursive stack
     * 
     */
    public TreeNode searchBST(TreeNode root, int val) {
        // base case
        if (root == null)
            return null;

        // if root's value is equal to val, return the root
        if (root.val == val)
            return root;

        // if root's value is less than val, recursively dfs it's right child
        if (root.val < val)
            return searchBST(root.right, val);
        // otherwise, dfs it's left child
        else
            return searchBST(root.left, val);
    }
}
