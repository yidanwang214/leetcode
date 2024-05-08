public class RelativeRanks {
    /*
     * algo:
     * 1. create String answer array of length of score's length
     * 2. use treemap to save the <score, index> pair from each element in score
     * array
     * ...scan through the score array, fill treemap with the <score, index>, so
     * that every pair will be saved in ascedning order based on the score
     * 3. pop each pair in the treemap, as the pair are pop in ascending order, we
     * will add them to the ans array using the value(index) to locate the slot we
     * are putting the placement in
     * Time: O(n)
     * Space: O(n)
     * 0 1 2 3 4
     * score: [8, 9, 6, 7, 5] -> sort with their original index
     * [[9,1],[8,0],[7,3],[6,2],[5,4]]
     * ans: [silver, gold, 4th, bronze, 5th]
     */

    public String[] findRelativeRanks(int[] score) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int i = 0; i < score.length; i++)
            tm.put(score[i], i);
        int placement = tm.size();
        String[] ans = new String[score.length];
        while (placement > 0) {
            Map.Entry<Integer, Integer> p = tm.pollFirstEntry();
            if (placement == 1)
                ans[p.getValue()] = "Gold Medal";
            else if (placement == 2)
                ans[p.getValue()] = "Silver Medal";
            else if (placement == 3)
                ans[p.getValue()] = "Bronze Medal";
            else
                ans[p.getValue()] = String.valueOf(placement);
            placement--;
        }
        return ans;

    }
}
