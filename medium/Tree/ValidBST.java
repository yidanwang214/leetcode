// https://leetcode.com/problems/validate-binary-search-tree/description/

public class ValidBST {
    /*
     * test case
     * input:
     * 5
     * 2 9
     * 1 4 6
     * 
     * output:
     * true
     * 
     * input:
     * 5
     * 2 9
     * 1 7 6
     * output: false
     * 
     * algo:
     * two pointers, pre refers to the previous node, root refers to the current
     * node, and use inorder to traverse the tree in ascedning order
     * check if the pre is always less than the root, if true, return true, else,
     * return false
     * 
     * 1. maintain a global TreeNode pre = null
     * 2. inorder(root)
     * // base condition
     * // if root is null, return true
     * 
     * // leftres
     * boolean leftRes = inorder(root.left);
     * 
     * boolean isPreSmaller = true;
     * if(pre != null){
     * isPreSmaller = root.val - pre.val > 0 ? true : false;
     * }
     * pre = root;
     * return leftRes && isPreSmaller && inorder(root.right);
     * 
     * Time: O(n)
     * Space: O(logn)
     */

    private TreeNode pre = null;

    public boolean isValidBST(TreeNode root) {
        // base condition
        // if root is null, return true
        if (root == null)
            return true;

        // leftres
        boolean leftRes = isValidBST(root.left);

        boolean isPreSmaller = true;
        if (pre != null) {
            isPreSmaller = root.val > pre.val ? true : false;
        }
        pre = root;
        return leftRes && isPreSmaller && isValidBST(root.right);
    }
}
