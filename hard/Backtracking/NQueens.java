import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/n-queens/description/

public class NQueens {
    public List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        dfs(0, n, new ArrayList<>());
        return res;
    }

    private void dfs(int r, int n, List<List<Character>> tmpRes) {
        // base cond
        if (tmpRes.size() == n) {
            List<String> strList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String str = "";
                for (int j = 0; j < n; j++) {
                    str += tmpRes.get(i).get(j);
                }
                strList.add(str);
            }
            res.add(new ArrayList<>(strList));
            return;
        }

        // explore
        for (int row = r; row < n; row++) {
            for (int col = 0; col < n; col++) {
                // store current state
                // fill the first row from the first col to the last col, without need to check
                // collision
                if (tmpRes.size() == 0) {
                    tmpRes.add(fillRow(col, n));
                    // when fill from the second row, check collision
                } else {
                    if (isCollide(row, col, n, tmpRes)) {
                        if (col < n - 1) {
                            System.out.println("collide row: " + row + ", col: " + col + ", n: " + n + ", tmpRes: "
                                    + tmpRes.size());
                            continue;
                        } else {
                            System.out.println("collide row(return): " + row + ", col: " + col + ", n: " + n
                                    + ", tmpRes: " + tmpRes.size());
                            return;
                        }
                    }
                    System.out.println(
                            "Non-ollide row: " + row + ", col: " + col + ", n: " + n + ", tmpRes: " + tmpRes.size());
                    tmpRes.add(fillRow(col, n));
                }
                dfs(row + 1, n, tmpRes);
                tmpRes.remove(tmpRes.size() - 1);
                if (col == n - 1)
                    return;
            }
        }
    }

    private List<Character> fillRow(int col, int n) {
        List<Character> al = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i == col)
                al.add('Q');
            else
                al.add('.');
        }
        return al;
    }

    private boolean isCollide(int row, int col, int n, List<List<Character>> tmpRes) {
        // check if the column had a queen before
        for (int i = 0; i < row; i++) {
            if (tmpRes.get(i).get(col) == 'Q')
                return true;
        }

        /*
         * the following is the wrong way of checking diagonal collision, as it just
         * consider the row, but we have to check multiple rows.
         * if((col > 0 && tmpRes.get(row-1).get(col-1) == 'Q') || (col < n-1 &&
         * tmpRes.get(row-1).get(col+1) == 'Q')) return true;
         * reference:
         * https://leetcode.com/problems/n-queens/solutions/19808/accepted-4ms-c-
         * solution-use-backtracking-and-bitmask-easy-understand/
         */

        // check if the 45 degree diagonal has a queen before
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (tmpRes.get(i).get(j) == 'Q')
                return true;
        }

        // check if the 135 degree diagonal has a queen before
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (tmpRes.get(i).get(j) == 'Q')
                return true;
        }
        return false;
    }
}
