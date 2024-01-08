
// https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/
import java.util.HashMap;
import java.util.Stack;
import java.util.stream.IntStream;

import javax.swing.tree.TreeNode;

// confusion: should we return the result in bfs order or should we return the root node
// answer: root node

class Node {
    int val;
    Node left;
    Node right;

    Node() {
    }

    Node(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    Node(int val, Node right, Node left) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class ConstructTreeFromInorderAndPostorder {
    // solution 1 (better):
    // ・build a hashmap to save each node and its index in the inorder array, so
    // that when can get a node's index in inorder in O(n) time, without traversing
    // the inorder to find a node's index
    // ・when recur left and right subtrees, use index as left/right pointers instead
    // of creating subarrays
    // reference 1:
    // https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/solutions/221681/a-better-python-solution/
    // reference 2: https://www.youtube.com/watch?v=vm63HuIU7kw
    // Time complexity: O(n) from traversing the array postorder
    // Space complexity: O(n) from building a hashtable

    // 1.1 using stack to traver postorder
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // store each node and its index in hashmap
        HashMap<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++)
            indexMap.put(inorder[i], i);

        // create stack to store every item in postorder
        Stack<Integer> postStack = new Stack<Integer>();
        for (int i = 0; i < postorder.length; i++)
            postStack.push(postorder[i]);

        return findChildren(0, inorder.length - 1, indexMap, postStack);
    }

    public static TreeNode findChildren(int l, int r, HashMap<Integer, Integer> indexMap, Stack<Integer> postStack) {
        if (l > r)
            return null;

        TreeNode node = new TreeNode(postStack.pop());
        node.right = findChildren(indexMap.get(node.val) + 1, r, indexMap, postStack);
        node.left = findChildren(l, indexMap.get(node.val) - 1, indexMap, postStack);
        return node;
    }

    // 1.2 using global prointer to traverse postorder
    // reference:
    // first comment on
    // https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/solutions/3302159/easy-solutions-in-java-python-and-c-look-at-once/
    int currPtr = 0;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // store each node and its index in hashmap
        HashMap<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++)
            indexMap.put(inorder[i], i);

        // current pointer that points to the currect node in postorder
        currPtr = postorder.length - 1;

        return findChildren(0, inorder.length - 1, indexMap, postorder);
    }

    public TreeNode findChildren(int l, int r, HashMap<Integer, Integer> indexMap, int[] postorder) {
        if (l > r)
            return null;

        TreeNode node = new TreeNode(postorder[currPtr--]);
        node.right = findChildren(indexMap.get(node.val) + 1, r, indexMap, postorder);
        node.left = findChildren(l, indexMap.get(node.val) - 1, indexMap, postorder);
        return node;
    }

    // 1.3 use right left pointer to narrow down the postorder
    // reference:
    // https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/solutions/3302159/easy-solutions-in-java-python-and-c-look-at-once/
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // store each node and its index in hashmap
        HashMap<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++)
            indexMap.put(inorder[i], i);

        return findChildren(inorder, 0, inorder.length - 1, indexMap, postorder, 0, postorder.length - 1);
    }

    public TreeNode findChildren(int[] inorder, int inl, int inr, HashMap<Integer, Integer> indexMap, int[] postorder,
            int pol, int por) {
        if (inl > inr || pol > por)
            return null;

        TreeNode node = new TreeNode(postorder[por]);
        int currNodeIndex = indexMap.get(node.val);
        System.out.println("node.val: " + node.val + " currNodeIndex: " + currNodeIndex);
        int leftSubSize = currNodeIndex - inl;
        int rightSubSize = inr - currNodeIndex;
        node.right = findChildren(inorder, currNodeIndex + 1, inr, indexMap, postorder, por - rightSubSize, por - 1);
        node.left = findChildren(inorder, inl, currNodeIndex - 1, indexMap, postorder, pol, pol + leftSubSize - 1);
        return node;
    }

    // solution 2 (worse):
    // Time complexity: O(n^2), for traversing the postorder (that is, we visit each
    // node in postorder once), for each node, we use the helper function indexOf()
    // to get the index of each node in array inorder to check if each node has a
    // left subtree and right subtree
    // Space complexity: O(n^2) for creating subarrays, layer 1 we creat 2 subarrays
    // with (n-1) items, layer 2 we created with (n-1) items, so in the recursive
    // tree we created 1+2+...+(n-2)+(n-1) trees, whcih is O(n^2)
    // public TreeNode buildTree(int[] inorder, int[] postorder) {
    // Stack<Integer> postStack = new Stack<Integer>();
    // for (int i = 0; i < postorder.length; i++)
    // postStack.push(postorder[i]);
    // return findChildren(inorder, postStack);
    // }

    public static TreeNode findChildren(int[] inorder, Stack<Integer> postStack) {
        TreeNode node = new TreeNode(postStack.pop());
        int nodeIndexInorder = indexOf(inorder, node.val);
        // check if current node has right subtree
        if (nodeIndexInorder != inorder.length - 1) { // has right subtree
            node.right = findChildren(
                    IntStream.range(nodeIndexInorder + 1, inorder.length).map(i -> inorder[i]).toArray(), postStack);
        } else {
            node.right = null;
        }
        // check if currect node has left subtree
        if (nodeIndexInorder != 0) { // has left subtree
            node.left = findChildren(IntStream.range(0, nodeIndexInorder).map(i -> inorder[i]).toArray(), postStack);
        } else {
            node.left = null;
        }
        return node;
    }

    public static int indexOf(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num)
                return i;
        }
        return -1;
    }

    public static void inOrder(Node root) {
        if (root == null)
            return;
        inOrder(root.left);
        System.out.print(root.val + ", ");
        inOrder(root.right);
    }

    public static void postOrder(Node root) {
        if (root == null)
            return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + ", ");
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);
        Node node13 = new Node(13);
        Node node14 = new Node(14);
        Node node15 = new Node(15);
        Node node16 = new Node(16);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.left = node8;
        node4.right = node9;
        node5.left = node10;
        node5.right = node11;
        node6.left = node12;
        node6.right = node13;
        node7.left = node14;
        node7.right = node15;
        node8.left = node16;

        System.out.println("inOrder: ");
        inOrder(node1);

        System.out.println("\npostOrder: ");
        postOrder(node1);

        int[] inorder = { 9, 3, 15, 20, 7 };
        int[] postorder = { 9, 15, 7, 20, 3 };

    }
}
