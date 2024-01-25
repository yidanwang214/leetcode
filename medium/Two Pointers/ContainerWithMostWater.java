public class ContainerWithMostWater {
    /*
     * height: (i,0) and (i, height[i])
     * n = height.length()
     * 2 <= n <= 10^5
     * 0 <= height[i] <= 10^4
     * 
     * height: [5, 6, 3, 8, 1, 10]
     * 
     * brute force:
     * 1. main a variable max, which saves the max area
     * 2. outer loop height from right to left, i++
     * inner loop scan from left to right, j--
     * max = Math.max((j-i)*Math.min(height[i], height[j]), max);
     * 3. return max
     * 
     * Time: O(n^2)
     * Space: O(1)
     * 
     * optimal: Time: O(n)
     * reference:
     * https://leetcode.com/problems/container-with-most-water/solutions/1915172/
     * java-c-easiest-explanations/
     * 
     * question 1: why do we move two pointers if they are of same height
     * answer:
     * https://leetcode.com/problems/container-with-most-water/solutions/1915172/
     * java-c-easiest-explanations/comments/1859665
     * https://leetcode.com/problems/container-with-most-water/solutions/1915172/
     * java-c-easiest-explanations/comments/1387965
     * 
     * question 2: why do we don't move the index with a longer height
     * answer:
     * https://leetcode.com/problems/container-with-most-water/solutions/1915172/
     * java-c-easiest-explanations/comments/1935610
     */
    class Solution {
        public int maxArea(int[] height) {
            int l = 0, r = height.length - 1, res = 0;
            while (l < r) {
                res = Math.max(Math.min(height[l], height[r]) * (r - l), res);
                if (height[l] < height[r])
                    l++;
                else if (height[l] > height[r])
                    r--;
                else {
                    l++;
                    r--;
                }
            }
            return res;
        }
    }
}
