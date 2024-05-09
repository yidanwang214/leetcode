import java.util.LinkedList;
import java.util.Queue;

public class NumberofIslands {
    /*
     * bfs + queue
     * Time: O(m*n) -> go through every node once
     * Space: O(m*n) -> visited
     */
    public int numIslandsBFS(char[][] grid) {

        int num = 0;
        int width = grid[0].length;
        int height = grid.length;
        int[][] visited = new int[height][width];

        // go through every node on the grid
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // if the node has not been visited
                if (grid[i][j] == '1' && visited[i][j] == 0) {
                    System.out.println("i: " + i + ", j:" + j);
                    Queue<int[]> q = new LinkedList<>();
                    // add coordinate of the current node i,j to q
                    int[] arr = new int[2];
                    arr[0] = i; //
                    arr[1] = j; //
                    q.add(arr);
                    while (!q.isEmpty()) {
                        int[] tmp = q.poll();
                        int x = tmp[0]; // i bounded by grid.length, row num
                        int y = tmp[1]; // j bounded by grid[0].length, col num
                        // check up node
                        if (x - 1 >= 0 && visited[x - 1][y] == 0 && grid[x - 1][y] == '1') {
                            int[] a = { x - 1, y };
                            q.add(a);
                            visited[x - 1][y] = 1;
                        }
                        // check down node
                        if (x + 1 < height && visited[x + 1][y] == 0 && grid[x + 1][y] == '1') {
                            int[] a = { x + 1, y };
                            q.add(a);
                            visited[x + 1][y] = 1;
                        }
                        // check right node
                        if (y + 1 < width && visited[x][y + 1] == 0 && grid[x][y + 1] == '1') {
                            int[] a = { x, y + 1 };
                            q.add(a);
                            visited[x][y + 1] = 1;
                        }
                        // check left node
                        if (y - 1 >= 0 && visited[x][y - 1] == 0 && grid[x][y - 1] == '1') {
                            int[] a = { x, y - 1 };
                            q.add(a);
                            visited[x][y - 1] = 1;
                        }
                    }
                    num++;
                }
            }
        }
        return num;
    }

    /*
     * DFS
     * Time: O(n*m) -> visit each node
     * Space: O(n*m) -> recursive stack O(m+n) + visited array O(m*n)
     */
    int num = 0;
    int[][] visited;

    public int numIslands(char[][] grid) {
        visited = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && visited[i][j] == 0) {
                    dfs(i, j, grid);
                    num++;
                }

            }
        }
        return num;
    }

    private void dfs(int i, int j, char[][] grid) {
        // base case
        // 1. out of bound
        if ((i < 0 || i >= grid.length) || (j < 0 || j >= grid[0].length))
            return;
        // 2. bordering sea or has visited
        if (grid[i][j] == '0' || visited[i][j] == 1) {
            visited[i][j] = 1;
            return;
        }

        // recursive case
        visited[i][j] = 1;
        System.out.println("i: " + i + ", j: " + j + ", visited[i][j]: " + visited[i][j]);
        // down
        dfs(i - 1, j, grid);
        // up
        dfs(i + 1, j, grid);
        // left
        dfs(i, j - 1, grid);
        // right
        dfs(i, j + 1, grid);
    }
}
