// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

public class ConstructBinaryTreeFromPreorderAndInorder {
    /*
     * input: [2] [2]
     * output: 2
     * base case: when pre.length == in.length == 1, return new TreeNode(pre.val);
     * 
     * 
     * intput: 12
     * 11 10
     * 9 7 6
     * 8 5 4
     * /\
     * 3 2
     * 
     * pre: [12, 11, 9, 8, 10, 7, 5, 4, 3, 2, 6]
     * in: [8, 9, 11, 12, 5, 7, 3, 4, 2, 10, 6]
     * step 1. root: 12, the first element of pre, instalise the root node
     * step 2. find 12 in arr inorder, elements before 12 are those in 12's left
     * subtree, after are right substree
     * extract leftInorder and rightInorder from arr inorder
     * get the length of 12's left subtree from inorder, since the length of subtree
     * in preorder and inorder are the same, we can use the length to extract
     * leftPreorder and rightPreorder
     * step 3. root.left = buildtree(int[] leftInorder, int[] leftPreorder)
     * root.right = buildtree(int[] rightInorder, int[] rightPreorder)
     * Time: O(n) -> visit every node
     * Space: O(n) -> in worst case, a tree tree's height is n, so the recursive
     * stack is n
     * 
     * can be optimised by using hashmap to access the index of root in inorder
     * array
     * 
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // base case
        if (preorder.length == 0 || inorder.length == 0)
            return null;
        if (preorder.length == 1 && inorder.length == 1)
            return new TreeNode(inorder[0]);

        // root is the first element of preorder, initialise the root node
        TreeNode root = new TreeNode(preorder[0]);
        // find root's index in inorder
        int i = 0;
        while (i < inorder.length) {
            if (inorder[i] == root.val)
                break;
            i++;
        }
        // construct leftInorder and rightInorder
        int[] leftInorder;
        int[] rightInorder;
        leftInorder = buildArray(0, i, inorder);
        rightInorder = buildArray(i + 1, inorder.length, inorder);

        // construct leftInorder and rigthInorder
        int[] leftPreorder;
        int[] rightPreorder;
        leftPreorder = buildArray(1, leftInorder.length + 1, preorder);
        rightPreorder = buildArray(1 + leftPreorder.length, preorder.length, preorder);

        // recursively construct tree
        root.left = buildTree(leftPreorder, leftInorder);
        root.right = buildTree(rightPreorder, rightInorder);
        return root;
    }

    private int[] buildArray(int start, int end, int[] arr) {
        int[] res = new int[end - start];
        int j = 0;
        System.out.println("Start: " + start);
        for (int i = start; i < end; i++) {
            res[j++] = arr[i];
        }
        return res;
    }
}
