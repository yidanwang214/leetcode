public class KthSmallestElementinaSortedMatrix {
    /*
     * binary search
     * algo:
     * 1. set the left pointer and right pointer to be the min val and max val in
     * the matrix, not index
     * 2. mid = l+(r-l)/2,
     * 3. find the number of elements <= mid is k, ans will the value of mid untill
     * l > r
     * reference: https://www.youtube.com/watch?v=SmxdebjWvfs
     * relebant questions:
     * https://leetcode.com/problems/k-th-smallest-prime-fraction/description/?
     * envType=daily-question&envId=2024-05-10
     * Time: O(log(max(maxtrix) - min(maxtrix)) * n*logm)
     * Space: O(1)
     */
    public int kthSmallest(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[0].length;
        int l = matrix[0][0], r = matrix[row - 1][col - 1];
        int ans = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int count = numofElementsLTEMid(mid, matrix, row, col);
            if (count >= k) {
                ans = mid;
                r = mid - 1;
            } else
                l = mid + 1;
        }
        return ans;
    }

    private int numofElementsLTEMid(int val, int[][] matrix, int row, int col) {
        int count = 0;
        for (int i = 0; i < row; i++) {
            int[] arr = matrix[i];
            int l = 0, r = col - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (arr[mid] > val) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            count += (r >= 0 ? (r + 1) : 0);
        }
        System.out.println("mid: " + val + ", count: " + count);
        return count;
    }
}
