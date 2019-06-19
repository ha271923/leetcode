package com.cspirat;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ReverseNodesinkGroup
 * Creator : Edward
 * Date : Nov, 2017
 * Description : 25. Reverse Nodes in k-Group
 */
public class ReverseNodesinkGroup {
    /**
     * For example,
     Given this linked list: 1->2->3->4->5

     For k = 2, you should return: 2->1->4->3->5

     For k = 3, you should return: 3->2->1->4->5

     2->1->4->3->5 k = 2

     time : O(n)
     space : O(n)

     * @param head
     * @param k
     * @return
     */
    /*  If K=3, Nodes=11, Recursive=11/3=3level
    *   1. Recursive to the last level
    *     R1+:  n1->n2->n3
    *     R2+:            ->n4->n5->n6
    *     R3+:                        ->n7->n8->n9
    *     R4+:                                    ->n10->n11->null
    *   2. Reverse from last level to first level
    *     R4-:                                    ->n10->n11->null
    *     R3-:                        ->n9->n8->n7
    *     R2-:            ->n6->n5->n4
    *     R1-:  n3->n2->n1
    * */
    // Tips0: Linkedlist只能往後遍歷,不能往前,所以head的移動要小心,注意Node連鎖反應
    // Tips1: 遞迴函數設計的上半部與下半部與cur與head變數處理
    // Tips2: 函數每次自有的stack變數
    // Tips3: Reverse ListNode algorithm的設計
    public ListNode reverseKGroup(ListNode head, int k) {
// R +++  head(當前的頭)與cur(下次的頭)的設定
        if (head == null || head.next == null) return head; // 最少要有兩個Node才能reverse
        int count = 0;
        ListNode cur = head;
        // 1. 先找出每一次Recursive的cur head
        while (cur != null && count != k) {
            cur = cur.next;
            count++;
        }
        if (count == k) { // 2a. 如果此次Recursive的node數量足夠進行K元數reverse, 則遞迴
            cur = reverseKGroup(cur, k); // 3. 遞迴會從最後面一組K元數reverse
// R ---
            while (count-- > 0) { // 4. algorithm是把每一個node, 在每一loop從head一個接一個搬到cur
                ListNode temp = head.next; // 先備份下一輪的node起頭
                head.next = cur;           // 斷開head.next正連接的node, 以免連動影響
                cur = head;                // 把新的node接到result(cur)
                head = temp;               // 斬斷一個node
            }
            head = cur;
        }
        return head; // 2b. 此次Recursive的node數量不足夠進行K元數reverse
    }

    // https://leetcode.wang/leetCode-25-Reverse-Nodes-in-k-Group.html
    public ListNode reverseKGroup_iterator(ListNode head, int k) {
        if (head == null)
            return null;
        ListNode sub_head = head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode tail = dummy;
        ListNode toNull = head;
        while (sub_head != null) {
            int i = k;
            //找到子链表的尾部
            while (i - 1 > 0) {
                toNull = toNull.next;
                if (toNull == null) {
                    return dummy.next;
                }
                i--;
            }
            ListNode temp = toNull.next;  //将子链表断开
            toNull.next = null;
            ListNode new_sub_head = reverse(sub_head);
            tail.next = new_sub_head; //将倒置后的链表接到 tail 后边
            //更新 tail
            tail = sub_head; //sub_head 由于倒置其实是新链表的尾部
            sub_head = temp;
            toNull = sub_head;
            tail.next = sub_head; //将后边断开的链表接回来
        }
        return dummy.next;
    }

    // reverse idea:
    // INPUT=        head= n1 -> n2 -> n3 -> n4 -> n5
    //       current_head= null
    //
    // result=       head= null
    //       current_head= n5 -> n4 -> n3 -> n2 -> n1
    //
    public ListNode reverse(ListNode head) {
        ListNode current_head = null;  // current_head作為result指標
        while (head != null) {         // 是把每一個node, 在每一loop從head一個接一個搬到current_head
            ListNode next = head.next; // 先備份下一輪的node起頭
            head.next = current_head;  // 斷開head.next正連接的node, 以免連動影響
            current_head = head;       // 把新的node接到result
            head = next;               // 斬斷一個node
        }
        return current_head;           // 回傳result
    }

    public ListNode reverseKGroup_recursive(ListNode head, int k) {
        if (head == null)
            return null;
        ListNode point = head;
        //找到子链表的尾部
        int i = k;
        while(i - 1 >0){
            point = point.next;
            if (point == null) {
                return head;
            }
            i--;
        }
        ListNode temp = point.next;
        //将子链表断开
        point.next = null;

        //倒置子链表，并接受新的头结点
        ListNode new_head = reverse(head);

        //head 其实是倒置链表的尾部，然后我们将后边的倒置结果接过来就可以了
        //temp 是链表断开后的头指针，可以参考解法一的图示
        head.next = reverseKGroup_recursive(temp,k);
        return new_head;
    }


}
