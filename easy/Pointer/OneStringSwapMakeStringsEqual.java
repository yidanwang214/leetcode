// https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal/description/

import java.util.Arrays;

public class OneStringSwapMakeStringsEqual {
    // 1. https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal/solutions/1108695/java-100-faster-runtime-memory-i-bet-the-easiest-to-understand/
    // store the occurence of each letter in a string in an array indexed by
    // alphabetical order
    // then compare if two arrays contains the same occurance for each letter
    // appeared
    // time complexity: O(n)
    // spacy complexity: O(n)
    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.equals(s2))
            return true;

        int[] s1Array = new int[26];
        int[] s2Array = new int[26];
        int diffCounter = 0;
        for (int i = 0; i < s1.length(); i++) {
            char char1 = s1.charAt(i);
            char char2 = s2.charAt(i);
            if (char1 != char2)
                diffCounter++;
            if (diffCounter > 2)
                return false;
            s1Array[char1 - 'a']++;
            s2Array[char2 - 'a']++;
        }
        return Arrays.equals(s1Array, s2Array);
    }

    // 2. https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal/solutions/1108610/my-java-solution-0ms/
    // check if the first different letter in s1 is same as the second letter in s2,
    // and vice versa
    // so i just need to sligtly change the comparison condition and the return
    // concondition
    // time complexity: O(n)
    // space complexity: O(1)
    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.equals(s2)) return true;

        int firstIndex = 0;
        int secondIndex = 0;
        int diffCounter = 0;
    
        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)){
            diffCounter++;
            if(diffCounter == 1) firstIndex = i;
            if(diffCounter == 2) secondIndex = i;
            if(diffCounter > 2) return false;
            }
        }
        if(diffCounter != 2) return false;
    
        return (s1.charAt(secondIndex) == s2.charAt(firstIndex)
        && s1.charAt(firstIndex) == s2.charAt(secondIndex)) ? true : false;
    }

    // 3: 1 pointer that iterate through s1 and s2 at the same time
    // see if there are only 2 letters in s1 that are different from s2
    // and swap the 2 letters in s1 and see if the swapped string equals to s2
    // time complexity: O(n)
    // space complexity: O(1)
    // public boolean areAlmostEqual(String s1, String s2) {
        if (s1.equals(s2)) return true;
    
        int firstIndex = 0;
        int secondIndex = 0;
        int diffCounter = 0;
        char[] swappedS1 = new char[s1.length()];
    
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diffCounter++;
                if (diffCounter == 1)
                firstIndex = i;
                if (diffCounter == 2)
                secondIndex = i;
            } else {
                swappedS1[i] = s1.charAt(i);
            }
        }
        if (diffCounter != 2) return false;

        swappedS1[firstIndex] = s1.charAt(secondIndex);
        swappedS1[secondIndex] = s1.charAt(firstIndex);
    
        return String.valueOf(swappedS1).equals(s2) ? true : false;
    }

    // 4. brute force: get the permution of every 2 letters in s1 and change swap them,
    // then check if equal to s2
    // Time complexity : (n!)/((n-2)! * 2!) -> n(n-1)/2 -> O(n^2)
    // Space complexity: O(n) for creating an char array + O(1) for storing strings
    // in the same string variable in each
    // loop

}
