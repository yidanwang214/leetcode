// https://leetcode.com/problems/merge-two-binary-trees/

public class MergeTwoBinaryTrees {
    /*
     * intput:
     * 3 5
     * 2 1 6 7
     * 0 8
     * output:
     * 8
     * / \
     * 8 8
     * / \
     * 8 0
     * 
     * 
     * intput:
     * 1 null
     * /
     * 2
     * /
     * 3
     * 
     * output:
     * 1
     * 2
     * 3
     * 
     * input:
     * 1 5
     * 2
     * 3
     * 
     * output:
     * 6
     * 2
     * 3
     * 
     * algo:
     * 1. dfs(root1, root2)
     * // base cond
     * // when both root1 and root2 are null
     * return null
     * 
     * // when root1 == null && root2 != null
     * return root2
     * 
     * // when root1 != null && root2 == null
     * return root1
     * 
     * // when root1 != null && root2 != null
     * root1.val += root2.val
     * root1.left = dfs(root1.left, root2.left)
     * root1.right = dfs(root1.right, root2.right)
     * 
     * // return root
     * Time: O(n) -> the number of nodes in the bigger tree
     * Space: O(n) -> in worst case the number of recursive stack can be the number
     * of nodes in the greater tree
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // base case
        // when both root1 and root2 are null
        if (root1 == null && root2 == null)
            return null;

        // when root1 == null && root2 != null
        if (root1 == null && root2 != null)
            return root2;

        // when root1 != null && root2 == null
        if (root1 != null && root2 == null)
            return root1;

        // when root1 != null && root2 != null
        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);

        // return root1
        return root1;
    }
}
