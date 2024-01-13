// https://leetcode.com/problems/valid-palindrome/description/
public class ValidPalindrome {
    /*
     * 1. set 2 pointers i and j, which starts from left hand side and right hand
     * side of the string respectively
     * 2. go over the string from left and from right at the same time
     * -> check if charAt(i) is alphebetic and digit, if not, i++, continue
     * -> check if charAt(j) is alphebetic and digit, if not, i--, continue
     * -> after confirming both chatAt(i) and charAt(j) are alphabetic, we can
     * compare if they are same, if not, return false; else, continue;
     * 3. return true if we finish checking that the right half of string and right
     * half of the string are identical
     */

    public static boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        // System.out.println(Character.isAlphabetic(s.charAt(i)));
        // System.out.println(Character.isAlphabetic(s.charAt(j)));
        while (i <= j) {
            if ((!Character.isAlphabetic(s.charAt(i))) &&
                    (!Character.isDigit(s.charAt(i)))) {
                i++;
                continue;
            }
            if (!Character.isAlphabetic(s.charAt(j)) && !Character.isDigit(s.charAt(j))) {
                j--;
                continue;
            }
            if (Character.isAlphabetic(s.charAt(i))) {
                if (Character
                        .compare(Character.toLowerCase(s.charAt(i)), Character.toLowerCase(s.charAt(j))) != 0) {
                    return false;
                }
            } else {
                if (Character.compare(s.charAt(i), s.charAt(j)) != 0) {
                    return false;
                }
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }
}
