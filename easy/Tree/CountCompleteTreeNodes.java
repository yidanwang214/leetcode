// https://leetcode.com/problems/count-complete-tree-nodes/

public class CountCompleteTreeNodes {
    /*
     * test case 1:
     * null
     * output: 0
     * 
     * test case 2:
     * input: [1]
     * output: 1
     * 
     * test case 3:
     * 1
     * 2 3
     * 4 5 6 7
     * 8
     * 
     * output: 8
     * 
     * algo: bfs
     * 1. create a queue, push root into the queue; maintain a counter res to store
     * the num of nodes in the tree
     * 2. pop the curent nodes in enqueue, and push all of their child nodes into
     * queue
     * loop, until queue is null
     * 
     * algo: dfs
     * time: O(n)
     * space: O(logn) for recursive stack
     */
    class Solution {
        private int res = 1;

        public int countNodes(TreeNode root) {
            if (root == null)
                return 0;
            dfs(root);
            return res;
        }

        private void dfs(TreeNode root) {
            // base case
            if (root == null)
                return;

            // // if current node is not null, res++
            // res++;

            if (root.left != null) {
                res++;
                dfs(root.left);
            }
            if (root.right != null) {
                res++;
                dfs(root.right);
            }
        }
    }
}
