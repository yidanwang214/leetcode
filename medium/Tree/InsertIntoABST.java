// https://leetcode.com/problems/insert-into-a-binary-search-tree/description/

// time: O(logn)
// space: O(logn)

// algo:
// dfs(root, val){
// if reach a leaf node
//     if (root == null)
//        root.val = new TreeNode(val);
//        return root
// if (root.val>val) root.left=dfs(root.left, val)
// else root.right = dfs(root.right, val)
// return root
//}

import javax.swing.tree.TreeNode;

/**
 * InsertIntoABST
 */
public class InsertIntoABST {
    // recursive
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            root = new TreeNode(val);
            return root;
        }
        if (root.val > val)
            root.left = insertIntoBST(root.left, val);
        else
            root.right = insertIntoBST(root.right, val);
        return root;
    }

    // iterative
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // edge case
        if (root == null) {
            root = new TreeNode(val);
            return root;
        } else {

            TreeNode parent = null;
            TreeNode node = root;
            while (true) {
                if (node == null) {
                    node = new TreeNode(val);
                    if (parent.val > val)
                        parent.left = node;
                    else
                        parent.right = node;
                    break;
                }
                if (node.val > val) {
                    parent = node;
                    node = node.left;
                } else {
                    parent = node;
                    node = node.right;
                }
            }
            return root;
        }
    }
}
