import java.util.Arrays;
import java.util.PriorityQueue;

public class MaximumBagsWithFullCapacityofRocks {
    // priority queue
    // time: O(n)
    // space: O(n)
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int maxBags = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < capacity.length; i++) {
            if (capacity[i] - rocks[i] == 0) {
                maxBags++;
                continue;

            }
            pq.add(capacity[i] - rocks[i]);
        }

        while (pq.size() != 0 && pq.peek() <= additionalRocks) {
            additionalRocks -= pq.remove();
            maxBags++;
        }

        return maxBags;
    }

    // brute force
    // time complexity : O(nlog)
    // space complexity : O(n)
    // public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
    // int[] diff = new int[capacity.length];
    // int maxBags = 0;
    // for (int i = 0; i < capacity.length && additionalRocks > 0; i++) {
    // diff[i] = capacity[i] - rocks[i];
    // }
    // Arrays.sort(diff);
    // for (int i = 0; i < diff.length; i++) {
    // if (diff[i] == 0) {
    // maxBags++;
    // continue;
    // }
    // if (additionalRocks >= diff[i]) {
    // additionalRocks = additionalRocks - diff[i];
    // maxBags++;
    // }
    // }
    // return maxBags;
    // }
}
