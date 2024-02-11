// https://leetcode.com/problems/find-mode-in-binary-search-tree/description/

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FindModeInBST {
    /*
     * 5
     * 4 5
     * 3 4 5 6
     * 
     * algo:
     * 1. Maintain a map to store the <number, freq> pair and a variable max to
     * store the greatest freq of a value in BST
     * 2. dfs() the root
     * 3. update every node.val's freq in map, and update max if the current
     * node.val's freq is greater than max
     * 4. iterate through the map, put the number whose freq is the same as max
     * time: O(n) -> traversed the tree and visited every node
     * space: O(n) + O(logn) -> if the freq of every node is 1, the size of the
     * stack is O(n), logn is recursive stack
     * 
     */
class Solution {
    HashMap<Integer, Integer> freq = new HashMap<>();
    int max = 1;
    public int[] findMode(TreeNode root) {
        dfs(root);
        List<Integer> res = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry: freq.entrySet()){
            if(entry.getValue() == max) res.add(entry.getKey());
        }
        int[] arr = new int[res.size()];
        for(int i = 0; i < res.size(); i++) arr[i] = res.get(i);
        return arr;
    }
    private void dfs(TreeNode root){
        // base cond
        if(root == null) return;

        // update freq
        freq.merge(root.val, 1, Integer::sum);
        if(freq.get(root.val) > max) max = freq.get(root.val);
        dfs(root.left);
        dfs(root.right);
    }
}
