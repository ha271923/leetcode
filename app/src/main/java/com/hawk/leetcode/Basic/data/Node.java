package com.hawk.leetcode.Basic.data;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int data;
    public boolean visited;
    List<Node> nodes;

    Node(int data) {
        this.data = data;
        this.nodes = new ArrayList<>();
    }

    public void connectNode(Node neighbourNode) {
        this.nodes.add(neighbourNode);
    }

    public List<Node> getNeighbours() {
        return nodes;
    }

}