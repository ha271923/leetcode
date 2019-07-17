package com.hawk.leetcode.Basic;

import com.hawk.leetcode.BaseClass;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS extends BaseClass {
    // Tips1: 全節點探索一次
    // Tips2: 利用一個queue暫存所有所需探索的橫向節點
    public String bfs_queue(Node startNode) {
        String out = "";
        Queue<Node> queue = new LinkedList<>(); // Key: BFS用QUEUE, 紀錄探尋到的, 需走訪node清單
        queue.add(startNode); // add start node to result queue
        startNode.visited = true; // 因為已經加入result, 所以一開始start node是visited
        while (!queue.isEmpty()) {
            Node node = queue.remove(); //  Retrieves and removes the head of this queue.
            out = out + node.data + " > ";
            List<Node> neighbours = node.getNeighbours(); // 抓出該端點所有鄰近端點清單
            for (int i = 0; i < neighbours.size(); i++) { // 將該端點相關的端點們, 一個一個列舉出來
                Node n = neighbours.get(i);
                if (n != null && !n.visited) {
                    queue.add(n); // KEY: 將直向Node轉為橫向Queue ,作為一個暫存每一次廣度探索的結果, 給while()下次探索使用
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
 *    40 ---> 20 ---> 50 ---> 70
 *     |      /|\             ^
 *     |     / | \            |
 *     V    /  |  \           /
 *    10 <--   |  --> 60 --->
 *     |       |      ^
 *     |       V      |
 *     | ---> 30 ---> |
 *
 *
 *     Ans: 40 > 10 > 20 > 30 > 60 > 50 > 70 >
 * */

        node40.connectNode(node10); // BFS在add node後, 是以 List<Node> 儲存與該node相鄰近的所有nodes
        node40.connectNode(node20);
        node10.connectNode(node30);
        node20.connectNode(node10);
        node20.connectNode(node30);
        node20.connectNode(node60);
        node20.connectNode(node50);
        node30.connectNode(node60);
        node60.connectNode(node70);
        node50.connectNode(node70);
        System.out.println("The BFS traversal of the graph is ");
        BFS bfsExample = new BFS();
        String res = bfsExample.bfs_queue(node40); // start node
        System.out.println("res = " + res);
        return null;
    }
}
