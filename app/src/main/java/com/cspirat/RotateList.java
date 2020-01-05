package com.cspirat;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : RotateList
 * Creator : Edward
 * Date : Oct, 2017
 * Description : 61. Rotate List
 */
public class RotateList {
    /**
     * For example:
     Given 1->2->3->4->5->NULL and k = 2,
     return 4->5->1->2->3->NULL.

     time : O(n);
     space : O(1);

     * @param head
     * @param k
     * @return
     */
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2; n2.next = n3; n3.next = n4; n4.next = n5; n5.next = null;
        ListNode res = rotateRight(n1, 2);
        ListNode.show(res);
    }

    // Tips: 三個節點操作, 頭指+中接+尾收null
    static public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null)
            return head;
        ListNode index = head;
        int len = 1;
        while (index.next != null) { // 先求出Node總長度, len
            index = index.next;
            len++;
        }

        // Q: 原本一開始
        // ptr[head,next]
        //       |
        //       V
        //    [n1,next]->[n2,next]->[n3,next]->[n4,next]->[n5,next]->null
        // A: 鏈結向右, 指標head向右移動(本提要求rotate right)
        //            ptr[head,next]
        //                 |
        //                 V
        //    [n1,next]->[n2,next]->[n3,next]->[n4,next]->[n5,next]->null
        //    由上可知,只要移動head(n1)的next從指向n1改成指向n4即可(右移三次)
        int step =len - k % len; // KEY:
        index.next = head;
        for (int i = 1; i < step; i++) { // KEY:  (len-(k%len)) , 逆推出公式ex: (5-(2%5))=3, 右移三次
            head = head.next;  // head指標右移一個node
        }
        ListNode res = head.next; // 取頭
        head.next = null; // 斷尾
        return res;
    }

    // Hawk thinks:
    // 1. 算出哪個node是最後一個node(指向null)
    // 2. 把當前的最後一個node的next接到第一個node
    // 3. 把算出來的最後一個node的next指向null

}
