// https://leetcode.com/problems/sudoku-solver/
public class SudokuSolver {
    /*
     * Time: O(m^9): m stands for the number of blank boxes to be filled
     * Space: O(m): m is the num of empty cells in sudoku board and also the number
     * of recursive stacks
     */
    // solution 1: pass the whole sudoku board
    public void solveSudoku(char[][] board) {
        dfs(board);
    }

    private boolean dfs(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == '.') {
                    for (int k = 1; k <= 9; k++) {
                        if (isValid(i, j, Character.forDigit(k, 10), board)) {
                            // save current state
                            board[i][j] = Character.forDigit(k, 10);
                            // backtrack
                            boolean res = dfs(board);
                            // if res is true, that means the board is correctly filled to the last empty
                            // box, we can return true to terminal search other branches
                            if (res == true)
                                return true;
                            // else if res is false, restore previous state
                            board[i][j] = '.';
                        }
                    }
                    // when no digit is valid for current box, return false
                    return false;
                }
            }
        }
        // when every box is filled with a valid number, return true
        return true;
    }

    private boolean isValid(int row, int col, char k, char[][] board) {
        // check if there is duplicate digits on the same row and same column
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == k)
                return false;
            if (board[i][col] == k)
                return false;
        }

        // check if there is any duplicate digits in the 3*3 sub-boxes of the grid
        int r = row / 3;
        int c = col / 3;
        for (int i = r * 3; i < r * 3 + 3; i++) {
            for (int j = c * 3; j < c * 3 + 3; j++) {
                if (board[i][j] == k)
                    return false;
            }
        }
        return true;
    }

    // solution 2: pass row, col and board
    public void solveSudoku(char[][] board) {
        dfs(0, 0, board);
    }

    private boolean dfs(int i, int j, char[][] board) {
        // skip filled cells and update row and col
        while (board[i][j] != '.') {
            if (i < 8)
                i++;
            else if (i == 8 && j < 8) {
                i = 0;
                j++;
            } else if (i == 8 && j == 8)
                return true;
        }

        for (char c = '1'; c <= '9'; c++) {
            if (isValid(i, j, c, board)) {
                board[i][j] = c;
                if (dfs(i, j, board))
                    return true;
                // restore
                else
                    board[i][j] = '.';
            }
        }
        return false;
    }

    private boolean isValid(int row, int col, char k, char[][] board) {
        // check if there is duplicate digits on the same row and same column
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == k)
                return false;
            if (board[i][col] == k)
                return false;
        }

        // check if there is any duplicate digits in the 3*3 sub-boxes of the grid
        int r = row / 3;
        int c = col / 3;
        for (int i = r * 3; i < r * 3 + 3; i++) {
            for (int j = c * 3; j < c * 3 + 3; j++) {
                if (board[i][j] == k)
                    return false;
            }
        }
        return true;
    }
}
