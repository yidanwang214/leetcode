public class Permutation {
    /*
     * Approach 1:
     * reference: https://www.youtube.com/watch?v=UvSPsz0jTQ4
     * 1. we insert every number of the nums into decision tree, permutation is an
     * empty ArrayList<>() that collects nums for every digit from root to node
     * 2. in dfs()
     * 1. base cond: when permutation's size == nums.length or dt's size == 0
     * 2. using loop to explore every possibility,
     * 2.1 made tmp copy of permutation and decision tree, tmpP and tmpDT (save
     * current state, and change state)
     * 2.2 add num[i] to tmpP and delete the nums[i] from tmpDT
     * 2.3 dfs(tmpP, tmpDT)
     * when the function returns, the tmpP and tmpDT are independent of Permutation
     * and dt, so we don't need to revert to the previous state
     */
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> decisionTree = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        for (int num : nums) {
            decisionTree.add(num);
        }
        dfs(new ArrayList<>(), decisionTree);
        return this.res;
    }

    public void dfs(List<Integer> permutation, List<Integer> dt) {
        // base case
        if (dt.size() == 0) {
            this.res.add(permutation);
            return;
        }
        // make a temporary copy of permutation and dt, and add nums[i] to tmpP and
        // delete nums[i] from tmpDT
        for (int i = 0; i < dt.size(); i++) {
            List<Integer> tmpP = new ArrayList<>(permutation);
            List<Integer> tmpDT = new ArrayList<>(dt);
            tmpP.add(dt.get(i));
            tmpDT.remove(dt.get(i));
            dfs(tmpP, tmpDT);
        }
    }

    /*
     * Approach 2:
     * reference:
     * https://leetcode.com/problems/permutations/solutions/18239/a-general-approach
     * -to-backtracking-questions-in-java-subsets-permutations-combination-sum-
     * palindrome-partioning
     */
    List<List<Integer>> res = new ArrayList<>();
    int[] nums;
    int j = 0;

    public List<List<Integer>> permute(int[] nums) {
        this.nums = nums;
        dfs(new ArrayList<>());
        return res;
    }

    private void dfs(ArrayList<Integer> permutation) {
        j++;
        // base condition
        if (permutation.size() == nums.length) {
            res.add(new ArrayList<>(permutation));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (permutation.contains(nums[i]))
                continue;
            permutation.add(nums[i]);
            dfs(permutation);
            System.out.println("i: " + i + ", nums[i]: " + nums[i] + " " + nums.length);
            System.out.println("p length: " + permutation.size() + ", j: " + j);
            /*
             * The remove method has two overloaded versions. One takes an index (remove(int
             * index)) and the other takes an object (remove(Object o)). Using
             * Integer.valueOf() can convert the primitive int to an Integer object, so that
             * the correct overload of remove is called
             */
            permutation.remove(Integer.valueOf(nums[i]));
        }
        /*
         * res: [1,2,3],[1,3,2],[2,1,3],[2,3,1]
         * dfs([1,2,3])
         * |
         * dfs([1]) -dfs([2,]) -dfs([3])
         * | | |
         * dfs([1,2]) - dfs([1,3]) dfs([2,1]) - dfs([2,3]) dfs([3,1]) - dfs([3,2])
         * | | | | | |
         * dfs([1,2,3]) dfs([1,3,2]) dfs([2,1,3]) dfs([2,3,1]) dfs([3,1,2]) dfs([3,2,1])
         */
    }
}
