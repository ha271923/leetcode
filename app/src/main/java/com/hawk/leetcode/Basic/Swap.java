package com.hawk.leetcode.Basic;

import com.cspirat.ListNode;

public class Swap {
    public static void main(String[] args) {
        int[] a = new int[]{1};
        int[] b = new int[]{2};
        swap(a, b);
        System.out.println(a[0]);
    }

    static public void swap(int[] a, int[] b) {
        int[] temp= new int[]{0};
        temp[0] = a[0];
        a[0] = b[0];
        b[0] = temp[0];
    }


    static public void swap(ListNode a, ListNode b) {
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
    }
}
