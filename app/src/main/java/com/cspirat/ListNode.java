package com.cspirat;

import com.utils.Out;

/**
 * Created by Edward on 25/07/2017.
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
        next = null;
    }

    public static void show(ListNode nodes) {
        Out.print_ListNode(nodes);
        /*
        while(nodes!=null) {
            System.out.println(nodes.val);
            nodes = nodes.next;
        }
        */
    }
}
