// https://leetcode.com/problems/island-perimeter/
public class IslandPerimeter {
    /*
     * Time: O(col*row)
     * Space: O(1)
     */
    public int islandPerimeter(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    // first row or last row
                    if (i == 0)
                        res++;
                    // last row
                    if (i == grid.length - 1)
                        res++;
                    // first col or last col
                    if (j == 0)
                        res++;
                    // last col
                    if (j == grid[0].length - 1)
                        res++;
                    // when the row above the current is water
                    if (i > 0 && grid[i - 1][j] == 0)
                        res++;
                    // when the col before the current is water
                    if (j > 0 && grid[i][j - 1] == 0)
                        res++;
                    // when the row below the current is water
                    if (i < grid.length - 1 && grid[i + 1][j] == 0)
                        res++;
                    // when the col after the current is water
                    if (j < grid[0].length - 1 && grid[i][j + 1] == 0)
                        res++;
                }
            }
        }
        return res;
    }
}
