import java.util.*;

/*
input:
5
1 2
2 1
10 1
179 100
 */

public class BinaryCafe {
    public void dfs(int stop, int n, double sum, int offset) {
        // base case
        if (sum > n)
            return;

        // backtracking
        for (int i = offset; i <= stop; i++) {
            if (sum + Math.pow(2, i) > n)
                return;
            this.res++;
            dfs(stop, n, sum + Math.pow(2, i), i + 1);
        }

    }

    // private double res = 0;
    private int res = 0;

    public static void main(String[] args) {
        // int n; // = 179;
        // int k; // = 100;
        Scanner reader = new Scanner(System.in);
        while (reader.hasNextLine()) {
            int n = reader.nextInt();
            int k = reader.nextInt();
            // System.out.println("n: " + n + " k: " + k);
            int pow = 0;
            while (Math.pow(2, pow) <= n) {
                pow++;
            }
            // double[] nums;
            int stop;
            if (k < n) {
                // nums = new double[k];
                // for (int i = 0; i < k; i++) {
                // nums[i] = Math.pow(2, i);
                // }
                stop = k - 1;
            } else {
                // nums = new double[pow];
                // for (int i = 0; i <= pow - 1; i++) {
                // nums[i] = Math.pow(2, i);
                // }
                stop = pow - 1;
            }

            BinaryCafe bc = new BinaryCafe();
            // bc.dfs(nums, n, 0, 0);
            bc.dfs(stop, n, 0, 0);
            int res = (int) bc.res + 1;
            System.out.println("res: " + res);
        }
    }
}
