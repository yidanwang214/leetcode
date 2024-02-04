public class Non-decreasingSubsequences
{
        /*
    [4,4,3,2,1]             
    |               |           |
    4|4321      4|321        3|21
    |               |           |
    44|321      4|3|21       3
    |               |
    44|3|21     4|32|1
    |               |
    44|32|1     4|321|
    |
    44|321|

    Time: (2^n-n)*n
    Space: (2^n-n)*n
     */
    HashSet<List<Integer>> res = new HashSet<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        dfs(0, new ArrayList<>(), nums);
        return new ArrayList<>(res);
    }

    private void dfs(int offset, List<Integer> subseq, int[] nums){
        // base case
        if(offset >= nums.length) return;

        // explore possibilities
        for(int i = offset; i < nums.length; i++){
            if(subseq.size() == 0 || subseq.get(subseq.size()-1) <= nums[i]){
                // store current state
                subseq.add(nums[i]);
                // add the result to res
                if(subseq.size() >= 2) res.add(new ArrayList<>(subseq));
                // dfs
                dfs(i+1, subseq, nums);
                //
                subseq.remove(subseq.size()-1);
            }
        }
    }
}
