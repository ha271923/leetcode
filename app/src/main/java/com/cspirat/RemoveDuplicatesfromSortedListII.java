package com.cspirat;

/**
 * Created by Edward on 25/07/2017.
 */
public class RemoveDuplicatesfromSortedListII {
    /**
     * 82. Remove Duplicates from Sorted List II (83. Remove Duplicates from Sorted List: follow up)
     * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

     For example,
     Given 0->1->2->3->3->4->4->5, return 1->2->5.
     Given 0->1->1->1->2->3, return 2->3.
     time : O(n);
     space : O(1);
     * @param head
     * @return
     */
    public static void main(String[] args) {
        // test1
        ListNode input1;
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(3);
        ListNode a5 = new ListNode(4);
        ListNode a6 = new ListNode(4);
        ListNode a7 = new ListNode(5);
        a1.next = a2; a2.next = a3; a3.next = a4; a4.next = a5;
        a5.next = a6; a6.next = a7; a7.next = null;
        input1 = a1;
        ListNode res = deleteDuplicates(input1);
        ListNode.show(res);
    }
    // ListNode就是畫圖
    //  |dummy|->|0|->|1|->|2|->|3|->|3|->|4|->|4|->|5|  STEP1: question
    //  |dummy|->|0|->|1|->|2|--------------------->|5|  STEP2: algorithm
    //           |0|->|1|->|2|--------------------->|5|  STEP3: assign head
    static public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = dummy;
        while (p.next != null && p.next.next !=null) {
            if (p.next.val == p.next.next.val) {
                int sameNum = p.next.val;
                while (p.next != null && p.next.val == sameNum) { // KEY: while變if重複數會保留一個
                    p.next = p.next.next;
                }
            } else {
                p = p.next;
            }
        }
        return dummy.next;
    }
}
