// https://leetcode.com/problems/maximum-units-on-a-truck/description/

import java.util.PriorityQueue;

public class MaximumUnitsOnaTruck {
    // counting sort
    // Time: O(n) -> iterate over the array boxTypes, n is the length of boxTypes
    // Space: O(1) -> for creating freq
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        int[] freq = new int[1001];
        for (int i = 0; i < boxTypes.length; i++) {
            freq[boxTypes[i][1]] += boxTypes[i][0];
        }
        int res = 0;
        for (int i = 1000; i > 0 && truckSize > 0; i--) {
            res += i * Math.min(truckSize, freq[i]);
            truckSize -= Math.min(truckSize, freq[i]);
            // if(freq[i] <= truckSize){
            // truckSize -= freq[i];
            // res += freq[i]*i;
            // } else {
            // res += truckSize*i;
            // truckSize = 0;
            // }
        }
        return res;
    }

    // time: nlogn for inserting n boxes into the pq, each node logn time
    // space: O(n) for creating the pq
    public int maximumUnits2(int[][] boxTypes, int truckSize) {
        int res = 0;

        // defind a priority queue and make box of more units higer priority
        PriorityQueue<int[]> pq = new PriorityQueue<>((box1, box2) -> box2[1] - box1[1]);
        // fill priority queue
        for (int i = 0; i < boxTypes.length; i++)
            pq.add(boxTypes[i]);

        // calculate the max number of boxes
        while (!pq.isEmpty() && truckSize > 0) {
            int[] box = pq.poll();
            int numBox = box[0];
            int numUnits = box[1];
            if (truckSize - numBox >= 0) {
                res += (numBox * numUnits);
                truckSize -= numBox;
            } else {
                res += (truckSize * numUnits);
                truckSize = 0;
            }
        }
        return res;
    }
}
