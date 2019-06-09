package com.freetymekiyan.algorithms.level.easy;

import com.freetymekiyan.algorithms.utils.Utils.ListNode;

/**
 * Given a linked list, remove the nth node from the end of list and return its
 * head.
 * <p>
 * For example,
 * <p>
 * Given linked list: 1->2->3->4->5, and n = 2.
 * <p>
 * After removing the second node from the end, the linked list becomes
 * 1->2->3->5.
 * <p>
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
 * <p>
 * Tags: Linked list, Two pointers
 */
class RemoveNthNodeFromEnd {

    /**
     * Dummy head and Runner's technique
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        int i = 0;
        while (i < n) { // EX:倒數第2個, 當fast指到前數2個時,才讓slow與fast開始跑, 當fast抵達終點時, slow剛好慢2步
            fast = fast.next;
            i++;
        }
        while (fast.next != null) { // slow與fast開始跑
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next; //刪除Nth Node
        return dummy.next;
    }
}