// https://leetcode.com/problems/maximum-number-of-balloons/
public class MaximumNumberofBalloons {
    /*
     * algo:
     * brute force
     * 1. loop: i: 0 < x/2
     * check if i*i == x -> if true, return i
     * check if i*i < x < (i+1)*(i+1) returni
     * time: O(squareroot of x)
     * space: O(1)
     */
    public int maxNumberOfBalloons(String text) {
        // the number of letters in text can't make up a balloon instance
        // if text length < balloon length
        if (text.length() < 7)
            return 0;

        // array: store the frequence of the letters a,b,l,n,o -> [0,1,2,3,4]
        // store frequencies [a,b,l,n,o]
        int[] arr = new int[5];

        // scan through the text, check if every char is a,b,l,n,o,
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == 'a')
                arr[0]++;
            if (text.charAt(i) == 'b')
                arr[1]++;
            if (text.charAt(i) == 'l')
                arr[2]++;
            if (text.charAt(i) == 'n')
                arr[3]++;
            if (text.charAt(i) == 'o')
                arr[4]++;
        }

        // return the min frequency of a, b ,n, l/2, o/2
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            if (i == 4 || i == 2) {
                res = Math.min(arr[i] / 2, res);
            } else {
                res = Math.min(arr[i], res);
            }
        }
        return res;
    }
}
