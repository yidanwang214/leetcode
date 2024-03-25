// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/

// reference: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/solutions/64963/3-lines-with-o-1-space-1-liners-alternatives/
// time: O(logn) ->  BST
// space: O(logn) -> BST
public class LowestCommonAncesterBST {
    // recursive approach
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if ((p.val < root.val) && (root.val > q.val))
            return lowestCommonAncestor(root.left, p, q);
        if ((p.val > root.val) && (root.val < q.val))
            return lowestCommonAncestor(root.right, p, q);
        // if the above 2 conditions are not fulfilled
        // p and q are in different subtrees of the current root
        // so the current root is the lowest common ancester of p and q
        return root;
    }

    // iterative approach
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while ((root.val - (long) p.val) * (root.val - (long) q.val) > 0)
            root = root.val < p.val ? root.right : root.left;
        return root;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // edge case
        // if the root is null or the same as p or q, return the root
        if (root == null || root == p || root == q)
            return root;
        // reversely find p and q in root
        TreeNode l = lowestCommonAncestor(root.left, p, q);
        TreeNode r = lowestCommonAncestor(root.right, p, q);
        return l == null ? r : r == null ? l : root;
    }
}
