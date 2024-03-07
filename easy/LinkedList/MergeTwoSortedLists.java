// https://leetcode.com/problems/merge-two-sorted-lists/description/

public class MergeTwoSortedLists {
    /*
     * recursive approach by creating a merge(l1, l2):
     * // base case
     * 1. if l1 and l2 are null, return null
     * 2. if l1 is not null and l2 is null, return l1
     * 3. if l1 is null and l2 is not null, reuturn l2
     * // recursive case
     * create a ListNode head
     * if l1.val <= l2.val, point head to l1, then set head.next = merge(l1.next,
     * l2)
     * else point head to l2, set head.next = merge(l1, l2.next)
     * return head
     * 
     * time: O(m+n) -> if the merged list consists of alternating nodes from l1 and
     * l2,
     * with 1 nodes from l1 between each node from l2, or the other way round,
     * then we need to check every and each node in both of the lists.
     * m is the num of nodes in l1, n is the num of nodes in l2
     * space: O(m+n) -> recursive stack in worst case can be 0(m+n), because we
     * handle 1 node in 1 stack
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // base case
        if (list1 == null && list2 == null)
            return null;
        if (list1 != null && list2 == null)
            return list1;
        if (list1 == null && list2 != null)
            return list2;
        // recurisve case
        ListNode head;
        if (list1.val <= list2.val) {
            head = list1;
            head.next = mergeTwoLists(list1.next, list2);
        } else {
            head = list2;
            head.next = mergeTwoLists(list1, list2.next);
        }
        return head;
    }

    /*
     * iterative approach
     * time: O(m+n) visit every node
     * space: O(1)
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null)
            return null;
        if (list1 != null && list2 == null)
            return list1;
        if (list1 == null && list2 != null)
            return list2;
        ListNode head;
        if (list1.val <= list2.val) {
            head = list1;
            list1 = list1.next;
        } else {
            head = list2;
            list2 = list2.next;
        }
        ListNode tmp = head;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                tmp.next = list1;
                list1 = list1.next;
                tmp = tmp.next;
            } else {
                tmp.next = list2;
                list2 = list2.next;
                tmp = tmp.next;
            }
        }

        if (list1 != null && list2 == null)
            tmp.next = list1;
        else if (list1 == null && list2 != null)
            tmp.next = list2;

        return head;
    }
}
