import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.lang.Math;

// https://leetcode.com/problems/find-the-original-array-of-prefix-xor/description/
public class OriginalArrayOfPrefixXor {
    // Bitwise Operator
    // Time complexity: O(n)
    // Space complexity: O(1)↑
    // iterate BACKWARDS!
    public int[] findArray(int[] pref) {
        if (pref.length == 1)
            return pref;

        for (int i = pref.length - 1; i > 0; i--) {
            pref[i] = pref[i - 1] ^ pref[i];
        }
        return pref;
    }

    // Time complexity: O(n)
    // Space complexity: O(n)
    public int[] findArray(int[] pref) {
        if (pref.length == 1)
            return pref;

        int[] arr = new int[pref.length];
        arr[0] = pref[0];
        for (int i = 1; i < pref.length; i++)
            arr[i] = pref[i - 1] ^ pref[i];
        return arr;
    }

    // brute force
    // time complexity: O(mn), m is the length of the pref, n if the number of
    // binary digits of each element in pref
    // space complexity: O(mn), m is the length of the pref, n if the number of
    // binary digits of each element in pref
    // failed to pass all test cases on leetcode
    // public int[] findArray(int[] pref) {
    // if (pref.length == 1)
    // return pref;

    // int[] arr = new int[pref.length];
    // arr[0] = pref[0];
    // List<Stack<Integer>> biHistory = new ArrayList<Stack<Integer>>();
    // biHistory.add(toBinary(pref[0]));
    // for (int i = 1; i < arr.length; i++) {
    // biHistory.add(toBinary(pref[i]));
    // int preLength = biHistory.get(i - 1).size();
    // int currLength = biHistory.get(i).size();
    // if (preLength > currLength) {
    // biHistory.set(i, padding0(biHistory.get(i), preLength - currLength));
    // } else if (preLength < currLength) {
    // biHistory.set(i - 1, padding0(biHistory.get(i - 1), currLength - preLength));
    // }

    // Stack<Integer> xorStack = new Stack();
    // Stack<Integer> preStack = new Stack();
    // Stack<Integer> currStack = new Stack();
    // // Stack<Integer> preStack = biHistory.get(i-1);
    // // Stack<Integer> currStack = biHistory.get(i);
    // cloneStack(preStack, biHistory.get(i - 1));
    // cloneStack(currStack, biHistory.get(i));

    // while (!preStack.isEmpty()) {
    // xorStack.push(xor(preStack.pop(), currStack.pop()));
    // }
    // arr[i] = toDecimal(xorStack);
    // }
    // return arr;
    // }

    // public static Stack<Integer> toBinary(int num) {
    // Stack<Integer> stack = new Stack<>();
    // if (num == 0) {
    // stack.push(0);
    // return stack;
    // }

    // int dividend = num;
    // int divisor = 2;
    // int quotient = 0;
    // while (dividend != 0) {
    // stack.push(dividend % 2);
    // dividend = dividend / 2;
    // if (dividend == 1) {
    // stack.push(1);
    // break;
    // }
    // }
    // return stack;
    // }

    // public static Stack<Integer> padding0(Stack<Integer> stack, int numOf0) {
    // while (numOf0 > 0) {
    // stack.push(0);
    // numOf0--;
    // }
    // return stack;
    // }

    // public static int toDecimal(Stack<Integer> stack) {
    // int length = stack.size();
    // int i = 0;
    // int decimal = 0;
    // while (i < length) {
    // int top = stack.pop();
    // if (top == 1)
    // decimal += Math.pow(2, i);
    // i++;
    // }
    // return decimal;
    // }

    // public static int xor(int num1, int num2) {
    // if (num1 == num2)
    // return 0;
    // return 1;
    // }

    // public static void cloneStack(Stack<Integer> s1, Stack<Integer> s2) {
    // for (int i = 0; i < s2.size(); i++) {
    // s1.push(s2.get(i));
    // }

    // }

    public static void main(String[] args) {
        int[] arr = { 5, 2, 0, 3, 1 };
        System.out.println(Arrays.toString(findArray(arr)));
    }

}
