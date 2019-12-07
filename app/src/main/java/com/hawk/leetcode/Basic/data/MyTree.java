package com.hawk.leetcode.Basic.data;

// Hawk: A BinaryTree Example
public class MyTree {
    Node node0 = null;
    Node node1 = null;
    Node node2 = null;
    Node node3 = null;
    Node node11 = null;
    Node node12 = null;
    Node node13 = null;
    Node node14 = null;
    Node node21 = null;
    Node node22 = null;
    Node node31 = null;

    Node node121 = null;
    Node node122 = null;
    Node node141 = null;
    Node node221 = null;

    public static MyTree buildMyTree(){
        MyTree mytree = new MyTree();
        // L0
        mytree.node0 = new Node(0);
        mytree.node0.toNodes.add(mytree.node1 = new Node(1));
        mytree.node0.toNodes.add(mytree.node2 = new Node(2));
        mytree.node0.toNodes.add(mytree.node3 = new Node(3));

        // L1
        mytree.node1.toNodes.add(mytree.node11 = new Node(11));
        mytree.node1.toNodes.add(mytree.node12 = new Node(12));
        mytree.node1.toNodes.add(null);
        mytree.node1.toNodes.add(mytree.node14 = new Node(14));

        mytree.node2.toNodes.add(mytree.node21 = new Node(21));
        mytree.node2.toNodes.add(mytree.node22 = new Node(22));

        mytree.node3.toNodes.add(mytree.node31 = new Node(31));

        // L2
        mytree.node11.toNodes.add(null);
        mytree.node12.toNodes.add(mytree.node121 = new Node(121));
        mytree.node12.toNodes.add(mytree.node122 = new Node(122));
        mytree.node14.toNodes.add(mytree.node141 = new Node(141));
        mytree.node21.toNodes.add(null);
        mytree.node22.toNodes.add(mytree.node221 = new Node(221));
        mytree.node31.toNodes.add(null);

        return mytree;

    }

    public static void travelMyTree(Node node) {
        Node ptr = node;
        if(ptr == null)
            return;
        if(ptr.toNodes.size()==0) // only end nodes
            System.out.println(ptr.data);
        // System.out.println(node.data);  // all to nodes
        for(int i=0; i<ptr.toNodes.size();i++) {
            travelMyTree(ptr.toNodes.get(i));
        }

    }

    public static void main(String[] args) {
        MyTree exampleTree = buildMyTree();
        travelMyTree(exampleTree.node0);
        System.out.println("----------------");
    }
}
