package com.cspirat;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : AddTwoNumbers
 * Creator : Edward
 * Date : Sep, 2017
 * Description : 2. Add Two Numbers
 */
public class AddTwoNumbers {
    /**

     Input: (2 -> 4 -> 3) + (5 -> 6 -> 4) = 342+465 = 708
     Output: 7 -> 0 -> 8

     time : O(n)
     space : O(n)

     * @param l1
     * @param l2
     * @return
     */
    /**
     *  Tips1: !! 注意題目Input/Output順序 !!
     *  Input: (個 -> 十 -> 百)  +  (個 -> 十 -> 百)
     *  Output: (百 -> 十 -> 個)
     * */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        int sum = 0;
        ListNode cur = head; // head永遠指向Node頭, cur PTR指向Node尾
        ListNode p1 = l1, p2 = l2; //  p1, p2 PTR可隨意浮動所指Node
        while (p1 != null || p2 != null) {
            if (p1 != null) {
                sum += p1.val;
                p1 = p1.next;
            }
            if (p2 != null) {
                sum += p2.val;
                p2 = p2.next;
            }
            cur.next = new ListNode(sum % 10); // 創造一個Node將兩數字AB的x位數運算結果存進去
            sum /= 10; // 計算是否進位? 如果除完為1 代表有進位
            cur = cur.next; //
        }
        if (sum == 1) { // 如果有進位1,新增一個Node(1), 就算是999+999=1998, 最前面的1
            cur.next = new ListNode(1);
        }
        return head.next; // 因為Node頭是0, 所以從head.next開始
    }

    public static void main(String[] args) {
        System.out.println("sss");
    }
}
