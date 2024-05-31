import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class TaskScheduler {
    // reference, similiar to my though process
    // https://www.youtube.com/watch?v=ySTQCRya6B0
    /*
     * 1. create an array that count the freq of each letter
     * 2. put the freq in pq which is sorted in descending order
     * 3. while pq is not empty:
     * for n+1 iteration, poll first into a tmp arraylist if the pq is not empty
     * then decrement every element in arraylist and add back to the pq
     * if pq is empty, op += arraylist.size(), else op += n+1 because we need to
     * wait n+1 cycles to get to the next round while ensuring there is a n
     * intervals between identical tasks
     * 
     * caution: use pq instead of treeset, cuz pq allows duplicate elements while
     * treeset doesn't
     * time: O(n * k) k is the freq of the most frequent number
     * space: O(n)
     */
    public int leastIntervalSimulator(char[] tasks, int n) {
        int res = 0;
        // record the freq of each letter
        int[] freq = new int[26];
        for (int i = 0; i < tasks.length; i++)
            freq[tasks[i] - 'A']++;
        // put the freq of each number into a descending pq
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < 26; i++) {
            if (freq[i] != 0)
                pq.add(freq[i]);
        }

        while (!pq.isEmpty()) {
            ArrayList<Integer> al = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                if (!pq.isEmpty())
                    al.add(pq.poll());
            }
            for (int i : al) {
                if (--i > 0)
                    pq.add(i);
                System.out.println(i);
            }
            /*
             * if pq is empty, we just finish processing the last batch of tasks, which
             * takes al.size() cycles
             * else, we need to wait n+1 cycles no matter how many tasks we have processed
             * in this iteration/batch
             */
            res += (pq.isEmpty() ? al.size() : (n + 1));
        }
        return res;
    }

    /*
     * intuitive
     * reference: https://www.youtube.com/watch?v=s8p8ukTyA2I&t=542s
     */
    public int leastInterval(char[] tasks, int n) {
        // record the freq of each letter
        int[] freq = new int[26];
        for (int i = 0; i < tasks.length; i++)
            freq[tasks[i] - 'A']++;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < 26; i++) {
            if (freq[i] != 0)
                pq.add(freq[i]);
        }
        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        int time = 0;
        while (!pq.isEmpty() || !q.isEmpty()) {
            time += 1;
            if (!pq.isEmpty()) {
                int leftTime = pq.poll() - 1;
                if (leftTime > 0)
                    q.add(new Pair<>(leftTime, time + n));
            }
            if (!q.isEmpty() && time == q.peek().getValue()) {
                Pair<Integer, Integer> tmp = q.poll();
                pq.add(tmp.getKey());
            }
        }
        return time;
    }

    /*
     * math:
     * reference: https://www.youtube.com/watch?v=YCD_iYxyXoo
     * 
     */
    public int leastIntervalMath(char[] tasks, int n) {
        int res = 0;

        // record the frequency of each letter in tasks
        int[] freq = new int[26];
        for (char c : tasks)
            freq[c - 'A']++;

        // get the max frequency among freq array
        Arrays.sort(freq);
        int maxFreq = freq[freq.length - 1];

        // formulata to get the intervals = (maxFreq-1)*(n+1)+ the number of chars that
        // have the same freq as the char that has most freq
        int numOfSameMaxFreq = 1;
        for (int i = freq.length - 2; i >= 0; i--) {
            if (freq[i] != maxFreq)
                break;
            numOfSameMaxFreq++;
        }
        return Math.max(tasks.length, numOfSameMaxFreq + (maxFreq - 1) * (n + 1));
    }
}