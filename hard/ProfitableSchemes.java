// https://leetcode.com/problems/profitable-schemes/

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class ProfitableSchemes {

    public static Stack<Integer> stack = new Stack<Integer>();
    public static int sum = 0;

    public static void testRecursiveBreak(int start, int[] arr) {

        for (int i = start; i < arr.length; i++) {
            stack.push(arr[i]);
            sum += arr[i];
            if (sum <= 10) {
                printStack(stack);
                testRecursiveBreak(i + 1, arr);
                stack.pop();
                sum -= arr[i];
            } else {
                stack.pop();
                sum -= arr[i];
                testRecursiveBreak(i + 1, arr);
                // i = arr.length - 1;
                break;
            }
        }
    }

    public static void printStack(Stack<Integer> stack) {
        System.out.print("[");
        for (int i = 0; i < stack.size(); i++) {
            System.out.print(stack.elementAt(i) + ", ");
        }
        System.out.print("]" + ", Sum: " + sum + "\n");
    }

    public static void print(ArrayList<Integer> arr) {
        System.out.print("[");
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(i + ", ");
        }
        System.out.print("]" + ", Sum: " + sum + "\n");
    }

    public static int sum(ArrayList<Integer> arr) {
        int sum = 0;
        for (int i = 0; i < arr.size(); i++) {
            sum += arr.get(i);
        }
        return sum;

    }

    public static void main(String[] args) {
        int[] arr = { 1, 6, 3, 9, 2, 0 }; // subset sum no more than 10
        testRecursiveBreak(0, arr);

        // ArrayList<ArrayList<Integer>> outer = new ArrayList<>();
        // ArrayList<Integer> inner = new ArrayList<Integer>();
        // ArrayList<Integer> inner2 = new ArrayList<Integer>();
        // inner.add(1);
        // inner.add(2);
        // inner.add(3);
        // outer.add(inner);
        // inner2.add(1);
        // inner2.add(2);
        // inner2.add(3);
        // outer.add(inner2);
        // for (int i = 0; i < outer.size(); i++) {
        // System.out.print("i: " + i + "; items: ");
        // for (int j = 0; j < outer.get(i).size(); j++) {
        // if (outer.get(i).get(j) == 2) {
        // break;
        // }
        // System.out.print(outer.get(i).get(j) + " ");
        // }
        // System.out.println();
        // }
    }
}
