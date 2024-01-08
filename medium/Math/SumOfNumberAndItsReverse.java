// https://leetcode.com/problems/sum-of-number-and-its-reverse/

import java.util.HashSet;

public class SumOfNumberAndItsReverse {
    // https://leetcode.com/problems/sum-of-number-and-its-reverse/solutions/2709340/java-beats-100-math-solution-with-backtracking-o-log-n-1ms/
    //

    // Brute force: hashset
    // add each number to its reverse
    // time complexity: O(n)
    static HashSet<Integer> set = new HashSet<>();
    static {
        for (int i = 0; i <= 100000; i++) {
            set.add(i + Integer.parseInt(new StringBuilder(Integer.toString(i)).reverse().toString()));
        }
    }

    public boolean sumOfNumberAndReverse(int num) {
        return set.contains(num);
    }

    // brute force: binary search
    // time complexiy: O(mlogn) n:the range of the num, m: the length of digits of
    // each num
    // space complexity: O(1)
    public boolean sumOfNumberAndReverse(int num) {
        int mid = num / 2;
        if (mid == num - mid && isReversed(mid, num - mid))
            return true;

        int l = 0;
        int r = num / 2;
        return findPair(l, r, num);
    }

    public static boolean findPair(int l, int r, int num) {
        if (l > r)
            return false;
        int mid = (r - l) / 2 + l;
        if (isReversed(mid, num - mid))
            return true;
        boolean result = (findPair(l, mid - 1, num) || findPair(mid + 1, r, num));
        return result;
    }

    public static boolean isReversed(int l, int r) {
        String lStr = Integer.toString(l);
        String rStr = Integer.toString(r);
        if (lStr.length() < rStr.length()) {
            String paddingNum = "0".repeat((rStr.length() - lStr.length()));
            lStr = paddingNum + lStr;
        }
        int lp = 0;
        int rp = rStr.length() - 1;
        while (lp < rStr.length()) {
            if (lStr.charAt(lp) != rStr.charAt(rp))
                return false;
            lp++;
            rp--;
        }
        return true;
    }

    // brute force: two pointers
    // time complexiy: O(nm) n:the range of the num, m: the number of digits of the
    // input num
    // space complexity: O(1)
    public boolean sumOfNumberAndReverse(int num) {
        int l = 0;
        int r = num;
        while (l <= r) {
            if (isReversed(l, r))
                return true;
            l++;
            r--;
        }
        return false;

    }

    public static boolean isReversed(int l, int r) {
        String lStr = Integer.toString(l);
        String rStr = Integer.toString(r);
        if (lStr.length() < rStr.length()) {
            String paddingNum = "0".repeat((rStr.length() - lStr.length()));
            lStr = paddingNum + lStr;
        }
        int lp = 0;
        int rp = rStr.length() - 1;
        while (lp < rStr.length()) {
            if (lStr.charAt(lp) != rStr.charAt(rp))
                return false;
            lp++;
            rp--;
        }
        return true;
    }

    public static void main(String[] args) {
        Integer num = 123;
        String s = '0';

    }
}
