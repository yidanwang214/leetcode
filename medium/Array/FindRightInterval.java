// https://leetcode.com/problems/find-right-interval/

import java.util.TreeMap;

public class FindRightInterval {
    /*
     * optimal solution: TreeMap
     * Time: O(nlogn) -> search time logn for n intervals
     * Space: O(n) -> n is the number of intervals
     */
    public int[] findRightInterval(int[][] intervals) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < intervals.length; i++) {
            map.put(intervals[i][0], i);
        }
        int[] res = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            if (map.ceilingKey(intervals[i][1]) != null) {
                res[i] = map.ceilingEntry(intervals[i][1]).getValue();
            } else
                res[i] = -1;
        }
        return res;
    }

    /*
     * algo:
     * 1. Create a hashmap, each key stores the start value of an interval, the
     * value store the index of the interval in intervals array.
     * Create a variable maxStart that stores the max start in the interval array.
     * Create a res array of size intervals.length.
     * 2. First pass:
     * Iterate through intervals, save every (start, index) to hashmap, update
     * maxStart if necessary
     * 3. Second pass:
     * Iterate through intervals, lookup hashmap to see if every interval's end
     * exist,
     * if exist:
     * assign the end's corresponding value in map to res array;
     * if not exist:
     * look up the map from end+1 to the max,
     * see if there is the number that is greater than end exist in the map,
     * if exists, get the index of the number and assign it to res,
     * else, return -1
     * time: in worst case, O(m*n), where n is the number of intervals and m is the
     * difference between max startj and the smallest endi
     * space: O(n) for res array and map
     */
    public int[] findRightInterval(int[][] intervals) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxStart = intervals[0][0];
        int[] res = new int[intervals.length];
        // first pass
        for (int i = 0; i < intervals.length; i++) {
            map.put(intervals[i][0], i);
            maxStart = Math.max(maxStart, intervals[i][0]);
        }
        // second pass
        boolean visited = false;
        for (int i = 0; i < intervals.length; i++) {
            if (map.containsKey(intervals[i][1])) {
                res[i] = map.get(intervals[i][1]);
            } else {
                int end = intervals[i][1];
                for (int j = end + 1; j <= maxStart; j++) {
                    if (map.containsKey(j)) {
                        res[i] = map.get(j);
                        visited = true;
                        break;
                    }
                }
                if (visited == false)
                    res[i] = -1;
                else
                    visited = false;
            }
        }
        return res;
    }
}
