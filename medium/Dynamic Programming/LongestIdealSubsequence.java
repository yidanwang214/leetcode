import java.util.Arrays;

public class LongestIdealSubsequence {
    /*
     * reference: https://www.youtube.com/watch?v=NlwpGpH4nLA
     * 1. additional array nearestChar, dp[i]: longest length of subsequence ending
     * at i
     * 2. equation: dp[i] = max(dp[i], dp[longestChar[j]]), j: [s.charAt(i)-k,
     * s.charAt(i)+k]
     * 3. initialisation: every element in dp to be 1, every element in longestChar
     * -1(not exist)
     * 4. scan direction: forward
     * 
     * 0 1 2 3 4 5 6 7 8 k=2
     * b c d e j k l m e
     * 
     * 0 1 2 3 4 5 6 7 8
     * dp: 1 2 3 4 1 2 3 4 5
     * 
     * 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8
     * s: a b c d e f g h i j k l m n
     * c: 1 1 1 8 -1 -1 -1 -1 4 5 6 7 -1
     * 
     */
    class Solution {
        public int longestIdealStringDP(String s, int k) {
            // nearestChar stores the index of a char with which a longest subsequece ends
            int[] longestChar = new int[26];
            Arrays.fill(longestChar, -1);
            longestChar[s.charAt(0) - 'a'] = 0;

            // dp array
            int[] dp = new int[s.length()];
            Arrays.fill(dp, 1);
            int res = 1;
            for (int i = 1; i < s.length(); i++) {
                int charStart = Math.max(s.charAt(i) - k - 'a', 0);
                int charEnd = Math.min(s.charAt(i) + k - 'a', 25);
                for (int j = charStart; j <= charEnd; j++) {
                    if (longestChar[j] != -1) {
                        dp[i] = Math.max(dp[i], dp[longestChar[j]] + 1);
                    }
                }
                longestChar[s.charAt(i) - 'a'] = i;
                res = Math.max(res, dp[i]);
            }
            return res;
        }

        // top down approach: https://www.youtube.com/watch?v=GpXdtLaTyrM
        /*
         * s = "acfgbd", k = 2, output: 4
         * a b c d e f g h i j k l m n o p q r s t
         * 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5
         * dp: 0
         * 1
         * 2
         * 3
         * 4
         * 5 0
         * dfs(0, '{')
         * |
         * choose = dfs(1,'a')+1, skip = dfs(1,'{')
         * |
         * choose = dfs(2,'b')+1, skip = dfs(2,'a')+1
         * |
         * choose = dfs(2,'')
         * 
         * s = "bm", k = 2, output: 1
         * a b c d e f g h i j k l m n o p q r s t
         * 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6
         * dp: 0
         * 1
         * dfs(0, '{') = 1
         * |
         * choose = dfs(1, 'b')+1=0+1=1 skip = dfs(1, '{') = 1 -> max = 1
         * |0 |1
         * choose = 0, skip = dfs(2, 'b') choose = dfs(2, 'm')+1= 0+1=1, skip = dfs(2,
         * '{')=0 -> max = 1, memo[1]['m'-'a']=1
         * ->max = 0 | |
         * memo[1]['b'-'a']=memo[1][1]=0 | |
         * | 0 0 0
         * 0
         * 
         */
        int[][] memo;
        String s;
        int k;

        public int longestIdealString(String s, int k) {
            // initialise memo
            memo = new int[s.length()][27];
            for (int i = 0; i < s.length(); i++)
                Arrays.fill(memo[i], -1);
            this.s = s;
            this.k = k;
            return dfs(0, '{');
        }

        private int dfs(int i, char lastChar) {
            // base case: wehn i == s.length(), we finish scanning the string so the subseq
            // will lengthen
            if (i == s.length())
                return 0;

            // check memo
            if (memo[i][lastChar - 'a'] != -1)
                return memo[i][lastChar - 'a'];

            int choose = 0;
            if (lastChar == '{' || Math.abs(lastChar - s.charAt(i)) <= k)
                choose = dfs(i + 1, s.charAt(i)) + 1;
            int skip = dfs(i + 1, lastChar);

            return memo[i][lastChar - 'a'] = Math.max(choose, skip);
        }

    }
}
