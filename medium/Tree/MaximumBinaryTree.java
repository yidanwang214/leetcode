// https://leetcode.com/problems/maximum-binary-tree/description/

import javax.swing.tree.TreeNode;

public class MaximumBinaryTree {
    /*
     * test case 1:
     * input: [1, 2, 3, 4, 5]
     * output:
     * 5
     * 4
     * 3
     * 2
     * 1
     * 
     * time: O(n)
     * space: O(n)
     */

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildTree(new TreeNode(), nums, 0, nums.length);
    }

    private TreeNode buildTree(TreeNode root, int[] nums, int l, int r) {
        // when nums is empty
        if (l >= r)
            return null;

        // find the max val in nums, can put the values left of into leftArr, values
        // right of max into rightArr
        int max = -1;
        int idx = -1;
        for (int i = l; i < r; i++) {
            if (max < nums[i]) {
                max = nums[i];
                idx = i;
            }
        }
        // insert max to root's value
        root = new TreeNode(max);
        root.left = buildTree(root.left, nums, l, idx);
        root.right = buildTree(root.right, nums, idx + 1, r);

        return root;
    }

}
