// https://leetcode.com/problems/diameter-of-binary-tree/description/

import javax.swing.tree.TreeNode;

public class DiameterOfBinaryTree {
    /*
     * crux: finding the diameter is to find the the max value of the sum of each
     * root's left longest path and their right longest path
     * using dfs
     * time: O(n) -> visit every node once
     * space: in worst case the recursive stack is O(n)
     */
    private int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return diameter;
    }

    private int dfs(TreeNode root) {
        // base case
        if (root == null)
            return 0;
        // reach leaf nodes
        if (root.left == null && root.right == null)
            return 0;
        int leftLen = (root.left == null ? 0 : (dfs(root.left) + 1));
        int rightLen = (root.right == null ? 0 : (dfs(root.right) + 1));
        diameter = Math.max(diameter, leftLen + rightLen);
        // return the longer path because the the greater value will count towards the
        // longest path from the root's parent to the farest leaf
        return Math.max(leftLen, rightLen);
    }
}
