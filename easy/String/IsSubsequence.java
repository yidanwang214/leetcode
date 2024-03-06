// https://leetcode.com/problems/is-subsequence/

public class IsSubsequence {
    /*
     * algo:
     * 2 pointers to iterate though s and t
     * while loop, ptrS< lenghth of s, ptrT < length of T
     * 1. ptrS =0 , ptrT = 0
     * 2. check if the charater at index prtS is the same as the character at index
     * prtT
     * if same, increment ptrS and ptrT
     * if not same, increment the ptrT
     * 
     * 3. check if ptrS == length
     * if true, return true
     * else false
     * 
     * Time: O(n), n is the length of t
     * Space: O(1)
     */
    public boolean isSubsequence(String s, String t) {
        // create 2 pointes, iterate though s and t
        int ptrS = 0;
        int ptrT = 0;
        // scan through s and t at the time
        while (ptrS < s.length() && ptrT < t.length()) {
            // check if the charater at index prtS is the same as the character at index
            // ptrT
            if (s.charAt(ptrS) == t.charAt(ptrT)) {
                ptrS++;
            }
            ptrT++;
        }
        // check if ptrS == length of s
        if (ptrS == s.length())
            return true;
        return false;
    }
}
