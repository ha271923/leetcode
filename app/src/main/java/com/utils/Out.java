package com.utils;

import com.cspirat.ListNode;
import com.cspirat.TreeNode;

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
            System.out.print(nodes.val);
            System.out.print("->");
            nodes = nodes.next;
        }
        System.out.print("null");
        System.out.println("");
    }
    public static void print_ListTreeNode(List<TreeNode> nodesLists) {
        for(TreeNode node: nodesLists) {
            printTreeNodes(node);
            System.out.print("\n");
        }
    }
    public static void printTreeNodes(TreeNode node) {
        if (node != null) {
            System.out.print(node.val);
            printTreeNodes(node.left);
            printTreeNodes(node.right);
        }
    }
}
