// https://leetcode.com/problems/invert-binary-tree/description/

import java.util.LinkedList;
import java.util.Queue;

public class InvertBinaryTree {
    /*
     * test case 1:
     * input: []
     * output: []
     * 
     * test case 2:
     * input:
     * 8
     * 7 6
     * 5 4 3 2
     * 1
     * 
     * output:
     * 8
     * 6 7
     * 2 3 4 5
     * 1
     * 8
     * 6 7
     * 2 3 4 5
     * 1
     * 
     * algo: bfs
     * 1. push root to queue
     * 2. while queue is not empty, pop the node on the same level, swap it's left
     * child and right child, then push their children into the queue untill the
     * queue becomes empty
     * 3. return root
     * Time: O(n)
     * Space: O(2^h), h stands for the depth/level of the tree, which is logn
     * 
     * n
     * / \
     * l r
     * \
     * tmp
     */
    class Solution {

        // bfs
        public TreeNode invertTree(TreeNode root) {
            if (root == null)
                return root;
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = q.poll();
                    if (node.left != null && node.right != null) {
                        TreeNode tmp = node.left;
                        node.left = node.right;
                        node.right = tmp;
                        q.add(node.left);
                        q.add(node.right);
                    } else if (node.left != null) {
                        node.right = node.left;
                        node.left = null;
                        q.add(node.right);
                    } else if (node.right != null) {
                        node.left = node.right;
                        node.right = null;
                        q.add(node.left);
                    }
                }
            }
            return root;
        }

        // dfs
        // swap every node's left and right child layer by layer
        // reference: https://leetcode.com/problems/invert-binary-tree/
        public TreeNode invertTree(TreeNode root) {
            // base case
            if (root == null)
                return null;

            TreeNode left = root.left,
                    right = root.right;
            root.left = invertTree(right);
            root.right = invertTree(left);
            return root;
        }
    }
}
