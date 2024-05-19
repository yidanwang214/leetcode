public class TakingMaximumEnergyFromtheMysticDungeon {
    public int maximumEnergyBF(int[] energy, int k) {
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < energy.length; i++) {
            int sum = energy[i];
            for (int j = i + k; j < energy.length; j += k) {
                sum += energy[j];
            }
            res = Math.max(res, sum);
        }
        return res;
    }

    /*
     * [5,-10,4,3,5,-9,9,-7]
     * Time: O(n + k^2)
     * Space: O(k)
     */
    public int maximumEnergyl(int[] energy, int k) {
        int[] arr = new int[k];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            arr[i] = energy[i];
            for (int j = i + k; j < energy.length; j += k) {
                arr[i] += energy[j];
            }
            max = Math.max(max, arr[i]);
            System.out.println(arr[i]);
        }
        for (int i = k; i < energy.length; i++) {
            max = Math.max(max, arr[i % k] -= energy[i - k]);
        }
        return max;
    }

    /*
     * dp
     * reference:
     * https://leetcode.com/problems/taking-maximum-energy-from-the-mystic-dungeon/
     * solutions/5145897/fastest-100-easy-to-understand-iterative-dp-short-concise
     * Time: O(n)
     * Space: O(n)
     * energy = [5, 2, -10, -5, 1] and k = 3
     * forward_dp = [5, 2, -10, 0, 3] wrong dp[i] = arr[i]+dp[i-k]
     * backward_dp = [0, 3, -10, -5, 1] correct
     */
    public int maximumEnergy(int[] energy, int k) {
        int[] dp = new int[energy.length];
        int res = Integer.MIN_VALUE;
        for (int i = energy.length - 1; i >= 0; i--) {
            dp[i] = (i + k >= energy.length ? 0 : dp[i + k]) + energy[i];
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
