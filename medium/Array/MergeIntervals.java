import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/merge-intervals/

public class MergeIntervals {
    /*
     * Time: O(nlogn)
     * Space: O(nlogn) + n for sorting and return value
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> list = new ArrayList<int[]>();
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int i = 0; i < intervals.length; i++) {
            start = intervals[i][0];
            end = intervals[i][1];
            while (i + 1 < intervals.length && end >= intervals[i + 1][0]) {
                if (end < intervals[i + 1][1])
                    end = intervals[i + 1][1];
                i++;
            }
            int[] arr = { start, end };
            list.add(arr);
        }
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
