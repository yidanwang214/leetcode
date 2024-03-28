
// https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
// really easy
// stumbled becaused i should have get mid by (high-low)/2+low, not (high-low)/2
// time: O(logn) -> n is the number of nums, we split nums logn times
// space: O(logn) -> n is the number of nums, we split nums logn times, the height of the recursive stack is logn
public class ConvertSortedArraytoBST {

import javax.swing.tree.TreeNode;

    private int[] nums;

    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        return dfs(0, nums.length - 1);
    }

    private TreeNode dfs(int l, int r) {
        // base case
        if (l > r)
            return null;
        if (l == r)
            return new TreeNode(nums[l]);

        // get mid point
        int mid = (r - l) / 2 + l;
        // choose mid point to be the root
        TreeNode root = new TreeNode(nums[mid]);
        // nums left of mid will form the left subtree of the root
        root.left = dfs(l, mid - 1);
        // nums right of the mid will form the right subtree of the root
        root.right = dfs(mid + 1, r);
        return root;
    }
}
