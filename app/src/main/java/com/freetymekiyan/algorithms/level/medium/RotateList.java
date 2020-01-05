package com.freetymekiyan.algorithms.level.medium;

import com.cspirat.ListNode;

/**
 * Given a list, rotate the list to the right by k places, where k is
 * non-negative.
 * <p>
 * For example:
 * Given 1->2->3->4->5->NULL and k = 2,
 * return 4->5->1->2->3->NULL.
 * <p>
 * Tags: Linked List, Two Pointers
 */
class RotateList {
    public static void main(String[] args) {
        ListNode n1 = new com.cspirat.ListNode(1);
        com.cspirat.ListNode n2 = new com.cspirat.ListNode(2);
        com.cspirat.ListNode n3 = new com.cspirat.ListNode(3);
        com.cspirat.ListNode n4 = new com.cspirat.ListNode(4);
        com.cspirat.ListNode n5 = new com.cspirat.ListNode(5);
        n1.next = n2; n2.next = n3; n3.next = n4; n4.next = n5; n5.next = null;
        com.cspirat.ListNode res = rotateRight(n1, 2);
        com.cspirat.ListNode.show(res);
    }

    /**
     * Two pointers
     * Move fast pointer to the end of the list to get length
     * Move slow pointer to len - n % len to get the break point
     * Connect fast with head, update new head
     * Set slow.next to null to unlink the list
     */
    // Tips: 三個節點操作, 頭指+中接+尾收null
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        // get length and move fast to the end of list
        int len;
        for (len = 0; fast.next != null; len++)
            fast = fast.next;
        // get the len-n%len th node
        int step = len - k % len; // KEY:
        for (int j = step; j > 0; j--)
            slow = slow.next;
        // step1--:
        //                                          slow                 fast
        //    [dummy,next]->[n1,next]->[n2,next]->[n3,next]->[n4,next]->[n5,next]->null
        //
        // step2++:
        //    dummy.next指向起點, fast.next接n1, slow.next接null
        //                                          slow                  fast
        //     [dummy,next]->[n1,next]->[n2,next]->[n3,next]->null  [n4,next]->[n5,next]->n1
        fast.next = dummy.next;
        dummy.next = slow.next;
        slow.next = null; // break linkedlist
        return dummy.next;
    }

    /**
     * My own implementation
     * Calculate length of list, then adjust n in range
     * Break the list at right point, which is len - n % len
     */
    public ListNode rotateRightB(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        int len = listLength(head);
        k %= len;
        if (k == 0) return head;
        if (k < 0) k += len;
        k = len - k;
        ListNode p = new ListNode(0);
        p.next = head;
        while (k > 0) {
            p = p.next;
            k--;
        }
        ListNode newHead = p.next;
        p.next = null;
        ListNode cur = newHead;
        while (cur.next != null) cur = cur.next;
        cur.next = head;
        return newHead;
    }

    int listLength(ListNode head) {
        ListNode cur = head;
        int res = 0;
        while (cur != null) {
            cur = cur.next;
            res++;
        }
        return res;
    }

}