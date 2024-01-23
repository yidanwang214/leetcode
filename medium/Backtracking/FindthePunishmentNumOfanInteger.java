public class FindthePunishmentNumOfanInteger {

    /*
     * reference:
     * https://leetcode.com/problems/find-the-punishment-number-of-an-integer/
     * solutions/3546812/simple-recursion-easy-to-understand/
     * 
     * the snag of this problem is Publishment Number
     * take 36 in example 2 for example, 36^2 = 1296, 1296 can be partitioned into
     * {1,2,9,6}, {12,9,6}, {12, 96},{129, 6}, {1,29,6},{1,296},{1296}, the sum of
     * different combinations are 18, 27, 108, 135, 36, 297, 1296, which has 36.
     * 36^2 is the a punishmentnumber of 37.
     * 
     * 
     * pesudo:
     * 1. iterate each inetger from 1 to n O(n)
     * 2. inside of each iteration, we get the current num's squre, and use
     * backtrack to generate different combinations if num^2's digits, if we found
     * one combination where the sum of substrings equals to num, we return true,
     * else false
     * 
     * [1,2,3,4][1,2,34][1,23,4][1,234][12,3,4][12,34][123,4][1234]
     * 
     * Time: O(n* 6^6)
     * Space: auxiliary O(n)
     */
    public static void main(String[] args) {
        public int punishmentNumber(int n) {
            int res = 0;
            for(int i = 0; i <= n; i++){
                if(bt(Integer.toString(i*i), i)) res+= i*i;
            }
            return res;
        }
    
        public boolean bt(String num, int target){
            // base con1
            // if num is empty and target is 0, there exists a combination of digits that could be used to fill num such that the sum of the digits would equal the sqrt of num
            if(num.equals("") && target == 0) return true;
    
            // base con2
            // if target is nagative, that means the sum of the combination exceeds the num, invalid
            if(target < 0) return false;
    
            // backtrack
            boolean isPunismentNum = false;
            for(int i = 0; i < num.length(); i++){
                String left = num.substring(0, i+1);
                String right = num.substring(i+1);
                int leftVal = Integer.valueOf(left);
                if(bt(right, target-leftVal)){
                    isPunismentNum = true;
                    break;
                }
            }
            return isPunismentNum;
        }
    }
}
