public class UniquePath2 {
    /*
     * tabulation
     * 1. dp[i][j]: # of routes to grid[i][j]
     * 2. equation: dp[i][j] = dp[i-1][j]+dp[i][j-1]
     * 3. initialisation: the first row/col to 0 for grids that has an obstacle or
     * grids after the obstacle, otherwise 1
     * 4. direction: l -> r
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rowNum = obstacleGrid.length;
        int colNum = obstacleGrid[0].length;

        /*
         * obstacleGrid = [[1]] expected: 0
         * obstacleGrid = [[0]] expected: 1
         * obstacleGrid = [[1],[0]] expected: 0
         */

        // initialise dp
        int[][] dp = new int[rowNum][colNum];
        boolean colFlag = false;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (i == 0) {
                    // if(obstacleGrid[i][j] == 0 && j>0 && (dp[i][j-1] != 0)){
                    // dp[i][j] = 1
                    // } else if(obstacleGrid[i][j] == 1 || dp[i][j-1] == 0) {
                    // dp[i][j] = 0;
                    // }
                    // if(j == 0) j = 1;
                    if (obstacleGrid[i][j] == 1) {
                        while (j < colNum) {
                            dp[i][j] = 0;
                            j++;
                        }
                    } else {
                        dp[i][j] = 1;
                    }
                } else if (j == 0) {
                    if (obstacleGrid[i - 1][j] == 1 || obstacleGrid[i][j] == 1 || colFlag) {
                        colFlag = true;
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = 1;
                    }
                }
            }
        }

        // use dp[i][j] = dp[i-1][j] + dp[i][j-1] to ietarate over and fill dp
        for (int i = 1; i < rowNum; i++) {
            for (int j = 1; j < colNum; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[rowNum - 1][colNum - 1];
    }

    /*
     * memoization
     * time: O(n)
     * space: O(n)
     * 1. create a 2d memo, with same length as obstacleGrid, initialise it with:
     * 1.1 memo[0][0]: if the first grid is free of obstacle, assign 0, else
     * 2. in dfs(row, col, destRow, destCol):
     * base cond1: row>rowNum or col>colNum or obstacleGrid[row][col] == 1, return 0
     * base cond2: row=desRow && col=destCol && obstacleGrid[row][col] != 1 return 1
     * else return 0
     * 
     * 
     * 00
     * ˇ ˇ
     * 01(1) 10
     * ˇ ˇ ˇ ˇ
     * 02(1) 11(0) 20(1) 11(0)
     * ˇ ˇ ˇ ˇ ˇ ˇ ˇ ˇ
     * x 1,2(1) x x 21 x
     * ˇ ˇ ˇ ˇ
     * 2,2(1) x x 22(1)
     * 
     * 
     * obstacleGrid = [[1],[0]] expected: 0
     */
    int[][] memo;
    int[][] obstacleGrid;

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rowNum = obstacleGrid.length;
        int colNum = obstacleGrid[0].length;

        this.obstacleGrid = obstacleGrid;
        // initialise memo[][]
        memo = new int[rowNum][colNum];
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++)
                memo[i][j] = -1;
        }

        return dfs(0, 0, rowNum - 1, colNum - 1);
    }

    public int dfs(int startRow, int startCol, int destRow, int destCol) {
        /*
         * base con 1
         * if startRow/startCol out of bound or the grid is a deadend
         */
        if (startRow > destRow || startCol > destCol || obstacleGrid[startRow][startCol] == 1)
            return 0;

        /*
         * base con 2
         * if startRow and startCol matches destRow and destCol
         */
        if (startRow == destRow && startCol == destCol && obstacleGrid[startRow][startCol] != 1) {
            if (memo[startRow][startCol] != -1)
                return memo[startRow][startCol];
            else {
                memo[startRow][startCol] = 1;
                return memo[startRow][startCol];
            }
            // or simply retrun 1
        }

        // check if memo[startRow][startCol] has been addreessed before
        if (memo[startRow][startCol] != -1) {
            return memo[startRow][startCol];
        }

        memo[startRow][startCol] = dfs(startRow + 1, startCol, destRow, destCol)
                + dfs(startRow, startCol + 1, destRow, destCol);

        return memo[startRow][startCol];
    }

    public static void main(String[] args) {
        int[][] arr = {
                { 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1,
                        0, 0 },
                { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0 },
                { 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0,
                        0, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1,
                        0, 1 },
                { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
                        0, 0 },
                { 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1,
                        0, 0 },
                { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1,
                        0, 0 },
                { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0,
                        0, 1 },
                { 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0,
                        0, 0 },
                { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0,
                        0, 0 },
                { 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0,
                        1, 0 },
                { 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0,
                        0, 1 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0,
                        0, 0 },
                { 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0 },
                { 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        1, 0 },
                { 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0,
                        0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                        0, 0 },
                { 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        1, 0 },
                { 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0,
                        0, 0 },
                { 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0,
                        0, 0 },
                { 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0,
                        0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0 },
                { 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        1, 0 },
                { 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1,
                        0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1,
                        0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                        0, 0 } };
        UniquePath2 p = new UniquePath2();
        System.out.println(p.uniquePathsWithObstacles(arr));
    }
}
