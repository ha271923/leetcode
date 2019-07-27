package com.hawk.leetcode.Basic.data;

import java.util.ArrayList;

public class Nodes {
    static public ArrayList<Node> nodes = createTestNodes();

    static public ArrayList<Node> createTestNodes() {
        ArrayList<Node> nodes = new ArrayList<Node>();

        // create each node
        Node node10 = new Node(10);
        Node node20 = new Node(20);
        Node node30 = new Node(30);
        Node node40 = new Node(40);
        Node node50 = new Node(50);
        Node node60 = new Node(60);
        Node node70 = new Node(70);
        // add all nodes to ArrayList for finding neighbours by looping
        nodes.add(node10);
        nodes.add(node20);
        nodes.add(node30);
        nodes.add(node40);
        nodes.add(node50);
        nodes.add(node60);
        nodes.add(node70);

/**
 *    adjacency Map: BFS與DFS是無方向性, 以任一Node起始, 朝四面八方走訪, 而非樹狀往下
 *    40 ---> 20 ---> 50 ---> 70
 *     |      /|\             ^
 *     |     / | \            |
 *     V    /  |  \           /
 *    10 <--   |  --> 60 --->
 *     |       |      ^
 *     |       V      |
 *     | ---> 30 ---> |
 *
 *     Ans: 40 > 10 > 20 > 30 > 60 > 50 > 70 >
 * */
        node10.connectNode(node30);
        node20.connectNode(node10);
        node20.connectNode(node30);
        node20.connectNode(node60);
        node20.connectNode(node50);
        node30.connectNode(node60);
        node40.connectNode(node10);
        node40.connectNode(node20);
        node50.connectNode(node70);
        node60.connectNode(node70);
        return nodes;
    }

    public static void clearVistedFlag()
    {
        for(int i=0; i<nodes.size(); i++) {
            nodes.get(i).visited = false;
        }
    }

}
