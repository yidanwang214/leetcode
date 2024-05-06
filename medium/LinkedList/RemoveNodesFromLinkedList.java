import java.lang.classfile.components.ClassPrinter.ListNode;

// Time: O(n)
// Space: O(1)
public class RemoveNodesFromLinkedList {
    public ListNode removeNodes(ListNode head) {
        // base case
        if (head.next == null)
            return head;

        // recursive case
        if (head.val < head.next.val)
            return head = removeNodes(head.next);
        else {
            head.next = removeNodes(head.next);
            if (head.next != null && head.val < head.next.val)
                head = head.next;
            return head;
        }
    }
}
