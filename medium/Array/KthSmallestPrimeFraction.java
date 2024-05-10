import java.util.TreeMap;
/*
 * brute force
algo:
1. get combinations of i and j, and save the fraction to a TreeMap
2. pop TreeMap k times, get the i and j, and return them as the result
time: O(n^2)
space: O(n(n-1)/2)
 */

public class KthSmallestPrimeFraction {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        // can't use float because float can represent decimal values up to 7 digits of
        // precision
        // but double can represent decimal values up to 16 digits of precision
        TreeMap<Double, int[]> tm = new TreeMap<>();
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int[] tmp = { arr[i], arr[j] };
                tm.put((double) arr[i] / (float) arr[j], tmp);
            }
        }
        int[] res = new int[2];
        while (k > 0) {
            if (k == 1) {
                res = tm.pollFirstEntry().getValue();
            }
            tm.pollFirstEntry();
            k--;
        }
        return res;
    }
}
