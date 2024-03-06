// https://leetcode.com/problems/check-if-the-sentence-is-pangram/

public class CheckIfPangram {
    public boolean checkIfPangram(String sentence) {
        int[] arr = new int[26];
        for (int i = 0; i < sentence.length(); i++) {
            arr[sentence.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (arr[i] == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println('b' - 'a');
    }
}
