// https://leetcode.com/problems/add-one-row-to-tree/description/?envType=daily-question&envId=2024-04-16
public class AddOneRowToTree {
    // Time: average case: O(2^depth), worst case: O(n)
    // Space: average case: O(2^depth), worst case: O(n)
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        // base case
        // when depth is 1
        if (depth == 1) {
            TreeNode node = new TreeNode(val, root, null);
            return node;
        }

        if (root == null)
            return null;

        // when reach the 1 layer above the depth
        if (depth - 2 == 0) {
            TreeNode nodeleft = new TreeNode(val, root.left, null);
            TreeNode noderight = new TreeNode(val, null, root.right);
            root.right = noderight;
            root.left = nodeleft;
            return root;
        }
        root.left = addOneRow(root.left, val, depth - 1);
        root.right = addOneRow(root.right, val, depth - 1);
        return root;
    }

    /*
     * BFS approach
     * 1. handle edge case when depth == 1
     * 2. create a queue, which stores the nodes on each layer; a var layer==1 to
     * record the layer we are traversing
     * 3. add root to queue, and enter a while loop, the condition is that the queue
     * is not empty
     * -> layer++, check if layer + 1 == depth, if yes,
     * for each and every node in queue, create 2 new nodes of value val,
     * make the left child of left new node point to each node's left child, new
     * right child of each node point to each node's right child
     * when finished, break out of the loop
     * -> add each node's child to queue
     * 
     * return root
     * 
     * time: O(n)
     * space: O(n)
     */
    public TreeNode addOneRowBFS(TreeNode root, int val, int depth) {
        // handle edge case when depth == 1
        if (depth == 1) {
            TreeNode node = new TreeNode(val, root, null);
            return node;
        }

        // create a queue, which stores the nodes on each layer; a var layer==1 to
        // record the layer we are traversing
        int layer = 1;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            if (layer + 1 == depth) {
                while (!q.isEmpty()) {
                    TreeNode curNode = q.poll();

                    TreeNode leftNode = new TreeNode(val);
                    leftNode.left = (curNode.left == null ? null : curNode.left);

                    TreeNode rightNode = new TreeNode(val);
                    rightNode.right = curNode.right;
                    curNode.left = leftNode;
                    curNode.right = rightNode;
                }
                return root;
            }
            layer++;
            int size = q.size();
            while (size > 0) {
                TreeNode node = q.poll();
                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);
                size--;
            }
        }
        return root;
    }
}
