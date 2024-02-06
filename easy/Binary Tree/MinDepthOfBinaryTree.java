// https://leetcode.com/problems/minimum-depth-of-binary-tree/description/

import java.util.LinkedList;
import java.util.Queue;

import javax.swing.tree.TreeNode;

public class MinDepthOfBinaryTree {
    /*
     * test case 1:intput:[]output:0
     * 
     * test case 2:intput:[1]output:1!
     * 
     * test case 3:1 2 3 4 5 output:2
     * 
     * test case 4:2 3 4 5 6
     * 
     * algo:
     * bfs()
     * 1. maintain a variable minPath to store the num of nodes on the shortest
     * path;
     * create a queue to push root in
     * 2. while queue is not empty
     * pop all of the nodes in queue, check if there is any leaf node, if not, we
     * minPath++, and push every node's children into the queue,
     * otherwise, res++, break the loop
     * 3. return minPath
     * 
     * time: O(n)
     * space: O(n) in worset case
     ********* 1
     *********** 2
     ************* 3
     */
    class Solution {
        // bfs efficient approach
        public int minDepth(TreeNode root) {
            if (root == null)
                return 0;
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            int level = 1;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    TreeNode tmp = q.poll();
                    if (tmp.right == null && tmp.left == null)
                        return level;
                    if (tmp.left != null)
                        q.add(tmp.left);
                    if (tmp.right != null)
                        q.add(tmp.right);
                }
                level++;
            }

            return -1;
        }

        // bfs
        // my approach
        public int minDepth(TreeNode root) {
            int minPath = 0;
            if (root == null)
                return 0;
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            while (q.peek() != null) {
                minPath++;
                System.out.println("top: " + minPath);
                Queue<TreeNode> tmpQ = new LinkedList<>();
                while (q.peek() != null) {
                    TreeNode tmp = q.poll();
                    if (tmp.left == null && tmp.right == null) {
                        return minPath;
                    }
                    if (tmp.left != null)
                        tmpQ.add(tmp.left);
                    if (tmp.right != null)
                        tmpQ.add(tmp.right);
                }
                System.out.println("bottom: " + minPath);
                while (tmpQ.peek() != null) {
                    q.add(tmpQ.poll());
                }
            }
            return minPath;
        }

        // dfs
        // my solution
        private int minPath = Integer.MAX_VALUE;

        public int minDepth(TreeNode root) {
            if (root == null)
                return 0;
            dfs(root, 0);
            return minPath;
        }

        private void dfs(TreeNode root, int depth) {
            // base case
            if (root == null)
                return;
            if (root.left == null && root.right == null) {
                minPath = Math.min(minPath, ++depth);
                System.out.println("minPath: " + minPath);
            }

            if (root.left != null && root.right != null) {
                // shouldn't use depth++ or ++ depth
                dfs(root.left, depth + 1);
                dfs(root.right, depth + 1);
            } else if (root.left != null) {
                dfs(root.left, depth + 1);
            } else if (root.right != null) {
                dfs(root.right, depth + 1);
            }
        }

        // optimal dfs
        // reference: https://leetcode.com/problems/minimum-depth-of-binary-tree/
        public int minDepth(TreeNode root) {
            if (root == null)
                return 0;
            if (root.right == null && root.left == null)
                return 1;
            int left = Integer.MAX_VALUE;
            int right = Integer.MAX_VALUE;
            if (root.left != null)
                left = minDepth(root.left);
            if (root.right != null)
                right = minDepth(root.right);
            return Math.min(left, right) + 1;
        }
    }
}
