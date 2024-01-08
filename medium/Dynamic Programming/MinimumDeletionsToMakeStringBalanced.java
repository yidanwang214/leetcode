public class MinimumDeletionsToMakeStringBalanced {
    // question: if input is "bbbbbbbab", will the output be "bbbbbbbb"?
    // answer: yes!

    class Solution {
        // solution: two pass
        // reference: https://www.youtube.com/watch?v=tR2tu8_Wp1o
        // time: O(n)
        // space: O(n)
        public int minimumDeletions(String s) {
            int len = s.length();
            int[] prefix = new int[len]; // keep track of the # of B before index i
            int[] suffix = new int[len]; // keep track of the # of A after index i

            // one pass: insert prefix with the the # of b before index i
            prefix[0] = s.charAt(0) == 'b' ? 1 : 0;
            for (int i = 1; i < len; i++)
                prefix[i] = prefix[i - 1] + (s.charAt(i) == 'b' ? 1 : 0);

            // two pass: insert suffix with the # of a after i
            suffix[len - 1] = s.charAt(len - 1) == 'a' ? 1 : 0;
            for (int i = len - 2; i >= 0; i--)
                suffix[i] = suffix[i + 1] + (s.charAt(i) == 'a' ? 1 : 0);

            // three pass: get the min sum of the number of b before the split and the
            // number of a after the split
            // handle edge case
            // suffix[0] = 0 + suffix[0], 0 is the # of b before the 0th index, suffix[0] is
            // the number of a after the 0th index
            // prefix[len-1] = prefix[len-1] + 0, prefix[len-1] is the # of b before the
            // (len-1-th) index, 0 is the number of a after the (len-1-th) index
            int res = Math.min(suffix[0], prefix[len - 1]); // edge case: bbbba
            for (int i = 0; i < len - 1; i++)
                res = Math.min(res, prefix[i] + suffix[i + 1]);

            return res;
        }

        // optimimal:
        // time: O(n)
        // space: O(1)
        // better explanation:
        // https://leetcode.com/problems/flip-string-to-monotone-increasing/solutions/189751/c-one-pass-dp-solution-0ms-o-n-o-1-one-line-with-explaination/
        // reference:
        // https://leetcode.com/problems/minimum-deletions-to-make-string-balanced/solutions/935701/dp-solution-beats-100-with-explanation/
        public int minimumDeletions(String s) {
            int len = s.length();
            int res = 0;
            int bcount = 0;
            for (int i = 0; i < len; i++) {
                if (s.charAt(i) == 'a')
                    res = Math.min(res + 1, bcount);
                else if (s.charAt(i) == 'b')
                    bcount++;
            }
            return res;
        }
    }
}
