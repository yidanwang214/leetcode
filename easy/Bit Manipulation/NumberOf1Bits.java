// https://leetcode.com/problems/number-of-1-bits/description/
// use: >>> not >> !!
// >>>: https://stackoverflow.com/questions/19058859/what-does-mean-in-java
/*The difference between >> and >>> would only show up when shifting negative numbers. The >> operator shifts a 1 bit into the most significant bit if it was a 1, and the >>> shifts in a 0 regardless. */
public class NumberOf1Bits {
    // Time: O(n), n for the number of bits
    // Space: O(1)
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            res += (n & 1);
            n = n >>> 1;
        }
        return res;
    }

    // wrong code:
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            if (n & 1 == 1)
                res++;
            n = n >> 1;
        }
        return res;
    }

    public int hammingWeight(int n) {
        return Integer.bitCount(n);
    }

    public static void main(String[] args) {
    }
}
