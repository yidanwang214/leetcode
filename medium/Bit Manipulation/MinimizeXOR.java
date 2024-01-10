// https://leetcode.com/problems/minimize-xor/

public class MinimizeXOR {
    // optimal solution
    // reference:
    // https://leetcode.com/problems/minimize-xor/solutions/2650706/deep-explanation-and-solution-for-someone-who-isn-t-good-at-bit-problems
    // Time: O(1)
    // Space: O(1)
    public int minimizeXor(int num1, int num2) {
        int bBits = Integer.bitCount(num2);
        int res = 0;

        // store set bits from MSB to LSB
        for (int i = 31; i >= 0 && bBits > 0; i--) {
            if (((1 << i) & num1) > 0) {
                res |= (1 << i);
                bBits--;
                // System.out.println("1: i: " + i + ", res: " + res);
            }
        }

        // store remaining set bits from LSB to MSB
        for (int i = 0; i <= 31 && bBits > 0; i++) {
            if (((1 << i) & num1) == 0) {
                res |= (1 << i);
                bBits--;
                // System.out.println("2: i: " + i + ", res: " + res);
            }
        }

        return res;
    }

    // /*
    // * my approach
    // * pseudo:
    // * 1. get the bitCount of num2
    // * 2. create a string result varaible
    // * 3. convert num1 to binary string, iterate from MSB to LSB, if the current
    // bit
    // * is 1, we append 0 to res, if the current bit is 0 we append 1 to res, until
    // * we use up availble bits/untill bitCount ==0
    // * 4. After the iteration, if bitCount > 0, we append the bitCount number of 1
    // * before res
    // * 5. convert res to binary then to decimal
    // *
    // * Time: O(n) where n represents the length of num1's binary representation
    // * Space: O(1)
    // */

    // public int minimizeXor(int num1, int num2) {
    // // 1. get the number of bits in num2
    // // we can either use Interger.bitCount API
    // // int bitCount1 = Integer.bitCount(num2);
    // // /*
    // // or we can use & sign and iteration to check the number of bits in num2
    // int bitCount2 = 0;
    // while (num2 != 0) {
    // if ((num2 & 1) == 1)
    // bitCount2++;
    // num2 = (num2 >> 1);
    // }
    // // */

    // // 2. create res variable
    // StringBuilder res = new StringBuilder();

    // // 3. convert num1 to string
    // // String num1Str = Integer.toBinaryString(num1);
    // StringBuilder num1Str = new StringBuilder();
    // for (int i = num1; i > 0; i /= 2) {
    // num1Str.append(i % 2);
    // }
    // num1Str = num1Str.reverse();

    // // iterate
    // for (int i = 0; i < num1Str.length(); i++) {
    // if (bitCount2 == 0)
    // break;
    // if (num1Str.charAt(i) == '1') {
    // res.append(1);
    // bitCount2--;
    // } else {
    // res.append(0);
    // }
    // }

    // if (bitCount2 == 0 && num1Str.length() > res.length())
    // res.append("0".repeat(num1Str.length() - res.length()));

    // // 4. if the number of 1s in num2 is greater than that in num1, we need to
    // flip
    // // reamining bitCount number of 0 from LSB in res
    // if (bitCount2 > 0) {
    // for (int i = res.length() - 1; i > 0 && bitCount2 > 0; i--) {
    // if (res.charAt(i) == '0') {
    // res.setCharAt(i, '1');
    // bitCount2--;
    // }
    // }
    // }

    // // 5. if after flipping all of the 0s to 1s in res, there are still remaining
    // 1s
    // // in res, then we have to add the bitCount number of 1s before res
    // if (bitCount2 > 0)
    // res.insert(0, "1".repeat(bitCount2));

    // // 6. convert res to binary int and then to decimal
    // int resDecimal = 0;
    // int len = res.length();
    // for (int i = 0; i < len; i++) {
    // resDecimal += ('a' - res.charAt(i) == 48 ? Math.pow(2, (len - i - 1)) : 0);
    // }
    // return resDecimal;
    // }

    public static void main(String args[]) {

        System.out.println(3 << 0);
    }
}
