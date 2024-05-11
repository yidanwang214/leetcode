import java.util.LinkedList;
import java.util.Queue;

public class NumberofEnclaves {
    /*
     * DFS
     * 1. keep a land that keeps the number of one's, visited[][] array, enclaves
     * 2. ietrate through every box that makes up the borders, dfs() each border
     * whose value is one, update res by the return value of dfs()
     * -> // base case, has visited, neighouring land, out of bound, return 0
     * -> return 1 + recurisively explore 4 direction
     * 3. return land - encalves
     * Time: O(m*n) -> visit every node
     * Space: O(m*n) -> visited array
     */
    int[][] visited;

    public int numEnclavesDFS(int[][] grid) {
        visited = new int[grid.length][grid[0].length];
        int land = 0, walkoffLand = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1)
                    land++;
            }
        }

        for (int i = 0; i < grid.length; i++) {
            if (i == 0 || i == grid.length - 1) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1 && visited[i][j] == 0) {
                        walkoffLand += dfs(i, j, grid, visited);
                    }
                }
            } else {
                walkoffLand += dfs(i, 0, grid, visited) + dfs(i, grid[0].length - 1, grid, visited);
            }
        }
        return land - walkoffLand;
    }

    private int dfs(int row, int col, int[][] grid, int[][] visited) {
        // base case
        if (row < 0 || row > grid.length - 1 || col < 0 || col > grid[0].length - 1)
            return 0;
        if (grid[row][col] == 0 || visited[row][col] == 1)
            return 0;

        visited[row][col] = 1;
        return 1 + dfs(row - 1, col, grid, visited) + dfs(row + 1, col, grid, visited)
                + dfs(row, col - 1, grid, visited) + dfs(row, col + 1, grid, visited);
    }

    /*
     * BFS
     * algo:
     * 1. create visited[][] array, the num of lands, the num of walkoffLand
     * 2. iterate through every bordering node, using bfs() with queue
     * 3. return land - walkoffLand
     * Time: O(m*n)
     * Space: O(m*n)
     */
    int[][] grid;
    int row, col;

    public int numEnclaves(int[][] grid) {
        row = grid.length;
        col = grid[0].length;
        int land = 0, walkoffLand = 0;
        visited = new int[row][col];
        this.grid = grid;
        // count the total number of lands
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1)
                    land++;
            }
        }

        for (int i = 0; i < row; i++) {
            if (i == 0 || i == row - 1) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == 1 && visited[i][j] == 0) {
                        walkoffLand += bfs(i, j);
                    }
                }
            } else {
                if (grid[i][0] == 1 && visited[i][0] == 0) {
                    walkoffLand += bfs(i, 0);
                }
                if (grid[i][col - 1] == 1 && visited[i][col - 1] == 0) {
                    walkoffLand += bfs(i, col - 1);
                }
            }
        }
        return land - walkoffLand;
    }

    private int bfs(int i, int j) {
        int count = 0;
        Queue<int[]> q = new LinkedList<>();
        int[] coordinate = { i, j };
        q.add(coordinate);
        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];
            if (visited[x][y] == 1)
                continue;
            visited[x][y] = 1;
            count++;
            if (x - 1 >= 0 && grid[x - 1][y] == 1 && visited[x - 1][y] == 0) {
                System.out.println("x-1: " + (x - 1) + ", y: " + y);
                int[] arr = { x - 1, y };
                q.add(arr);
            }
            if (x + 1 < row && grid[x + 1][y] == 1 && visited[x + 1][y] == 0) {
                System.out.println("x+1: " + (x + 1) + ", y: " + y);
                int[] arr = { x + 1, y };
                q.add(arr);
            }
            if (y - 1 >= 0 && grid[x][y - 1] == 1 && visited[x][y - 1] == 0) {
                System.out.println("x: " + x + ", y-1: " + (y - 1));
                int[] arr = { x, y - 1 };
                q.add(arr);
            }
            if (y + 1 < col && grid[x][y + 1] == 1 && visited[x][y + 1] == 0) {
                System.out.println("x: " + x + ", y+1: " + (y + 1));
                int[] arr = { x, y + 1 };
                q.add(arr);
            }
        }
        return count;
    }
}
