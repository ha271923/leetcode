package com.cspirat;

public class MergeTwoSortedLists {
    /**
     * 21. Merge Two Sorted Lists
     * Merge two sorted linked lists and return it as a new list. The new list should be made
     * by splicing together the nodes of the first two lists.

     time : O(n);
     space :O(1) / O(n);
     * @param l1
     * @param l2
     * @return
     */
    // 迴圈解
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while (l1 != null && l2 != null) { // KEY: 串接Node迴圈
            if (l1.val < l2.val) { // 數值小的將被創建Node
                cur.next = new ListNode(l1.val); // 接上新Node(數值小的)
                l1 = l1.next;
            } else {
                cur.next = new ListNode(l2.val);  // 接上新Node(數值小的)
                l2= l2.next;
            }
            cur = cur.next; // 下一次起點
        }
        if (l1 != null) { // 殘留尚未串接的剩餘Node, 一次串接到最尾端
            cur.next = l1;
        } else {
            cur.next = l2;
        }
        return head.next;
    }
    // 遞迴解
    // 讀取L1目前的值與L2目前的值比較，如果L1.val < L2.val，將當前的L1節點加
    // 入新的連結串列(result)，然後L1指向下一個節點。
    // 如果L1.val > L2.val較小，則把L2當前的節點加到result，直到L1或L2一方
    // 為null則停止比較，並且將另外一邊剩下的節點加入result。
    // Tips0: 垂直思考遞迴輸入(未合併兩串),處理(比大小),輸出(合併後一串)
    // Tips1: ListNode是Call By Value, 是以next起點copy一份, l1=l1.next將會"刪除"最前端節點
    // Tips2: 越上層的遞迴, 串接位址越前端, 數字越小
    // Tips3: 最終結果可能會return L1或L2整串之一
    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2; // 已遞迴至最底部 -- 往上返回，並且將另外一邊剩下的節點返回，以加入result。
        if (l2 == null) return l1;

        // 尚未遞迴至最底部 +++ 往下進入
        if (l1.val < l2.val) { // 因為L1與L2均為排過序的NodeList, 比對當前Node數值後, 留下小的, 往下再比
            l1.next = mergeTwoLists2(l1.next, l2); // 因為l1比l2還小, 所以此層遞迴保留l1, 並把下ˋ一個比對組合l1.next VS l2往下遞迴, 返回後, 將新的l?.next接上
            // 已遞迴至最底部 -- 往上返回
            return l1; // 最終結果可能會return L1或L2整串之一
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            // 已遞迴至最底部 -- 往上返回
            return l2; // 最終結果可能會return L1或L2整串之一
        }
    }

    public static void main(String[] args) {
        ListNode param1 = new ListNode(1);
        param1.next = new ListNode(2);
        param1.next.next = new ListNode(4);
        ListNode in1 = param1;
        ListNode param2 = new ListNode(1);
        param2.next = new ListNode(3);
        param2.next.next = new ListNode(4);
        ListNode in2 = param2;
        ListNode res = mergeTwoLists(in1, in2);
        // ListNode res = mergeTwoLists2(in1, in2);
        ListNode.show(res);
    }
}
