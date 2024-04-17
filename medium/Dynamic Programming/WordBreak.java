// https://leetcode.com/problems/word-break/description/

public class WordBreak {
    // time: O(n^2)
    // space: O(n)
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        Arrays.fill(dp, false);
        dp[0] = true;

        // think of indices of scanned chars as capacity of knapsack
        for (int i = 1; i < s.length() + 1; i++) {
            // think of each substring as items
            for (int j = 0; j < i; j++) {
                if (wordDict.contains(s.substring(j, i)) && (dp[j] == true)) {
                    dp[i] = true;
                }
            }
        }
        return dp[s.length()];
    }

    // dfs
    // space: O(n)
    // time: O(2^n) -> O(n^2)
    int[][] dp;
    List<String> wordDict;

    public boolean wordBreakBT(String s, List<String> wordDict) {
        this.wordDict = wordDict;
        this.dp = new int[s.length() + 1][s.length() + 1];
        for (int i = 0; i < s.length() + 1; i++) {
            Arrays.fill(this.dp[i], -1);
        }
        dfs(s, 0);
        return this.dp[s.length()][s.length()] == 1 ? true : false;

    }

    private int dfs(String s, int start) {
        if (start == this.dp.length - 1) {
            this.dp[start][start] = 1;
            return 1;
        }
        ;

        // backtrack
        for (int i = start; i < s.length() + 1; i++) {
            if (this.dp[start][i] != -1)
                return this.dp[start][i];
            if (wordDict.contains(s.substring(start, i))) {
                this.dp[start][i] = 1;
                this.dp[i][s.length()] = dfs(s, i);
                if (this.dp[i][s.length()] == 1) {
                    System.out.println("i: " + i + " s.length(): " + s.length());
                    return this.dp[i][s.length()];
                }
            } else
                this.dp[start][i] = 0;
        }
        this.dp[start][s.length()] = 0;
        return this.dp[start][s.length()];
    }
}
