import java.util.Scanner;

// https://leetcode.com/problems/valid-parenthesis-string/?envType=daily-question&envId=2024-04-07
public class ValidParenthesisString {
    // 1. greedy approach
    // https://www.youtube.com/watch?v=ReR0bp9cAtc
    // Time: O(s.length)
    // Space: O(1)
    public boolean checkValidStringGreedy(String s) {
        int leftMin = 0; // min num of unmatched left parenthesis, see * as )
        int leftMax = 0; // max num of unmatched left parenthesis, see * as (
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                leftMin--; // to get the min num of unmatched left parenthesis we need to treat * as ),
                           // which can balance out (
                leftMax++; // to get the max num of unmatched right parenthesis we nned to treat * as (,
                           // which can add to the number of left parenthesis
            } else if (s.charAt(i) == '(') {
                leftMin++;
                leftMax++;
            } else { // s.charAt(i) == ")"
                leftMin--;
                leftMax--;
            }
            // if leftMin < 0, we revert a * treated as ) back to */empty string by setting
            // leftMin to 0
            if (leftMin < 0) {
                leftMin = 0;
            }
            if (leftMax < 0) {
                // if max unmatched ( is not enought to cancel out all of the ) we have
                // encountered, the string is unbalanced and can't be remedied by the remaining
                // unscanned chars
                return false;
            }
        }
        /*
         * if we can finish running the loop,
         * [leftMin, leftMax] >= 0,
         * if leftMin > 0, that means the min num of ( can't be cancelled out by the )
         * in the str, even we may have reverted some ) back to *\empty string,
         * so if leftMin = 0, the min num of ( can be cancelled out by all of the ),
         * which makes the str valid
         */
        return leftMin == 0;
    }

    // 2. memoization is correct but has TLE
    private boolean[][] dp; // dp[i][j]:when the length of str is i, the number of left ( is j, if the str
                            // is valid

    public boolean checkValidStringDP(String s) {
        this.dp = new boolean[s.length() + 1][s.length() + 1];
        for (int i = 0; i < s.length() + 1; i++) {
            for (int j = 0; j < s.length() + 1; j++) {
                dp[i][j] = false;
            }
        }
        dfs(s, 0);
        return dp[0][0];
    }

    private boolean dfs(String s, int leftNum) {
        // base case
        if (s.length() == 0 && leftNum == 0) {
            dp[0][0] = true;
            return true;
        }
        // if we checked all signs in s and s is empty but leftNum is not 0, means the
        // num of open parenthesis is not same as right parenthesis
        if (s.length() <= 0 || leftNum < 0)
            return false;

        if (dp[s.length()][leftNum] == true)
            return dp[s.length()][leftNum];

        // if the first/current char is (
        if (s.charAt(0) == '(') {
            System.out.println("( s: " + s);
            dp[s.length()][leftNum] = dfs(s.substring(1), leftNum + 1);
        } else if (s.charAt(0) == '*') { // if the first/current char is )
            System.out.println("* s: " + s);
            if (dfs(s.substring(1), leftNum + 1) == true || dfs(s.substring(1), leftNum) == true
                    || dfs(s.substring(1), leftNum - 1) == true)
                dp[s.length()][leftNum] = true;

        } else { // if the first/current char is )
            System.out.println(") s: " + s + ", leftNum-1:" + (leftNum - 1));
            dp[s.length()][leftNum] = dfs(s.substring(1), leftNum - 1);
        }
        return dp[s.length()][leftNum];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ValidParenthesisString obj = new ValidParenthesisString();
        while (sc.hasNextLine()) {
            obj.checkValidString(sc.nextLine());
        }
    }
}
