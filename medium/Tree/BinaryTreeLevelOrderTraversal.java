
// https://leetcode.com/problems/binary-tree-level-order-traversal/
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (q.peek() != null) {
            List<TreeNode> tmp = new ArrayList<>();
            List<Integer> tmpInt = new ArrayList<>();
            while (q.peek() != null) {
                tmp.add(q.peek());
                tmpInt.add(q.poll().val);
            }
            for (int i = 0; i < tmp.size(); i++) {
                TreeNode node = tmp.get(i);
                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);

            }
            res.add(new ArrayList<>(tmpInt));
        }
        return res;
    }
}
