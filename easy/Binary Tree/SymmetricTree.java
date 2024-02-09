// https://leetcode.com/problems/symmetric-tree/description/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class SymmetricTree {
    // 1. recursive approach
    public boolean isSymmetric(TreeNode root) {
        return root == null || bfs(root.left, root.right);
    }

    private boolean bfs(TreeNode left, TreeNode right) {
        // base case
        if (left == null || right == null)
            return left == right;

        // compare if left's value is euqal's right's value
        if (left.val != right.val)
            return false;
        return bfs(left.left, right.right) && bfs(left.right, right.left);
    }

    // 2. iterative approach
    // bfs
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            ArrayList<TreeNode> al = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = q.poll();
                if (tmp == null)
                    continue;
                q.add(tmp.left);
                al.add(tmp.left);
                q.add(tmp.right);
                al.add(tmp.right);
            }
            for (int i = 0, j = al.size() - 1; i < al.size() / 2 && j >= al.size() / 2; i++, j--) {
                if ((al.get(i) == null && al.get(j) != null) || (al.get(i) != null && al.get(j) == null)
                        || (al.get(i) != null && al.get(j) != null && al.get(i).val != al.get(j).val)) {
                    return false;
                }
            }
        }
        return true;
    }

    // 3. my inefficient recursive approach
    List<Integer> leftSubtree = new ArrayList<>();
    List<Integer> rightSubtree = new ArrayList<>();

    public boolean isSymmetric(TreeNode root) {
        // for left subtree we use preorder
        preorder(root.left);
        // for right subtree we use the reverse of preorder
        reversePreorder(root.right);
        if (leftSubtree.size() != rightSubtree.size())
            return false;
        // for(int i = 0; i < leftSubtree.size(); i++){
        // System.out.println("l: " + leftSubtree.get(i));
        // }
        // for(int i = 0; i < rightSubtree.size(); i++){
        // System.out.println("r: " + rightSubtree.get(i));
        // }
        for (int i = 0; i < leftSubtree.size(); i++) {
            // if(leftSubtree.get(i) != rightSubtree.get(i)) {
            // System.out.println("i: "+ i + ", l:" + leftSubtree.get(i) + ", r:" +
            // rightSubtree.get(i));
            // return false;
            // }
            if (!Objects.equals(leftSubtree.get(i), rightSubtree.get(i)))
                return false;
        }
        return true;
    }

    private void preorder(TreeNode root) {
        // base case
        if (root == null) {
            leftSubtree.add(null);
            return;
        }

        // preorder traversal
        leftSubtree.add(root.val);
        preorder(root.left);
        preorder(root.right);

    }

    private void reversePreorder(TreeNode root) {
        // base case
        if (root == null) {
            rightSubtree.add(null);
            return;
        }

        // reversePreorder traversal
        rightSubtree.add(root.val);
        reversePreorder(root.right);
        reversePreorder(root.left);
    }
}
