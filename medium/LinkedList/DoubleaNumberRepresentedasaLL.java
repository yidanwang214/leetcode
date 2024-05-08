// https://leetcode.com/problems/double-a-number-represented-as-a-linked-list/description/?envType=daily-question&envId=2024-05-07

import java.lang.classfile.components.ClassPrinter.ListNode;

public class DoubleaNumberRepresentedasaLL {
    // reference:
    // https://leetcode.com/problems/double-a-number-represented-as-a-linked-list/?envType=daily-question&envId=2024-05-07
    public ListNode doubleIt(ListNode head) {
        if (head.val > 4)
            head = new ListNode(0, head);
        for (ListNode node = head; node != null; node = node.next) {
            node.val = node.val * 2 % 10;
            if (node.next != null && node.next.val > 4)
                node.val++;
        }
        return head;
    }
}