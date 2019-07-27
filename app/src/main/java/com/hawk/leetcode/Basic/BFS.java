package com.hawk.leetcode.Basic;

import com.hawk.leetcode.Basic.data.Node;
import com.hawk.leetcode.Basic.data.Nodes;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode之Breadth-first Search题目汇总
 * Binary Tree Level Order Traversal
 * Binary Tree Level Order Traversal II
 * Binary Tree Zigzag Level Order Traversal
 * Course Schedule
 * Course Schedule II
 * Surrounded Regions
 * */
public class BFS {
    public static void main(String[] args) {
        System.out.println("The BFS traversal of the graph is ");
        String res = bfs_queue(Nodes.nodes.get(3));
        System.out.println("res = " + res);
    }

    // Tips1: 全節點探索一次
    // Tips2: 利用一個queue暫存所有所需探索的橫向節點
    // Tips3: BFS不用recursive搜尋, 而是 two loops
    static public String bfs_queue(Node startNode) {
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

}
