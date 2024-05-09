import java.util.Arrays;

public class MaximizeHappinessofSelectedChildren {
    /*
     * happiness = [1,1,1,4,5], k=2
     * 1. sort(happiness)
     * 2. diff = 0 to record the number of turns we have selected kids, res = 0,
     * lastIndex = happiness.length-1
     * 3. while(k > 0){
     * if(happiness[0]-diff > 0) res += happiness[lastIndex]
     * else res += happiness[0] break;
     * diff++;
     * lastIndex--;
     * }
     * Time: O(nlogn) -> n: length of happiness, nlogn: sorting
     * Space: O(nlogn) -> logn: depth of quicksort's recursive stack
     */
    public long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);
        long res = 0;
        int lastIndex = happiness.length - 1, diff = 0;
        while (k > 0) {
            if (happiness[0] - diff > 0)
                res += (happiness[lastIndex--] - diff);
            else {
                // a kid's happiness can be negative, but when we come across such a negative
                // value, we can choose 0 instead
                res += Math.max((happiness[lastIndex--] - diff), 0);
            }
            diff++;
            k--;
        }
        return res;
    }
}