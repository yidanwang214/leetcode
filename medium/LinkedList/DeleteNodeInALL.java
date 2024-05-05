public class DeleteNodeInALL {
    // reference editorial:
    // https://leetcode.com/problems/delete-node-in-a-linked-list/editorial/?envType=daily-question&envId=2024-05-05
    // time: O(1)
    // space: O(1)

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
