// https://leetcode.com/problems/delete-node-in-a-bst/description/

public class DeleteNodeInaBST {
    /*
     * algo: iterative approach
     * // base case
     * // if when root == null return null;
     * // when root.val == key, we check if the root is leaf or how many children it
     * has
     * // if the root is leaf
     * if(root.left == null && root.right == null) return null;
     * // if the root has 1 child
     * if(root.left != null && root.right == null){
     * return root.left;
     * }
     * if(root.left == null && root.right != null){
     * return root.right;
     * }
     * 
     * // if the root has 2 children
     * // we either look for the node that is smaller than it and closest to its
     * value, rightest in left subtree
     * // or the node that is greater than it and closest to its value, leftest in
     * right subtree
     * // in this implementation, i am finding max node in left subtree
     * TreeNode max = findMax(root.left);
     * max.left = root.left;
     * max.right = root.right;
     * return max;
     * 
     * // if(root.val < key) root.right = deleteNode(root.right, key);
     * // else(root.val > key) root.left = deleteNode(root.left, key);
     * // return root;
     */

    // time: O(logn) -> find operation is logn, but deletion is constant time
    // space: O(logn) -> logn recursive stacks

    private TreeNode findMax(TreeNode root) {
        // base case
        // when the root is replacing the node to be deleted
        if (root.right != null && root.right.right == null) {
            TreeNode max = root.right;
            root.right = root.right.left;
            max.left = null;
            return max;
        }
        return findMax(root.right);
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        // base case
        // if root == null
        if (root == null)
            return null;

        // if the current node's value is key
        if (root.val == key) {
            // if root has no child
            if (root.left == null && root.right == null)
                return null;
            // if root has only 1 child
            if (root.left == null && root.right != null)
                return root.right;
            if (root.right == null && root.left != null)
                return root.left;

            // if root has 2 children: find the greatest node in the left substree of the
            // node
            // if the greatest node is left child of the root, return the greatest node
            if (root.left.right == null) {
                root.left.right = root.right;
                return root.left;
            }
            // find the greatest node
            TreeNode max = findMax(root.left);
            max.left = root.left;
            max.right = root.right;
            return max;
        }

        // if the current node's value is not equal to key
        if (root.val < key)
            root.right = deleteNode(root.right, key);
        else
            root.left = deleteNode(root.left, key);
        return root;
    }
}
