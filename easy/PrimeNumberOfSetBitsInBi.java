import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// Time O(R-L+1)
// Space O(1)
public class PrimeNumberOfSetBitsInBi {
    // 一、bit manipulation
    // points i missed out on:
    // 1. since the max value of r is 10^6 < 2^20, so there will be at most 20 1s
    // so I should add all prime nums less than 20 into HashSet
    // instead of calculating
    // 2. use bit manilation if don't know Integer's API Integer.bitCount()
    // time complexity: O(n)
    // space: O(1)
    public int countPrimeSetBits(int left, int right) {
        Set<Integer> primeNum = new HashSet<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19));
        int res = 0;
        for (int i = left; i <= right; i++) {
            int bitNum = 0;
            for (int j = i; j > 0; j >>= 1)
                bitNum += j & 1; // bit manipulation
            res += primeNum.contains(bitNum) ? 1 : 0;
        }
        return res;
    }

    // 二、
    // https://leetcode.com/problems/prime-number-of-set-bits-in-binary-representation/solutions/113248/easy-o-n-java-solution-using-dp/
    public int countPrimeSetBits(int L, int R) {
        int cnt = 0;
        Set<Integer> listPrimes = new HashSet<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29));
        int[] res = countBits(R);
        for (int i = L; i <= R; i++) {
            if (listPrimes.contains(res[i])) {
                cnt++;
            }
        }
        return cnt;
    }

    // the number of setbits in an interger is the sum of (the setbits in half of
    // the integer + the least significant bit & 1)
    // i.e. : 10 => 1010; 10>>1 == 101 == 5 (has 2 setbits); 10&1 = 0 (0 setbits)
    // 2+0 = 2, so 10 has 2 bits
    public int[] countBits(int num) {
        if (num == 0)
            return new int[1];
        int[] dp = new int[num + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= num; i++) {
            dp[i] = dp[i >> 1] + dp[i & 1]; // i >> 1 is i / 2 and i & 1 is i % 2
        }
        return dp;
    }

    // 三、brute force
    // iterate
    // helper func: isPrime(), if isPrime, the num saved to hashset primeNum
    // time: O(n√m), n the range from left to right, m is the number of set bits in
    // each interger in the range[left, right]
    // space: O(1)
    public static HashSet<Integer> primeNum = new HashSet<Integer>();

    public int countPrimeSetBits(int left, int right) {
        primeNum.add(2);
        primeNum.add(3);
        int res = 0;
        for (int i = left; i <= right; i++) {
            int bitNum = Integer.bitCount(i);
            if (isPrime(bitNum))
                res++;
        }
        return res;
    }

    public static boolean isPrime(int num) {
        if (num == 1)
            return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0)
                return false;
        }
        primeNum.add(num);
        return true;
    }

    public static void main(String[] args) {
        byte val = 100; // 100 = 1100100
        val = (byte) (val >> 2);
        System.out.println(val);
        System.out.println(5 & 1);
    }
}
