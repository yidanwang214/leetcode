// https://leetcode.com/problems/flip-string-to-monotone-increasing/description/

public class FlipStringToMonotoneIncreasing {
    // optimal solution
    // One scan: dy approach
    // https://leetcode.com/problems/flip-string-to-monotone-increasing/solutions/189751/c-one-pass-dp-solution-0ms-o-n-o-1-one-line-with-explaination/
    // pesudecode:
    // 1. flipCount stores the number of min flips needed from left to right;
    // oneCount store the number of 1s from right to left;
    // 2. think of the every letter we encounter is newly appended to the end of the
    // string
    // 2.1 When '1' comes, no more flip should be applied since '1' is appended to
    // the tail of the string
    // 2.2 When '0' comes, there are 2 options for us:
    // option1: after flipCount flips makes the string monotone, flip 0 to 1, so the
    // total num of options is (flipCount + 1)
    // option2: flip oneCount '1' in the original string to make the string monotone
    // the two options is where min(flipCount + 1, oneCount) comes in.
    // Time: O(n)
    // Space: O(1)
    public int minFlipsMonoIncr(String s) {
        int flipCount = 0;
        int oneCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                flipCount = Math.min(flipCount + 1, oneCount);
            } else {
                oneCount++;
            }
        }
        return flipCount;
    }

    // my solution
    // two pass, find the split where frequency of 1 before it and frequency of 0
    // after 0 is minimal
    // pseudo:
    // 1. scan through the string from left to right and record the frequency of 1
    // in an array, indicating the number of 1 before 0
    // 2. scan through the string from right to left and record the frequency of 0
    // in an array, indicate the number of 0 after 1
    // 3. create an array with a length of string.length+1, get the sum of
    // freqOne[i-1]+freqZero(i), the min sum will be the min flip
    // Time: O(n)
    // Space: O(n)
    public int minFlipsMonoIncr(String s) {
        int[] freqOne = new int[s.length()];
        int[] freqZero = new int[s.length()];
        int counter = 0;

        // get the frequency of 1 before 0
        freqOne[0] = (s.charAt(0) == '1' ? 1 : 0);
        for (int i = 1; i < s.length(); i++) {
            freqOne[i] = (s.charAt(i) == '1' ? freqOne[i - 1] + 1 : freqOne[i - 1]);
        }

        // get the frequency of 0 after 1
        freqZero[freqZero.length - 1] = s.charAt(s.length() - 1) == '0' ? 1 : 0;
        for (int i = s.length() - 2; i >= 0; i--) {
            freqZero[i] = s.charAt(i) == '0' ? freqZero[i + 1] + 1 : freqZero[i + 1];
        }

        // scan two arrays simultaneously and get the sum of the value before split and
        // the value after split
        // handle edge cases where sum=0+freqZero[0] and sum=freqOne[lastIndex]+0
        int minFlip = Math.min(freqOne[freqOne.length - 1], freqZero[0]);
        for (int i = 0; i < freqOne.length - 1; i++) {
            minFlip = Math.min(minFlip, freqOne[i] + freqZero[i + 1]);
        }
        return minFlip;
    }

    // below is my solution, the above is a more skillful approach
    public int minFlipsMonoIncr(String s) {
        int[] freqOne = new int[s.length()];
        int[] freqZero = new int[s.length()];
        int counter = 0;

        // get the frequency of 1 before 0
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1')
                counter++;
            freqOne[i] = counter;
        }

        // get the frequency of 0 after 1
        counter = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '0')
                counter++;
            freqZero[i] = counter;
        }

        // scan two arrays simultaneously and get the sum of the value before split and
        // the value after split
        int minFlip = 0 + freqZero[0]; // the first split will be before index 0
        for (int i = 0; i < freqOne.length; i++) {
            if (i == freqOne.length - 1) {
                minFlip = Math.min(minFlip, freqOne[i] + 0);
            } else {
                minFlip = Math.min(minFlip, freqOne[i] + freqZero[i + 1]);
            }
        }
        return minFlip;
    }

}
