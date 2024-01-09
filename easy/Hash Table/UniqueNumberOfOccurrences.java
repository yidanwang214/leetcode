// https://leetcode.com/problems/unique-number-of-occurrences/description/

public class UniqueNumberOfOccurrences {
    // 1 single hashmap
    // pseudo:
    // 1. counter the occurrence of each num
    // 2. compare if the numbers of distinct num and distinct counts are equal
    public boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int a : arr) {
            freq.put(a, 1 + freq.getOrDefault(a, 0));
        }
        return freq.size() == new HashSet<Integer>(freq.values()).size();
    }

    /*
     * 1 hashmap + 1 hashset
     * pesudo:
     * 1. Iterate through the array and stores the frequency of each number to
     * hashmap
     * 2. Iterate through the HashMap, save a frequency in hashset if it is unseen,
     * return false if a freqency has be in the hashmap.
     * Time: O(n)
     * Space: O(n)
     */
    public boolean uniqueOccurrences(int[] arr) {
        // create 2 hashmaps
        HashMap<Integer, Integer> numFreq = new HashMap<>();

        // iterate through arr
        for (int i = 0; i < arr.length; i++) {
            // If the specified value is not associated with a value or is associated with
            // null, associate it with the given non-null value (in this case 1). Otherwise,
            // replace the associated value with the results of the givin remapping function
            // or removes if the result is null.
            // https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html#merge-K-V-java.util.function.BiFunction-
            numFreq.merge(arr[i], 1, Integer::sum);
        }

        HashSet<Integer> freq = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : numFreq.entrySet()) {
            if (!freq.contains(entry.getValue())) {
                freq.add(entry.getValue());
            } else {
                return false;
            }
        }
        return true;
    }
}
