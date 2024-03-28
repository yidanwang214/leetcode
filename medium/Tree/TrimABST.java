// https://leetcode.com/problems/trim-a-binary-search-tree/description/

import javax.swing.tree.TreeNode;

public class TrimABST {
    class Solution {
        public TreeNode trimBST(TreeNode root, int low, int high) {
            // base case
            if (root == null)
                return null;

            // recursive case
            if (root.val < low)
                return trimBST(root.right, low, high);
            if (root.val > high)
                return trimBST(root.left, low, high);

            // if the current node's value is in the range of low to high
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
    }
}
