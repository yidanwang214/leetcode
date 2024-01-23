public class SolvingQuestionsWithBrainPower {
    // reference: https://www.youtube.com/watch?v=yPaLLSEDlX4

    /*
     * 1. memoization
     * 
     * 0 1 2 3
     * questions = [[3, 2], [4, 3], [4, 4], [2, 5]]
     * index:0
     * ˇ ˇ
     * 3 skip
     * ˇ ˇ ˇ ˇ
     * 3+2=5 0 4 skip
     * ˇ ˇ
     * 4 skip
     * ˇ ˇ
     * 2 skip
     * memo[questions.length]
     * dfs(startIdx, questions){
     * 
     * if(startIdx) >= questions.length return0;
     * // points that we can get for not skipping
     * notSkip = dfs(startIndex+questions[startIndex][1]+1, questions)
     * // points that we can get for skipping the current question
     * Skip = dfs(startIndex+1, questions);
     * // leave the greater value
     * memo[startIndex] = Math.max(notSkip, Skip);
     * return memo[startIndex];
     * }
     * Time: O(n), neever evaluating each question more than once = we just visit
     * each question once
     * Space: O(n)
     */

    long[] memo;
    int[][] questions;

    public long mostPoints(int[][] questions) {
        this.memo = new long[questions.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = -1L;
        }
        this.questions = questions;
        return dfs(0);
    }

    public long dfs(int startIndex) {
        if (startIndex >= this.questions.length)
            return 0L;
        if (memo[startIndex] != (long) -1)
            return memo[startIndex];
        long nonSkipPoints = dfs(startIndex + questions[startIndex][1] + 1) + questions[startIndex][0];
        long skipPoints = dfs(startIndex + 1);
        memo[startIndex] = Math.max(nonSkipPoints, skipPoints);
        return memo[startIndex];
    }

    /*
     * 2. Tabulation
     * i.e.1
     * questions = [[3, 2], [4, 3], [4, 4], [2, 5]]
     * dp = [3 , 4 , 4 , 2+3 ]
     * 
     * i.e.2
     * questions = [[1,1],[2,2],[3,3],[4,4],[5,5]]
     * dp = [ ,3, 4, 5]
     * 
     * 2.1 direction: right to left
     * 2.2 dp[i] means maxPoints we can get for question[i]
     * 2.3 equation:
     * dp[i] = max between question[i][0] + dp[i+question[i][1]] (if
     * (i+question[i][1]) < dp.length) and dp[i+1]
     * compare the sum of points for solvingCurrentQuestion and
     * maxPointOfItsNextQuestion [i+arr[i][1]], with maxPoints for skipping this
     * question, which is value of the right next index in dp [i+1]
     * 2.4 initialisation: dp[i] = 0
     */
    public long mostPoints(int[][] questions) {
        long[] dp = new long[questions.length];
        int len = dp.length;
        // from right to left
        for (int i = len - 1; i >= 0; i--) {
            long pts = (long) questions[i][0];
            int steps = questions[i][1];
            dp[i] = Math.max(
                    // max points we can get if we skip the current question
                    (i + 1 > len - 1 ? 0L : dp[i + 1]),

                    // max points we can get if we solve the current question
                    (pts + (i + steps + 1 > len - 1 ? 0L : dp[i + steps + 1])));
        }
        return dp[0];
    }
}
