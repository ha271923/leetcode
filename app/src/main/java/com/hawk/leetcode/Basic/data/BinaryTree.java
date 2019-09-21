package com.hawk.leetcode.Basic.data;

// Hawk: A BinaryTree Example
public class BinaryTree {
    LRNode root = null;

    public BinaryTree() {
        root = null;
    }

    public static BinaryTree buildBinaryTree(){
        BinaryTree tree = new BinaryTree();
        // L0
        tree.root = new LRNode(0);
        // L1
        tree.root.L = new LRNode(1);
        tree.root.R = new LRNode(2);
        // L2
        tree.root.L.L = new LRNode(11);
        tree.root.L.R = new LRNode(12);

        tree.root.R.L = new LRNode(21);
        tree.root.R.R = new LRNode(22);
        // L3
        tree.root.L.L.L = new LRNode(111);
        tree.root.L.L.R = new LRNode(112);

        tree.root.L.R.L = new LRNode(121);
        tree.root.L.R.R = new LRNode(122);

        tree.root.R.L.L = new LRNode(211);
        tree.root.R.L.R = new LRNode(212);

        tree.root.R.R.L = new LRNode(221);
        tree.root.R.R.R = new LRNode(222);

        return tree;

    }

    public static void travelBinaryTree(LRNode lrnode) {
        LRNode ptr = lrnode;
        if(ptr == null)
            return;
        // if(ptr.R==null && ptr.L==null) // only end nodes
        //    System.out.println(ptr.val);
        System.out.println(ptr.val);  // all nodes
        travelBinaryTree(ptr.L);
        travelBinaryTree(ptr.R);
    }

    public static void main(String[] args) {
        BinaryTree exampleTree = buildBinaryTree();
        travelBinaryTree(exampleTree.root);
        System.out.println("----------------");
    }
}
