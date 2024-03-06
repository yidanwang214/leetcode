// https://leetcode.com/problems/merge-k-sorted-lists/description/

public class MergeKSortedLists {
    /*
     * sulution1: using pq
     * reference:
     * https://leetcode.com/problems/merge-k-sorted-lists/solutions/10528/a-java-
     * solution-based-on-priority-queue/
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        if (lists.length == 1)
            return lists[0];

        /*
         * same implementation, but TreeSet doesn't give right result
         * TreeSet can't save duplicate Node
         */
        // TreeSet<ListNode> ts = new TreeSet<>(new Comparator<ListNode>(){
        // @Override
        // public int compare(ListNode node1, ListNode node2){
        // if(node1.val < node2.val) return -1;
        // else if(node1.val == node2.val) return 0;
        // else return 1;
        // }
        // });
        PriorityQueue<ListNode> ts = new PriorityQueue<ListNode>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.val < o2.val)
                    return -1;
                else if (o1.val == o2.val)
                    return 0;
                else
                    return 1;
            }
        });

        for (ListNode node : lists) {
            if (node != null)
                ts.add(node);
        }
        ListNode root = new ListNode(0);
        ListNode res = root;
        while (!ts.isEmpty()) {
            // root.next = ts.pollFirst();
            root.next = ts.poll();
            root = root.next;
            if (root.next != null)
                ts.add(root.next);
        }
        return res.next;
    }

    /*
     * solution2: merge sort
     * reference: https://leetcode.com/problems/merge-k-sorted-lists/
     */
    public ListNode mergeKLists(ListNode[] lists) {
        return partition(lists, 0, lists.length - 1);
    }

    private ListNode partition(ListNode[] lists, int start, int end) {
        // base case
        // when there is only 1 list left
        if (start == end)
            return lists[start];

        if (start < end) {
            int mid = (start + end) / 2;
            ListNode l1 = partition(lists, start, mid);
            ListNode l2 = partition(lists, mid + 1, end);
            return merge(l1, l2);
        } else
            return null;
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }
}
