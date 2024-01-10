import java.util.HashMap;
import java.util.Map;

public class BullsAndCows {
    /*
     * optimal solution:
     * https://leetcode.com/problems/bulls-and-cows/
     * Time: O(n)
     * Space: O(1)
     */

    /*
     * secret: 987
     * guess: 189
     * bulls: 1(8)
     * cows: 1(9)
     * 1A1B
     * 
     * psuedo:
     * 1. initialise bullCounter, cowCounter, secret HashMap stores (num, frequency)
     * in secret, guess hashMap stores(num, frequency) in guess
     * 2. iterate through secreat and guess at the same time:
     * if secret[i]==guess[i], increment bullCounter;
     * else, update bullMap and cowMap at the same time cuz we want to exclude bull
     * from cow
     * 3. iterate through the bullMap, use teh same key to look up cowMap, if
     * exsits, increate cowCounter by min(bullMap.get(key), cowMap.get(key)) cuz we
     * want to avoid adding duplicate digits that are not exist in secret
     * Time: O(n), n for the length of input
     * Space: O(n) due to HashMap
     */

    public String getHint(String secret, String guess) {
        // initialse bullcounter and cowcounter
        int bullCounter = 0;
        int cowCounter = 0;
        // initialise bullMap and cowMap which stores the (number, frequency of each
        // Number) for secret and guess respectively
        HashMap<Integer, Integer> bullMap = new HashMap<>();
        HashMap<Integer, Integer> cowMap = new HashMap<>();

        // iterate through 2 strings at the same time
        for (int i = 0; i < secret.length(); i++) {
            char b = secret.charAt(i);
            char c = guess.charAt(i);
            if (b == c) {
                bullCounter++;
            } else {
                bullMap.merge((int) b, 1, Integer::sum);
                cowMap.merge((int) c, 1, Integer::sum);
            }
        }

        // loop cow and use same key to get the frequency in bull
        for (Map.Entry<Integer, Integer> entry : cowMap.entrySet()) {
            cowCounter += Math.min(bullMap.getOrDefault((entry.getKey()), 0), entry.getValue());
        }

        // return result
        return bullCounter + "A" + cowCounter + "B";

    }
}
