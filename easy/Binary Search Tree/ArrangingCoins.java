public class ArrangingCoins {

    // binary search
    // time: O(logn)
    // space: O(1)
    public static int arrangeCoins(int n) {
        long l = 0;
        long r = n;
        long mid = 0;
        long sum = 0;
        while (l <= r) {
            mid = l + (r - l) / 2;
            sum = mid * (1 + mid) / 2;
            if (sum == n) {
                return (int) mid;
            } else if (sum > n) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int) r;
    }

    // brutal force: iterative subtraction
    // time: O(√n)
    // space: O(1)
    // public int arrangeCoins(int n) {
    // int counter = 0; // count the number of complete rows

    // while(n >= 0 && n > counter){
    // counter++;
    // n = n - counter;
    // }
    // return counter;
    // }

    // brutal force: iterative addition
    // time: O(√n)
    // space: O(1)
    // public static int arrangeCoins(int n) {
    // long counter = 0; // count the number of complete rows
    // long sum = 0; // get the sum of coins in complete rows
    // while (sum < n) {
    // counter++;
    // sum += counter;
    // if ((sum + counter + 1) > n) {
    // break;
    // }
    // }
    // return (int) counter;
    // }

    public static void main(String[] args) {
        System.out.println(arrangeCoins(2147483647));
    }
}
