// https://leetcode.com/problems/number-of-laser-beams-in-a-bank/description/
public class NumOfLaserBeamsInABank {
    /*
     * algo:
     * 1. initialise res = 0
     * 2. iterate though every row, get the number of 1 in each row, and get the num
     * of 1 in the next row
     * if the num of 1 in the next row is 0, we skip to the row follwing the next
     * otherwise, res = res +num of 1 in the first row * the num of 1 in the next
     * row that has 1, then we update the next row to be the row we are visiting in
     * the next iteration
     * 3. return res
     * time: O(m*n)
     * space: O(1)
     */
    public int numberOfBeams(String[] bank) {
        int res = 0;
        for (int i = 0; i < bank.length; i++) {
            int preNumOfDevice = 0;
            String s = bank[i];
            // get the number of devices in current row
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '1')
                    preNumOfDevice++;
            }
            if (preNumOfDevice == 0)
                continue;
            // check next row
            int nextNumOfDevice = 0;
            for (int j = i + 1; j < bank.length; j++) {
                String sNext = bank[j];
                for (int k = 0; k < sNext.length(); k++) {
                    if (sNext.charAt(k) == '1')
                        nextNumOfDevice++;
                }
                if (nextNumOfDevice == 0)
                    continue;
                else {
                    i = j - 1;
                    res += preNumOfDevice * nextNumOfDevice;
                    break;
                }
            }

        }

        return res;
    }
}
