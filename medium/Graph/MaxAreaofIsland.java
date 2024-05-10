import java.util.LinkedList;
import java.util.Queue;

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

    /*
     * bfs
     */
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        this.visited = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && visited[i][j] == 0) {
                    Queue<int[]> q = new LinkedList<>();
                    int[] coordinate = { i, j };
                    q.add(coordinate);
                    int area = 0;
                    while (!q.isEmpty()) {
                        area++;
                        int[] tmp = q.poll();
                        int row = tmp[0];
                        int col = tmp[1];
                        visited[row][col] = 1;
                        System.out.println("area: " + area + ", i: " + row + ", j: " + col);
                        // up
                        if (row - 1 >= 0 && grid[row - 1][col] == 1 && visited[row - 1][col] == 0) {
                            int[] arr = { row - 1, col };
                            q.add(arr);
                            visited[row - 1][col] = 1;
                        }
                        // down
                        if (row + 1 < grid.length && grid[row + 1][col] == 1 && visited[row + 1][col] == 0) {
                            int[] arr = { row + 1, col };
                            q.add(arr);
                            visited[row + 1][col] = 1;
                        }
                        // left
                        if (col - 1 >= 0 && grid[row][col - 1] == 1 && visited[row][col - 1] == 0) {
                            int[] arr = { row, col - 1 };
                            q.add(arr);
                            visited[row][col - 1] = 1;
                        }
                        // right
                        if (col + 1 < grid[0].length && grid[row][col + 1] == 1 && visited[row][col + 1] == 0) {
                            int[] arr = { row, col + 1 };
                            q.add(arr);
                            visited[row][col + 1] = 1;
                        }
                    }
                    max = Math.max(max, area);
                    System.out.println(max);
                }
            }
        }
        return max;
    }

}