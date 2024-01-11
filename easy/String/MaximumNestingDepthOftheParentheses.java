// https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/description/

public class MaximumNestingDepthOftheParentheses {
    /*
     * Better solution: for loop, complexity same as while but runs faster on
     * leetcode
     * Time: O(n)
     * Space: O(1)
     */
    public static int maxDepth(String s) {
        int count = 0; // count the current depth of ()
        int depth = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            } else if (s.charAt(i) == ')') {
                count--;
            } else {
                continue;
            }
            depth = Math.max(depth, count);
        }
        return depth;
    }

    /*
     * my solution: while loop
     * pseudo:
     * 1. initialise varibale depth = 0 and parenthesis pair occurence leftNum
     * 2. iterate over the string s (while)
     * -> if (indexOf('(') == -1), there is no opening (, then return depth
     * -> else, there is a opening (:
     * depth = max(depth, leftNum);
     * compared the index of following '(' and ')'
     * string = substring from the first occurance of (
     * ->if '(' comes before ')'
     * leftNum++;
     * s = substring(s.indexOf(());
     * ->else,
     * while(')' comes before '('){
     * leftCount--;
     * s = subtring(from the index after ')')
     * }
     * 3. return depth;
     * 
     * Time: O(n)
     * Space: O(1)
     * 
     * test:(1+(2*3)+((8)/4))+1
     * // s: 8)/4))+1
     * // leftNum: 3
     * // depth: 3
     */
    public static int maxDepth(String s) {
        // initialise varaibles
        int depth = 0;
        int leftNum = 0;
        // iterate over string
        while (s.length() != 0) {
            // if there is no any ( in the string
            if (s.indexOf("(") == -1) {
                return depth;
            }
            // if ther is an ( in string
            else {
                leftNum++;
                depth = Math.max(depth, leftNum);
                s = s.substring(s.indexOf('(') + 1);
                int nextLeft = s.indexOf("(");
                int nextRight = s.indexOf(")");
                if (nextLeft != -1 && nextLeft < nextRight) {
                    s = s.substring(nextLeft);
                    System.out.println("(");
                } else {
                    while (s.indexOf(')') < s.indexOf('(')) {
                        leftNum--;
                        s = s.substring(s.indexOf(')') + 1);
                    }
                }
            }
        }
        return depth;
    }

    public static void main(String[] args) {
        System.out.println(maxDepth("(1+(2*3)+((8)/4))+1"));
    }
}
