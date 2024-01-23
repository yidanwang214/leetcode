public class Finding3DigitEvenNumbers {
    class Solution {
        /*
         * Three cycles
         * reference: https://leetcode.com/problems/finding-3-digit-even-numbers/
         */
        public int[] findEvenNumbers(int[] digits) {
            // freq of each digit
            int[] freq = new int[10];
            for (int d : digits)
                freq[d]++;

            // al saves valid num
            ArrayList<Integer> al = new ArrayList<>();

            // generate combinations using 3 loops
            /*
             * 1. i represents the most significant digit
             * 2. since there can't be leading 0, i should start from 1
             */
            for (int i = 1; i < 10; i++) {
                /*
                 * 1. j represents the second MSD.
                 * 2. freq[i] ensures there is at least 1 occurance of i in digits to make the
                 * generated num valid
                 */
                for (int j = 0; freq[i] > 0 && j < 10; j++) {
                    /*
                     * 1. k represents the least significant digit.
                     * 2. Since the generated num should be even, we increment it by 2.
                     * 3. freq[j] > (i == j ? 1 : 0) simply means, if i==j, there should be at least
                     * 2 occurence of j in digits, otherwise, the freq of j should be at least 1 j
                     */
                    for (int k = 0; (freq[j] > (i == j ? 1 : 0)) && k < 10; k += 2) {
                        /*
                         * if i == k, there has to be at least 2 occurance of k, and if j == k, there
                         * has to be at lease 2 occurance of k as well to get the valid combination
                         */
                        if (freq[k] > ((i == k ? 1 : 0) + (j == k ? 1 : 0))) {
                            al.add(100 * i + 10 * j + k);
                        }
                    }
                }
            }

            int[] arr = new int[al.size()];
            for (int i = 0; i < al.size(); i++) {
                arr[i] = al.get(i);
            }
            return arr;
        }

        /*
         * reference:
         * https://leetcode.com/problems/finding-3-digit-even-numbers/solutions/1612135/
         * c-with-explanation-o-n-solution-easy-to-understand/
         * 2 freqmaps, 1 for the freq of each digits in digits[], another for freq of
         * each digits in each even number between 100-999
         */
        // public int[] findEvenNumbers(int[] digits) {
        // // frequency map of each digit in digits
        // int[] digitFreq = new int[10];
        // for(int d : digits) digitFreq[d]++;

        // // inilialise result
        // ArrayList<Integer> res = new ArrayList<>();

        // // check every even num from [100 to 998]
        // for(int i = 100; i <= 998; i = i+2){
        // int[] tmpFreq = new int[10];
        // int num = i;
        // boolean flag = false;
        // for(int j = num; j != 0; j /= 10)tmpFreq[j%10]++;
        // for(int k = 0; k < 10; k++){
        // if(digitFreq[k] < tmpFreq[k]){
        // flag = true;
        // break;
        // }
        // }
        // if(!flag) res.add(i);
        // }
        // int[] arr = new int[res.size()];
        // for(int i = 0; i < res.size(); i++){
        // arr[i] = res.get(i);
        // }
        // return arr;
        // }
    }
}
