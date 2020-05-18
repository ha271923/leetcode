package com.cspirat;

/**
 * Created by Edward on 25/07/2017.
 */
public class RemoveDuplicatesfromSortedList {
    /**
     * 83. Remove Duplicates from Sorted List
     * Given a sorted linked list, delete all duplicates such that each element appear only once.

     For example,
     Given 1->1->2, return 1->2.
     Given 1->1->2->3->3, return 1->2->3.

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
    //   |0|->|1|->|2|->|3|->|3|->|4|->|4|->|5|  STEP1: question
    //   |0|->|1|->|2|->|3|------>|4|------>|5|  STEP2: algorithm
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;
        // KEY: 不需要 ListNode dummy , 因為題目所需的答案不需要進行回溯比對, 所以可以從head開始, 因此p.val為現在值
        ListNode p = head;
        while (p.next != null) {
            if (p.next.val == p.val) { // 相同
                p.next = p.next.next;  // KEY: 跳下一個
            } else {
                p = p.next;
            }
        }
        return head;
    }
}
