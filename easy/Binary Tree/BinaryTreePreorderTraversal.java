// https://leetcode.com/problems/binary-tree-preorder-traversal/description/
public class BinaryTreePreorderTraversal {
    /time:
    O(n) -> visit each node once
// space: O(n) -> res.size is O(n), the recursive stack is at most n
class Solution {
    // recursive solution
    private List<Integer> res;
    public List<Integer> preorderTraversal(TreeNode root) {
        res = new ArrayList<>();
        dfs(root);
        return res;
    }

    private void dfs(TreeNode root){
        // base condition
        if(root == null) return;
        // add current node to root
        res.add(root.val);
        dfs(root.left);
        dfs(root.right);
    }

    // iterative solution
    /*
    root: 3,1,2
    stack: []
    res: [3, 1, 2]
    tmp:
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode tmp = stack.pop();
            /* handle edge case when the root node is null */
            if(tmp == null) break;
            res.add(tmp.val);
            /* be careful of the order of pushing right and left child,
            since stack follows the FIFO rule, if we push left child first,
            the left child's children will be pushed to the stack first,
            then the right child's children will be pushed to the stack on top,
            so in next iteration, the right child's children will be explored first,
            which is contrarary to preOrder
            */
            if(tmp.right != null) stack.push(tmp.right);
            if(tmp.left != null) stack.push(tmp.left);
            
        }
        return res;
    }
}
}
