// https://leetcode.com/problems/count-good-nodes-in-binary-tree/

public class CountGoodNodesInBinaryTree {
    /*
     * 
     * test case:
     * input: [1]
     * output: 1
     * 
     * input:
     * 3
     * 4 5
     * 4 5 4
     * output: 5
     * 
     * 
     * intput
     * 4
     * 4 5
     * 2
     * 7
     * output: 4
     * dfs(4,4), res=1, max=4
     * / \
     * dfs(4,4)res:2,max:4 dfs(5,4),res:4,max:5
     * /
     * dfs(2,4),res:2,max:4
     * /
     * dfs(7,4),res:3,max:7
     * 
     * 
     * algo:
     * 1. maintain a global counter res to store the number of good nodes
     * 2. dfs(root, max), max passed from main function to be root's value
     * // base cond
     * when root is null, return
     * 
     * // compare root's value with max, if root's value >= max, increment res, and
     * assign root's value to max
     * // dfs(root.left, max)
     * // dfs(root.right, max)
     * 
     * 3. return res
     * 
     * time: O(n) visit each node in the tree
     * space: O(n) in worst case, which is the length of recursive stack
     */
    private int res = 0;

    public int goodNodes(TreeNode root) {
        dfs(root, root.val);
        return res;
    }

    private void dfs(TreeNode root, int max) {
        // base cond, when root is null, return
        if (root == null)
            return;

        // compare root's value with max, if root's value >= max, increment res, and
        // assign root's value to max
        if (root.val >= max) {
            res++;
            max = root.val;
        }
        dfs(root.left, max);
        dfs(root.right, max);
    }
}
