package com.freetymekiyan.algorithms.level.medium;

import com.cspirat.ListNode;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * <p>
 * For example,
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * <p>
 * Your algorithm should use only constant space. You may not modify the values
 * in the list, only nodes itself can be changed.
 * <p>
 * Tags: Linkedlist
 */
public class SwapNode {

    /**
     * create a node at before the head
     * swap two next nodes on the node before them
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummyhead = new ListNode(0);
        dummyhead.next = head;
        ListNode cur = dummyhead;

        while (cur != null && cur.next != null && cur.next.next != null) { // dummyhead作為操作N1的指標ptr
            cur.next = swap(cur.next, cur.next.next);
            cur = cur.next.next; // 前移兩個Node 作為下次迴圈的start node
        }

        return dummyhead.next;
    }

    private ListNode swap(ListNode front, ListNode behind) {
        front.next = behind.next;
        behind.next = front;
        return behind; // return latter node
    }

}
