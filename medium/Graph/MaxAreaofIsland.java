public class MaxAreaofIsland {

    /*
     * algo:
     * 1. keep a var max to save the max area
     * 2. dfs or bfs the grid, find when finish exploring an island, update the max
     * 3. return max
     * Time: O(m*n) -> traverse every box
     * Space: O(m*n) -> visited array
     */

    /* dfs */
    int[][] visited;

    public int maxAreaOfIslandDFS(int[][] grid) {
        int max = 0;
        this.visited = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && visited[i][j] == 0) {
                    max = Math.max(max, dfs(i, j, grid));
                }
            }
        }
        return max;
    }

    private int dfs(int i, int j, int[][] grid) {
        // base case
        // 1. out of bound
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length)
            return 0;
        // 2. has visited or neighbouring water
        if (visited[i][j] == 1 || grid[i][j] == 0)
            return 0;

        visited[i][j] = 1;
        int area = 1;
        // up + down + left + right
        area += dfs(i - 1, j, grid) + dfs(i + 1, j, grid) + dfs(i, j - 1, grid) + dfs(i, j + 1, grid);
        return area;
    }
}