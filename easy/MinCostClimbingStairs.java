public class MinCostClimbingStairs {

    public int minCostClimbingStairs(int[] cost) {

        // 1. Memoization approach
        // https://www.youtube.com/watch?v=sbZF9RnVOUU from 3:23
        int[] cost;
        Integer[] cache;
        public int minCostClimbingStairs(int[] cost) {
            this.cost = cost;
            this.cache = new Integer[cost.length];
            int minCostFromIndex0 = helper(0);
            int minCostFromIndex1 = helper(1);
            return Math.min(minCostFromIndex0, minCostFromIndex1);
        }
        private int helper(int start){
            if(start >= cost.length) return 0;
    
            if(cache[start] != null) return cache[start];
    
            int take1Step = helper(start + 1) + cost[start];
            int take2Step = helper(start + 2) + cost[start];
            cache[start] = Math.min(take1Step, take2Step);
            return cache[start];
        }

        // 2. Tabulation (bottom up approach)
        // reference: Neetcode: https://www.youtube.com/watch?v=ktmzAZWkEZ0
        // 2.1 Space: O(1)
        // Time: O(n)
        // Space: O(n) -> O(1)
        // from right to left
        int res = cost[cost.length - 1]; // min cost of next index to dest
        int costOf2ToDest = 0; // min cost of from 2 indices later to dest
        for (int i = cost.length - 2; i >= 0; i--) {
            int costOf1Jump = cost[i] + res; // cost of 1 jump to next index + min cost of next index to dest
            int costOf2Jump = cost[i] + costOf2ToDest; // cost of 2 jump to 2 indcies later + min cost of from 2 indices
                                                       // later to dest
            costOf2ToDest = res; // in the next round, the min cost from 2 indices later to dest will be the cost
                                 // of from next index to dest, which is res
            res = Math.min(costOf1Jump, costOf2Jump); // res stores the min cost from current index to dest
        }
        return Math.min(res, costOf2ToDest); // res stores the min cost staring from the index 0, costOf2ToDest stores
                                             // the min cost starting from index 1
    }

    public int minCostClimbingStairs(int[] cost) {
        // Neetcode 1.2 Space: O(1)
        // reference: Neetcode: https://www.youtube.com/watch?v=ktmzAZWkEZ0
        // Tabulation (bottom up approach)
        // Time: O(n)
        // Space: O(n) -> O(1)
        // from right to left
        int[] res = new int[cost.length + 1];
        res[res.length - 1] = 0;
        res[res.length - 2] = cost[cost.length - 1];
        for (int i = res.length - 3; i >= 0; i--) {
            res[i] = Math.min(cost[i] + res[i + 2], cost[i] + res[i + 1]);
        }
        return Math.min(res[0], res[1]);
    }
}
