// https://leetcode.com/problems/points-that-intersect-with-cars/description/

// Line Sweep Algorithms
// tutorial: https://www.youtube.com/watch?v=qkhUNzCGDt0&list=PLubYOWSl9mItBLmB2WiFU0A_WINUSLtGH&index=2
// tutorual: https://www.youtube.com/watch?v=phrSBwaBs7o
// reference: https://leetcode.com/discuss/study-guide/2166045/line-sweep-algorithms
// reference: https://leetcode.com/problems/points-that-intersect-with-cars/solutions/4024738/brute-force-line-sweep-solution-o-n/

/*
things i didn't do well in
1. edge case:
    there can be duplicate elements, and the difference of current start and end can cover the range of next few elements: [[4,4],[9,10],[9,10],[3,8]]
2. don't know the api to sort 2D lists and 2D arrays:
    Collections.sort(nums, Comparator,comparingInt(list -> list.get(0)));
    Arrays.sort(nums, (a,b)->a[0]-b[0]);
    Arrays.sort(nums, (a,b)->Integer.compare(a[0], b[0])); // https://stackoverflow.com/questions/15452429/java-arrays-sort-2d-array
*/

public class PointsThatIntersectWithCars {

    // solution 1: sweep line algo
    public int numberOfPoints(List<List<Integer>> nums) {
        int[] line = new int[102];
        int res = 0, sum = 0;
        for (List<Integer> pair : nums) {
            line[pair.get(0)]++;
            line[pair.get(1) + 1]--;
        }
        for (int i = 1; i <= line.length - 1; i++) {
            sum += line[i];
            if (sum != 0)
                res++;
        }
        return res;
    }

    // solution 2: hashset to record the every number that has appeared
    // time: O(n) n for the number of the coordinates
    // space: O(n) n for the number of the coordinates
    // hashset
    public int numberOfPoints(List<List<Integer>> nums) {
        Set<Integer> set = new HashSet();
        for (List<Integer> list : nums) {
            for (int i = list.get(0); i <= list.get(1); i++)
                set.add(i);
        }
        return set.size();
    }

    // solution 3
    // steps:
    // 1. if the current end is greater or equal to the next start, two elements
    // overlap
    // 1.1 if the current end is greater than the next end, the number of
    // coordinates in the next element is fully included in the current element, so
    // we can skip the next element and check the one after
    // 1.2 if the current end is smaller than the next end, the current element and
    // the next element are partially overlapping, we can extend the current end to
    // be the next end, and skip to check the one after
    // 2. get out of the loop when either the current element is the last element in
    // num or the current element and the next element we are accessing does not
    // intersect
    // 3. update counter
    // time: O(nlogn)
    // space: O(1)
    /*
     * test case
     * i
     * t
     * 0 1 2 3 4 5 6 7
     * 3,8 3,3 4,5 9,10 9,10 11,12 11,15 11,17
     * counter = (8 - 3) + 1 = 6 -> 6 + (2-1)+1 = 8 -> 8 + (17-11)+1 = 15
     */
    public int numberOfPoints(List<List<Integer>> nums) {
        Collections.sort(nums, Comparator.comparingInt(list -> list.get(0)));
        int counter = 0;
        for (int i = 0; i <= nums.size() - 1; i++) {
            int curStart = nums.get(i).get(0);
            int curEnd = nums.get(i).get(1);

            // while the current end is greater than the next start
            int tmpIndex = i + 1;
            while (tmpIndex <= nums.size() - 1 && curEnd >= nums.get(tmpIndex).get(0)) {
                // if the range of current element can cover the next element
                if (curEnd >= nums.get(tmpIndex).get(1)) {
                    tmpIndex++; // skip the next (start,end) pair
                } else { // the range of cuurent element is partiall overlapping with the next element,
                         // so we can extend the curEnd to be the next end
                    curEnd = nums.get(tmpIndex).get(1);
                    tmpIndex++; // skip the next (start,end) pair
                }
            }
            counter += curEnd - curStart + 1;
            i = --tmpIndex;
        }
        return counter;
    }

    // solution 4
    // steps: 1. sort list nums by the the value of start
    // 2. iterate through nums:
    // 2.1 if the current end is greater or equal to the next start
    // 2.1.1 if the end if greater or equal to the next end, the number of of
    // coordinates in the next element havs already been included in the current
    // element
    // replace the next end with the current end to avoid factoring the the number
    // of coordinates in the next element again
    // 2.2.2 else, do nothing
    // then update the next start with the sum of 1 + current end, no matter the
    // current end is greater/equal or less than the next end
    // 2.2 if the cuurent end is less than the next start, the current coordinates
    // does not intersect with coordinates in the next element
    // just update the value of the counter
    // time: O(nlogn)
    // space: O(1)
    /*
     * test case
     * 3,8 3,3 4,5 9,10 9,10
     * 3,8 9,8 9,8 9,10 11,10
     * counter = 8
     */
    public int numberOfPoints(List<List<Integer>> nums) {
        Collections.sort(nums, Comparator.comparingInt(list -> list.get(0)));
        int counter = 0;
        for (int i = 0; i <= nums.size() - 1; i++) {
            int curStart = nums.get(i).get(0);
            int curEnd = nums.get(i).get(1);
            if (i < nums.size() - 1 && curEnd >= nums.get(i + 1).get(0)) {
                if (curEnd >= nums.get(i + 1).get(1)) {
                    nums.get(i + 1).set(1, curEnd);
                }
                nums.get(i + 1).set(0, curEnd + 1);
                counter += curEnd - curStart + 1;
            } else { // the current element not overlapping next one || last element
                counter += curEnd - curStart + 1;
            }
        }
        return counter;
    }

}
