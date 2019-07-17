package com.hawk.leetcode.Basic;

import com.hawk.leetcode.BaseClass;

import java.util.List;
import java.util.Stack;

public class DFS extends BaseClass {

    // Tips1: 全節點探索一次
    // Tips2: 因為Node在connect時, 便是以List資料結構串接, 所以只要一層一層遞迴即可
    public String dfs_recursive(Node node) {
        String out = "";
        out = node.data + " > ";
        node.visited = true;
        List<Node> neighbours = node.getNeighbours();
        for (int i = 0; i < neighbours.size(); i++) { // 將該端點相關的端點們, 一個一個列舉出來
            Node n = neighbours.get(i);
            if (n != null && !n.visited) {
                dfs_recursive(n); // KEY: visited flag, 且順著Node路徑一層一層往下挖
            }
        }
        return out;
    }

    // Iterative DFS using stack
    public String dfs_stack(Node startNode) {
        String out = "";
        Stack<Node> stack = new Stack<Node>();
        stack.push(startNode);
        startNode.visited = true;
        while (!stack.isEmpty()) {
            Node element = stack.pop();
            out = element.data + " > ";
            System.out.print(element.data + " > ");
            List<Node> neighbours = element.getNeighbours();
            for (int i = 0; i < neighbours.size(); i++) {
                Node n = neighbours.get(i);
                if (n != null && !n.visited) {
                    stack.push(n);
                    n.visited = true;
                }
            }
        }
        return out;
    }

    public Object test() {

        Node node10 = new Node(10);
        Node node20 = new Node(20);
        Node node30 = new Node(30);
        Node node40 = new Node(40);
        Node node50 = new Node(50);
        Node node60 = new Node(60);
        Node node70 = new Node(70);

/**
 *    adjacency Map:
 *    40 ---> 20 ---> 50 ---> 70
 *     |      /|\             ^
 *     |     / | \            |
 *     V    /  |  \           /
 *    10 <--   |  --> 60 --->
 *     |       |      ^
 |       V      |
 *     | ---> 30 ---> |
 *
 *     Ans: 40 > 10 > 20 > 30 > 60 > 50 > 70 >
 * */
        node40.connectNode(node10); // DFS在add node後, 是以 List<Node> 儲存與該node相鄰近的所有nodes
        node40.connectNode(node20);
        node10.connectNode(node30);
        node20.connectNode(node10);
        node20.connectNode(node30);
        node20.connectNode(node60);
        node20.connectNode(node50);
        node30.connectNode(node60);
        node60.connectNode(node70);
        node50.connectNode(node70);

        DFS dfsExample = new DFS();
        System.out.println("The DFS traversal of the graph using stack ");
        dfsExample.dfs_stack(node40);

        System.out.println();

        // Resetting the visited flag for nodes
        node40.visited = false;
        node10.visited = false;
        node20.visited = false;
        node30.visited = false;
        node60.visited = false;
        node50.visited = false;
        node70.visited = false;

        System.out.println("The DFS traversal of the graph using recursion ");
        dfsExample.dfs_recursive(node40); // start node
        return null;
    }
}
