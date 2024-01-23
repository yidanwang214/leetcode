// https://leetcode.com/problems/unique-paths/description/

public class UniquePath {
    // reference:
    // https://www.bilibili.com/video/BV1ve4y1x7Eu/?spm_id_from=333.788&vd_source=c74db3b5742c8630d40add5529146de2
    /* 1. decision tree + memoization */
    int[][] dp;

    public int uniquePaths(int m, int n) {
        dp = new int[m + 1][n + 1];
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                dp[i][j] = -1;
            }
        }
        return dfs(1, 1, m, n);
    }

    public int dfs(int startRow, int startCol, int destRow, int destCol) {

        if (startRow > destRow || startCol > destCol)
            return 0;
        if (startRow == destRow && startCol == destCol)
            return 1;

        // If the value is already computed, return it.
        if (dp[startRow][startCol] != -1)
            return dp[startRow][startCol];

        dp[startRow][startCol] = dfs(startRow + 1, startCol, destRow, destCol)
                + dfs(startRow, startCol + 1, destRow, destCol);
        return dp[startRow][startCol];
    }

    // 2. dp: tabulation
    public int uniquePaths(int m, int n) {
        // create dp
        int[][] dp = new int[m + 1][n + 1];

        // dp[i][j] means the # of unique path to grid[i][j]

        // equation
        // dp[i][j] = dp[i][]

        // initialise dp
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (i == 1)
                    dp[i][j] = 1;
                else if (j == 1)
                    dp[i][j] = 1;
                else
                    dp[i][j] = -1;
            }
        }

        // iterate from smallest grid to larger grid, i = row, j = col
        for (int i = 2; i < m + 1; i++) {
            for (int j = 2; j < n + 1; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        int[][] arr = new int[2][3];
        System.out.println(arr.length);
    }
}
