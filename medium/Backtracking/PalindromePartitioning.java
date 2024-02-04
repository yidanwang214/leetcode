// https://leetcode.com/problems/palindrome-partitioning/description/

import java.util.ArrayList;
import java.util.List;

/* s: abbbabba
output: abbba abba

s: aab
output: [a,a,b], [aa,b]
    aab
|
a|ab  -> aa|b   ->  aab|   
|           |
|       aa,b|
|       
|
a,a|b -> a,ab|
|
a,a,b|


            aabb()
    |
    a|abb              -> ab|bb            -> abb|b    -> abbb|
    |                       |                  |  
    a,a|bb -> a,ab|b      ab,b|b -> ab,bb|    abb,b|
    |           |           |        
    |         a,ab,b      ab,b,b|
    |                      
    a,a,b|b ->  a,a,bb
    |             
    a,a,b,b
    


s: a
output: a
 */

public class PalindromePartitioning {
    /*
     * 1. a better approach: check if every potential valid substring is palindrome,
     * if they are, we can dfs to go down the branch, if not, just continue to
     * another branch
     */
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> partition(String s) {
        dfs(0, new ArrayList<>(), s);
        return res;

    }

    private void dfs(int start, List<String> palindrome, String s) {
        // base case
        if (start == s.length()) {
            res.add(new ArrayList<>(palindrome));
            return;
        }

        // explore possibilities
        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s.substring(start, i + 1))) {
                palindrome.add(s.substring(start, i + 1));
                dfs(i + 1, palindrome, s);
                palindrome.remove(palindrome.size() - 1);
            }
        }
    }

    /*
     * 2. my approach: check if every substring is a palindrome in base case, it is
     * quite inefficient
     */
    List<List<String>> res = new ArrayList<>();

    // StringBuilder s;
    public List<List<String>> partition(String s) {
        // this.s = new StringBuilder(s);
        dfs(1, new ArrayList<>(), s);
        return res;
    }

    // dfs
    private void dfs(int offset, List<String> palindrome, String s) {
        // base case
        if (offset > s.length() + 1)
            return;
        if (offset == s.length() + 1) {
            for (int i = 0; i < palindrome.size(); i++) {
                if (!isPalindrome(palindrome.get(i)))
                    return;
            }
            res.add(new ArrayList<>(palindrome));
            return;
        }

        // explore possibile results
        for (int i = offset; i <= s.length(); i++) {
            // save current state
            palindrome.add(s.substring(0, i));

            dfs(1, palindrome, s.substring(i));

            // revert to previous state
            palindrome.remove(palindrome.size() - 1);
        }
    }

    // helper function check if a string is palindrome
    private boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r))
                return false;
            l++;
            r--;
        }
        return true;
    }

}
