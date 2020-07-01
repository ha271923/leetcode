package com.cspirat;

import com.utils.Out;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ReverseLinkedListII
 * Creator : Edward
 * Date : Oct, 2017
 * Description : 92. Reverse Linked List II
 */
public class ReverseLinkedListII {
    public static void main(String[] args) {
        // test1
        ListNode input1;
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);
        ListNode a5 = new ListNode(5);
        a1.next = a2; a2.next = a3; a3.next = a4;
        a4.next = a5; a5.next = null;
        input1 = a1;
        // test2
        ListNode input2;
        ListNode b1 = new ListNode(1);
        ListNode b2 = new ListNode(2);
        ListNode b3 = new ListNode(3);
        ListNode b4 = new ListNode(4);
        ListNode b5 = new ListNode(5);
        ListNode b6 = new ListNode(6);
        ListNode b7 = new ListNode(7);
        ListNode b8 = new ListNode(8);
        b1.next = b2; b2.next = b3; b3.next = b4;
        b4.next = b5; b5.next = b6; b6.next = b7;
        b7.next = b8; b8.next = null;
        input2 = b1;

        // ListNode.show(input1);
        // ListNode res = reverseBetween(input1, 2, 4);
        ListNode.show(input2);
        ListNode res = reverseBetween(input2, 2, 6);
        // ListNode res = reverseBetween_hawk(input1, 2, 4); // FAILED
        ListNode.show(res);
    }
    /**
     * For example:
     Given 1->2->3->4->5->NULL, m = 2 and n = 4,

     return 1->4->3->2->5->NULL.

     1->2->3->4->5
     p  c  t


     time : O(n);
     space : O(1);

     * @param head
     * @param m
     * @param n
     * @return
     */
    // Tips: 注意是區間元素Reverse逆向, 不是兩元素Swap互換
    static public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy; // pre永遠是cur的前一個, 所以pre.next=cur
        ListNode cur = dummy.next;
        for (int i = 1; i < m; i++) { // point to the begin node at M
            cur = cur.next;
            pre = pre.next;
        }
        /*

Tips: 為了更動pre.next與cur.next, 請將pre與cur固定

                        M          N
                  pre  cur(固定)
  INPUT1: dummy -> 1 -> 2 -> 3 - > 4 -> 5 -> null
                        3 -> 2 - > 4
                        4 -> 3 - > 2
                        M          N
 OUTPUT1: dummy -> 1 -> 4 -> 3 - > 2 -> 5 -> null



                        M          N
                    pre cur(固定)
                           取3
 INPUT2: dummy -> 0-> 1->2->3->4->5->6->7->8->null
                        插3    取4
                         3->2->4->5->6
                        插4       取5
                         4->3->2->5->6
                        插5          取6
                         5->4->3->2->6
                        插6
 OUTPUT2:dummy -> 0-> 1->6->5->4->3->2->7->8->null
       */
        // 頭不動: pre       cur
        // 尾動:   pre.next  cur.next
        for (int i = 0; i < n - m; i++) {
            ListNode pick = cur.next; // 備份3, 注意cur.next下一輪會往右移動一步
            cur.next = pick.next; // KEY: 將2接4, 形成3被抽掉, cur.next往右移動
            pick.next = pre.next; // KEY: 脫離主線串, 將目前的串列接到pick node串,利用pre node
            pre.next = pick; // 將pick node串 接回去主線, 將3接回去1後面, 完成2跟3的node swap
            Out.print_ListNode(dummy);
        }
        return dummy.next;
    }

    // FAILED: 我以為是swap, 而且也寫錯QQ
    static public ListNode reverseBetween_hawk(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode ptr = head;
        ListNode ptrMPrev = null, ptrNPrev = null;
        ListNode ptrM = null, ptrN = null;
        int i=1;
        while(ptr != null) {
            if(i == m-1) {
                ptrMPrev = ptr;
            }
            if(i == m) {
                ptrM = ptr;
            }
            if(i == n-1) {
                ptrNPrev = ptr;
            }
            if(i == n) {
                ptrN = ptr;
                break;
            }
            ptr = ptr.next;
            i++;
        }

        if(ptrMPrev != null && ptrM != null && ptrNPrev != null ) {
            ptrMPrev.next = ptrN;
            ptrN.next = ptrM.next;
            ptrNPrev.next = ptrM;
            ptrM.next = ptrN.next;

        }
        return dummy.next;
    }
}
