package com.hawk.leetcode.Exams;

import android.util.Log;

import com.hawk.leetcode.BaseClass;

import static com.hawk.leetcode.Global.TAG;

public class AddTwoNumbers extends BaseClass {
    public static ListNode input1;
    public static ListNode input2;
    ListNode output;


    public AddTwoNumbers(){
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        a1.next = a2; a2.next = a3; a3.next = null;
        input1 = a1;

        ListNode b1 = new ListNode(4);
        ListNode b2 = new ListNode(5);
        ListNode b3 = new ListNode(6);
        b1.next = b2; b2.next = b3; b3.next = null;
        input2 = b1;
    }


    @Override
    public Object test() {
        output = addTwoNumbers(input1, input2);
        return output;
    }

    @Override
    public Object result() {
        int total = 0;
        ListNode node = output;
         for (int i = 0; node != null; i++) {
             // Log.i(TAG,i+ " node.val = "+ node.val);
             System.out.println(i+ " node.val = "+ node.val);
             node = node.next;
        }
        return output;
    }

    public class ListNode{
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

