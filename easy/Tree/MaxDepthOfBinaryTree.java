import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
public class MaxDepthOfBinaryTree {
    class Solution {
        // time: O(n)
        // space: O(ns)
        // bfs
        public int maxDepth(TreeNode root) {
            if (root == null)
                return 0;
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            int depth = 0;
            while (!q.isEmpty()) {
                depth++;
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    TreeNode tmp = q.poll();
                    if (tmp.left != null)
                        q.add(tmp.left);
                    if (tmp.right != null)
                        q.add(tmp.right);
                }
            }
            return depth;
        }

        // dfs
        public int maxDepth(TreeNode root) {
            // base case
            if (root == null)
                return 0;
            if (root.left == null && root.right == null)
                return 1;

            // dfs
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);

            return Math.max(left, right) + 1;
        }
    }
}