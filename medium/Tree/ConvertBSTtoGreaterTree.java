// https://leetcode.com/problems/convert-bst-to-greater-tree/description/

// 1. traverse the root counterclockwise, in the order of right, root, and left
// so that we can accumulate the value from max to min
// 2. maintain a global counter to keep track of the sum of nodes we have visited
// Time: O(n) -> visit every node
// Space: O(logn) -> we are reconstructure a BST, so the number of recursive stack created is logn

public class ConvertBSTtoGreaterTree {
    private int sum = 0;

    private void dfs(TreeNode root) {
        // base case: null node
        if (root == null)
            return;

        // recursive case
        dfs(root.right);
        root.val += sum;
        sum = root.val;
        dfs(root.left);
    }

    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }
}