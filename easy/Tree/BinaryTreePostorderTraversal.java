// https://leetcode.com/problems/binary-tree-postorder-traversal/description/

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.tree.TreeNode;

public class BinaryTreePostorderTraversal {
    // iterative approach
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode tmp = stack.peek();
            // handle edge case null root: []
            if (tmp == null)
                break;
            // when we reach a leaf node or when the current node has been cut off from its
            // children
            if (tmp.left == null && tmp.right == null)
                res.add(stack.pop().val);
            // when the node has a right child, we push right child into stack, and cut the
            // node off from it's right child
            if (tmp.right != null) {
                stack.push(tmp.right);
                tmp.right = null;
            }
            // when the node has a left child, we push left child into stack, and cut the
            // node off from it's left child
            if (tmp.left != null) {
                stack.push(tmp.left);
                tmp.left = null;
            }
        }
        return res;
    }

    // recursive approach
    private List<Integer> res;

    public List<Integer> postorderTraversal(TreeNode root) {
        res = new ArrayList<>();
        dfs(root);
        return res;
    }

    private void dfs(TreeNode root) {
        // base case
        if (root == null)
            return;

        // recursively traverse
        dfs(root.left);
        dfs(root.right);
        res.add(root.val);
    }
}
