// https://leetcode.com/problems/integer-to-roman/

public class IntegerToRoman {

    // https://leetcode.com/problems/integer-to-roman/solutions/6274/simple-solution/
    // Time: O(1)
    // Space: O(1)
    public String intToRoman(int num) {
        // [less than 1k, 1k, 2k, 3k]
        String[] M = { "", "M", "MM", "MMM" };
        // [less than 100, 100, 200, 300, 400, 500, 600, 700, 800, 900]
        String[] C = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
        // [less than 10, 10, 20, 30, 40, 50, 60, 70, 80, 90]
        String[] X = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
        // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        String[] I = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };

        return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
    }

    /*
     * brute force
     * pesudo:
     * 1. create hashmap, push special mapping of value,symbol into hashmap
     * 2. create a digit stack, which saves input number's each digit from LSD to
     * MSD
     * 3. pop each and every element from stack
     * 3.1. if the current digit is between 5(inclusive) and 9, append to res the
     * mapping of 5's multiple
     * which is equivalent to 5 times 10 raised to the power of size of the stack,
     * i.e.,
     * suppose the number we are dealing with is 879, the digit popped from stack is
     * 8, the currect stack size is 2, so we can append 8 * 10^2 's roman mapping to
     * res. then subtract 5 from the current digit.
     * 3.2. if the current digit is between 1(inclusive) and 4, append to res the
     * mappig of 1's multiple * the times 1's roman representation should repeat,
     * which is the equivalent of 1 time 10 raised to power
     * of the size of the stack times the digit. ie, after 3.1., digit = 8 - 5 = 3,
     * so we need to append 1 * 10^2 .repeat(digit)
     * 3.3. if the current digit is 4 or 9, append to res the mapping of the digit's
     * multiple, ie.when we are dealing with the last digit 9 from 879, we should
     * append 9*10^0 to res
     * 3.4. if the current digit is 0, no need to handle.
     * 
     * Time: O(n), n is the number of digits in inputs
     * Space: O(n), n is the number of digits in input
     */
    public String intToRoman(int num) {
        // return numbersrepresented by a single letter
        HashMap<Integer, String> ara2Roman = new HashMap<>();
        ara2Roman.put(1, "I");
        ara2Roman.put(4, "IV");
        ara2Roman.put(5, "V");
        ara2Roman.put(9, "IX");
        ara2Roman.put(10, "X");
        ara2Roman.put(40, "XL");
        ara2Roman.put(50, "L");
        ara2Roman.put(90, "XC");
        ara2Roman.put(100, "C");
        ara2Roman.put(400, "CD");
        ara2Roman.put(500, "D");
        ara2Roman.put(900, "CM");
        ara2Roman.put(1000, "M");
        if (ara2Roman.containsKey(num))
            return ara2Roman.get(num);

        // push every digit onto stack, the most significant digit will be on top
        Stack<Integer> digitStack = new Stack<>();
        while (num != 0) {
            digitStack.push(num % 10);
            num = num / 10;
        }

        // get the roman numemral for every digit of num
        String res = "";
        int digit = 0;
        // reason for "digit != 0" is because when we handle 5< digit < 9, we need to
        // subtract 5 from the digit
        while (digitStack.size() != 0) {
            // pop each and every digit to digit
            digit = digitStack.pop();
            // if digit 5 < digit < 9
            if (digit >= 5 && digit < 9) {
                res += ara2Roman.get(5 * (int) Math.pow(10, digitStack.size()));
                if (digit == 5)
                    continue;
                digit -= 5;
            }

            // if digit 1 <= digit < 4
            if (digit >= 1 && digit < 4) {
                res += ara2Roman.get(1 * (int) Math.pow(10, digitStack.size())).repeat(digit);
                continue;
            }

            if (digit == 4 || digit == 9) {
                res += ara2Roman.get(digit * (int) Math.pow(10, digitStack.size()));
                continue;
            }

            // if digit == 0, don't need to handle
            if (digit == 0)
                continue;
        }
        return res;
    }

    public static void main(Stringp[] args) {
        String i = "123";
    }
}
