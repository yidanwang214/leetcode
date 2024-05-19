public class LargestLocalValuesinaMatrix {
    /*
     * 1 2 3 9
     * 4 5 6
     * 7 8 9
     * 
     * 7 2 5 9 i=0,j=0,b=[0,2],r=[0,2],max=8 8 9
     * 1 5 7 0 i=0,j=1,b=[0,2],r=[1,3],max=9 8 9
     * 8 4 3 9 i=1,j=0,i=[1,3],r=[0,2],max=8
     * 1 5 4 7 i=1,j=1,i=[1,3],j=[1,3],max=9
     * 
     * algo:
     * 1. create maxLocal with width and height of grid.length-2
     * 2. scan through grid from left to right, top to bottom, for every 3*3 matrix,
     * find the max one and assign to maxLocal[i-2][j-2]
     * 3. return maxLocal
     * Time: O(9*(n-2)^2)
     * Space: O((n-2)^2)
     */
    public int[][] largestLocal(int[][] grid) {
        int[][] maxLocal = new int[grid.length - 2][grid.length - 2];
        for (int i = 0; i < grid.length - 2; i++) {
            for (int j = 0; j < grid.length - 2; j++) {
                int max = 1;
                for (int b = i; b < i + 3; b++) {
                    for (int r = j; r < j + 3; r++) {
                        max = Math.max(max, grid[b][r]);
                    }
                }
                maxLocal[i][j] = max;
            }
        }
        return maxLocal;
    }
}
