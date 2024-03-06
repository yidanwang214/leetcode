// https://leetcode.com/problems/univalued-binary-tree/description/

public class UnivaluedBinaryTree {
    /*
     * root: [8,8,8,null,2,8,8,8]
     * 8
     * 8 8
     * null 2 8 8
     * 8
     * 
     * root: [6,null,1,6,6,6,null,null,6]
     * 6
     * 1
     * 6 6
     * 6 6
     * forgot to check each node's value!!!
     * Time: O(n) visit every node
     * Space: O(n) in worst case n recursive stack
     */
    private int value;

    public boolean isUnivalTree(TreeNode root) {
        if (root == null)
            return true;
        value = root.val;
        return dfs(root);
    }

    private boolean dfs(TreeNode root) {
        // base case
        // if null node, since its value won't affect the result, we can simply return
        // true
        if (root == null)
            return true;
        // recursive case
        if (root.val != value)
            return false;
        return dfs(root.right) && dfs(root.left);
    }
}