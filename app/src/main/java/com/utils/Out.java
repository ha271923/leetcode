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
            System.out.print(node.val+",");
            printTreeNodes(node.left);
            printTreeNodes(node.right);
        }
    }
    static public void print2DArray(int[][] dp){
        for (int y = 0; y < dp.length; y++) {
            for (int x = 0; x < dp[y].length; x++) {
                System.out.print(dp[y][x] + " ");
            }
            System.out.println();
        }
        // System.out.println(Arrays.deepToString(dp));
    }
    static public void print2DArray(boolean[][] dp){
        for (int y = 0; y < dp.length; y++) {
            for (int x = 0; x < dp[y].length; x++) {
                System.out.print(dp[y][x] + " ");
            }
            System.out.println();
        }
        // System.out.println(Arrays.deepToString(dp));
    }
    static public void printArray(int[] dp){
        for (int x = 0; x < dp.length; x++) {
            System.out.print(dp[x] + " ");
        }
        System.out.println();
        // System.out.println(Arrays.toString(dp));
    }

    // Given a binary tree, print its nodes in preorder
    public static void printBTreeByPreorder(TreeNode node)
    {
        if (node == null)
            return;
        System.out.print(node.val + " "); // Pre-order 先印
        printBTreeByPreorder(node.left);
        printBTreeByPreorder(node.right);
    }

    // Given a binary tree, print its nodes in In-order
    public static void printBTreeByInorder(TreeNode node)
    {
        if (node == null)
            return;
        printBTreeByInorder(node.left);
        System.out.print(node.val + " "); // In-order 中印
        printBTreeByInorder(node.right);
    }

    // Given a binary tree, print its nodes in Post-order
    public static void printBTreeByPostorder(TreeNode node)
    {
        if (node == null)
            return;
        printBTreeByPostorder(node.left);
        printBTreeByPostorder(node.right);
        System.out.print(node.val + " "); // Post-order 後印
    }

}
