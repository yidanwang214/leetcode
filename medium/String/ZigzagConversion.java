// https://leetcode.com/problems/zigzag-conversion/description/
public class ZigzagConversion {
    /*
     * Approach 1: Math (worse time than StringBuilder approach)
     * fiding pattern
     * numRow = 1 return
     * numRow = 2 first row: res(0)+res(i+2)+res(i+4)+..., second row: same
     * numRow = 3 first row: res(0)+res(i+4)+res(i+8)+ res(i+12), second row:
     * res(1)+ res(i+2) + res(i+4) + res(i+6), third: res(2)+res(i+4)+res(i+8)
     * numRow = 4 res(0)+res(i+6)+res(i+12), second: res(1)+res(i+4)+ res
     * numRow = 5 res(0)+res(i+8)+res(i+16)
     * numRow = 6 res(0)+res(i+10)+res(i+20)
     * diff
     * first row: 2*numRow-2: steps from topdown to bottomup - the start point -the
     * last row cuz letters are not replicated on the lowest row
     * 
     * PAYPALISHIRING
     * 2
     * P Y A I H R N
     * A P L S I I G
     * 
     * PAYPALISHIRING
     * 3
     * P A H N
     * A P L S I I G
     * Y I R
     * 
     * PAYPALISHIRING
     * 4 row diff
     * P I N 0 6/6 2*row-2 -> 2(row-1)
     * A L S I G 1 4/6 2*(row-1)-2*1(1=i) / 2(row-1)
     * Y A H R 2 2/6 2*(row-1)-2*2(2=i) / 2(row-1)
     * L I 3 0/6 s2(row-1)
     * 
     * PAYPALISHIRING
     * 5 row diff
     * P H P 0 8/8 2*row-2 -> 2(row-1)
     * A S I P L 1 6/8 2(row-1-1) / 2(row-1)
     * Y I R A N 2 4/8 2(row-1-2) / 2(row-1)
     * P L I G O 3 2/8 2(row-1-3) / 2(row-1)
     * A N W 4 0/8 2(row-1-4) / 2(row-1)
     * 
     * 
     * 1 <= s.length <= 1000
     * 1 <= numRows <= 1000
     */

    /*
     * Pattern:
     * 1. In each row, the index difference between each letter and its next letter
     * is (row-1)*2
     * 2. Between the first and last rows, an additional third letter exists between
     * each letter and its next letter. This third letter is (row-1-currentRowNum)2
     * indices away from the original letter, and there is a distance of
     * currentRowNum*2 from the next letter.
     * 
     * pseudo:
     * 1. Get the difference(majorDiff) from between original character and the
     * character letter by using (row-1)*2
     * 2. Use nested loop to get anwser
     * 2.1. The outer loop iterates from index 0 to index (numRows-1), indicating we
     * are scanning each row. For rows between the first and the last, we should
     * also get the difference(minorDiff) between the third character and the next
     * character
     * 2.2 The inner loop scan the array and jumps from the previous character to
     * the next character with a distance of majorDiff. We append characters in each
     * iteration to result.
     * Time: O(s.length/numRows)
     * Space: O(1)
     */
    public String convert(String s, int numRows) {
        // if only one row, return original string
        if (numRows == 1)
            return s;

        int len = s.length();
        int majorDiff = numRows * 2 - 2; // the diff of index between the current letter and the next letter, can be
                                         // simplied to (numRows-1)*2
        String res = "";
        // outer loop iterates through the first numRows num of letters
        for (int i = 0; i < numRows; i++) {
            // index before stores the index difference between the current letter and the
            // potential previous letter, which should be considered for rows between the
            // first row and the second row
            int minorDiff = 0;
            if (i != 0)
                minorDiff = 2 * i;
            // inner loop jumps
            for (int j = i; (j < len || j - minorDiff < len); j += majorDiff) {
                if (i != (numRows - 1) && (i != 0) && j - minorDiff >= 0)
                    res += s.charAt(j - minorDiff);
                if (j < len)
                    res += s.charAt(j);

            }
        }
        return res;
    }

    // approach 2: StringBuilder
    // reference:
    // https://github.com/Eric-programming/CodeSolution/blob/master/src/Array/ZigZagConversion/ZigZagConversion.java
    // time: O(n)
    // space: O(n)
    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;

        // Define String Builders
        StringBuilder[] sbs = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            sbs[i] = new StringBuilder();
        }

        // Define Variables
        char[] arr = s.toCharArray();
        int len = arr.length;
        int index = 0;

        // Traversing Zig Zag
        while (index < len) {
            for (int i = 0; i < numRows && index < len; i++) {
                sbs[i].append(arr[index++]);
            }
            for (int i = numRows - 2; i > 0 && index < len; i--) {
                sbs[i].append(arr[index++]);
            }
        }

        StringBuilder res = sbs[0];
        for (int i = 1; i < numRows; i++) {
            res.append(sbs[i].toString());
        }
        return res.toString();
    }
}
