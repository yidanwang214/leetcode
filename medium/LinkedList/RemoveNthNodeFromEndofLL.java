// https://leetcode.com/problems/remove-nth-node-from-end-of-list/

public class RemoveNthNodeFromEndofLL {
    /*
     * optimal solution: 1 pass
     * reference: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
     * find a way to both rreach the end of the list with one point and also reach
     * the n'th node from the end simultaneously with a second pointer
     * we can stagger our two pointers by n nodes, so that when fast reaches the
     * end, slow can reach the n'th node
     * time: O(n)
     */

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head, slow = head;
        // stagger fast and slow by n, to make sure slow can eventually point to the
        // node before n-th node when fast reaches the end
        for (int i = 0; i < n; i++)
            fast = fast.next;
        // if n-th node is the head of the linkedlist
        if (fast == null)
            return head.next;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // the slow points to the node before the n-th node,
        // therefore we need to update slow.next by 2 step ahead
        slow.next = slow.next.next;
        return head;
    }

    /*
     * my approach: 2 pass
     * algo:
     * 1. first pass: count the number of nodes in the linkedlist, save it to
     * variable counter
     * 2. create preNode and curNode to store the previous node and the current node
     * 3. second pass: visit every node from head, check if sequence equals to n,
     * if true, point preNode's next to curNode's next, break out of loop
     * else, counter--, update preNode and curNode respectively, go to the next
     * iteration
     * 4. return head
     * time: O(n) -> in worst case, if n = 1, we need to visit every node in the
     * likedlist to reach to the last node
     * space: O(1) -> no extra memory, just a dew vairables that saves the addresses
     * of nodes
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // first pass
        int counter = 1;
        ListNode tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
            counter++;
        }

        // edge case: if the node to be deleted is head
        if (counter == n)
            return head.next;

        // create preNode and curNode to store the previous node and the current node
        ListNode pre = null;
        ListNode cur = head;
        while (cur.next != null) {
            // System.out.println("counter: " + counter);
            if (counter != n) {
                counter--;
                pre = cur;
                cur = cur.next;
                if (counter == n) {
                    pre.next = cur.next;
                    break;
                }
            }
        }

        return head;
    }
}
