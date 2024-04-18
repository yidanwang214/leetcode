// https://leetcode.com/problems/house-robber-iii/description/
public class HouseRobber3 {
    // reference:
    // https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0337.%E6%89%93%E5%AE%B6%E5%8A%AB%E8%88%8DIII.md
    // dp solution: return result in array [maxNotChoose, maxChoose]

    public int rob(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0], res[1]);
    }

    private int[] dfs(TreeNode root) {
        // base case
        if (root == null) {
            int tmp[] = { 0, 0 };
            return tmp;
        }

        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        int choose = root.val + left[0] + right[0]; // index 0 repreents the max value not choosing the node that
                                                    // current array represents
        int notchoose = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        int[] res = { notchoose, choose };
        return res;
    }

    // cache each node in hashmap
    // reference:
    // https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0337.%E6%89%93%E5%AE%B6%E5%8A%AB%E8%88%8DIII.md
    // time: O(n)
    // space: O(n)
    HashMap<TreeNode, Integer> map = new HashMap<>();

    public int robBruteForce(TreeNode root) {
        dfsBruteForce(root);
        return map.get(root);
    }

    private int dfsBruteForce(TreeNode root) {
        // edge case
        if (root == null)
            return 0;
        if (map.containsKey(root))
            return map.get(root);

        int cur = root.val + (root.left != null ? (dfs(root.left.left) + dfs(root.left.right)) : 0)
                + (root.right != null ? (dfs(root.right.left) + dfs(root.right.right)) : 0);
        int nocur = dfs(root.left) + dfs(root.right);
        map.put(root, Math.max(cur, nocur));
        return map.get(root);
    }
}
