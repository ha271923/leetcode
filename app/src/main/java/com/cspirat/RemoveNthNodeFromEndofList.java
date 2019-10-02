package com.cspirat;

import com.hawk.leetcode.Basic.data.Node;

import static com.hawk.leetcode.Basic.data.Nodes.nodes;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : RemoveNthNodeFromEndofList
 * Creator : Edward
 * Date : Oct, 2017
 * Description : 19. Remove Nth Node From End of List
 */
public class RemoveNthNodeFromEndofList {
    /**
     *  Given linked list: 1->2->3->4->5, and n = 2.

     After removing the second node from the end, the linked list becomes 1->2->3->5.

     time : O(n)
     space : O(1)

     * @param head
     * @param n
     * @return
     */
    // Tips1: 利用兩個指標差, 讓正數第N個變倒數第N個
    static public ListNode removeNthFromEnd(ListNode head, int n) {
        // 3個指標: dummy指向頭,用來回傳結果, 最終L用來指向欲移除的Node的左端, R用來指向欲移除的Node的右端,
        // 但刪除Node不是透過L(slow)指到R(fast), 而是slow.next指到slow.next.next
        //                   remove2thFromEnd
        //  Start  6   5   4   3   2   1  End
        //        N0->N1->N2->N3->N4->N5->null
        // dummy->N0->N1->N2-> L->rm-> R->null
        ListNode dummy = new ListNode(0);
        ListNode slow = dummy;
        ListNode fast = dummy; // fast pointer should pointer to null at end.
        dummy.next = head;
        // 題目雖然要倒數第N個node, 但是可以先找出正數第N個node
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // 接者利用fast與slow兩個pointer, 來一路推next到null, 這樣就知道倒數第N個node,
        // 並讓slow指著此node, 例如:我要倒數第2個, 先利用fast指向前數第2個, 然後再讓fast跟slow同時起跑到終點null,
        // 這樣slow就會指著倒數第2個, 因為slow永遠落後fast兩步
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next; // KEY: remove a node, L.next == L.next變成L.next.next
        return dummy.next;
    }

    public static void main(String[] args) {
        // create each node
        ListNode res = new ListNode(1);
        res.next = new ListNode(2);
        res.next.next = new ListNode(3);
        res.next.next.next = new ListNode(4);
        res.next.next.next.next = new ListNode(5);
        res.next.next.next.next.next = null;
        res =removeNthFromEnd(res,2);
        ListNode dummy = res;
        while(dummy!=null) {
            System.out.println(dummy.val);
            if(dummy.next!=null)
                dummy = dummy.next;
            else
                break;
        }
    }
}
