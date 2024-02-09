// https://leetcode.com/problems/binary-tree-level-order-traversal-ii/

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {
    /*
     * 1
     * 2 3
     * 4 5 6 7
     * [4,5,6,7,2,3,1]
     * 
     * bfs()
     * algo:
     * 1. maintain a List<List<Integer>>, dfs, push every layer of nodes from left
     * to right into the list
     * 2. reverse the list
     * Time: O(n)
     * Space: O(height)
     * dfs()
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> tmpList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = q.poll();
                if (tmp == null)
                    return res;
                tmpList.add(tmp.val);
                if (tmp.left != null)
                    q.add(tmp.left);
                if (tmp.right != null)
                    q.add(tmp.right);
            }
            res.add(tmpList);
        }
        Collections.reverse(res);
        return res;
    }
}
