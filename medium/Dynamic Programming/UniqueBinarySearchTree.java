// https://leetcode.com/problems/unique-binary-search-trees/description/
// reference: https://leetcode.com/problems/unique-binary-search-trees/solutions/31666/dp-solution-in-6-lines-with-explanation-f-i-n-g-i-1-g-n-i/
// reference: https://www.bilibili.com/video/BV1eK411o7QA/?spm_id_from=333.788&vd_source=c74db3b5742c8630d40add5529146de2

public class UniqueBinarySearchTree {
    /*
     * The number of structurally unique BST: # of leftSubtree * # of rightSubtree
     * of a specific node
     */
    public int numTrees(int n) {
        // dp[i]: the number of structurally unique BST which has i unique nodes
        int[] dp = new int[n + 1];
        // when there is 0 node, the tree is empty but counts as 1 tree structure
        dp[0] = 1;
        // when there is 1 node, we have 1 tree structure
        dp[1] = 1;

        // we start construct dp from 2 nodes untill i=n, i stands for the number of
        // nodes in the tree
        for (int i = 2; i <= n; i++) {
            // j: the root value of the tree, it increase from 1 to n
            for (int j = 1; j <= i; j++) {
                // j-1 is the number of structure in left sub tree
                // i-j is the number of structure in right sub tree
                System.out.println("j-1: " + (j - 1) + ", i-j: " + (i - j));
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

}
