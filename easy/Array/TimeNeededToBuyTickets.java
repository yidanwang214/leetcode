// https://leetcode.com/problems/time-needed-to-buy-tickets/description/?envType=daily-question&envId=2024-04-09
public class TimeNeededToBuyTickets {
    /*
     * Optimal solution:
     * traverse the tickets array
     * if i < k: if tickets[i] <= k, res += tickets[i], else: res += tickets[k]
     * if i = k: res += k
     * if i > k: if tickets[i] < k, res += tickets[i], else: res += tickets[k]-1
     * Time: O(tickets.length)
     * Space: O(1)
     */
    public int timeRequiredToBuy(int[] tickets, int k) {
        int res = 0;
        for (int i = 0; i < tickets.length; i++) {
            if (i < k) {
                if (tickets[i] <= tickets[k])
                    res += tickets[i];
                else
                    res += tickets[k];
            } else if (i == k) {
                res += tickets[k];
            } else { // i > k
                if (tickets[i] < tickets[k])
                    res += tickets[i];
                else
                    res += (tickets[k] - 1);
            }
        }
        return res;
    }

    /*
     * brute force
     * time: O(k * n)
     * space: O(1)
     * test case
     * tickets = [3,4,5], k = 2
     * a b k a b k a b k b k k
     * 2 1 2 1 2 1 1 1 1
     * 
     * tickets=[2,3,4,5], k=2
     * a b k c a b k c b k c k c
     * 2 1 1 2 1 1 1 1 1 0
     * 
     * tickets = [3,4,5], k = 1
     * i=0, res=1, tickets = [2,4,5]
     * i=1, res=2, tickets = [2,3,5]
     * i=2, res=3, tickets = [2,3,4]
     * i=0, res=4, tickets = [1,3,4]
     * i=1, res=5, tickets = [1,2,4]
     * i=2, res=6, tickets = [1,2,3]
     * i=0, res=7, tickets = [0,2,3]
     * i=1, res=8, tickets = [0,1,3]
     * i=2, res=9, tickets = [0,1,2]
     * i=1, res=9, tickets = [0,1,2]
     * i=2, res=10, tickets = [0,0,2]
     */
    public int timeRequiredToBuyBF(int[] tickets, int k) {
        int res = 0;
        boolean isFinished = false;
        while (tickets[k] != 0) {
            for (int i = 0; i < tickets.length; i++) {
                if (tickets[i] > 0) {
                    res += 1;
                    tickets[i]--;
                    if (i == k && tickets[i] == 0) {
                        isFinished = true;
                        break;
                    }
                }
            }
            if (isFinished == true)
                break;
        }
        return res;
    }
}
