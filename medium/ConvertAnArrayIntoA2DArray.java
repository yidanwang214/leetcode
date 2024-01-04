//https://leetcode.com/problems/convert-an-array-into-a-2d-array-with-conditions/description/
// an easy medium, but i didn't come up with the optimal solution
public class ConvertAnArrayIntoA2DArray {

    // better approach:
    // https://leetcode.com/problems/convert-an-array-into-a-2d-array-with-conditions/editorial/?envType=daily-question&envId=2024-01-02
    // time: O(n)
    // space: O(n)
    public List<List<Integer>> findMatrix(int[] nums) {
        int[] freq = new int[nums.length + 1];
        List<List<Integer>> res = new ArrayList<>();

        for (int c : nums) {
            if (freq[c] >= res.size())
                res.add(new ArrayList<>());
            res.get(freq[c]).add(c);
            freq[c]++;
        }
        return res;
    }

    // my approach
    // Time: O(nm), n: the count of number in nums array, m: ave frequency of each
    // number
    // Space: O(n)
    // psudocode:
    // 1. hashmap: key:number, value: frequency
    // 2. interate through the number array, insert nnumber, frequnecy into hashmap,
    // counte the amx frequency, which is gonna be the number of rows in the 2d
    // array we will return
    // 3. ietrate the hashmap, for each interger, append it to the rows from 0 to
    // frenquency -1
    public List<List<Integer>> findMatrix(int[] nums) {
        // create hashmap
        HashMap<Integer, Integer> numFreq = new HashMap<Integer, Integer>();
        int maxFreq = 0;

        // scan nums
        for (int i = 0; i < nums.length; i++) {
            if (numFreq.containsKey(nums[i])) {
                numFreq.replace(nums[i], numFreq.get(nums[i]) + 1);
            } else {
                numFreq.put(nums[i], 1);
            }
            maxFreq = Math.max(numFreq.get(nums[i]), maxFreq);
        }

        // create AL with the length of maxFreq
        List<List<Integer>> res = new ArrayList<>(maxFreq);
        int j = 0;
        while (j < maxFreq) {
            res.add(new ArrayList<Integer>());
            j++;
        }

        // iterate hashmap
        for (Map.Entry<Integer, Integer> entry : numFreq.entrySet()) {
            int number = entry.getKey();
            int freq = entry.getValue();
            for (int i = 0; i < freq; i++)
                res.get(i).add(number);
        }
        // return
        return res;
    }
}
