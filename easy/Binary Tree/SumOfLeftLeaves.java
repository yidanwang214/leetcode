public class SumOfLeftLeaves {
    /*
     * test case 1:
     * 5
     * 4 3
     * 2 1
     * 
     * output: 2
     * 
     * test case 2:
     * 5
     * 4
     * 3
     * 
     * 
     * algo: dfs()
     * 1. global var: sum = 0
     * 2. dfs(root)
     * // if(node.left == null && node.right == null)
     * return
     * 
     * // crux: how do we know if we reached a left leaf?
     * if(node.left != null && node.left.right == null && node.left.left == null)
     * sum += node.left.val;
     * 
     * dfs(root.left)
     * dfs(root.right)
     * time: O(n)
     * space: O(1) + O(n) -> global var sum + recursive stack
     */
    class Solution {
        private int sum = 0;

        public int sumOfLeftLeaves(TreeNode root) {
            dfs(root);
            return sum;
        }

        private void dfs(TreeNode root) {
            if (root == null)
                return;

            // if the node is a right leaf node, do nothing
            if (root.left == null && root.right == null)
                return;

            // how do we know if we reached a left leaf?
            // by checking if a root's left child is a leaf node
            if (root.left != null && root.left.left == null && root.left.right == null) {
                sum += root.left.val;
            }

            dfs(root.left);
            dfs(root.right);
        }
    }
}
