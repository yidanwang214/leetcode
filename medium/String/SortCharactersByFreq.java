// https://leetcode.com/problems/sort-characters-by-frequency/

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import javafx.util.Pair;

public class SortCharactersByFreq {
    /*
     * convert char to ascii: (int)c
     * convert ascii to char: (char)ascii
     * Time: O(n) for scan + O(n) for building the tree
     * Space: O(n) worst case each letter is different
     */
    public String frequencySort(String s) {
        PriorityQueue<Pair<Character, Integer>> pq = new PriorityQueue<>(new Comparator<Pair<Character, Integer>>() {
            @Override
            public int compare(Pair<Character, Integer> a, Pair<Character, Integer> b) {
                return b.getValue() - a.getValue();
            }
        });

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.merge((int) s.charAt(i), 1, Integer::sum);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int ascii = entry.getKey();
            Character character = new Character((char) ascii);
            System.out.println(ascii);
            System.out.println(character);
            Pair<Character, Integer> pair = new Pair<>(character, entry.getValue());
            pq.add(pair);
        }

        StringBuilder res = new StringBuilder();
        while (!pq.isEmpty()) {
            Pair<Character, Integer> pair = pq.poll();
            int loops = pair.getValue();
            char c = pair.getKey();
            while (loops != 0) {
                loops--;
                res.append(c);
            }
        }
        return res.toString();
    }
}
