// https://leetcode.com/problems/minimum-time-difference/description/
public class MinimumTimeDifference {

    /*
     * optimal solution: bucket
     * pseudocode:
     * 1. create a bucket of size 24*60, which is the number of minutes
     * 2. interate through the timePoints and insert time points to the slot with
     * the index same as each time point
     * 3. iterate the bucket to get the min difference
     * time: O(n)
     * space: O(n)
     */
    public int findMinDifference(List<String> timePoints) {
        boolean[] bucket = new boolean[24 * 60];
        int minTime = Integer.MAX_VALUE, maxTime = Integer.MIN_VALUE;
        // iterate
        for (String time : timePoints) {
            int minutes = convertToMinites(time);
            if (bucket[minutes])
                return 0;
            bucket[minutes] = true;
            minTime = Math.min(minTime, minutes);
            maxTime = Math.max(maxTime, minutes);
        }
        int preTimeIndex = 0, minDiff = Integer.MAX_VALUE;
        for (int i = minTime; i <= maxTime; i++) {
            if (bucket[i]) {
                if (i == minTime) {
                    minDiff = Math.min(minDiff, Math.min(maxTime - minTime, (24 * 60 - (maxTime - minTime))));
                } else {
                    minDiff = Math.min(minDiff, i - preTimeIndex);
                }
                preTimeIndex = i;
            }
        }
        return minDiff;
    }

    /*
     * Solution2
     * pesudocode:
     * 1. create pq
     * 2. sort timePoints by hour and then minutes
     * 3. iterate through the sorted timePoints arr, get the minutes difference
     * between every other time points, also, get the difference between the first
     * element and the last element as well because they might result in the min
     * minutes such as "00:00" and "23:59"
     * Time: O(nlogn)
     * Space: O(n)
     */
    public int findMinDifference(List<String> timePoints) {
        // create priority queue and array that saves the converted minutes of clock
        // time points
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        Comparator<String> customComparator = new Comparator<String>() {
            @Override
            public int compare(String time1, String time2) {
                int hour1 = Integer.parseInt(time1.substring(0, 2));
                int minute1 = Integer.parseInt(time1.substring(3));
                int hour2 = Integer.parseInt(time2.substring(0, 2));
                int minute2 = Integer.parseInt(time2.substring(3));
                int hourComparison = Integer.compare(hour1, hour2);
                if (hourComparison != 0)
                    return hourComparison;
                return Integer.compare(minute1, minute2);
            }
        };
        Collections.sort(timePoints);

        // ieterate through the timePoints
        int minMinutes = 720;
        for (int i = 0; i < timePoints.size(); i++) {
            int j = i + 1;
            if (i == timePoints.size() - 1)
                j = 0;
            int curMinutes = convertToMinites(timePoints.get(i));
            int nextMinutes = convertToMinites(timePoints.get(j));
            int minutesDiff = Math.abs(curMinutes - nextMinutes);
            if (minutesDiff > 720)
                minutesDiff = 1440 - minutesDiff;
            minMinutes = Math.min(minMinutes, minutesDiff);
            pq.add(minMinutes);
        }
        return pq.peek();
    }

    public int convertToMinites(String timePoint) {
        int hour = Integer.parseInt(timePoint.substring(0, 2));
        int minute = Integer.parseInt(timePoint.substring(3));
        return hour * 60 + minute;
    }

    /*
     * solution 3: brute force
     * couldn't pass the second last test case due to time limit exceeded
     * psudo code:
     * 1. create a priority queue
     * 2. iterate through the timePoints to get the min mins difference betweeb
     * any 2 points, add the the min difference to priority queue
     * 3. return the root value of the priority queue
     * time: O(n^2)
     * space: O(n)
     * create priority queue and array that saves the converted minutes of clock
     * time points
     */
    public int findMinDifference(List<String> timePoints) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        int[] minutes = new int[timePoints.size()];

        // ieterate through the timePoints
        for (int i = 0; i < timePoints.size(); i++) {
            int minMinutes = 720;
            /*
             * the max minutes difference between two time points
             * is 720 mins, indicating the two time points are diagonally situated on/angled
             * across the clock
             */
            int curMinutes = convertToMinites(timePoints.get(i));
            for (int j = i + 1; j < timePoints.size(); j++) {
                int nextMinutes = convertToMinites(timePoints.get(j));
                int minutesDiff = Math.abs(curMinutes - nextMinutes);
                if (minutesDiff > 720)
                    minutesDiff = 1440 - minutesDiff;
                minMinutes = Math.min(minMinutes, minutesDiff);
            }
            pq.add(minMinutes);
        }
        return pq.peek();
    }

    public static void main(String[] args) {

    }
}
