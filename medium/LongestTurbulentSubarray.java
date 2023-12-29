// https://leetcode.com/problems/longest-turbulent-subarray/description/

public class LongestTurbulentSubarray {
    // Sliding window:
    // we know when the next block ends:
    // either when it is the last two elements
    // or the sequence isn't alternating
    // time complexity: O(n)
    // space complexity: O(1)
    public int maxTurbulenceSize(int[] arr) {
        int ans = 1;
        int anchor = 0;
        for (int i = 1; i < arr.length; i++) {
            int c = Integer.compare(arr[i - 1], arr[i]);
            if (c == 0) {
                anchor = i;
            } else if (i == (arr.length - 1) || c * Integer.compare(arr[i], arr[i + 1]) != -1) {
                ans = Math.max(ans, i - anchor + 1);
                anchor = i;
            }
        }
        return ans;
    }

    // time complexity: O(n)
    // space complexity: O(1)
    public int maxTurbulenceSize(int[] arr) {
        int maxSize = 1;
        int tempMax = 1;
        char lastSign = '=';
        char currSign = '=';
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1)
                return Math.max(maxSize, tempMax);

            currSign = arr[i] > arr[i + 1] ? '>' : (arr[i] < arr[i + 1] ? '<' : '=');

            if (currSign == '=') {
                lastSign = currSign;
                maxSize = Math.max(maxSize, tempMax);
                tempMax = 1;
                continue;
            }

            if (((lastSign != currSign) && (currSign != '='))) {
                lastSign = currSign;
                tempMax++;
                continue;
            }
            if (lastSign == currSign) {
                lastSign = currSign;
                maxSize = Math.max(maxSize, tempMax);
                tempMax = 2;
                continue;
            }
        }
        return maxSize;
    }
}
