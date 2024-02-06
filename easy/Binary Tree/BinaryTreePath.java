import java.util.ArrayList;
import java.util.List;

public class BinaryTreePath {
    // my approach
    /*
     * dfs
     * input:
     * 6
     * 5 4
     * 3 2 1 1
     * 0
     * output: [6->5->3,6->5->4,6->4->1,6->4->1->0]
     * 
     * we add the path to list when we reach a leaf node
     * time: O(n)
     * space: O(logn) recursive stack
     */
    List<String> res = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        StringBuilder path = new StringBuilder();
        path.append(root.val);
        dfs(root, path);
        return res;
    }

    private void dfs(TreeNode root, StringBuilder path) {
        // base cond
        // when we reach a leaf node
        if (root.left == null && root.right == null) {
            res.add(path.toString());
            return;
        }

        if (root.left != null) {
            path.append("->" + root.left.val);
            dfs(root.left, path);
            path.delete(path.lastIndexOf(Integer.toString(root.left.val)), path.length());
            path.delete(path.length() - 2, path.length());
        }
        if (root.right != null) {
            path.append("->" + root.right.val);
            dfs(root.right, path);
            path.delete(path.lastIndexOf(Integer.toString(root.right.val)), path.length());
            path.delete(path.length() - 2, path.length());
        }
    }

    // more efficient solution by using string
    // reference:
    // https://leetcode.com/problems/binary-tree-paths/solutions/68258/accepted-java-simple-solution-in-8-lines/
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        dfs(root, "", res);
        return res;
    }

    private void dfs(TreeNode root, String path, List<String> res) {
        // base case
        if (root.left == null && root.right == null)
            res.add(path + root.val);

        if (root.left != null)
            dfs(root.left, path + root.val + "->", res);
        if (root.right != null)
            dfs(root.right, path + root.val + "->", res);
    }
}