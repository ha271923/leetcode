package com.hawk.leetcode.Exams;

import com.hawk.leetcode.BaseClass;

public class AddTwoNumbers extends BaseClass {
    ListNode input;
    ListNode target;

    @Override
    public Object test() {
        ListNode output = addTwoNumbers(input, target);
        return output;
    }


    class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val=val;
            this.next=null;
        }
    }

    public ListNode addTwoNumbers(ListNode p, ListNode q) {
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(1);
        }
        return dummyHead.next;
    }




}

