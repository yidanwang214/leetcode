public class GetEqualSubstringsWithinBudget {
        /*
    my solution:
    brute force: O(n^2) time, because we need to generate n^2 combinations
    of consecutive difference bettwen s and t to get the longest substring

    better  solution
    algo:
    1. maintain curStart = 0, sum = 0, maxLen = 0
    2. create arr to store the absolute different between each char in s and t
    3. scan through the arr:
    -> if sum + arr[i] <= maxCost:
        increment sum by arr[i]
        if i is the last index in the array, return maxLen = max(maxLen, i-curStart+1)
    -> if sum + arr[i] > maxCost:
        update maxLen = max(maxLen, i-currStart)
        -> if arr[i] <= maxCost:
            decrement sum by arr[currStart++] if sum+arr[i] > maxCost and curStart < i,
            increment sum by arr[i]
        -> if arr[i] > maxCost: set curStart's value to be the next index, set sum to 0

        Time complexity: O(n)
        Space complexity: O(n)
     */
    public int equalSubstringMy(String s, String t, int maxCost) {
        // 1. create an arr and fill the arr with the different between each letter in s and t
        int[] arr = new int[s.length()];
        for(int i = 0; i < s.length(); i++) arr[i] = Math.abs(s.charAt(i) - t.charAt(i));

        for(int i = 0; i < s.length(); i++) System.out.println(arr[i]);

        // 2. maintain variables
        int curStart = 0, sum = 0, maxLen = 0;

        // 3. scan through arr
        for(int i = 0; i < arr.length; i++){
            if(sum + arr[i] <= maxCost){
                sum += arr[i];
                if(i == arr.length - 1) return maxLen = Math.max(maxLen, i-curStart+1);
            } else {
                maxLen = Math.max(maxLen, i-curStart);
                if(arr[i] <= maxCost){
                    while(sum + arr[i] > maxCost && curStart < i) sum -= arr[curStart++];
                    sum += arr[i];
                } else {
                    curStart = i+1;
                    sum = 0;
                }
            }
        }
        return maxLen;
    }

    /*
    NeetCode: O(n) time & O(1) space solution
    reference: https://www.youtube.com/watch?v=3lsT1Le526U

    algo: sliding window
    1. maintain a maxLen, left pointer l, curCost
    2. iterate through both strings at the same time:
    -> curCost += abs(s.charAt(i) - t.charAt(i))
    -> while(curCost > maxCost){
        curCost -= s.charAt(l);
        l++;
    }
    -> maxLen = max(maxLen, i-l+1)
    3. return maxLen
     */
    public int equalSubstring(String s, String t, int maxCost) {
        int maxLen = 0, l = 0, curCost = 0;
        for(int i = 0; i < s.length(); i++){
            curCost += Math.abs(s.charAt(i) - t.charAt(i));
            while(curCost > maxCost) {curCost -= Math.abs(s.charAt(l) - t.charAt(l)); l++;}
            maxLen = Math.max(maxLen, i-l+1);
        }
        return maxLen;
    }
}
