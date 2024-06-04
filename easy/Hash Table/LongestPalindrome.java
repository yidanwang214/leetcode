public class LongestPalindrome {
    /*
     * s: A a B b A a -> 5
     * s: A a A a C c c -> 7
     * 
     * algo:
     * 1. count the freqency of each letter
     * 2. iterate through the freqency array, add all even number to res,
     * and all odd (number / 2)*2 to res
     * 3. if there is any odd number, add 1 to res
     * 
     * Time: O(n)
     * Space: O(58) -> O(1)
     */
    public int longestPalindrome(String s) {
        int res = 0;
        int[] freq = new int['z' - 'A' + 1];
        for (int i = 0; i < s.length(); i++)
            freq['z' - s.charAt(i)]++;

        boolean isOdd = false;
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] % 2 == 0)
                res += freq[i];
            else {
                isOdd = true;
                res += (freq[i] / 2) * 2;
            }
        }
        return isOdd == true ? res + 1 : res;
    }
}
