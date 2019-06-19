package com.cspirat;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SwapNodesinPairs
 * Creator : Edward
 * Date : Sep, 2017
 * Description : TODO
 */
public class SwapNodesinPairs {
    /**
     * 24. Swap Nodes in Pairs
     * Given a linked list, swap every two adjacent nodes and return its head.

     For example,
     Given 1->2->3->4, you should return the list as 2->1->4->3.


     time : O(n);
     space : O(1);
     * @param node
     * @return
     */
    // Tips0: Linkedlist只能往後遍歷,不能往前,所以head的移動要小心,注意Node連鎖反應
    // 較好理解com.freetymekiyan.algorithms.level.medium\swapPairs.java
    public ListNode swapPairs(ListNode node) {
        if (node == null || node.next == null) return node;
        ListNode dummyhead = new ListNode(0);
        dummyhead.next = node; // dummyhead作為操作N1的指標ptr,為了將頭結點也一般化,我們創建一個dummy結點
        ListNode l1 = dummyhead; // l1 = dummyhead->node1->node2->node3...
        ListNode l2 = node;      // l2 =            node1->node2->node3...
        while (l2 != null && l2.next != null) { // 4. algorithm是把每一個node, 在每一loop從node一個接一個搬到dummyhead
            ListNode nextStart = l2.next.next; // l2.next.next=n3 每次置換前先記住下一輪起點,以便下一輪使用
            // SOUR: l1.next=l2, l2.next=l3, start=l1
            // SWAP: l1.next=l3, l2.next=l1, start=l2
            l1.next = l2.next;    // 1. n1 = n2  置換兩個節點之1 , front=behind
            l2.next.next = l2;    // 2. n3 = n1  置換兩個節點之2 , behind=front
            l2.next = nextStart;  // 3. n2 = nexStart = n3  設定下次起始節點
            l1 = l2;              // 4. l1 = n1 ( l1起點往右移一位, 以利下一輪使用 )
            l2 = l2.next;         // 5. l2 = n2 ( l2起點往右移一位, 以利下一輪使用 )
        }
        return dummyhead.next;
    }
}
