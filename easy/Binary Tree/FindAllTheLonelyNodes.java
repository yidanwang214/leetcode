
// https://leetcode.ca/all/1469.html

import java.util.ArrayList;
import java.util.List;

public class FindAllTheLonelyNodes {
    /*
     * Time: O(n) -> visit every node
     * Space: O(n) -> worst case recursive stack
     */
    private List<Integer> res = new ArrayList<>();

    public List<Integer> getLonelyNodes(TreeNode root) {
        dfs(root);
        return res;
    }

    private void dfs(TreeNode root) {
        // base case
        // null node
        if (root == null)
            return;
        // leaf node: not necessarily a lovely child
        if (root.left == null && root.right == null)
            return;
        // has only left child, the left child is a single child, add left child to res
        if (root.left != null && root.right == null)
            res.add(root.left.val);
        // has only right child, the right child is a single child, add right child to
        // res
        else if (root.left == null && root.right != null)
            res.add(root.right.val);

        dfs(root.left);
        dfs(root.right);
    }
}
