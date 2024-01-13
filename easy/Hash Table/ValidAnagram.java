import java.util.HashMap;

public class ValidAnagram {
    // Time: O(n)
    // Space: O(m), m represents 26
    public boolean isAnagram(String s, String t) {
        int[] letters = new int[26];
        for (int i = 0; i < s.length(); i++)
            letters[s.charAt(i) - 'a']++;
        for (int i = 0; i < t.length(); i++)
            letters[t.charAt(i) - 'a']--;
        for (int i : letters)
            if (i != 0)
                return false;
        return true;
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        HashMap<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.merge(c, 1, Integer::sum);
        }
        for (char c : t.toCharArray()) {
            if (!freq.containsKey(c)) {
                return false;
            } else {
                if (freq.get(c) == 1)
                    freq.remove(c);
                else
                    freq.put(c, freq.get(c) - 1);
            }
        }
        return true;
    }
}
