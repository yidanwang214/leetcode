// https://leetcode.com/problems/find-bottom-left-tree-value/

import javax.swing.tree.TreeNode;

public class FindBottomLeftTreeValue {
    /*
     * if the there is only 1 node in the tree, the node's value won't be null
     * so the input won't be a null node
     * input:
     * 1
     * 2 3
     * 4 5
     * 6
     * output: 6
     * 
     * intput:
     * []
     * output: invalid input
     * 
     * intput: [2]
     * output: 2
     * 
     * input:
     * 0
     * \
     * -1
     * output: -1
     * 
     * algo:
     * dfs()
     * 1. maintain 2 vars, maxLevel=Integer.MIN_VALUE and res = root.val
     * 2. dfs(root, 1)
     * // when root is null
     * if(root == null) return;
     * 
     * // when root is leaf
     * return
     * 
     * // when the node is left leaf
     * if(root.left != null && root.left.left == null && root.left.right == null){
     * // compare the maxLevel with current level, only update res and maxLevel when
     * the cuurent level is greater than maxLevel
     * // because we want to return the leftmoset value when two leaf are of the
     * same level
     * int leftLeafLevel = level + 1;
     * if(levelMax < leftLeafLevel) {
     * levelMax = leftLeafLevel
     * res = root.left.val
     * }
     * }
     * 
     * dfs(root.left, level+1);
     * dfs(root.right, level+1);
     * 
     * time: O(n) -> visit every node in the tree
     * space: O(n) -> worse case recursive stack
     * 
     * bfs():
     * 1. create a queue intto which var is pushed, a var maxlevel (initialised to
     * be 0) and a var res (intialised to be the root.val)
     * 2. while the queue is not empty:
     * increment curlevel;
     * get the size of the queue, which stands for the number of the nodes in the
     * current level
     * for(i < size){
     * tmp = queue.pop();
     * // if tmp is null, we skip to next iteration
     * if(tmp == null) continue;
     * if(curlevel > maxlevel){
     * // update maxlevel to be curlevel
     * // update res to be tmp's value
     * }
     * queue.add(tmp.left);
     * queue.add(tmp.right);
     * }
     * 3. return res
     * 
     * // better solution:
     * https://leetcode.com/problems/find-bottom-left-tree-value/solutions/98802/
     * simple-java-solution-beats-100-0/
     *
     */
    // global vars
    private int maxLevel = 1;
    private int res;

    public int findBottomLeftValue(TreeNode root) {
        this.res = root.val;
        dfs(root, 1);
        return this.res;
    }

    private void dfs(TreeNode root, int level) {
        // if the root is null, terminate
        if (root == null)
            return;

        // if the root is a leaf, terminate
        if (root.left == null && root.right == null)
            return;

        // when the root's left child is a leaf
        if (root.left != null && root.left.left == null && root.left.right == null) {
            // compare the maxLevel with current level, only update res and maxLevel when
            // the cuurent level is greater than maxLevel
            // because we want to return the leftmoset value when two leaf are of the same
            // level
            int leftLeafLevel = level + 1;
            if (maxLevel < leftLeafLevel) {
                maxLevel = leftLeafLevel;
                res = root.left.val;
            }
        }
        // if the root's left child is null but right child is a leaf
        // the right leaf might be a potential result
        if (root.left == null && root.right != null && root.right.left == null && root.right.right == null) {
            int rightLeafLevel = level + 1;
            if (maxLevel < rightLeafLevel) {
                maxLevel = rightLeafLevel;
                res = root.right.val;
            }
        }

        dfs(root.left, level + 1);
        dfs(root.right, level + 1);

    }
}
