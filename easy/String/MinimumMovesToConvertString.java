// https://leetcode.com/problems/minimum-moves-to-convert-string/
// Time: O(n)
// Space: O(1)

public class MinimumMovesToConvertString {
    public int minimumMoves(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'X') {
                res++;
                i += 2;
            }
        }
        return res;
    }
}
