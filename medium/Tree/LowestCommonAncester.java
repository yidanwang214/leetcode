// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/

// reference1: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/solutions/65225/4-lines-c-java-python-ruby/
// reference2: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/solutions/65226/my-java-solution-which-is-easy-to-understand/
/*
if q and p are in different subtrees of a node, that node should be their lowest common ancester
if q and p are in the same subtree, the one that is firsted found will be the lowest common ancester
 */

// time: O(n)
// space: O(n)
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    // if the current root is null, or is p, or is q, return the root
    if (root == null || root == p || root == q)
        return root;

    // Recursive case
    // reverse the right subtree and left subtree of the current node respectively
    // if we found a node in the subtree, the node will be returned and stored in l
    // or r
    TreeNode l = lowestCommonAncestor(root.left, p, q);
    TreeNode r = lowestCommonAncestor(root.right, p, q);
    // if l is null, return r
    // else if r is null, return l
    // else (if r is not null and r is not null) return root
    return l == null ? r : r == null ? l : root;
}