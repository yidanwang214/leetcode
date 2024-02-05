// https://leetcode.com/problems/binary-tree-inorder-traversal/

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.tree.TreeNode;

public class BinaryTreeInorderTraversal {
    // time: O(n) -> visite each node once
    // space: O(n) -> res.size O(n) + worse case recursive stack O(n) = O(2n)
class Solution {
    /*
    1
        2
       3
    [1, 2, 3] -> output: 1, 3, 2
    stack: [2, 3]
    res: [1, 3, 2]
    tmp: 

        1
    2       3
  4   5   6    7
    output: 4,2,5,1,6,3,7
    stack: [7, 3, 6]
    res: [4, 2, 5, 1, 6, 3, 7]
    tmp: 
     */
    // iterative approach
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while(!stack.isEmpty()){
            TreeNode tmp = stack.peek();
            // handle edge case when root is a null node: []
            if(tmp == null) break;
            // handle leaf node or the parent(mid) that has been cut off from its children
            if(tmp.right == null && tmp.left == null){
                res.add(stack.pop().val);
                continue;
            }
            // add right child to stack
            if(tmp.right != null){
                tmp = stack.pop();
                stack.push(tmp.right);
                tmp.right = null;
                stack.push(tmp);
            }
            // add left child to stack
            if(tmp.left != null){
                stack.push(tmp.left);
                tmp.left = null;
            }
        }
        return res;
    }

    // recursive approach
    List<Integer> res;
    public List<Integer> inorderTraversal(TreeNode root) {
        res = new ArrayList<>();
        dfs(root);
        return res;
    }
    private void dfs(TreeNode root){
        // base case
        if(root == null) return;
        // recursive traversal
        dfs(root.left);
        res.add(root.val);
        dfs(root.right);
    }
}
