package com.hawk.leetcode.Basic.data;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int data;
    public boolean visited;
    List<Node> toNodes;

    Node(int data) {
        this.data = data;
        this.toNodes = new ArrayList<>();
    }

    public void connectToNode(Node toNode) {
        this.toNodes.add(toNode);
    }

    public List<Node> getMyToNodes() {
        return toNodes;
    }

}