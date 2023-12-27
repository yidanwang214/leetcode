import java.util.HashMap;

public class BestPokerHand {

    // brute force: accessing array directly to record the occurance of each element
    // in ranks
    // reference:
    // https://leetcode.com/problems/best-poker-hand/solutions/2350329/without-hashmap-tc-o-n-sc-o-1/
    // time: O(n)
    // space: O(n)
    public String bestHand(int[] ranks, char[] suits) {
    int maxOccur = 0;
    int sameCard = 0;
    char ch = suits[0];
    int[] rankOccur = new int[14];

    for(int i = 0; i < 5; i++){
    rankOccur[ranks[i]]++;
    maxOccur = Math.max(maxOccur, rankOccur[ranks[i]]);
    if(ch == suits[i]) sameCard++;
    }
    
    // Flush
    if(sameCard == 5) return "Flush";

    // Three of a Kind
    return maxOccur >= 3 ? "Three of a Kind" : (maxOccur == 2 ? "Pair" : "High
    Card");
    }

    // brute force: using HashMap
    // time: O(n) for ietrating ranks and suits; put, replace and containsKey and
    // containsValue all takes O(1)
    // space: O(n)
    // public String bestHand(int[] ranks, char[] suits) {

    // HashMap<Character, Integer> suitOccur = new HashMap<Character, Integer>();
    // HashMap<Integer, Integer> rankOccur = new HashMap<Integer, Integer>();
    // for (int i = 0; i < 5; i++) {
    // if (suitOccur.containsKey(suits[i])) {
    // suitOccur.replace(suits[i], suitOccur.get(suits[i]) + 1);
    // } else {
    // suitOccur.put(suits[i], 1);
    // }

    // if (rankOccur.containsKey(ranks[i])) {
    // rankOccur.replace(ranks[i], rankOccur.get(ranks[i]) + 1);
    // } else {
    // rankOccur.put(ranks[i], 1);
    // }
    // }

    // // Flush
    // if (suitOccur.containsValue(5))
    // return "Flush";

    // // Three of a Kind
    // if (rankOccur.containsValue(3) || rankOccur.containsValue(4) ||
    // rankOccur.containsValue(5))
    // return "Three of a Kind";

    // // Pair
    // if (rankOccur.containsValue(2))
    // return "Pair";

    // return "High Card";
    // }
}
