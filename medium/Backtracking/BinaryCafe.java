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
    public void dfs(double[] nums, int n, double sum, int offset) {
        // base case
        if (sum > n)
            return;

        // backtracking
        for (int i = offset; i < nums.length; i++) {
            if (sum + nums[i] > n)
                return;
            this.res++;
            dfs(nums, n, sum + nums[i], i + 1);
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
            double[] nums;
            if (k < n) {
                nums = new double[k];
                for (int i = 0; i < k; i++) {
                    nums[i] = Math.pow(2, i);
                }
            } else {
                nums = new double[pow];
                for (int i = 0; i <= pow - 1; i++) {
                    nums[i] = Math.pow(2, i);
                }
            }

            BinaryCafe bc = new BinaryCafe();
            bc.dfs(nums, n, 0, 0);
            int res = (int) bc.res + 1;
            System.out.println("res: " + res);
        }
    }
}
