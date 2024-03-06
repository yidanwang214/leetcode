// https://leetcode.com/problems/sqrtx/description/

public class Sqrt {
    /*
     * Time: O(logn) -> the num of seach
     * Space: O(logn) -> recursive stack
     */
    public int mySqrt(int x) {
        return (int) binarySearch(0, x / 2, x);
    }

    /*
     * bs(0, 22, 44) -> 6
     * mid: 11, bs(0, 10, 44)
     * mid: 5, bs(6, 10, 44)
     * mid: 8, bs(6, 7, 44)
     * mid: 6, bs(7, 7, 44)
     */

    private long binarySearch(long start, long end, int x) {
        // base case
        if (start > end)
            return end;
        if (start == end && (end + 1) * (end + 1) == x)
            return end + 1;
        if (start == end && end * end > x && (end - 1) * (end - 1) < x)
            return end - 1;
        if (start == end && end * end < x && (end + 1) * (end + 1) > x)
            return end;

        // recursive case
        long mid = start + (end - start) / 2;
        if (mid * mid == x)
            return mid;
        else if (mid * mid < x)
            return binarySearch(mid + 1, end, x);
        else
            return binarySearch(start, mid - 1, x);
    }
}
