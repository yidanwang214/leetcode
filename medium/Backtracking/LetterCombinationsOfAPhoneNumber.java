import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// https://leetcode.com/problems/letter-combinations-of-a-phone-number/

public class LetterCombinationsOfAPhoneNumber {
    /*
     * 23
     * | | |
     * a b c
     * | | |
     * d->e->f d->e->f d->e->f
     */
    List<String> res = new ArrayList<>();
    HashMap<Character, String> map = new HashMap<>();

    public List<String> letterCombinations(String digits) {
        if (digits.equals(""))
            return new ArrayList<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        dfs(0, new StringBuilder(), digits);
        return res;
    }

    private void dfs(int offset, StringBuilder combo, String digits) {
        // base case
        if (combo.length() == digits.length()) {
            res.add(combo.toString());
            return;
        }

        // explore more possibilities
        for (int i = offset; i < digits.length(); i++) {
            // get mapping of a number to letters
            String letters = map.get(digits.charAt(i));
            for (int j = 0; j < letters.length(); j++) {
                // save current state
                combo.append(letters.charAt(j));
                // dfs
                dfs(i + 1, combo, digits);
                // revert to previous state
                combo.deleteCharAt(combo.length() - 1);
            }
        }
    }
}
