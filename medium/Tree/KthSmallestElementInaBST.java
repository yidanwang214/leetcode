class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class KthSmallestElementInaBST {

    /*
     * appraoch: traverse the tree inOrder, keep a counter that will increment, and
     * save the result in a class-level varaible res
     * pseudocode:
     * 1. base condition: return when we reach a null node
     * 2. recursively traverse left child of the current node
     * 3. increment counter to be align with the sequence of parent node, check its
     * sequence with k, if counter == k, save the value of parent node.value to res,
     * return
     * 4. recursively traverse right child of the current node
     * 5. return
     * Time: O(n)
     * Space: O(1)
     */
    private int counter;
    private int key;
    private int res;

    public int kthSmallest(TreeNode root, int k) {
        counter = 0;
        key = k;
        inOrder(root);
        return res;
    }

    public void inOrder(TreeNode node) {
        if (node == null)
            return;
        inOrder(node.left);
        System.out.println("left: " + node.left);
        counter++;
        if (counter == key) {
            System.out.println("counter: " + counter + ", Node: " + node.val);
            res = node.val;
            return;
        }
        inOrder(node.right);
        System.out.println("right: " + node.right);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        node1.right = node2;
        node3.left = node1;
        node3.right = node4;

        KthSmallestElementInaBST bst = new KthSmallestElementInaBST();
        // bst.kthSmallest(node3, 2);
        System.out.println(bst.kthSmallest(node3, 2));

    }
}
