package com.utils;

import com.cspirat.ListNode;

import java.util.List;

public class Out {
    static public void i(String s) {
        System.out.println(s);
    }


    static public void print_IntArray(int[] input) {
        for (int x : input) {
            System.out.print(x+",");
        }
        System.out.println();
    }

    static public void print_List(List<Integer> input){
        for(Integer x: input) {
            System.out.print(x+",");
        }
        System.out.println();
    }

    static public void println_List(List<Integer> input){
        for(Integer x: input) {
            Out.i(""+x);
        }
    }

    static public void println_ListList(List<List<Integer>> input){
        for(List<Integer> x: input) {
            Out.i(""+x);
        }
    }

    public static void print_ListNode(ListNode nodes) {
        while(nodes!=null) {
            System.out.println(nodes.val);
            nodes = nodes.next;
        }

    }
}
