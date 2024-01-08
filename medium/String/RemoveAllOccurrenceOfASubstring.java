import java.util.Arrays;

public class RemoveAllOccurrenceOfASubstring {
    // stack solution: SttingBuilder!!!
    // https://leetcode.com/problems/remove-all-occurrences-of-a-substring/solutions/1298766/c-simple-solution-faster-than-100/
    // time complexity: O(m+n)
    // space complexity: O(1)
    public String removeOccurrences(String s, String part) {
        int i = 0;
        int j = 0;
        StringBuilder res = new StringBuilder(s);

        for (i = 0, j = 0; i < s.length(); i++) {
            res.setCharAt(j++, s.charAt(i));
            if (j >= part.length() && res.substring(j - part.length(), j).equals(part)) {
                j -= part.length();
            }
        }
        return res.substring(0, j);
    }

    // String API
    // time: O(m/n), m:s.length, n:part.length,
    // space: O(1)
    // class Solution {
    public String removeOccurrences(String s, String part) {
        int l = part.length();
        while (s.indexOf(part) != -1) {
            s = s.substring(0, s.indexOf(part)) + s.substring(s.indexOf(part) + l);
        }
        return s;
    }

    }
    // case 1:
    // s: daabcbaabcbc -> dabaabcbc -> dababc
    // p: abc
    // (0,2)+ (5) -> (0,4)+(7) -> (0, 3)+ (6)

    // case 2: xyxxxxyyyyb
    // part: xy -> xxxxyyyyb -> xxxyyyb -> xxyyb -> xyb -> b
    // (0,0)+(2) -> (0,3)+(5) -> (0,2)+(4) -> (0,1)+(3) -> (0,0)+(2)
    // pointer
    // time: O(n) ave, worst O(mn) where m for length of s and n for length of part
    // space: O(1)
    // 1 <= s.length <= 1000
    // 1 <= p.lenth <= 1000
    // s: abcabcabc
    // part: ab
    // s: ccc
    public static void main(String[] args) {
        // String s = "ab";
        // s.System.out.println(s.substring(0, 0).length()); // "" for ab
        // System.out.println(s.substring(0, 1)); // "" for a
        // char[] carr = { a, b, c, e };
        // System.out.println();
        removeOccurrences("daabcbaabcbc", "abc");
    }
}
