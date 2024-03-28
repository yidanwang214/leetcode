// https://leetcode.com/problems/minimum-absolute-difference-in-bst/

public class MinAbsDiffInBST {
    /*
     * duplicate nodes: no
     * since the tree is a binary tree, if we traverse the tree in order, we can
     * make sure the values are sorted in ascending order. Thus, the difference
     * between the left child, root, and right child can guarantee to be minimal
     * reference1:
     * https://leetcode.com/problems/minimum-absolute-difference-in-bst/solutions/
     * 99905/two-solutions-in-order-traversal-and-a-more-general-way-using-treeset
     * reference2:
     * https://www.bilibili.com/video/BV1fW411k7eT/?spm_id_from=333.337.search-card.
     * all.click&vd_source=c74db3b5742c8630d40add5529146de2
     */
    private int min = Integer.MAX_VALUE;
    private TreeNode pre = null; // node previous to current root

    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return min;
    }

    private void inorder(TreeNode root) {
        // base case: when reaching a null node
        if (root == null)
            return;
        inorder(root.left);
        if (pre != null)
            min = Math.min(min, root.val - pre.val);
        pre = root;
        inorder(root.right);
    }
}
