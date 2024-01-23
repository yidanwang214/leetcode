public class DeleteaMiddleNodeofaLinkedList {

    /*
     * Optimal sol: fast+=2, slow++, when fast == null or fast.next == null, slow
     * awways points to the node before midddle point
     * Time: O(n)
     * Space: O(1)
     */
    public ListNode deleteMiddle(ListNode head) {
        /*
         * 2 [] slow:0(1 node beofre mid) fast:null
         * 3 [] slow:0(1 node before mid) fast:2
         * 4 [] slow:0 fast:2 -> slow:1(1 node before mid) fast: null
         * 5 [] slow:0 fast:2 -> slow:1(1 node before mid) fast:4
         * 6 [] slow:0 fast:2 -> slow:1 fast:4 -> slow:2(1 node before mid) fast:null
         * 7 [] slow:0 fast:2 -> slow:1 fast:4(1 node before mid) -> slow:2(1 node
         * before mid) fast:6
         * 8 [] slow:0 fast:2 -> slow:1 fast:4 -> slow:2 fast:6 -> slow:3(1 node before
         * mid) fast:null
         * 9 [] slow:0 fast:2 -> slow:1 fast:4 -> slow:2 fast:6 -> slow:3 fast:8 ->
         * slow:4(1 node before mid) fast:null
         */
        // if the first node is null or there is just 1 node in linkedlist
        if (head == null || head.next == null)
            return null;

        // slow pointer always points to the node before the middle node when fast=null
        // or head.next==null as seen above
        ListNode slow = head;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        /*
         * slow -> slow.next -> slow.next.next
         * ->>>>>>>>>tmp
         */
        ListNode tmp = slow.next;
        slow.next = slow.next.next;
        tmp = null;
        return head;
    }
}
